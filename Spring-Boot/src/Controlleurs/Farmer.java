package com.Farmer.Farm4U.Controlleurs;

import com.Farmer.Farm4U.Entities.Farm.Farmer;
import com.Farmer.Farm4U.Services.FarmerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/farmer")
@CrossOrigin("http://localhost:5173")
public class FarmerController {
    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }
    @GetMapping
    public List<Farmer> getAllFarmers(){
        return farmerService.getAllFarmers();
    }
    @PostMapping()
    public void registerNewFarmer(@RequestBody Farmer farmer){
        farmerService.addNewFarmer(farmer);
    }
    @DeleteMapping(path = "{userId}")
    public  void deleteFarmer(@PathVariable("userId") Long userId){
        farmerService.deleteFarmer(userId);
    }
    @PutMapping(path = "{userId}")
    public void updateFarmer(@PathVariable("userId") Long userId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) String address,
                            @RequestBody long phone){
        farmerService.updateFarmer(userId,name,email,address,phone);
    }
}
