package com.example.instagramclone.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.Home.HomeActivity;
import com.example.instagramclone.R;
import com.example.instagramclone.Utils.FirebaseMethods;
import com.example.instagramclone.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView pleaseWaitTextView;
    ConstraintLayout constraintLayout;
    FirebaseMethods firebaseMethods;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidget();
    }

    private void initWidget() {
        firebaseMethods = new FirebaseMethods(LoginActivity.this);
        Button button = findViewById(R.id.loginButton);
        final EditText emailEditText = findViewById(R.id.inputAddress);
        final EditText passwordEditText = findViewById(R.id.inputPassword);
        progressBar = findViewById(R.id.loginProgress);
        pleaseWaitTextView = findViewById(R.id.pleaseWait);
        constraintLayout = findViewById(R.id.constraintLayout);
        TextView noAccount = findViewById(R.id.noAccount);

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.GONE);
        pleaseWaitTextView.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (!isStringNull(email) && !isStringNull(password)) {
                    constraintLayout.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    pleaseWaitTextView.setVisibility(View.VISIBLE);
                    signInWithEmailAndPassword(email, password);
                }
            }
        });

    }

    private boolean isStringNull(String text) {
        return text.trim().equals("");
    }

    public void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (mAuth.getCurrentUser().isEmailVerified()) {
                                Toast.makeText(LoginActivity.this, "successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Email not verified check your inbox", Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                                constraintLayout.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                pleaseWaitTextView.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}