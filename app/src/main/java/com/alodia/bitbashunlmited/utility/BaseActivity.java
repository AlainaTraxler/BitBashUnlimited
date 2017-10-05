package com.alodia.bitbashunlmited.utility;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alodia.bitbashunlmited.R;
import com.alodia.bitbashunlmited.ui.LoginActivity;
import com.alodia.bitbashunlmited.ui.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BaseActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public String TAG;
    public Context mContext;
    public FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mAuth = FirebaseAuth.getInstance();
        TAG = ">>>>>";
        mContext = this;
        mUser = mAuth.getCurrentUser();

        if (mUser != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
            if(mContext.getClass().getSimpleName().equals("LoginActivity")){
                startActivity(new Intent(mContext, MainActivity.class));
            }
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
            if(!mContext.getClass().getSimpleName().equals("LoginActivity")){
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mUser != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
