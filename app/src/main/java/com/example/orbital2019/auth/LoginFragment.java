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
import android.widget.TextView;
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
public class LoginFragment extends Fragment {

    private EditText loginEmail, loginPassword;
    private TextView regNew;
    private Button buttonLogin;
    private ProgressBar progressBar;
    private BottomNavigationView btmNavView;

    private FirebaseAuth mAuth;

    public UserDetails user;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Login");
        mAuth = FirebaseAuth.getInstance();

        btmNavView = getActivity().findViewById(R.id.main_nav);
        loginEmail = getActivity().findViewById(R.id.loginEmail);
        loginPassword = getActivity().findViewById(R.id.loginPassword);

        buttonLogin = getActivity().findViewById(R.id.loginBtn);
        progressBar = getActivity().findViewById(R.id.progressBar);
        regNew = getActivity().findViewById(R.id.regNew);
        //forgotPw = getActivity().findViewById(R.id.tvforgotpw);

        btmNavView.setVisibility(View.INVISIBLE);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });

        regNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new RegisterFragment());
                ft.commit();
            }
        });

        /*
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new ResetPasswordFragment());
                ft.commit();
            }
        });
*/
    }

    private void loginUserAccount() {
        progressBar.setVisibility(View.VISIBLE);

        final String email, password;
        email = loginEmail.getText().toString();
        password = loginPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity().getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity().getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity().getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            btmNavView.setVisibility(View.VISIBLE);

                            user = new UserDetails(email);

                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.main_frame, new HomeFragment());
                            ft.commit();

                        }
                        else {
                            Toast.makeText(getActivity().getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
