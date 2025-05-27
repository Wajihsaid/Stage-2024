package com.Farmer.Farm4U.Services;

import com.Farmer.Farm4U.Entities.Motel.Motel;
import com.Farmer.Farm4U.Entities.Product.Product;
import com.Farmer.Farm4U.Entities.User.User;
import com.Farmer.Farm4U.Repositories.MotelRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MotelService {
    private final MotelRepository motelRepository;
    private final UserService userService;

    public MotelService(MotelRepository motelRepository, UserService userService) {
        this.motelRepository = motelRepository;
        this.userService = userService;
    }

    public List<Motel> getAllMotels() {
        return motelRepository.findAll();
    }

    public List<Motel> getMotelByFarmerName(String userName) {
        return motelRepository.findByUserName(userName);
    }

    public void addNewMotel(@NotNull Motel motel,String userName) {
        Optional<Motel> motelOptional = motelRepository.findByMotelId(motel.getMotelId());
        User user=userService.getUserByName(userName);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (motelOptional.isPresent()) {
            throw new IllegalStateException("Motel already exist!");
        }
        motelRepository.save(motel);
    }

    public void deleteMotel(Long motelId) {
        motelRepository.findByMotelId(motelId);
        boolean exists = motelRepository.existsById(motelId);
        if (exists) {
            motelRepository.deleteById(motelId);
        } else {
            throw new IllegalStateException("motel with " + motelId + " not found");
        }
    }
    @Transactional
    public void updateMotel ( long motelId, String localisation,float price, Boolean availability){
        Motel motel = motelRepository.findByMotelId(motelId).
                orElseThrow(() -> new IllegalStateException("motel with " + motelId + " not found"));
        if (localisation != null && !localisation.isEmpty() && !motel.getLocalisation().equals(localisation)) {
            motel.setLocalisation(localisation);
        }
        if (price != motel.getPrice() && price>0) {
            motel.setPrice(price);
        }
        if(availability != null) {
            Optional<Motel> motelOptional = motelRepository.findByAvailability(availability);
//            if (motelOptional.isPresent()) {
//                throw new IllegalStateException("avail already updated");
//            }
            motel.setAvailability(availability);
        }
    }
}
