package com.example.orbital2019.auth;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.orbital2019.AboutDetails;
import com.example.orbital2019.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserDetails implements Serializable {

    // Database Keys
    public static final String userDetailsKey = "UserDetails";
    public static final String nameKey = "Name";
    public static final String petNameKey = "PetName";
    public static final String petBreedKey = "PetBreed";
    public static final String petGenderKey = "PetGender";

    public String email;
    private static String name;
    private static String petName;
    private static String petBreed;
    private static String petGender;


    public UserDetails(String email, String name, String petName, String petBreed, String petGender){

        this.email = email;
        this.name = name;
        this.petName = petName;
        this.petBreed = petBreed;
        this.petGender = petGender;

    }

    public UserDetails(String email) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(userDetailsKey).document(email);

        Log.d("Userdetails", "created");
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            UserDetails.this.name = documentSnapshot.getString(nameKey);
                            UserDetails.this.petName = documentSnapshot.getString(petNameKey);
                            UserDetails.this.petBreed = documentSnapshot.getString(petBreedKey);

                        } else {
                            //Toast.makeText(this, "Document does not exist", Toast.LENGTH_SHORT).show();
                            Log.d("Userdetails", "document does not exist");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Userdetails", "onfailure");
                    }
                });
    }

    public static String getName() {
        return name;
    }

    public static String getPetName() {
        return petName;
    }

    public static String getPetBreed() {
        return petBreed;
    }

    public static String getPetGender() {
        return petGender;
    }


    /*
    // call to update entry In database.
    public void updateEntry () {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> data = new HashMap<>();

        data.put(nameKey, name);
        data.put(petNameKey, petName);
        data.put(petBreedKey, petBreed);

        db.collection(userDetailsKey)
                .add(data);
        //db.collection(userDetailsKey).document(matriculationNumber).update(data);
    }
*/

    // call to create entry In database.
    public void createEntry () {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,String> data = new HashMap<>();

        data.put(nameKey, name);
        data.put(petNameKey, petName);
        data.put(petBreedKey, petBreed);
        data.put(petGenderKey, petGender);

        db.collection(userDetailsKey).document(email).set(data);

        //db.collection(userDetailsKey).add(data);

    }

    /*
    public void deleteEntry() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //db.collection(userDetailsKey).document(matriculationNumber).delete();
    }
*/


}

