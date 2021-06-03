package io.swagger.security;

import io.swagger.model.Account;
import io.swagger.model.DTO.AccountDTO.ArrayOfAccounts;
import io.swagger.model.Enums.Role;
import io.swagger.model.Holder;
import io.swagger.service.Holders.HolderService;
import io.swagger.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthCheck {

    @Autowired
    private HolderService holderService;

    @Autowired
    private AccountsService accountsService;

    public Boolean isOwnerOfAccountOrEmployee(Authentication authentication, Account account) {
        String email = authentication.getName();
        Holder holder = holderService.getHolderByEmail(email);

        if(holder.getId() == account.getHolderId() || holder.getRole() == Role.ROLE_EMPLOYEE) {
            return true;
        } else {
            return false;
        }
    }
//    public Boolean isOwnerOfAccountOrEmployee(Authentication authentication, ArrayOfAccounts account) {
//        String email = authentication.getName();
//        Holder holder = holderService.getHolderByEmail(email);
//
//        if(holder.getId() == account.getHolderId() || holder.getRole() == Role.ROLE_EMPLOYEE) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    public Boolean isHolderMakingRequestOrEmployee(Authentication authentication, Holder holder) {
        String email = authentication.getName();
        Holder authenticatedHolder = holderService.getHolderByEmail(email);
        if(authenticatedHolder.getId() == holder.getId() || authenticatedHolder.getRole() == Role.ROLE_EMPLOYEE) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean hasRole (String roleName) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + roleName));
    }
}
