package com.rarosa.mpandey.kuberjobs.parsers;

import com.rarosa.mpandey.kuberjobs.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public static List<User> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<User> userList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                User user = new User();

                user.setUid(obj.getInt("userId"));
                user.setName(obj.getString("name"));

                userList.add(user);
            }

            return userList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
