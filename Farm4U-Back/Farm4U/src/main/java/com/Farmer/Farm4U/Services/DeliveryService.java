package com.Farmer.Farm4U.Services;

import com.Farmer.Farm4U.Entities.Delivery.Delivery;
import com.Farmer.Farm4U.Repositories.DeliveryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
    public List<Delivery> getDeliveryByStatus(boolean status) {
        return deliveryRepository.findDeliveryByStatusF(status);
    }

    public void addNewDelivery(@NotNull Delivery delivery) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findByDeliveryName(delivery.getDeliveryName());
        if (deliveryOptional.isPresent()) {
            throw new IllegalStateException("delivery already exist!");
        }
        deliveryRepository.save(delivery);
    }

    public void deleteDelivery(Long deliveryId) {
        deliveryRepository.findByDeliveryId(deliveryId);
        boolean exists = deliveryRepository.existsById(deliveryId);
        if (exists) {
            deliveryRepository.deleteById(deliveryId);
        } else {
            throw new IllegalStateException("delivery with " + deliveryId + " not found");
        }
    }
    @Transactional
    public void updateDelivery ( long deliveryId, String deliveryName, long phone,double cotisation){
        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId).
                orElseThrow(() -> new IllegalStateException("delivery with " + deliveryId + " not found"));
        if (deliveryName != null && !deliveryName.isEmpty() && !delivery.getDeliveryName().equals(deliveryName)) {
            delivery.setDeliveryName(deliveryName);
        }
        if(phone > 0 && phone != delivery.getPhone()) {
            Optional<Delivery> deliveryOptional = deliveryRepository.findByPhone(phone);
            if (deliveryOptional.isPresent()) {
                throw new IllegalStateException("phone already in use");
            }
            delivery.setPhone(phone);
        }
        if(cotisation > 0 && cotisation != delivery.getCotisation()) {
            Optional<Delivery> deliveryOptional = deliveryRepository.findByCotisation(cotisation);
            if (deliveryOptional.isPresent()) {
                throw new IllegalStateException("cotisation already present");
            }
            delivery.setCotisation(cotisation);
        }
    }

    @Transactional
    public void updateDeliveryStatus(long deliveryId, boolean newStatus) {
        Delivery delivery = deliveryRepository.findDeliveryByStatus(deliveryId)
                .orElseThrow(() -> new IllegalStateException("Delivery with ID " + deliveryId + " not found or already delivered"));

        // Update the delivery status
        delivery.setDeliveryStatus(newStatus);
        deliveryRepository.save(delivery);
    }
}
