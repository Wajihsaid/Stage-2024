package com.Farmer.Farm4U.Controlleurs;


import com.Farmer.Farm4U.Entities.Order.Order;
import com.Farmer.Farm4U.Services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/order")
@CrossOrigin("http://localhost:5173")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @PostMapping()
    public void registerNewOrder(@RequestBody Order order){
        orderService.addNewOrder(order);
    }
    @DeleteMapping(path = "{orderId}")
    public  void deleteOrder(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);
    }
    @PutMapping(path = "{orderId}")
    public void updateOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Long quantitDem,
                            @RequestBody Long prixTotal){
        orderService.updateOrder(orderId, quantitDem, prixTotal);
    }
}
