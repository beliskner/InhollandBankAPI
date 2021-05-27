package io.swagger.security;

import io.swagger.model.Account;
import io.swagger.model.Enums.Role;
import io.swagger.model.Holder;
import io.swagger.service.Holders.HolderService;
import io.swagger.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthCheck {

    @Autowired
    private HolderService holderService;

    @Autowired
    private AccountsService accountsService;

    public Boolean isOwnerOfAccountOrEmployee(Authentication authentication, String iban) {
        String email = authentication.getName();
        Optional<Account> optionalAccount = accountsService.getAccountByIban(iban);
        Account account;
        if(optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else {
            return false;
        }
        Holder holder = holderService.getHolderByEmail(email);


        if(holder.getId() == account.getHolderId() || holder.getRole() == Role.ROLE_EMPLOYEE) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isHolderMakingRequestOrEmployee(Authentication authentication, int id) {
        String email = authentication.getName();
        Holder holder = holderService.getHolderByEmail(email);
        if(holder.getId() == id || holder.getRole() == Role.ROLE_EMPLOYEE) {
            return true;
        } else {
            return false;
        }
    }
}
