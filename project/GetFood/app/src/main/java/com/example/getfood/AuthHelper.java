package com.example.getfood;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthHelper {

    public FirebaseAuth mAuth;

    private FirestoreHelper fireHelper;

    static final String TAG = "AuthHelper";


    String userId;

    public void onCreate() {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        fireHelper = new FirestoreHelper();
    }

    public void onStart(Activity activity) {
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser, activity);
    }
    public void onStartLogin(Activity activity) {
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUILogin(currentUser, activity);
    }

    public void updateUI (FirebaseUser account, Activity activity){

        if(account != null){
            Toast.makeText(activity,"Now Verify Your Email",Toast.LENGTH_SHORT).show();
            signOut();
            activity.startActivity(new Intent(activity, LoginActivity.class));

        } else {

        }

    }

    public void updateUILogin(FirebaseUser account, Activity activity){

        if(account != null){
            Toast.makeText(activity,"U Signed In",Toast.LENGTH_SHORT).show();
            activity.startActivity(new Intent(activity, UserMainActivity.class));

        } else {

        }

    }

    public void signOut() {
        mAuth.signOut();
        updateUI(null, null);
    }


    public void register(String type, String email, String name,String password, Activity activity) {

        fireHelper.onCreate();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userId = mAuth.getCurrentUser().getUid();
                            fireHelper.newUser(userId,type,email,name,password);
                            //user.sendEmailVerification();
                            updateUI(user, activity);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            updateUI(null, null);
                        }

                    }
                });

    }



    public void loginAccount (String email, String password, Activity activity) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUILogin(user,activity);

                            //if(user.isEmailVerified()){
                              //  updateUILogin(user,activity);
                            //}else{
                              //  user.sendEmailVerification();
                            //}


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            updateUILogin(null, null);
                        }
                    }
                });
    }


}
