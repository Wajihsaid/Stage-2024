package com.Farmer.Farm4U.Repositories;


import com.Farmer.Farm4U.Entities.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByOrderId(Long orderId);
    @Query("SELECT o FROM Command o WHERE o.quantitDem=?1")
    Optional<Order> findByQuantit(Long quantitDem);
    @Query("SELECT o FROM Command o WHERE o.prixTotal=?1")
    Optional<Order> findByPrixTotal(Long prixTotal);
}
