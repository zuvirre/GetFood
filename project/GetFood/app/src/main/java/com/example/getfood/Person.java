package com.example.getfood;

import java.util.Date;

class Person {

    private String uid, name,email, password;
    private int type;


    // No-argument constructor is required to support conversion of Firestore document to POJO
    public Person() {}

    // All-argument constructor is required to support conversion of Firestore document to POJO
    public Person(String uid, int type, String name, String email, String password) {
        this.uid=uid;
        this.type=type;
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public String getUid() {
        return uid;
    }


    public int getType(){
        return type;
    }
    public void setType(int type){
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
