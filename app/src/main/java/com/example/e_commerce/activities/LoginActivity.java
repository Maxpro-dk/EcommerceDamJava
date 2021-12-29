package com.example.e_commerce.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private TextView textView;
    private TextInputEditText textInputemail;
    private TextInputEditText textInputpassword;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private ProgressDialog dialog;
    private TextView textViewforgetkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                //  verification();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
        textViewforgetkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void init() {
        login = findViewById(R.id.login);
        textView = findViewById(R.id.no_account);
        textInputemail = findViewById(R.id.email);
        textInputpassword = findViewById(R.id.password);
        textViewforgetkey = findViewById(R.id.password_forgot);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please waitting");
    }

    private String email, password;

    public void verification() {
        email = textInputemail.getText().toString().trim();
        password = textInputpassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            textInputemail.setError("Email is Required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputpassword.setError("Invalide email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            textInputpassword.setError("Password must be altleast 8 characters ");
            return;
        }
        if (password.length() < 8) {
            textInputpassword.setError("Password must be altleast 8 characters ");
            return;
        }

        Usersignup();
    }

    public void Usersignup() {
        dialog.setMessage("Creating Account ...");
        dialog.show();
        auth.signInWithEmailAndPassword(email, HashPassword(password)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }

    public static String HashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}