package com.Farmer.Farm4U.Controlleurs;

import com.Farmer.Farm4U.Entities.Delivery.Delivery;
import com.Farmer.Farm4U.Services.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/delivery")
@CrossOrigin("http://localhost:5173")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
    @GetMapping
    public List<Delivery> getAllDeliveries(){
        return deliveryService.getAllDeliveries();
    }
    @GetMapping(path = "/{deliveryStatus}")
    public List<Delivery> getDeliveryByStatus(@PathVariable("deliveryStatus") boolean deliveryStatus ){
        return deliveryService.getDeliveryByStatus(deliveryStatus);
    }
    @PostMapping()
    public void registerNewDelivery(@RequestBody Delivery delivery){
        deliveryService.addNewDelivery(delivery);
    }
    @DeleteMapping(path = "{deliveryId}")
    public  void deleteDelivery(@PathVariable("deliveryId") Long deliveryId){
        deliveryService.deleteDelivery(deliveryId);
    }
    @PutMapping(path = "/{deliveryId}")
    public void updateDelivery(@PathVariable("deliveryId") Long deliveryId,
                             @RequestParam(required = false) String deliveryName,
                             @RequestBody long phone,
                             @RequestBody double cotisation){
        deliveryService.updateDelivery(deliveryId,deliveryName,phone,cotisation);
    }
    @PutMapping(path="/{deliveryId}/{deliveryStatus}")
    public void updateDeliveryStatus(@PathVariable("deliveryId") Long deliveryId,
                                     @PathVariable("deliveryStatus") boolean deliveryStatus){
        deliveryService.updateDeliveryStatus(deliveryId,deliveryStatus);
    }
}
