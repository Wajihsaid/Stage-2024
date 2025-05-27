package com.Farmer.Farm4U.Services;

import com.Farmer.Farm4U.Entities.Order.Order;
import com.Farmer.Farm4U.Repositories.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addNewOrder(@NotNull Order order) {
        Optional<Order> orderOptional = orderRepository.findByOrderId(order.getOrderId());
        if (orderOptional.isPresent()) {
            throw new IllegalStateException("Order already passed!");
        }
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.findByOrderId(orderId);
        boolean exists = orderRepository.existsById(orderId);
        if (exists) {
            orderRepository.deleteById(orderId);
        } else {
            throw new IllegalStateException("order with " + orderId + " not found");
        }
    }
    @Transactional
    public void updateOrder(long orderId, long quantitDem, long prixTotal){

        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new IllegalStateException("order with " + orderId + " not found"));

        if(quantitDem > 0 && quantitDem!=order.getQuantitDem()) {
            Optional<Order> orderOptional = orderRepository.findByQuantit(quantitDem);
            if (orderOptional.isPresent()) {
                throw new IllegalStateException("quantity is already ordered");
            }
            order.setQuantitDem(quantitDem);
        }
        if(prixTotal > 0 && prixTotal!=order.getPrixTotal()) {
            Optional<Order> orderOptional = orderRepository.findByPrixTotal(prixTotal);
            if (orderOptional.isPresent()) {
                throw new IllegalStateException("Price already present ");
            }
            order.setPrixTotal(prixTotal);
        }
    }
}
