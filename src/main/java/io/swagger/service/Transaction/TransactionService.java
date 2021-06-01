package io.swagger.service.Transaction;

import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.model.DTO.TransactionDTO.*;
import io.swagger.model.DTO.TransactionDTO.Wrappers.TransactionWrapper;
import io.swagger.model.Holder;
import io.swagger.model.Transaction;
import io.swagger.repository.AccountsRepo;
import io.swagger.repository.HolderRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.swagger.helpers.MapListsHelper.modelMapper;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private HolderRepository holderRepository;

    @Autowired
    private AccountsRepo accountsRepo;
    private LocalDate convertStringToDate(String date) {
        return LocalDate.parse(date);
    }

    public List<Transaction> getAllTransactions(String iban, String startDate, String endDate) {
        List<Transaction> transactions;
        if (iban != null && (startDate == null && endDate == null)) {
            transactions = transactionRepository.findAllByIban(iban);
        } else if (startDate!= null || endDate != null) {
            OffsetDateTime convertedStartDate;
            OffsetDateTime convertedEndDate;
            if (startDate == null) {
                convertedStartDate = OffsetDateTime.parse("2021-05-31T00:00:00+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            } else {
                convertedStartDate = OffsetDateTime.of(convertStringToDate(startDate), LocalTime.MIN, ZoneOffset.UTC);
            }
            if (endDate == null) {
                convertedEndDate = OffsetDateTime.now();
            } else {
                convertedEndDate = OffsetDateTime.of(convertStringToDate(endDate), LocalTime.MAX, ZoneOffset.UTC);
            }
            if (iban == null) {
                transactions = transactionRepository.findAllByDatetimeLessThanEqualAndDatetimeGreaterThanEqual(convertedEndDate, convertedStartDate);
            } else {
                List<Transaction> allTransactionsByDate = transactionRepository.findAllByDatetimeLessThanEqualAndDatetimeGreaterThanEqual(convertedEndDate, convertedStartDate);
                transactions = allTransactionsByDate.stream().filter(p -> p.getFromAccount().equals(iban) || p.getToAccount().equals(iban)).collect(Collectors.toList());
            }

        }else {
            transactions = transactionRepository.findAll();
        }
        return transactions;
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public TanDTO getTANByTransactionId(Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        TanDTO tanDTO = new TanDTO();
        transaction.ifPresent(value -> tanDTO.setTAN(value.getTAN()));

        return tanDTO;
    }

    public TransactionWrapper createTransaction(RequestBodyTransaction body, Holder holder, Boolean isEmployee, Account fromAccount) {
        Transaction transaction = modelMapper.map(body, Transaction.class);

        return createAppropriateTransaction(transaction, holder.getId(), isEmployee, fromAccount);
    }

    public Transaction createDepositTransaction(RequestBodyDeposit body, Integer performedHolderId, Boolean isEmployee) {
        Transaction transaction = modelMapper.map(body, Transaction.class);

        return createAppropriateTransaction(transaction, performedHolderId, isEmployee, new Account()).getTransaction();
    }

    public TransactionWrapper createWithdrawalTransaction(RequestBodyWithdrawal body, Holder holder, Boolean isEmployee, Account fromAccount) {
        Transaction transaction = modelMapper.map(body, Transaction.class);
        return createAppropriateTransaction(transaction, holder.getId(), isEmployee, fromAccount);
    }

    private TransactionWrapper createAppropriateTransaction(Transaction transaction, Integer performedHolderId, Boolean isEmployee, Account fromAccount){
        Transaction appropriateTransaction = new Transaction();
        appropriateTransaction.setDatetime(OffsetDateTime.now());
        appropriateTransaction.setTransactionType(transaction.getTransactionType());
        appropriateTransaction.setPerformedHolder(performedHolderId);
        if(transaction.getFromAccount() != null) {
            appropriateTransaction.setFromAccount(transaction.getFromAccount());
        }
        if(transaction.getToAccount() != null) {
            appropriateTransaction.setToAccount(transaction.getToAccount());
        }
        if(isEmployee) {
            appropriateTransaction.setStatus(Transaction.StatusEnum.APPROVED);
        } else {
            appropriateTransaction.setStatus(Transaction.StatusEnum.PENDING);
        }
        BaseTransaction.TransactionTypeEnum type = transaction.getTransactionType(); // Readability

        if(type == BaseTransaction.TransactionTypeEnum.TRANSFER && !isEmployee) {
            appropriateTransaction.setTAN(createTAN());
        }
        appropriateTransaction.setAmount(transaction.getAmount());
        TransactionWrapper wrapper = new TransactionWrapper();
        if(type == BaseTransaction.TransactionTypeEnum.TRANSFER || type == BaseTransaction.TransactionTypeEnum.WITHDRAWAL || type == BaseTransaction.TransactionTypeEnum.REFUND) {
            wrapper = passesAllChecks(appropriateTransaction, fromAccount);
        } else {
            wrapper.setSucces(true);
            wrapper.setTransaction(appropriateTransaction);
        }
        if(wrapper.getSucces() && (!tanRequired(wrapper.getTransaction().getTransactionType()) || isEmployee)) {
            Boolean balancesUpdated = updateBalancesByTransaction(wrapper.getTransaction());
            if(!balancesUpdated) {
                wrapper.setSucces(false);
                wrapper.setMessage("Balances could not be updated");
            } else {
                transactionRepository.save(wrapper.getTransaction());
            }
        } else if(wrapper.getSucces()) {
            transactionRepository.save(wrapper.getTransaction());
        }

        return wrapper;
    }

    private TransactionWrapper passesAllChecks(Transaction transaction, Account fromAccount) {
        boolean passesChecks = true;
        TransactionWrapper wrapper = new TransactionWrapper();
        if (DailyLimitExceeded(fromAccount, transaction.getAmount())) {
            passesChecks = false;
            wrapper.setMessage("Daily limit for transactions exceeded");
        } else if (MaxTransferExceeded(transaction.getAmount(), fromAccount.getMaxTransfer())) {
            passesChecks = false;
            wrapper.setMessage("Transfer amount exceeds max transfer for this account");
        } else if (MinBalanceExceeded(transaction.getAmount(), fromAccount)) {
            passesChecks = false;
            wrapper.setMessage("Transfer would put account balance below minimum required balance for this account");
        }
        wrapper.setTransaction(transaction);
        wrapper.setSucces(passesChecks);
        return wrapper;
    }

    private Boolean DailyLimitExceeded(Account fromAccount, BigDecimal toBeAdded) {
        OffsetDateTime convertedStart = OffsetDateTime.of(LocalDate.now(), LocalTime.MIN, ZoneOffset.UTC);
        OffsetDateTime convertedEnd = OffsetDateTime.of(LocalDate.now(), LocalTime.MAX, ZoneOffset.UTC);
        List<Transaction> transactionsToday = transactionRepository.findAllByDatetimeLessThanEqualAndDatetimeGreaterThanEqual(convertedEnd, convertedStart).stream().filter(p -> p.getFromAccount().equals(fromAccount.getIban())).collect(Collectors.toList());
        List<BigDecimal> transactionAmountList = new ArrayList<>();
        transactionsToday.forEach((tempTrans) -> transactionAmountList.add(tempTrans.getAmount()));
        transactionAmountList.add(toBeAdded);
        BigDecimal totalTransacted = transactionAmountList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        Optional<Holder> holder = holderRepository.findById(fromAccount.getHolderId());
        return holder.get().getDailyLimit().compareTo(totalTransacted) == -1;
    }

    private Boolean MaxTransferExceeded(BigDecimal transferAmount, BigDecimal maxTransfer) {
        return maxTransfer.compareTo(transferAmount) == -1;
    }

    private Boolean MinBalanceExceeded(BigDecimal transferAmount, Account account) {
        BigDecimal balanceAfterTransfer = account.getBalance().subtract(transferAmount);
        return balanceAfterTransfer.compareTo(account.getMinBalance()) == -1;
    }

    private int createTAN() {
        return (int) ((Math.random() * (9999 - 1000)) + 1000);
    }

    private Boolean addToAccountBalanceByIban(String iban, BigDecimal balanceUpdate){
        Optional<Account> optionalAccount = accountsService.getAccountByIban(iban);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            BigDecimal oldBalance = account.getBalance();
            BigDecimal newBalance = account.getBalance().add(balanceUpdate);
            account.setBalance(newBalance);
            accountsRepo.save(account);
            Optional<Account> toBeCheckAccount = accountsService.getAccountByIban(account.getIban());
            if(toBeCheckAccount.isPresent() && !toBeCheckAccount.get().getBalance().equals(account.getBalance())) {
                account.setBalance(oldBalance);
                accountsRepo.save(account);
                return false;
            }
            return true;
        }
        return false;
    }

    private Boolean subtractFromAccountBalanceByIban(String iban, BigDecimal balanceUpdate) {
        Optional<Account> optionalAccount = accountsService.getAccountByIban(iban);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            BigDecimal oldBalance = account.getBalance();
            BigDecimal newBalance = oldBalance.subtract(balanceUpdate);
            account.setBalance(newBalance);
            accountsRepo.save(account);
            Optional<Account> toBeCheckAccount = accountsService.getAccountByIban(account.getIban());
            if(toBeCheckAccount.isPresent() && !toBeCheckAccount.get().getBalance().equals(account.getBalance())) {
                account.setBalance(oldBalance);
                accountsRepo.save(account);
                return false;
            }
            return true;
        }
        return false;
    }

    private Boolean updateBalancesByTransaction(Transaction transaction) {
        Boolean updateBalancesSuccessful = true;
        if(transaction.getToAccount() != null) {
            updateBalancesSuccessful = addToAccountBalanceByIban(transaction.getToAccount(), transaction.getAmount());
        }
        if(transaction.getFromAccount() != null) {
            updateBalancesSuccessful = subtractFromAccountBalanceByIban(transaction.getFromAccount(), transaction.getAmount());
        }
        return updateBalancesSuccessful;
    }

    private Boolean tanRequired(BaseTransaction.TransactionTypeEnum transactionType) {
        return transactionType != BaseTransaction.TransactionTypeEnum.DEPOSIT && transactionType != BaseTransaction.TransactionTypeEnum.WITHDRAWAL;
    }

    public TanVerificationDTO verifyTransactionByTan(Integer id, Integer tan) {
        TanVerificationDTO tanVerification = new TanVerificationDTO();
        Optional<Transaction> optionalTransaction = getTransactionById(id);
        if(optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            if (transaction.getTAN().equals(tan)) {
                TransactionWrapper wrapper = passesAllChecks(transaction, accountsRepo.findById(transaction.getFromAccount()).get());
                if (wrapper.getSucces()) {
                    Boolean updateBalances = updateBalancesByTransaction(transaction);
                    if (updateBalances) {
                        tanVerification.setSuccess(true);
                        tanVerification.setMessage("TAN Correct. Transaction approved.");
                        transaction.setDatetime(OffsetDateTime.now());
                        transaction.setStatus(Transaction.StatusEnum.APPROVED);
                        transactionRepository.save(transaction);
                    } else {
                        tanVerification.setSuccess(false);
                        tanVerification.setMessage("Balances could not be updated due to database error");
                    }
                } else {
                    tanVerification.setMessage("Payment not finalized. The payment exceeds accounts minimum balance requirement, maximum transfer or it exceeds the holder's daily limit.");
                    tanVerification.setSuccess(false);
                }
            } else {
                tanVerification.setSuccess(false);
                tanVerification.setMessage("TAN incorrect.");
            }
        } else {
            tanVerification.setMessage("Transaction with ID " + id + " not found.");
            tanVerification.setSuccess(false);
        }
        return tanVerification;
    }
}
