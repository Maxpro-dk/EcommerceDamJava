package com.example.e_commerce.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce.R;
import com.example.e_commerce.entities.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.MessageDigest;

public class RegisterActivity extends AppCompatActivity {

    private TextView textAccount;
    private TextInputEditText Fullname;
    private TextInputEditText Email;
    private TextInputEditText Password;
    private TextInputEditText ConfirmePassword;
    private ProgressDialog progressDialog;
    private Button RegisterButton;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        RegisterButton = findViewById(R.id.inscription_button);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Verify();
            }
        });
    }

    public void init() {
        Fullname = findViewById(R.id.full_name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        ConfirmePassword = findViewById(R.id.confirme_password);
        RegisterButton = findViewById(R.id.inscription_button);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please waitting");
    }

    private String fullname, email, password, password_confirm;

    public void Verify() {
        fullname = Fullname.getText().toString().trim();
        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();
        password_confirm = ConfirmePassword.getText().toString().trim();

        if (TextUtils.isEmpty(fullname)) {
            Fullname.setError("Full name is required");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Email.setError("Email is required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Invalide email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Password.setError("Password must be altleast 8 characters ");
            return;
        }
        if (password.length() < 8) {
            Password.setError("Password must be altleast 8 characters ");
            return;
        }
        if (!password.equals(password_confirm)) {
            ConfirmePassword.setError("Password doesn't match");
            return;
        }

        Users users = new Users();
        users.setFull_name(fullname);
        users.setEmail(email);
        users.setPassword(password);
        CreatAccount(users);
    }

    public void CreatAccount(Users users) {
        progressDialog.setMessage("Creating Account ...");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(users.getEmail(), HashPassword(users.getPassword())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser fuser = auth.getCurrentUser();
                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Verification email has been send", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "Email not send" + e.getMessage());
                        }
                    });
                    Toast.makeText(RegisterActivity.this, "User create successful", Toast.LENGTH_SHORT).show();
                    DocumentReference documentReference = db.collection("Users").document(fuser.getUid());
                    Users users1 = new Users(fuser.getUid(), users.getFull_name(), users.getEmail(), users.getAddress(), HashPassword(users.getPassword()), users.getSexe(), users.getCity(), users.getBirth_day(), users.getUser_image(), users.isStatus());
                    documentReference.set(users1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "user profil created successul", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed .check log", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
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