package com.example.getfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.getfood.OwnersActivities.OwnerSettingsActivity;

public class OwnerMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button DisplayOrdersButton, SettingsButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_main_activity);

        DisplayOrdersButton = (Button)findViewById(R.id.display_orders_button);
        DisplayOrdersButton.setOnClickListener(this);

        SettingsButton = (Button) findViewById(R.id.settings_button);
        SettingsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.display_orders_button:
                //переход на страницу с заказами
                break;
            case R.id.settings_button:
                startActivity(new Intent(OwnerMainActivity.this, OwnerSettingsActivity.class));
                break;

        }

    }
}