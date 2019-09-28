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

import com.rarosa.mpandey.kuberjobs.R;
import com.rarosa.mpandey.kuberjobs.operations.LoadUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "myInfo";
    private static final int REQUEST_LOGIN = 0;
    private static final int REQUEST_SIGNUP = 0;

    private EditText loginText;
    private EditText passwordText;
    private Button   loginButton;
    private TextView signupLink;

    private String userLoginType;

    private String login;
    private String password;

    boolean  isLoginSuccesful = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        //ButterKnife.bind(this);

        loginText    = (EditText) findViewById(R.id.input_login);
        passwordText = (EditText) findViewById(R.id.input_password);
        loginButton  = (Button)   findViewById(R.id.btn_login);
        signupLink   = (TextView) findViewById(R.id.link_signup);

        login    = loginText.getText().toString();
        password = passwordText.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Log.i(TAG, "Signup start");
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
                //startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate(login, password)) {
            onLoginFailed();
            return;
        }

        userLoginType = classifyUserLogin(login);

        switch (userLoginType){
            case "LoginId":
                BackendActivity.getBackendActivity().LoginViaLoginId(login, password);
                break;
            case "Phone":
                BackendActivity.getBackendActivity().LoginViaTelephone(login, password);
                break;
            default:
                isLoginSuccesful = false;
                break;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if (BackendActivity.getBackendActivity().isLoginSuccesful() == true) {
                            onLoginSuccess();
                        }
                        else {
                            onLoginFailed();
                        }

                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                isLoginSuccesful = true;

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                //this.finish();
            }
            else
            {
                isLoginSuccesful = false;
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);

        Intent intent = new Intent(this, LoadUser.class);
        Bundle extras = new Bundle();
        extras.putString("LOAD-USER-ID","idText");
        intent.putExtras(extras);
        startActivity(intent);

        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public String classifyUserLogin(String username) {
        String loginType = null;

        if (!android.util.Patterns.PHONE.matcher(username).matches()) {
            loginType = "Phone";
        }
        else
        {
            loginType = "LoginId";
        }

        return loginType;
    }

    /* Function does a basic plausbility check of login and password fields */
    public boolean validate(String login, String password) {
        boolean valid = true;

        if (login.isEmpty() || !android.util.Patterns.PHONE.matcher(login).matches()) {
            loginText.setError("enter a valid login address");
            valid = false;
        } else {
            loginText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}