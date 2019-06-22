package com.example.orbital2019.auth;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.orbital2019.HomeFragment;
import com.example.orbital2019.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private EditText regEmail, regPassword, regName, regPetName, regPetBreed;
    private Spinner regPetGender;
    private Button regBtn;
    private ProgressBar progressBar;
    private BottomNavigationView btmNavView;

    private FirebaseAuth mAuth;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Register");
        mAuth = FirebaseAuth.getInstance();

        btmNavView = getActivity().findViewById(R.id.main_nav);
        //initialise components and look for them according to their IDs
        regEmail = getActivity().findViewById(R.id.regEmail);
        regPassword = getActivity().findViewById(R.id.regPassword);
        progressBar = getActivity().findViewById(R.id.regProgressBar);
        regName = getActivity().findViewById(R.id.regName);
        regPetName = getActivity().findViewById(R.id.regPetName);
        regPetBreed = getActivity().findViewById(R.id.regPetBreed);
        regPetGender = getActivity().findViewById(R.id.regPetGender);

        regBtn = getActivity().findViewById(R.id.regBtn);

        btmNavView.setVisibility(View.INVISIBLE);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getApplicationContext(), "button pressed!", Toast.LENGTH_LONG).show();
                registerNewUser();
            }
        });

    }

    private void registerNewUser() {
        progressBar.setVisibility(View.VISIBLE);
        //get the actual String or text that the user type
        final String email, password, name, petName, petBreed, petGender;
        email = regEmail.getText().toString();
        password = regPassword.getText().toString();
        name = regName.getText().toString();
        petName = regPetName.getText().toString();
        petBreed = regPetBreed.getText().toString();
        petGender = regPetGender.getSelectedItem().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(name) || TextUtils.isEmpty(petName) ||
                TextUtils.isEmpty(petBreed) || TextUtils.isEmpty(petGender)) {
            Toast.makeText(getActivity().getApplicationContext(), "Please enter all fields", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity().getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);


                            // Create user details if successful.
                            UserDetails currentUser = new UserDetails(email, name, petName, petBreed, petGender);
                            currentUser.createEntry();

                            btmNavView.setVisibility(View.VISIBLE);
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.main_frame, new HomeFragment());
                            ft.commit();
                        }
                        else {

                            Log.d("testing ", "onComplete: " + task.toString());
                            Toast.makeText(getActivity().getApplicationContext(), "Registration failed!" + task.getException().toString(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

}
