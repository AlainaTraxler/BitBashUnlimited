package com.alodia.bitbashunlmited.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alodia.bitbashunlmited.R;
import com.alodia.bitbashunlmited.models.Player;
import com.alodia.bitbashunlmited.utility.BaseActivity;
import com.alodia.bitbashunlmited.utility.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity implements View.OnLongClickListener{
    @BindView(R.id.textView_Handle) TextView mTextView_Handle;
    @BindView(R.id.editText_Handle) EditText mEditText_Handle;

    DatabaseReference mDbRef;
    Player mPlayer;
    String mPlayerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        mDbRef = FirebaseDatabase.getInstance().getReference();
        mPlayerId = mAuth.getCurrentUser().getUid();
        
        mDbRef.child(Constants.DB_PLAYERS).child(mPlayerId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPlayer = dataSnapshot.getValue(Player.class);
                setFields();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mTextView_Handle.setOnLongClickListener(this);
        mEditText_Handle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    String newHandle = mEditText_Handle.getText().toString();

                    mDbRef.child(Constants.DB_PLAYERS).child(mPlayerId).child("handle").setValue(newHandle);
                    mPlayer.setHandle(newHandle);
                    mTextView_Handle.setText(newHandle);
                    mEditText_Handle.setVisibility(View.INVISIBLE);
                    mTextView_Handle.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, "Handle changed", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onLongClick(View view) {
        
        if(view == mTextView_Handle){
            mTextView_Handle.setVisibility(View.INVISIBLE);
            mEditText_Handle.setVisibility(View.VISIBLE);
        }
        return false;
    }

    private void setFields(){
        mTextView_Handle.setText(mPlayer.getHandle());
        mEditText_Handle.setText(mPlayer.getHandle());
    }
}
