package io.swagger.api.AccountsController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

    @Autowired
    AccountsRepo accountsRepo;

}
