package com.alodia.bitbashunlmited.utility;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
        mContext = this;
        TAG = mContext.getClass().getSimpleName();
        mUser = mAuth.getCurrentUser();

        if (mUser != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
            if(TAG.equals("LoginActivity")){
                startActivity(new Intent(mContext, MainActivity.class));
            }
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
            if(!TAG.equals("LoginActivity")){
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = mAuth.getCurrentUser();
                if (mUser != null) {
                    // User is signed in

                    if(TAG.equals("LoginActivity")){
                        startActivity(new Intent(mContext, MainActivity.class));
                    }
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
                } else {
                    // User is signed out
                    if(!TAG.equals("LoginActivity")){
                        startActivity(new Intent(mContext, LoginActivity.class));
                    }
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    public void signOut(){
        //TODO  Add protectiosn to erase mVars with user-oriented information
        mAuth.signOut();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_signOut:
                signOut();
                break;
            default:
                break;
        }

        return true;
    }
}
