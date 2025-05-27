package com.Farmer.Farm4U.Repositories;

import com.Farmer.Farm4U.Entities.Farm.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer,Long> {
    Optional<Farmer> findByUserId(Long id);
//    Optional <Farmer>  findByUserName(String userName);
    Optional<Farmer> findByAddress(String address);
    Optional<Farmer> findByEmail(String email);
    Optional<Farmer> findByPhone(Long phone);
}
