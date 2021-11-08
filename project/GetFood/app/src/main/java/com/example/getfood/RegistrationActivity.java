package com.example.getfood;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegistrationActivity extends AppCompatActivity implements OnClickListener{


//    private AuthHelper authHelper;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    private Button RegistrationButton;
    private EditText RegistrationEmailInput, RegistrationNameInput, RegistrationPasswordInput;
    private ProgressBar progressBar;

    public TextView OwnerButton, ClientButton;

    String userId;

    private static String TAG = "DataBase";


    public String tp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        tp = "0";

//        authHelper = new AuthHelper();
//        authHelper.onCreate();





        RegistrationButton = (Button) findViewById(R.id.registration_button);
        RegistrationButton.setOnClickListener(this);

        RegistrationNameInput = (EditText) findViewById(R.id.registration_name_input);
        RegistrationEmailInput = (EditText) findViewById(R.id.registration_email_input);
        RegistrationPasswordInput = (EditText) findViewById(R.id.registration_password_input);

        OwnerButton = (TextView) findViewById(R.id.owner_panel_link);
        OwnerButton.setOnClickListener(this);

        ClientButton = (TextView) findViewById(R.id.client_panel_link);
        ClientButton.setOnClickListener(this);


        progressBar = (ProgressBar) findViewById(R.id.progress_bar);



    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        authHelper.onStart(this);
//    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.owner_panel_link:
                tp = "1";
                findViewById(R.id.owner_panel_link).setVisibility(View.INVISIBLE);
                findViewById(R.id.client_panel_link).setVisibility(View.VISIBLE);
                TextView textView =  findViewById(R.id.registration_text);
                textView.setText("Регистрация ресторана");
                EditText editText1 = findViewById(R.id.registration_name_input);
                editText1.setHint("Название ресторана");


                break;


            case R.id.client_panel_link:
                tp = "0";
                findViewById(R.id.owner_panel_link).setVisibility(View.VISIBLE);
                findViewById(R.id.client_panel_link).setVisibility(View.INVISIBLE);
                TextView textView1 = findViewById(R.id.registration_text);
                textView1.setText("Регистрация аккаунта");
                EditText editText2 = findViewById(R.id.registration_name_input);
                editText2.setHint("Имя");
                break;


            case R.id.registration_button:

                    createAccount();
                break;




        }

    }

    private  void createAccount() {

        String email = RegistrationEmailInput.getText().toString().trim();
        String name = RegistrationNameInput.getText().toString().trim();
        String password = RegistrationPasswordInput.getText().toString().trim();


        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        if (name.isEmpty()) {
            RegistrationNameInput.setError("Ввод имени обязателен!");
            RegistrationNameInput.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            RegistrationEmailInput.setError("Ввод почты обязателен!");
            RegistrationEmailInput.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            RegistrationEmailInput.setError("Пожалуйста, введите существующую почту!");
            RegistrationEmailInput.requestFocus();
            return;

        }
        if (password.isEmpty()) {
            RegistrationPasswordInput.setError("Ввод пароля обязателен!");
            RegistrationPasswordInput.requestFocus();
            return;
        }
        if (password.length() < 8) {
            RegistrationPasswordInput.setError("Пароль должен состоять из 8 и более символов!");
            RegistrationPasswordInput.requestFocus();
            return;
        }
        if (password.length() > 30) {
            RegistrationPasswordInput.setError("Пароль не может быть длиннее 30 символов!");
            RegistrationPasswordInput.requestFocus();
            return;
        } else {


            progressBar.setVisibility(View.VISIBLE);
        //authHelper.register(tp, email, name, password, this);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Пользователь успешно авторизирован", Toast.LENGTH_SHORT).show();

                        userId = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users").document(userId);
                        Map<String, Object> user = new HashMap<>();
                        //user.put("UID", userId);
                        user.put("type", tp);
                        user.put("email", email);
                        user.put("name", name);
                        user.put("password", password);
                        user.put("phone", "");
                        user.put("place","");
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "onSuccess: user Profile is created for" + userId);

                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                currentUser.sendEmailVerification();

                                updateUI(currentUser, RegistrationActivity.this);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });

                    } else {
                        Toast.makeText(RegistrationActivity.this, "Ошибка регистрации: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }





    }


    public void updateUI (FirebaseUser account, Activity activity) {

        if (account != null) {
            Toast.makeText(activity, "Вы успешно Зарегистрировались!", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            Toast.makeText(RegistrationActivity.this, "Подтвердите письмо на почте." , Toast.LENGTH_LONG).show();
            activity.startActivity(new Intent(activity, LoginActivity.class));

        } else {

        }
    }
    public void signOut() {
        mAuth.signOut();
        updateUI(null, null);
    }

}