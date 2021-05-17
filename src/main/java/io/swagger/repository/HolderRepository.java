package io.swagger.repository;


import io.swagger.model.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolderRepository extends JpaRepository<Holder, Long> {
    Holder findByEmail(String email);
}
