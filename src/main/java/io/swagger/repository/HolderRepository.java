package io.swagger.repository;


import io.swagger.model.DTO.HolderDTO.BodyDailyLimit;
import io.swagger.model.Enums.Role;
import io.swagger.model.Holder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolderRepository extends CrudRepository<Holder, Integer> {
    Holder findByEmail(String email);

//    List<Holder> findAll();

    Holder findById(int id);

    @Query("SELECT h FROM Holder h WHERE h.role = ?1 AND h.firstName like %?2 AND h.lastName like %?3 AND h.status = ?4")
    List<Holder> searchAllHolders(Role role, String firstName, String lastName, Holder.StatusEnum status);

    @Query("SELECT h FROM Holder h WHERE h.role = ?1 AND h.status = ?2")
    List<Holder> searchAllHolders2(Role role, Holder.StatusEnum status);

    @Query("SELECT h FROM Holder h WHERE h.role = ?1")
    List<Holder> searchAllHolders3(Role role);
}
