package com.alodia.bitbashunlmited.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alodia.bitbashunlmited.R;
import com.alodia.bitbashunlmited.utility.BaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.editText_Email) EditText mEditText_Email;
    @BindView(R.id.editText_Password) EditText mEditText_Password;
    @BindView(R.id.button_LogIn) Button mButton_LogIn;
    @BindView(R.id.button_SignUp) Button mButton_SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mButton_LogIn.setOnClickListener(this);
        mButton_SignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = mEditText_Email.getText().toString();
        String password = mEditText_Password.getText().toString();

        if(email.length() != 0 && password.length() != 0){
            if(view == mButton_LogIn){
                signInWithEmailAndPassword(email, password);
            }else if(view == mButton_SignUp){
                createUserWithEmailAndPassword(email, password);
            }
        }else{
            Toast.makeText(mContext, "Please enter an email and/or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void createUserWithEmailAndPassword(String _email, String _password){
        mAuth.createUserWithEmailAndPassword(_email, _password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Account creation failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void signInWithEmailAndPassword(String _email, String _password){
        mAuth.signInWithEmailAndPassword(_email, _password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Sign in failed",
                                    Toast.LENGTH_SHORT).show();
                        }else {

                        }

                        // ...
                    }
                });
    }
}
