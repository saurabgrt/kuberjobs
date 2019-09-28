package com.rarosa.mpandey.kuberjobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import com.rarosa.mpandey.kuberjobs.R;
import com.rarosa.mpandey.kuberjobs.activities.LoginActivity;
import com.rarosa.mpandey.kuberjobs.operations.LoadUser;

//import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //MobileAds.initialize(this, "ca-app-pub-5011428059096183~7161330955");

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //txt=(TextView) findViewById(search_jobs);
        btn1=(Button) findViewById(R.id.button_search_jobs);

        btn2=(Button) findViewById(R.id.button_show_db);

        btn1.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
              if (myIntent != null) {
                  startActivity(myIntent);
              }
              //btn.setText("Hello Agam and Ragu");
          }
        });


        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, LoadUser.class);
                if (myIntent != null) {
                   startActivity(myIntent);
                }
                //btn.setText("Hello Agam and Ragu");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
