package io.swagger.repository;


import io.swagger.model.DTO.HolderDTO.BodyDailyLimit;
import io.swagger.model.Holder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolderRepository extends CrudRepository<Holder, Integer> {
    Holder findByEmail(String email);

    List<Holder> findAll();

    Holder findById(int id);
}
