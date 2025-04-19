package com.shoping.amazonCricketbatApi.repository;

import com.shoping.amazonCricketbatApi.model.CricketBat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CricketBatRepository extends JpaRepository<CricketBat, Long> {

    //SPRING UNDERSTANDS THIS

    //find all bats by brand
    List<CricketBat> findByBrand(String brand);

    //find all bats by style
    List<CricketBat> findByStyle(String style);

    //find all bats by  style and brand
    List<CricketBat> findByBrandAndStyle(String brand,String style);

    //find all bats by price less than a given
    List<CricketBat> findByPriceLessThan(Double price);

    //find all bats with keyword
    List<CricketBat> findByNameContaining(String keyword);


    //Using @Query annotation for custom JPQL query
    @Query("SELECT b FROM CricketBat b WHERE b.brand = :brand AND b.price <= :maxPrice")
    List<CricketBat> findAffordableBatsByBrand(@Param("brand") String brand, @Param("maxPrice") Double maxPrice);






}
