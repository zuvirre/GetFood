package com.example.getfood;

public class User {

    private String name, email, password;
    private String type;




    public User(String type, String name, String email, String password) {

        this.type = type;
        this.name = name;
        this.email = email;
        this.password = password;

    }
    public void setUser(User user){

        this.type= user.type;
        this.email=user.email;
        this.name=user.name;
        this.password=user.password;
    }




    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){this.name=name;}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email){this.email=email;}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password){this.password=password;}




}
