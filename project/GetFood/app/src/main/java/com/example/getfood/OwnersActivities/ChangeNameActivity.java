package com.example.getfood.OwnersActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.getfood.OwnerMainActivity;
import com.example.getfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChangeNameActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private Button AffirmationButton, CancellationButton;
    private EditText NewNameInput;

    private String name, uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);


        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        AffirmationButton = (Button) findViewById(R.id.affirm_button);
        AffirmationButton.setOnClickListener(this);

        CancellationButton = (Button) findViewById(R.id.cancel_button);
        CancellationButton.setOnClickListener(this);

        NewNameInput = (EditText) findViewById(R.id.new_name_input);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.affirm_button:
                name = NewNameInput.getText().toString().trim();
                uid = mAuth.getCurrentUser().getUid();
                db.collection("users").document(uid).update("name",name);
                startActivity(new Intent(ChangeNameActivity.this,OwnerMainActivity.class));
                break;



            case R.id.cancel_action:
                startActivity(new Intent(ChangeNameActivity.this, OwnerMainActivity.class));
                break;
        }
    }
}