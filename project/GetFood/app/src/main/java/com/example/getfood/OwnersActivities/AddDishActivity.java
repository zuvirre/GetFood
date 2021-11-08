package com.example.getfood.OwnersActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.getfood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddDishActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;

    FirebaseFirestore db;

    private static String TAG = "Firestore";
    private Button SaveButton;
    private EditText DishCategoryInput, DishNameInput, DishDetailsInput, DishPriceInput;

    private ImageView ImageDownloadButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        SaveButton = (Button) findViewById(R.id.save_button);
        SaveButton.setOnClickListener(this);

        DishCategoryInput = (EditText) findViewById(R.id.dish_category_input);
        DishNameInput = (EditText) findViewById(R.id.dish_name_input);
        DishDetailsInput = (EditText) findViewById(R.id.dish_details_input);
        DishPriceInput = (EditText) findViewById(R.id.dish_price_input);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_button:
                newDish();
                startActivity(new Intent(AddDishActivity.this, OwnerSettingsActivity.class));
                break;
        }

    }


    public void newDish(){
        String category = DishCategoryInput.getText().toString().trim();
        String name = DishNameInput.getText().toString().trim();
        String details = DishDetailsInput.getText().toString().trim();
        String price = DishPriceInput.getText().toString().trim();


        if (TextUtils.isEmpty(category)) {
            DishCategoryInput.setError("Введите категорию блюда");
            DishCategoryInput.requestFocus();
        }
        if (TextUtils.isEmpty(name)) {
            DishNameInput.setError("Введите название блюда");
            DishNameInput.requestFocus();
        }
        if (TextUtils.isEmpty(details)) {
            DishDetailsInput.setError("Введите описание блюда");
            DishDetailsInput.requestFocus();
        }
        if (TextUtils.isEmpty(price)) {
            DishPriceInput.setError("Введите цену блюда");
            DishPriceInput.requestFocus();
        }
        else{
            Map<String,Object> dish = new HashMap<>();
            dish.put("category",category);
            dish.put("name",name);
            dish.put("details",details);
            dish.put("price",price);

            db.collection("menu").add(dish).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG,"Dish added successfully");
                    Toast.makeText(AddDishActivity.this,"Блюдо добавлено", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG,"Error adding dish");
                    Toast.makeText(AddDishActivity.this,"Ошибка. Попробуйте еще раз.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}