package com.Farmer.Farm4U.Controlleurs;

import com.Farmer.Farm4U.Entities.Motel.Motel;
import com.Farmer.Farm4U.Services.MotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/motel")
@CrossOrigin("http://localhost:5173")
public class MotelController {
    private final MotelService motelService;

    public MotelController(MotelService motelService) {
        this.motelService = motelService;
    }
    @GetMapping
    public List<Motel> getAllMotels(){
        return motelService.getAllMotels();
    }

    @GetMapping("/{useName}")
    public List<Motel> getMotel(@PathVariable("useName") String userName){
        return motelService.getMotelByFarmerName(userName);
    }

    @PostMapping("/{userName}")
    public void registerNewMotel(@RequestBody Motel motel, @PathVariable("userName") String userName){
        motelService.addNewMotel(motel,userName);
    }
    @DeleteMapping(path = "/{motelId}")
    public  void deleteMotel(@PathVariable("motelId") Long motelId){
        motelService.deleteMotel(motelId);
    }
    @PutMapping(path = "/{motelId}")
    public void updateMotel(@PathVariable("motelId") Long motelId,@RequestBody Motel motel){
        motelService.updateMotel(motelId,motel.getLocalisation(),motel.getPrice(),motel.getAvailability());
    }
}
