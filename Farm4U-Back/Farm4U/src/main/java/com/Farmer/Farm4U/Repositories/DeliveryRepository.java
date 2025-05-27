package com.Farmer.Farm4U.Repositories;

import com.Farmer.Farm4U.Entities.Delivery.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    Optional<Delivery> findByDeliveryId(Long id);
    Optional<Delivery> findByDeliveryName(String deleveryName);
    Optional<Delivery> findByPhone(long phone);
    Optional<Delivery> findByCotisation(double cotisation);
    @Query("SELECT D FROM Delevery D WHERE D.deliveryStatus = FALSE AND D.deliveryId = :deliveryId")
    Optional<Delivery> findDeliveryByStatus(@Param("deliveryId") long deliveryId);
    @Query("SELECT d from Delevery d where d.deliveryStatus=false and d.deliveryStatus=:deliveryStatus")
    List<Delivery> findDeliveryByStatusF(@Param("deliveryStatus") boolean deliveryStatus);
}
