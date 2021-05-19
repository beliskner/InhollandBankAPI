package io.swagger.service.accountService;


import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount.AccountTypeEnum;
import io.swagger.model.BaseModels.BaseAccount.StatusEnum;
import io.swagger.model.DTO.AccountDTO.RequestBodyAccount;
import io.swagger.model.DTO.AccountDTO.ReturnBodyAccount;
import io.swagger.repository.accountRepo.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
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
        if (body.getMaxTransfer() == null){maxTransfer = new BigDecimal("500");}
        BigDecimal minBalance = body.getMinBalance();
        if (body.getMinBalance() == null){minBalance = new BigDecimal("-500");}

        Account acc = new Account();
        acc.setStatus(status);
        acc.setAccountType(accountType);
        acc.setHolderId(holderId);
        acc.setBalance(new BigDecimal(0));
        acc.setMinBalance(minBalance);
        acc.setMaxTransfer(maxTransfer);

        return accountsRepo.save(acc);



/*        ReturnBodyAccount rba = new ReturnBodyAccount();
        rba.setBalance(acc.getBalance());
        rba.setHolderId(acc.getHolderId());
        rba.setIban(acc.getIban());
        rba.setAccountType(acc.getAccountType());
        rba.setMinBalance(acc.getMinBalance());
        rba.setStatus(acc.getStatus());*/

    }
    public void updateAccount(@Valid RequestBodyAccount body) {

        StatusEnum status = body.getStatus();
        AccountTypeEnum accountType = body.getAccountType();
        Integer holderId = body.getHolderId();
        BigDecimal maxTransfer =  body.getMaxTransfer();
        BigDecimal minBalance = body.getMinBalance();


        Account acc = new Account();
        acc.setStatus(status);
        acc.setAccountType(accountType);
        acc.setHolderId(holderId);
        acc.setBalance(new BigDecimal(0));
        acc.setMinBalance(minBalance);
        acc.setMaxTransfer(maxTransfer);

        accountsRepo.save(acc);
    }

    public List<Account> getAllAccounts() {
        Iterator iterator =  accountsRepo.findAll().iterator();
        //List<Account> accounts = (List<Account>) StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false).collect(Collectors.toList());

        Stream<Account> x = StreamSupport
                .stream(Spliterators
                        .spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);

       return  x.collect(Collectors.toList());
    }
}
