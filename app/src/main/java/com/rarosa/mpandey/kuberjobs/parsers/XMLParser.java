package com.rarosa.mpandey.kuberjobs.parsers;

import com.rarosa.mpandey.kuberjobs.model.User;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public static List<User> parseFeed(String content) {

        try {
            boolean inDataItemFlag = false;
            String currentTagName = "";
            User user = null;
            List<User> userList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if (currentTagName.equals("user")) {
                            inDataItemFlag = true;
                            user = new User();
                            userList.add(user);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("user")) {
                            inDataItemFlag = false;
                        }
                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        if (inDataItemFlag && (user != null)) {
                            switch (currentTagName) {
                                case "userId":
                                    user.setUid(Integer.parseInt(parser.getText()));
                                    break;
                                case "name":
                                    user.setName(parser.getText());
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                }

                eventType = parser.next();
            }

            return userList;
        } catch(Exception e) {
          e.printStackTrace();
          return null;
        }
    }
}