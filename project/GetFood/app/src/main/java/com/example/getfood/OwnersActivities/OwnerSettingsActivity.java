package com.example.getfood.OwnersActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.getfood.FirestoreHelper;
import com.example.getfood.LoginActivity;
import com.example.getfood.R;
import com.example.getfood.UserDataReceiveListener;
import com.google.firebase.auth.FirebaseAuth;

public class OwnerSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ChangeMenuButton, ChangePlanButton, ChangeNameButton, ChangePlaceButton;
    private TextView SignOutText;

    private TextView SettingsText;
    private FirebaseAuth mAuth;
    private FirestoreHelper fireHelper;
    private String name;

    UserDataReceiveListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_settings);





        mAuth = FirebaseAuth.getInstance();

        ChangeMenuButton = (Button) findViewById(R.id.ChangeMenu);
        ChangeMenuButton.setOnClickListener(this);

        ChangePlanButton = (Button) findViewById(R.id.ChangePlan);
        ChangePlanButton.setOnClickListener(this);

        ChangeNameButton = (Button) findViewById(R.id.ChangeName);
        ChangeNameButton.setOnClickListener(this);

        ChangePlaceButton = (Button) findViewById(R.id.ChangePlace);
        ChangePlaceButton.setOnClickListener(this);

        SignOutText = (TextView) findViewById(R.id.signout_text);
        SignOutText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ChangeMenu:
                //переход в изменение меню
                startActivity(new Intent(OwnerSettingsActivity.this, AddDishActivity.class));
                break;
            case R.id.ChangePlan:
                //переход в изменение плана
                startActivity(new Intent(OwnerSettingsActivity.this,ChangePlanActivity.class));
                break;
            case R.id.ChangeName:
                startActivity(new Intent(OwnerSettingsActivity.this, ChangeNameActivity.class));
                break;
                //переход в изменение меню
//            case R.id.ChangePlace:
                //переход в изменение место положения
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