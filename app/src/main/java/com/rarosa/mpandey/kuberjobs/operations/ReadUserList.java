package com.rarosa.mpandey.kuberjobs.operations;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rarosa.mpandey.kuberjobs.R;
import com.rarosa.mpandey.kuberjobs.activities.BackendActivity;

public class ReadUserList extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("User Lists");

        setListAdapter(new ArrayAdapter<String>(this, R.layout.read_users, BackendActivity.getBackendActivity().getUserList()));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
             public void onItemClick(AdapterView< ? > parent, View view, int position, long id) {
                //Object listItem = list.getItemAtPosition(position);
                Intent intent = new Intent(ReadUserList.this, ShowUser.class);
                intent.putExtra("NAME-ID", ((TextView) view).getText());
                startActivity(intent);
            }
        });
    }
}