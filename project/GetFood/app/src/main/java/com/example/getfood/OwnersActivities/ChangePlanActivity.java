package com.example.getfood.OwnersActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.getfood.Dish;
import com.example.getfood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ChangePlanActivity extends AppCompatActivity {

    String[] dish_categories= {"First Dish","Second Dish","Dessert","Drinks"};


    ArrayList<Dish> list_of_first_dishes = new ArrayList<Dish>();
//    ArrayList<Dish> list_of_second_dishes = new ArrayList<Dish>();
//    ArrayList<Dish> list_of_desserts = new ArrayList<Dish>();
//    ArrayList<Dish> list_of_drinks = new ArrayList<Dish>();
//
//    ArrayList<ArrayList> listofalldishes= new ArrayList<ArrayList>();


    FirebaseFirestore db;


    Dish dish;

    private static String TAG = "Firestore";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_plan);



        db = FirebaseFirestore.getInstance();



        for(int i=0;i<=dish_categories.length;i++) {
            db.collection("menu")
                    .whereEqualTo("category", dish_categories[i])
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());

                                    dish = new Dish(
                                            document.get("name").toString(),
                                            document.get("details").toString(),
                                            document.get("price").toString());
                                    list_of_first_dishes.add(dish);

                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
        for(int i = 0;i<list_of_first_dishes.toArray().length;i++){

        }




    }
}