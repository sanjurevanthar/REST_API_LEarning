package com.shoping.amazonCricketbatApi.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Entity
public class CricketBat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate the id in an order automatically so that could identify the response
    private Long id;

    private String brand;
    private  String style;
    private String name;
    private Double price;

    @Column(columnDefinition = "TEXT") // this text informs the JPA to map this filed to the database column of type TEXT
    private String reviewsJson; //stores the jsonString version of the list of strings in db

    @Transient
    private List<String> reviews; // Tells jpa to ignore this file when saving to the database

    public CricketBat(){
        //uses reflection to create instance without manually calling them
    }

    public CricketBat(String brand, String style, String name, Double price, List<String> reviews){
        this.brand = brand;
        this.style = style;
        this.name = name;
        this.price = price;
        setReviews(reviews); //setting the value
    }

    //getter
    public Long getId(){
        return id;
    }
    public String getBrand(){
        return brand;
    }
    public String getStyle(){
        return style;
    }
    public Double getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public List<String> getReviews(){   //get from db and convert to list
        if(reviews == null && reviewsJson != null ){
            try{
                ObjectMapper objectMapper = new ObjectMapper();
                reviews = objectMapper.readValue(reviewsJson, new TypeReference<>() {});
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return reviews;
    }


    //Setter

    public void setId(Long id){
        this.id =id;
    }

    public void setBrand(String brand){
        this.brand =brand;
    }

    public void setStyle(String style){
        this.style =style;
    }

    public void setName(String name){
        this.name =name;
    }

    public void setPrice(Double price){
        this.price =price;
    }

    public void setReviews(List<String> reviews){ //convert to json and store backs
        this.reviews = reviews;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            this.reviewsJson = objectMapper.writeValueAsString(reviews);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
