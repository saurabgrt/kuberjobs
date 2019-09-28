package com.rarosa.mpandey.kuberjobs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rarosa.mpandey.kuberjobs.model.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    @SuppressWarnings("unused")
    private List<User> userList;

    public UserAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.userList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_user_db, parent, false);

        //Display user name in the Textview Widget
        User user = userList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(user.getName());

        //Display user photo in the Imageview Widget
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        image.setImageBitmap(user.getProfile_photo());

        return view;
    }
}

