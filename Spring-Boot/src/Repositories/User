package com.Farmer.Farm4U.Repositories;

import com.Farmer.Farm4U.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUserName(String userName);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByAddress(String address);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(Long phone);
}
