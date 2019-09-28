package com.rarosa.mpandey.kuberjobs.operations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rarosa.mpandey.kuberjobs.activities.BackendActivity;
import com.rarosa.mpandey.kuberjobs.model.User;

public class DeleteUser extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();

        String name = extras.getString("ADD-NAME-ID");
        String email = extras.getString("ADD-EMAIL-ID");
        String password = extras.getString("ADD-PASSWORD-ID");

        // Add only if user does not exist before
        //BackendActivity.getBackendActivity().add(name, email, password);

        User user = User.getUser();
        user = null; /* delete the object by removeíng the reference */

        Toast.makeText(getApplicationContext(), "User added to simple DB", Toast.LENGTH_SHORT).show();
    }
}