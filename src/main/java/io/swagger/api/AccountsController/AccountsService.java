package io.swagger.api.AccountsController;


import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount.AccountTypeEnum;
import io.swagger.model.BaseModels.BaseAccount.StatusEnum;
import io.swagger.model.DTO.AccountDTO.RequestBodyAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepo accountsRepo;


    public void addAccount(@Valid RequestBodyAccount body) {

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
}
