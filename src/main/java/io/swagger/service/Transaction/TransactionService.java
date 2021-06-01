package io.swagger.service.Transaction;

import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.model.DTO.TransactionDTO.*;
import io.swagger.model.Transaction;
import io.swagger.repository.AccountsRepo;
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
    private AccountsRepo accountsRepo;

    private List<Transaction> getTransactionsForIbanForToday(String iban) {
        OffsetDateTime convertedStart = OffsetDateTime.of(LocalDate.now(), LocalTime.MIN, ZoneOffset.UTC);
        OffsetDateTime convertedEnd = OffsetDateTime.of(LocalDate.now(), LocalTime.MAX, ZoneOffset.UTC);
        List<Transaction> transactions = transactionRepository.findAllByDatetimeLessThanEqualAndDatetimeGreaterThanEqual(convertedEnd, convertedStart);

        return transactions.stream().filter(p -> p.getFromAccount().equals(iban)).collect(Collectors.toList());
    }

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
                transactions = allTransactionsByDate.stream().filter(p -> p.getFromAccount().equals(iban)).collect(Collectors.toList());
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

    public Transaction createTransaction(RequestBodyTransaction body, Integer performedHolderId, Boolean isEmployee) {
        Transaction transaction = modelMapper.map(body, Transaction.class);
        Transaction appropriateTransaction = createAppropriateTransaction(transaction, performedHolderId, isEmployee);
        if(!tanRequired(appropriateTransaction.getTransactionType()) || isEmployee) {
            updateBalancesByTransaction(appropriateTransaction);
        }
        transactionRepository.save(appropriateTransaction);

        return appropriateTransaction;
    }

    public Transaction createDepositTransaction(RequestBodyDeposit body, Integer performedHolderId, Boolean isEmployee) {
        Transaction transaction = modelMapper.map(body, Transaction.class);
        Transaction appropriateTransaction = createAppropriateTransaction(transaction, performedHolderId, isEmployee);
        if(!tanRequired(appropriateTransaction.getTransactionType()) || isEmployee) {
            updateBalancesByTransaction(appropriateTransaction);
        }
        transactionRepository.save(appropriateTransaction);

        return appropriateTransaction;
    }

    public Transaction createWithdrawalTransaction(RequestBodyWithdrawal body, Integer performedHolderId, Boolean isEmployee) {
        Transaction transaction = modelMapper.map(body, Transaction.class);
        Transaction appropriateTransaction = createAppropriateTransaction(transaction, performedHolderId, isEmployee);
        if(!tanRequired(appropriateTransaction.getTransactionType()) || isEmployee) {
            updateBalancesByTransaction(appropriateTransaction);
        }
        transactionRepository.save(appropriateTransaction);

        return appropriateTransaction;
    }

    private Transaction createAppropriateTransaction(Transaction transaction, Integer performedHolderId, Boolean isEmployee){
        Transaction appropriateTransaction = new Transaction();
        appropriateTransaction.setDatetime(OffsetDateTime.now());
        System.out.println(OffsetDateTime.now());
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
        if(transaction.getTransactionType() == BaseTransaction.TransactionTypeEnum.TRANSFER && !isEmployee) {
            appropriateTransaction.setTAN(createTAN());
        }
        appropriateTransaction.setAmount(transaction.getAmount());
        return appropriateTransaction;
    }

    private Boolean passesAllChecks(Transaction transaction) {
        return false;
    }

    private int createTAN() {
        return (int) ((Math.random() * (9999 - 1000)) + 1000);
    }

    private Boolean addToAccountBalanceByIban(String iban, BigDecimal balanceUpdate){
        Optional<Account> optionalAccount = accountsService.getAccountByIban(iban);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            BigDecimal oldBalance = account.getBalance();
            account.setBalance(account.getBalance().add(balanceUpdate));
            accountsRepo.save(account);
            Optional<Account> toBeCheckAccount = accountsService.getAccountByIban(account.getIban());
            if(toBeCheckAccount.isPresent() && !toBeCheckAccount.get().getBalance().equals(account.getBalance())) {
                account.setBalance(oldBalance);
                accountsRepo.save(account);
                return false;
                //throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Account balance for account: " + toBeCheckAccount.get().getIban() + " could not be updated");
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
            // if (newBalance < account.getMinBalance())
            account.setBalance(newBalance);
            accountsRepo.save(account);
            Optional<Account> toBeCheckAccount = accountsService.getAccountByIban(account.getIban());
            if(toBeCheckAccount.isPresent() && !toBeCheckAccount.get().getBalance().equals(account.getBalance())) {
                account.setBalance(oldBalance);
                accountsRepo.save(account);
                return false;
                //throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Account balance for account: " + toBeCheckAccount.get().getIban() + " could not be updated");
            }
            return true;
        }
        return false;
    }

    private void updateBalancesByTransaction(Transaction transaction) {
        if(transaction.getToAccount() != null) {
            addToAccountBalanceByIban(transaction.getToAccount(), transaction.getAmount());
        }
        if(transaction.getFromAccount() != null) {
            subtractFromAccountBalanceByIban(transaction.getFromAccount(), transaction.getAmount());
        }
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
                tanVerification.setSuccess(true);
                tanVerification.setMessage("TAN Correct. Transaction approved.");

                updateBalancesByTransaction(transaction);

                transaction.setStatus(Transaction.StatusEnum.APPROVED);
                transactionRepository.save(transaction);
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
