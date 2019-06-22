package com.example.orbital2019;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.orbital2019.auth.UserDetails;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutDetails extends Fragment {

    FirebaseFirestore db;

    private TextView petNameTextView, nameTextView, petBreedTextView, petGenderTextView;

    public AboutDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_details, container, false);

        petNameTextView = view.findViewById(R.id.petNameTextView);
        nameTextView = view.findViewById(R.id.nameTextView);
        petBreedTextView = view.findViewById(R.id.breedTextView);
        petGenderTextView = view.findViewById(R.id.genderTextView);

        String petName = UserDetails.getPetName();
        String name = UserDetails.getName();
        String petBreed = UserDetails.getPetBreed();
        String petGender = UserDetails.getPetGender();

        petNameTextView.setText(petName);
        nameTextView.setText(name);
        petBreedTextView.setText(petBreed);
        petGenderTextView.setText(petGender);

        // Inflate the layout for this fragment
        return view;
    }

}
