package com.rarosa.mpandey.kuberjobs.operations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rarosa.mpandey.kuberjobs.R;

public class UpdateUser extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        String userName = ((Intent) intent).getStringExtra("NAME-ID");
        //User user = BackendActivity.getBackendActivity().loadUserDetails(userName);

        setContentView(R.layout.show_user);
        //setTitle(user.getName());
        setTitle(userName);

        TextView userNameView     = (TextView) findViewById(R.id.user_db_name);
        TextView userEmailView    = (TextView) findViewById(R.id.user_db_email);
        TextView userPasswordView = (TextView) findViewById(R.id.user_db_password);

        //userNameView.setText(user.getName());
        userNameView.setText("agamya");
        //userEmailView.setText(user.getEmail());
        //userPasswordView.setText(user.getPassword());
    }
}