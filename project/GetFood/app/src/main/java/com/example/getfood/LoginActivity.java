package com.example.getfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getfood.OwnersActivities.OwnerSettingsActivity;
import com.example.getfood.UserActivities.UserRestaurantActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    private FirebaseAuth mAuth;


    private FirestoreHelper fireHelper;

    private User user;
    public String type;


    private TextView RegisterLink,ForgotPasswordLink;
    private Button LoginButton;
    private EditText LoginEmailInput, LoginPasswordInput;
    private ProgressBar progress_bar;

    private UserTypeReceiveListener listener;
    final static String TAG = "LOGIN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //authHelper.onCreate();



        mAuth = FirebaseAuth.getInstance();
        fireHelper = new FirestoreHelper();

        LoginButton = (Button)findViewById(R.id.second_login_button);
        LoginButton.setOnClickListener(this);

        LoginEmailInput = (EditText)findViewById(R.id.login_email_input);
        LoginPasswordInput = (EditText)findViewById(R.id.login_password_input);


        RegisterLink = (TextView)findViewById(R.id.to_register);
        RegisterLink.setOnClickListener(this);

        ForgotPasswordLink = (TextView)findViewById(R.id.forgot_password_link);
        ForgotPasswordLink.setOnClickListener(this);


        progress_bar = (ProgressBar)findViewById(R.id.progress_bar_2);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Toast.makeText(this,"Вы успешно вошли!",Toast.LENGTH_SHORT).show();
            this.startActivity(new Intent(this, OwnerMainActivity.class));
        }
        //authHelper.onStartLogin(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.to_register:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;

            case R.id.second_login_button:
                SignInAccount();
                break;


        }
    }

    private void SignInAccount() {
        String email = LoginEmailInput.getText().toString().trim();
        String password = LoginPasswordInput.getText().toString().trim();


        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()) {

                                updateUILogin(user, LoginActivity.this);

                            }
                            else{
                                user.sendEmailVerification();
                                Toast.makeText(LoginActivity.this, "Подтвердите письмо на почте перед авторизацией. " , Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUILogin(null,null);
                    }
                }
        });
    }




    public void updateUILogin (FirebaseUser account, Activity activity) {

        if (account != null) {
            Toast.makeText(activity, "Вы успешно вошли!", Toast.LENGTH_SHORT).show();

            fireHelper.getCurrentUserType( type -> {
                Log.d(TAG,"тип пользователя извлечен:"+type);
                if(type.equals("1")){
                    Log.d(TAG,"Переход к активити согласно типу пользователя:"+type);
                    activity.startActivity(new Intent(activity, OwnerMainActivity.class));

            } else{
                    Log.d(TAG,"Переход к активити согласно типу пользователя:"+type);
                    activity.startActivity(new Intent(activity, UserRestaurantActivity.class));
                }
            });
        } else {

        }

    }
    public void signOut() {
        mAuth.signOut();
    }

}

