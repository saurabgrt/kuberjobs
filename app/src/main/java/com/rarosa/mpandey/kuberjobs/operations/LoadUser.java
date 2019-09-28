package com.rarosa.mpandey.kuberjobs.operations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rarosa.mpandey.kuberjobs.activities.BackendActivity;
import com.rarosa.mpandey.kuberjobs.model.User;

import java.util.HashMap;

public class LoadUser extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        String username = ((Intent) intent).getStringExtra("LOAD-USER-ID");

        HashMap<String, String> attributes = BackendActivity.getBackendActivity().load(username);

        User user = User.getUser();
        user.setName(attributes.get("name"));
        user.setEmail(attributes.get("email"));
        user.setPassword(attributes.get("password"));
        user.setPhoneNumber(attributes.get("phone"));

        Toast.makeText(getApplicationContext(), "User loaded from DB to app ", Toast.LENGTH_SHORT).show();
    }
}