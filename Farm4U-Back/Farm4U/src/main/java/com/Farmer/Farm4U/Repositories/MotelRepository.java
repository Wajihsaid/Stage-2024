package com.Farmer.Farm4U.Repositories;


import com.Farmer.Farm4U.Entities.Motel.Motel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotelRepository extends JpaRepository<Motel,Long> {
    Optional<Motel> findByMotelId(Long id);
//    Optional<Motel> findByLocalisation(String localisation);
    Optional<Motel> findByAvailability(boolean availability);
    @Query("SELECT m FROM motel m JOIN m.farmer f WHERE f.userName = :userName")
    List<Motel> findByUserName(@Param("userName") String userName);
}
