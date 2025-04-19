package com.shoping.amazonCricketbatApi.controller;

import com.shoping.amazonCricketbatApi.model.CricketBat;
import com.shoping.amazonCricketbatApi.service.CricketBatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bats")
public class CricketBatController {

    @Autowired
    private CricketBatService cricketBatService;

    // Get all bats
    @GetMapping
    public List<CricketBat> getAllBats() {
        return cricketBatService.getAllBats();
    }

    // Save a new bat
    @PostMapping
    public CricketBat saveBat(@RequestBody CricketBat bat) {
        return cricketBatService.saveBat(bat);
    }

    // Get bats by brand
    @GetMapping("/brand/{brand}")
    public List<CricketBat> getBatsByBrand(@PathVariable String brand) {
        return cricketBatService.findByBrand(brand);
    }

    // Get bats by style
    @GetMapping("/style/{style}")
    public List<CricketBat> getBatsByStyle(@PathVariable String style) {
        return cricketBatService.findByStyle(style);
    }

    // Search by name
    @GetMapping("/search")
    public List<CricketBat> searchByName(@RequestParam String keyword) {
        return cricketBatService.findByNameContaining(keyword);
    }

    // Affordable bats by brand
    @GetMapping("/affordable")
    public List<CricketBat> getAffordableBats(@RequestParam String brand,
                                              @RequestParam Double price) {
        return cricketBatService.findAffordableBatsByBrand(brand, price);
    }

    @DeleteMapping
    public String deleteBat(@PathVariable Long id){
        cricketBatService.deleteById(id);
        return "Cricket bat with ID "+ id + " has been deleted";
    }

    @PutMapping("/{id}")
    public CricketBat updateBat(@PathVariable Long id, @RequestBody CricketBat updatedBat){
        return cricketBatService.updateBat(id,updatedBat);
    }
}