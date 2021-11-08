package com.example.getfood;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirestoreHelper {

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    public User user;

    public String name;
    public String type;



    public void onCreate() {
        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
        type="0";
    }

    static final String TAG = "FirestoreHelper";
    public void newUser(String userId,String type, String email, String name, String password){


        mAuth = FirebaseAuth.getInstance();




        Map<String, Object> user = new HashMap<>();
        //user.put("UID", userId);
        user.put("type", type);
        user.put("email", email);
        user.put("name", name);
        user.put("password", password);
        user.put("phone", "");

// Add a new document with a generated ID
        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }





    public void getCurrentUserData(UserDataReceiveListener listener) {
        if (user != null) {
            listener.onUserReceived(user);
        }


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        db.collection("users")
                .whereEqualTo("email", mAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                user = new User(
                                        document.get("type").toString(),
                                        document.get("name").toString(),
                                        document.get("email").toString(),
                                        document.get("password").toString());
                                Log.d(TAG,"Данные о пользователе получены"+user);
                                listener.onUserReceived(user);
                                Log.d(TAG,"Данные о пользователе прослушены"+user);


                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



    }
    public void getCurrentUserType(UserTypeReceiveListener listener){


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        db.collection("users")
                .whereEqualTo("email", mAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                type = document.get("type").toString();

                                listener.onTypeReceived(type);

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }
}
