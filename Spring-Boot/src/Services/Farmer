package com.Farmer.Farm4U.Services;

import com.Farmer.Farm4U.Entities.Farm.Farmer;
import com.Farmer.Farm4U.Repositories.FarmerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {
    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    public void addNewFarmer(@NotNull Farmer farmer) {
        Optional<Farmer> farmerOptional = farmerRepository.findByEmail(farmer.getEmail());
        if (farmerOptional.isPresent()) {
            throw new IllegalStateException("email already in use");
        }
        farmerRepository.save(farmer);
    }

    public void deleteFarmer(Long userId) {
        farmerRepository.findByUserId(userId);
        boolean exists = farmerRepository.existsById(userId);
        if (exists) {
            farmerRepository.deleteById(userId);
        } else {
            throw new IllegalStateException("user with " + userId + " not found");
        }
    }
    @Transactional
    public void updateFarmer ( long userId, String name, String email,String address,long phone){
        Farmer farmer = farmerRepository.findByUserId(userId).orElseThrow(() -> new IllegalStateException("Farmer with " + userId + " not found"));
        if (name != null && !name.isEmpty() && !farmer.getUserName().equals(name)) {
            farmer.setUserName(name);
        }
        if(email != null && !email.isEmpty() && !farmer.getEmail().equals(email)) {
            Optional<Farmer> farmerOptional = farmerRepository.findByEmail(email);
            if (farmerOptional.isPresent()) {
                throw new IllegalStateException("email already in use");
            }
            farmer.setEmail(email);
        }
        if(phone > 0 && phone != farmer.getPhone()) {
            Optional<Farmer> farmerOptional = farmerRepository.findByPhone(phone);
            if (farmerOptional.isPresent()) {
                throw new IllegalStateException("phone already in use");
            }
            farmer.setPhone(phone);
        }
        if(address != null && !address.isEmpty() && !farmer.getAddress().equals(address)) {
            Optional<Farmer> userOptional = farmerRepository.findByAddress(address);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("address already in use");
            }
            farmer.setEmail(address);
        }
    }
}
