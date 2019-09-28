package com.rarosa.mpandey.kuberjobs;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {

//   public static String getData(RequestPackage p) {
//       BufferedReader reader = null;
//       String uri = p.getUri();
//
//       if (p.getMethod().equals("GET")) {
//           uri += "?" + p.getEncodedParams();
//       }
//
//       try {
//           URL url = new URL(uri);
//           HttpURLConnection con = (HttpURLConnection)url.openConnection();
//           con.setRequestMethod(p.getMethod());
//
//           JSONObject json = new JSONObject(p.getParams());
//           String params = "params=" + json.toString();
//
//           if (p.getMethod().equals("POST")) {
//               Log.i("myInfo", "Try posting");
//               con.setDoOutput(true);
//               con.setRequestProperty("Content-Type", "application/json");
//               OutputStream os = con.getOutputStream();
//               OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
//               //writer.write(p.getEncodedParams());
//               String body = "{\"uid\":256,\"name\":\"new\"}";
//               writer.write(body);
//               writer.flush();
//               writer.close();
//               os.close();
//               con.connect();
//           }
//
//           StringBuilder sb = new StringBuilder();
//           reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//           String line;
//           while ((line = reader.readLine()) != null) {
//              sb.append(line + "\n");
//           }
//           Log.i("myInfo", line);
//
//           //return sb.toString();
//           //read the inputstream and print it
//           String result;
//           BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
//           ByteArrayOutputStream buf = new ByteArrayOutputStream();
//           int result2 = bis.read();
//           while(result2 != -1) {
//               buf.write((byte) result2);
//               result2 = bis.read();
//           }
//           result = buf.toString();
//           System.out.println(result);
//           Log.i("myInfo", result);
//
//           return null;
//       } catch (Exception e) {
//           e.printStackTrace();
//           return null;
//       } finally {
//           if(reader != null) {
//               try {
//                   reader.close();
//               } catch (IOException e) {
//                   e.printStackTrace();
//                   return null;
//               }
//           }
//       }
//   }
}
