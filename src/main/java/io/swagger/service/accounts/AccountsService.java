package io.swagger.service.accounts;

import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount.AccountTypeEnum;
import io.swagger.model.BaseModels.BaseAccount.StatusEnum;
import io.swagger.model.DTO.AccountDTO.*;
import io.swagger.model.Holder;
import io.swagger.repository.AccountsRepo;
import io.swagger.repository.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AccountsService {
    @Autowired
    private AccountsRepo accountsRepo;
    @Autowired
    private HolderRepository holderRepository;

    public Account addAccount(@Valid RequestBodyAccount body) {
        StatusEnum status = body.getStatus();
        AccountTypeEnum accountType = body.getAccountType();
        Integer holderId = body.getHolderId();
        if (!holderRepository.findById(holderId).isPresent()) {
            return null;
        }

        BigDecimal maxTransfer = body.getMaxTransfer();
        BigDecimal minBalance = body.getMinBalance();

        Account acc = new Account();
        acc.setStatus(status);
        acc.setAccountType(accountType);
        acc.setHolderId(holderId);
        acc.setBalance(new BigDecimal(0));
        acc.setMinBalance(minBalance);
        acc.setMaxTransfer(maxTransfer);


        return accountsRepo.save(acc);

    }

    public Optional<Account> getAccountByIban(String iban) {

        return accountsRepo.findById(iban);

    }

    public Account deleteAccount(@Valid String selectIban) {
        Optional<Account> byIban = accountsRepo.findById(selectIban);
        if (!byIban.isPresent()) return null;

        return accountsRepo.save(byIban.get());

    }

    public Account updateStatusAccount(@Valid String selectIban, RequestBodyUpdateAccount body) {
        StatusEnum status = body.getStatus();
        AccountTypeEnum accountType = body.getAccountType();
        BigDecimal maxTransfer = body.getMaxTransfer();
        BigDecimal minBalance = body.getMinBalance();


        Optional<Account> optionalAccount = accountsRepo.findById(selectIban);
        if (!optionalAccount.isPresent()) return null;

        Account acc = optionalAccount.get();

        acc.setStatus(status);
        acc.setAccountType(accountType);
        acc.setBalance(new BigDecimal(0));
        acc.setMinBalance(minBalance);
        acc.setMaxTransfer(maxTransfer);

        accountsRepo.save(acc);
        return acc;

    }

    public MaxTransfer updateMaxTransferByIban(String iban, MaxTransfer maxTransfer) {
        Optional<Account> optionalAccount = accountsRepo.findById(iban);

        if (!optionalAccount.isPresent()) return null;

        Account account = optionalAccount.get();

        account.setMaxTransfer(maxTransfer.getMaxTransfer());
        MaxTransfer newMax = new MaxTransfer();
        newMax.setMaxTransfer(account.getMaxTransfer());
        accountsRepo.save(account);
        return newMax;
    }

    public MinBalance updateMinAccount(@Valid String selecIban, MinBalance min) {
        Optional<Account> optionalAccount = accountsRepo.findById(selecIban);

        if (!optionalAccount.isPresent()) return null;

        Account account = optionalAccount.get();

        account.setMinBalance(min.getMinBalance());
        MinBalance newMin = new MinBalance();
        newMin.setMinBalance(account.getMinBalance());
        accountsRepo.save(account);
        return newMin;
    }

    public List<Account> getAllAccounts(String includeClosed) {
        List<Account> accounts = Collections.emptyList();

        if (includeClosed == null || includeClosed.equals("Yes")) {
            accounts = (List<Account>) accountsRepo.findAll();

        } else {
            accounts = accountsRepo.findAllWhereStatusOpen();
        }

        if (accounts == null || accounts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No accounts found");
        }

        return accounts;
    }

    public List<Account> getAllAccountsByHolderId(Integer id) {
        List<Account> accounts = accountsRepo.findByHolderId(id);

        return accounts;
    }

    public void addAccountForBank() {
        Account account = new Account();
        account.setAccountType(AccountTypeEnum.CURRENT);
        account.setMaxTransfer(new BigDecimal("5000.00"));
        account.setMinBalance(new BigDecimal("-500.00"));
        account.setStatus(StatusEnum.OPEN);
        account.setBalance(new BigDecimal("1000000.25"));
        account.setHolderId(1);
        accountsRepo.save(account);
    }

    public void addTestAccounts() {
        Account account = new Account();
        account.setAccountType(AccountTypeEnum.CURRENT);
        account.setMaxTransfer(new BigDecimal("5000.00"));
        account.setMinBalance(new BigDecimal("-5000.00"));
        account.setStatus(StatusEnum.OPEN);
        account.setBalance(new BigDecimal("1000000.25"));
        account.setHolderId(1);
        accountsRepo.save(account);

        Account account2 = new Account();
        account.setAccountType(AccountTypeEnum.CURRENT);
        account.setMaxTransfer(new BigDecimal("5000.00"));
        account.setMinBalance(new BigDecimal("-5000.00"));
        account.setStatus(StatusEnum.OPEN);
        account.setBalance(new BigDecimal("1000000.25"));
        account.setHolderId(1);
        accountsRepo.save(account);
    }

    public void addAccountForCustomer() {
        Account account = new Account();
        account.setAccountType(AccountTypeEnum.CURRENT);
        account.setMaxTransfer(new BigDecimal("5000.00"));
        account.setMinBalance(new BigDecimal("-500.00"));
        account.setStatus(StatusEnum.OPEN);
        account.setBalance(new BigDecimal("5000.25"));
        account.setHolderId(2);
        accountsRepo.save(account);
    }

    public List<Account> getAccountsByAuthentication(Authentication authentication) {
        String mail = authentication.getName();
        Holder acc = holderRepository.findByEmail(mail);
        return acc.getAccounts();

    }
}
