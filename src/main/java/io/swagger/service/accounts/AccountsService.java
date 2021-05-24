package io.swagger.service.accounts;
import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount.AccountTypeEnum;
import io.swagger.model.BaseModels.BaseAccount.StatusEnum;
import io.swagger.model.DTO.AccountDTO.MaxTransfer;
import io.swagger.model.DTO.AccountDTO.MinBalance;
import io.swagger.model.DTO.AccountDTO.RequestBodyAccount;
import io.swagger.repository.AccountsRepo;
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
    public Account deleteAccount(@Valid String selecIban) {
        Account byIban = accountsRepo.findById(selecIban).get();
        byIban.setStatus(StatusEnum.CLOSED);
        return accountsRepo.save(byIban);

    }
    public Account updateMaxAccount(@Valid String selecIban, MaxTransfer max) {
        Account byIban = accountsRepo.findById(selecIban).get();
        byIban.setMaxTransfer(max.getMaxTransfer());
        return accountsRepo.save(byIban);

    }
    public Account updateMinAccount(@Valid String selecIban,@Valid BigDecimal min) {
        Account byIban = accountsRepo.findById(selecIban).get();
        byIban.setMinBalance(min);
        return accountsRepo.save(byIban);

    }
    public void updateBalanceAccount(@Valid String selecIban,@Valid BigDecimal balances) {
        Account byIban = accountsRepo.findById(selecIban).get();
        byIban.setBalance(balances);
        accountsRepo.save(byIban);

    }
    public List<Account> getAllAccounts(@Valid String includeClosed) {
        Iterator iterator =  null;

        if (includeClosed.equals("Yes")){
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
}