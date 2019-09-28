package com.rarosa.mpandey.kuberjobs.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rarosa.mpandey.kuberjobs.HttpManager;
import com.rarosa.mpandey.kuberjobs.R;
import com.rarosa.mpandey.kuberjobs.RequestPackage;
import com.rarosa.mpandey.kuberjobs.operations.AddUser;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "myInfo";
    private static final int REQUEST_SIGNUP = 1;

    EditText nameText;
    EditText emailText;
    EditText passwordText;
    EditText phoneText;

    String name;
    String email;
    String password;
    String phone;

    Button   signupButton;
    TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        Log.i(TAG, "Signup entered");

        nameText     = (EditText) findViewById(R.id.input_name);
        emailText    = (EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        phoneText    = (EditText) findViewById(R.id.input_phone);

        signupButton = (Button) findViewById(R.id.btn_signup);
        loginLink    = (TextView) findViewById(R.id.link_login);

        name         = "testName"; //nameText.getText().toString();
        email        = "testEmail"; //emailText.getText().toString();
        password     = "testPassword"; //passwordText.getText().toString();
        phone        = "testPhone"; //phoneText.getText().toString();


        Log.i(TAG, "Try sending the data");

        // Add only if user does not exist before
        //BackendActivity.getBackendActivity().add(name, email, phone, password);

        RequestPackage req = new RequestPackage();
        req.setUri("https://4ndmqiktoe.execute-api.ap-south-1.amazonaws.com/v1/testresource/{proxy+}/");
        req.setMethod("POST");
        HttpManager.getData(req);

        //Log.i(TAG, "Data sent");

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.i(TAG, "Signup");

        //signupButton.setEnabled(false);

        /*final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();*/

        /*String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String phone = phoneText.getText().toString();*/

        //if (!validate(name, email, password, phone)) {
            //onSignupFailed();
            //return;
        //}
        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        //onSignupSuccess();
                        // onSignupFailed();
                        //progressDialog.dismiss();
                    }
                }, 30000);
    }


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        // start add user activity to add user in db
        Intent intent = new Intent(this, AddUser.class);
        Bundle extras = new Bundle();
        extras.putString("ADD-NAME-ID","nameText");
        extras.putString("ADD-EMAIL-ID","emailText");
        extras.putString("ADD-PASSWORD-ID","passwordText");
        extras.putString("ADD-PHONE-ID","phoneText");
        intent.putExtras(extras);
        startActivity(intent);

        setResult(RESULT_OK, null);
        //finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate(String name, String email, String password, String phone) {
        boolean valid = true;

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (phone.isEmpty() || !android.util.Patterns.PHONE.matcher(phone).matches()) {
            phoneText.setError("enter a valid phone");
            valid = false;
        } else {
            phoneText.setError(null);
        }

        return valid;
    }
}