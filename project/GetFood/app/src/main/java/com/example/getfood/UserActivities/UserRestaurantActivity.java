package com.example.getfood.UserActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.getfood.LoginActivity;
import com.example.getfood.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserRestaurantActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private Button ReserveButton, HowToGetButton;
    private TextView SignOutText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_restaurant);

        mAuth = FirebaseAuth.getInstance();

        ReserveButton = (Button) findViewById(R.id.reserve_button);
        ReserveButton.setOnClickListener(this);

        HowToGetButton = (Button) findViewById(R.id.how_to_get_button);
        HowToGetButton.setOnClickListener(this);

        SignOutText = (TextView) findViewById(R.id.signout_text);
        SignOutText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.reserve_button:
                //переход на бронь стола
                break;
            case R.id.how_to_get_button:
                //переход в карты
                break;
            case R.id.signout_text:
                signOut();
                break;
        }

    }

    public void signOut() {
        mAuth.signOut();
        startActivity( new Intent(this, LoginActivity.class));
    }
}