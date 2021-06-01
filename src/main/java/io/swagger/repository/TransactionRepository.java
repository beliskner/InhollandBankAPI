package io.swagger.repository;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.threeten.bp.OffsetDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAll();

    @Query(value = "SELECT * FROM TRANSACTION t WHERE t.TO_ACCOUNT = :iban OR t.FROM_ACCOUNT = :iban",
            nativeQuery = true)
    List<Transaction> findAllByIban(@Param("iban") String iban);

//    @Query(value = "SELECT t FROM TRANSACTION t WHERE (t.FROM_ACCOUNT = :iban OR t.TO_ACCOUNT = :iban) AND t.DATETIME BETWEEN :startDate AND :endDate")
//    List<Transaction> findAllByIbanAndDates(@Param("iban") String iban, @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);
//
//    @Query(value = "SELECT t FROM TRANSACTION t WHERE t.DATETIME BETWEEN :startDate AND :endDate")
//    List<Transaction> findAllByDates(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

    List<Transaction> findAllByDatetimeLessThanEqualAndDatetimeGreaterThanEqual(OffsetDateTime endDate, OffsetDateTime startDate);
}
