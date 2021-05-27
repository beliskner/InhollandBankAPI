package io.swagger.service.Transaction;

import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.model.DTO.AccountDTO.ArrayOfAccounts;
import io.swagger.model.DTO.TransactionDTO.*;
import io.swagger.model.Transaction;
import io.swagger.repository.AccountsRepo;
import io.swagger.repository.TransactionRepository;
import io.swagger.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.OffsetDateTime;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static io.swagger.helpers.MapListsHelper.modelMapper;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private AccountsRepo accountsRepo;

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions;
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction;
    }

    public TanDTO getTANByTransactionId(Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        TanDTO tanDTO = new TanDTO();
        tanDTO.setTAN(transaction.get().getTAN());

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

    private int createTAN() {
        return (int) ((Math.random() * (9999 - 1000)) + 1000);
    }

    private void addToAccountBalanceByIban(String iban, BigDecimal balanceUpdate){
        Optional<Account> optionalAccount = accountsService.getAccountByIban(iban);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance().add(balanceUpdate));
            accountsRepo.save(account);
        }
    }

    private void subtractFromAccountBalanceByIban(String iban, BigDecimal balanceUpdate) {
        Optional<Account> optionalAccount = accountsService.getAccountByIban(iban);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance().subtract(balanceUpdate));
            accountsRepo.save(account);
        }
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
        if(transactionType == BaseTransaction.TransactionTypeEnum.DEPOSIT || transactionType == BaseTransaction.TransactionTypeEnum.WITHDRAWAL) {
            return false;
        } else {
            return true;
        }
    }
}
