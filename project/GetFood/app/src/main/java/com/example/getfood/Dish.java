package com.example.getfood;

public class Dish {
    private String category, name, details, price;

    public Dish(String name, String details, String price){

    this.name=name;
    this.details=details;
    this.price=price;
    }

    public void setDish(Dish dish){
        this.category=category;
        this.name=name;
        this.details=details;
        this.price=price;
    }
    public String getCategory(){
        return category;
    }

    public String getName(){
        return name;
    }

    public String getDetails(){
        return details;
    }

    public String getPrice(){
        return price;
    }

    public void setDetails(String details){
        this.details=details;
    }

    public  void setPrice(String price){
        this.price=price;
    }

}