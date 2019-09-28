package com.rarosa.mpandey.kuberjobs.operations;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rarosa.mpandey.kuberjobs.HttpManager;
import com.rarosa.mpandey.kuberjobs.R;
import com.rarosa.mpandey.kuberjobs.RequestPackage;
import com.rarosa.mpandey.kuberjobs.UserAdapter;
import com.rarosa.mpandey.kuberjobs.model.User;
import com.rarosa.mpandey.kuberjobs.parsers.JSONParser;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadUsersActivity extends ListActivity {

    @SuppressWarnings("unused")
    private static final String PHOTOS_BASE_URL = "http://services.hanselandportal.com/photos/";

    TextView output;
    ProgressBar pb;
    List<MyTask> tasks;
    List<User> userList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_user_list);

        output.setMovementMethod(new ScrollingMovementMethod());

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();
        startTasks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       //getMenuInflater().inflate(R.menu.main, menu);
       return true;
    }

    public void startTasks() {
        if (isOnline()) {
            requestData("http://services.hanselpetal.com/feeds/users.xml");
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }

    private void requestData(String uri) {
        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri(uri);
        p.setParam("uid", "1");
        p.setParam("name", "Rosa");

        MyTask task = new MyTask();
        task.execute(p);
    }


    protected void updateDisplay() {

        if (userList != null) {
            for (User user : userList) {
                output.append(user.getName() + "\n");
            }
        }

        UserAdapter adapter = new UserAdapter(this, R.layout.activity_read_user_list, userList);
        setListAdapter(adapter);
    }


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if ((netInfo != null) && ((NetworkInfo) netInfo).isConnectedOrConnecting()) {
           return true;
        } else {
            return false;
        }
    }

    private class MyTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected void onPreExecute() {
//          updateDisplay("Starting task");

            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(RequestPackage... params) {

            String content = HttpManager.getData(params[0]);
            userList = JSONParser.parseFeed(content);

            for(User user : userList) {
                try {
                    String imageUrl = PHOTOS_BASE_URL + user.getProfile_photo();
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    user.setProfile_photo(bitmap);
                    in.close();
                } catch (Exception e) {

                }
            }
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            userList = JSONParser.parseFeed(result);

            updateDisplay();

            tasks.remove(this);
            if(tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //updateDisplay(values[0]);
        }
    }
}