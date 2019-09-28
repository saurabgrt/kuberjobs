package com.rarosa.mpandey.kuberjobs.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rarosa.mpandey.kuberjobs.R;

public class SearchAfterTaskActivity extends AppCompatActivity {

    private String     currency;
    private String     profession;

    Button searchButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAfterTaskDisplay();
            }
        });
    }

    public void searchAfterTaskDisplay() {
        //search(UserProfession.uid, UserProfession.min_amount, UserProfession.max_amount, UserProfession.user_choice, UserProfession.currency );
    }

    public void onSearchSuccess() {
        Toast.makeText(getBaseContext(), "Search before task display sucessful", Toast.LENGTH_LONG).show();
    }

    public void onSearchFailed() {
        Toast.makeText(getBaseContext(), "Search before task display failed", Toast.LENGTH_LONG).show();
    }
}