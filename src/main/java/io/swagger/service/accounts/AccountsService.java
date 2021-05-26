package io.swagger.service.accounts;
import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount.AccountTypeEnum;
import io.swagger.model.BaseModels.BaseAccount.StatusEnum;
import io.swagger.model.DTO.AccountDTO.*;
import io.swagger.model.Enums.Role;
import io.swagger.model.Holder;
import io.swagger.repository.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AccountsService {
    @Autowired
    private AccountsRepo accountsRepo;

    public Account addAccount(@Valid RequestBodyAccount body) {
        StatusEnum status = body.getStatus();
        AccountTypeEnum accountType = body.getAccountType();
        Integer holderId = body.getHolderId();
        BigDecimal maxTransfer =  body.getMaxTransfer();
        //TODO: DEFAULT WAARDES IN MODEL ZETTEN.
        if (body.getMaxTransfer() == null){maxTransfer = new BigDecimal("500");}
        BigDecimal minBalance = body.getMinBalance();
        if (body.getMinBalance() == null){minBalance = new BigDecimal("-9900");}

        Account acc = new Account();
        acc.setStatus(status);
        acc.setAccountType(accountType);
        acc.setHolderId(holderId);
        acc.setBalance(new BigDecimal(0));
        acc.setMinBalance(minBalance);
        acc.setMaxTransfer(maxTransfer);

        return accountsRepo.save(acc);

    }

    public Optional<Account> getAccountByIban(String iban){

        return accountsRepo.findById(iban);

    }

    public Account deleteAccount(@Valid String selectIban) {
        Account byIban = accountsRepo.findById(selectIban).get();
        byIban.setStatus(StatusEnum.CLOSED);
        return accountsRepo.save(byIban);

    }
    public Account updateStatusAccount(@Valid String selectIban, RequestBodyUpdateAccount body) {
        StatusEnum status = body.getStatus();
        AccountTypeEnum accountType = body.getAccountType();
        BigDecimal maxTransfer =  body.getMaxTransfer();
        BigDecimal minBalance = body.getMinBalance();
        Account acc = accountsRepo.findById(selectIban).get();
        acc.setStatus(status);
        acc.setAccountType(accountType);
        acc.setBalance(new BigDecimal(0));
        acc.setMinBalance(minBalance);
        acc.setMaxTransfer(maxTransfer);

        accountsRepo.save(acc);
        return acc;

    }

    public MaxTransfer updateMaxTransferByIban(String iban, MaxTransfer maxTransfer) {
        Account account = accountsRepo.findById(iban).get();
        account.setMaxTransfer(maxTransfer.getMaxTransfer());
        MaxTransfer newMax = new MaxTransfer();
        newMax.setMaxTransfer(account.getMaxTransfer());
        accountsRepo.save(account);
        return newMax;
    }

    public MinBalance updateMinAccount(@Valid String selecIban, MinBalance min) {
        Account account = accountsRepo.findById(selecIban).get();
        account.setMinBalance(min.getMinBalance());
        MinBalance newMin = new MinBalance();
        newMin.setMinBalance(account.getMinBalance());
        accountsRepo.save(account);
        return newMin;

    }

    public List<Account> getAllAccounts(String includeClosed) {
        Iterator iterator =  null;

        if (includeClosed == null ||  includeClosed.equals("Yes")){
            iterator = accountsRepo.findAll().iterator();

        }else {
            iterator =  accountsRepo.findAllWhereStatusOpen().iterator();
                    //accountsRepo.findAllWhereStatusOpen();
        }

        Stream<Account> x = StreamSupport
                .stream(Spliterators
                        .spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);

       return  x.collect(Collectors.toList());
    }

    public List<Account> getAllAccountsByHolderId(Integer id) {
        List<Account> accounts = accountsRepo.findByHolderId(id);

        return accounts;
    }

    public void addAccountForBank() {
        Account account = new Account();
        account.setAccountType(AccountTypeEnum.CURRENT);
        account.setMaxTransfer(new BigDecimal("5000.00"));
        account.setMinBalance(new BigDecimal("-5000.00"));
        account.setStatus(StatusEnum.OPEN);
        account.setBalance(new BigDecimal("1000000.25"));
        account.setHolderId(1);
        accountsRepo.save(account);
    }
}
