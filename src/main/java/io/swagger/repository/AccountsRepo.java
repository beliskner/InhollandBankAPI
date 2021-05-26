package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface AccountsRepo extends CrudRepository<Account, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM ACCOUNTS where STATUS = 1" )
    List<Account> findAllWhereStatusOpen();

    List<Account> findByHolderId(int id);

    Account findByIban(String iban);
}
