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

    Holder findById(int id);

    @Query("SELECT h FROM Holder h WHERE h.role = ?1 AND LOWER(h.firstName) LIKE %?2% AND LOWER(h.lastName) LIKE %?3% AND h.status = ?4")
    List<Holder> searchAllHoldersByRoleAndStatus(Role role, String firstName, String lastName, Holder.StatusEnum status);

    @Query("SELECT h FROM Holder h WHERE h.role = ?1 AND LOWER(h.firstName) LIKE %?2% AND LOWER(h.lastName) LIKE %?3%")
    List<Holder> searchAllHoldersByRole(Role role, String firstName, String lastName);

    @Query("SELECT h FROM Holder h WHERE LOWER(h.firstName) LIKE %?1% AND LOWER(h.lastName) LIKE %?2% AND LOWER(h.status) = ?3")
    List<Holder> searchAllHoldersByStatus(String firstName, String lastName, Holder.StatusEnum status);

    @Query("SELECT h FROM Holder h WHERE LOWER(h.firstName) LIKE %?1% AND LOWER(h.lastName) LIKE %?2%")
    List<Holder> searchAllHolders(String firstName, String lastName);
}
