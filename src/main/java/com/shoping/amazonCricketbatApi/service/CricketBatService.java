package com.shoping.amazonCricketbatApi.service;

import com.shoping.amazonCricketbatApi.model.CricketBat;
import com.shoping.amazonCricketbatApi.repository.CricketBatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CricketBatService {

    @Autowired
    private CricketBatRepository repository;

    public List<CricketBat> getAllBats() {
        return repository.findAll();
    }

    public CricketBat saveBat(CricketBat bat) {
        return repository.save(bat);
    }

    public List<CricketBat> findByBrand(String brand) {
        return repository.findByBrand(brand);
    }

    public List<CricketBat> findByStyle(String style) {
        return repository.findByStyle(style);
    }

    public List<CricketBat> findByNameContaining(String keyword) {
        return repository.findByNameContaining(keyword);
    }

    public List<CricketBat> findAffordableBatsByBrand(String brand, Double price) {
        return repository.findAffordableBatsByBrand(brand, price);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public CricketBat updateBat(Long id, CricketBat updatedBat) {
        return repository.findById(id).map(existingBat -> {
            existingBat.setBrand(updatedBat.getBrand());
            existingBat.setStyle(updatedBat.getStyle());
            existingBat.setName(updatedBat.getName());
            existingBat.setPrice(updatedBat.getPrice());
            existingBat.setReviews(updatedBat.getReviews());
            return repository.save(existingBat);
        }).orElseThrow(() -> new RuntimeException("Bat not found with ID: " + id));
    }
}