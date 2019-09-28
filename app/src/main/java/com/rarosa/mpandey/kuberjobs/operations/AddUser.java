package com.rarosa.mpandey.kuberjobs.operations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rarosa.mpandey.kuberjobs.activities.BackendActivity;
import com.rarosa.mpandey.kuberjobs.model.User;

public class AddUser extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();

        String name  = extras.getString("ADD-NAME-ID");
        String email = extras.getString("ADD-EMAIL-ID");
        String password = extras.getString("ADD-PASSWORD-ID");
        String phone = extras.getString("ADD-PHONE-ID");

        // Add only if user does not exist before
        BackendActivity.getBackendActivity().add(name, email, phone, password);

        /* On succesful addition in database */
        User user = User.getUser();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setPassword(password);

        Toast.makeText(getApplicationContext(), "User added to simple DB", Toast.LENGTH_SHORT).show();
    }
}