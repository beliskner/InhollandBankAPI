package io.swagger.api.AccountsController;

import io.swagger.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepo extends CrudRepository<Account, String> {
}
