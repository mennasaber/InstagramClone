package com.example.instagramclone.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.Home.HomeActivity;
import com.example.instagramclone.Models.AccountSetting;
import com.example.instagramclone.Models.Users;
import com.example.instagramclone.R;
import com.example.instagramclone.Utils.FirebaseMethods;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    ProgressBar mProgressBar;
    TextView mTextView, mHasAccount;
    EditText mEmail, mPassword, mUsername;
    ConstraintLayout mConstraintLayout;
    FirebaseMethods firebaseMethods;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initWidget();
    }

    private void initWidget() {
        firebaseMethods = new FirebaseMethods(RegisterActivity.this);
        Button button = findViewById(R.id.loginButton);
        mEmail = findViewById(R.id.inputAddress);
        mPassword = findViewById(R.id.inputPassword);
        mUsername = findViewById(R.id.inputName);
        mProgressBar = findViewById(R.id.registerProgress);
        mTextView = findViewById(R.id.pleaseWait);
        mConstraintLayout = findViewById(R.id.constraintLayout);
        mHasAccount = findViewById(R.id.hasAccount);

        mProgressBar.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String userName = mUsername.getText().toString();
                if (isValid(email, password, userName)) {
                    mConstraintLayout.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.VISIBLE);
                    mTextView.setVisibility(View.VISIBLE);
                    signUpWithEmailAndPassword(email, password, userName);
                }
            }
        });
        mHasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean isValid(String email, String password, String userName) {
        return !email.trim().equals("") && !password.trim().equals("") && !userName.trim().equals("");
    }

    public void signUpWithEmailAndPassword(final String email, String password, final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendVerificationMessage();
                            createAccountInFireStore(mAuth.getCurrentUser().getUid(), email, username);
                            Toast.makeText(RegisterActivity.this, "send message", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                mPassword.setError("Password should be at least 6 characters");
                                mPassword.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                mEmail.setError("Invalid Email");
                                mEmail.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                mEmail.setError("User is exist");
                                mEmail.requestFocus();
                            } catch (Exception e) {
                                Toast.makeText(RegisterActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                            }
                            mConstraintLayout.setVisibility(View.VISIBLE);
                            mProgressBar.setVisibility(View.GONE);
                            mTextView.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void sendVerificationMessage() {
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Toast.makeText(RegisterActivity.this, "send email successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(RegisterActivity.this, "send email faild", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAccountInFireStore(String uid, String email, String username) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(uid).set(new Users(email, uid, username));
        db.collection("AccountSetting").document(uid).set(new AccountSetting(username, "", "", "", 0, 0, 0));
    }
}