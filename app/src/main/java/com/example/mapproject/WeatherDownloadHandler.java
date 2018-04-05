package com.example.mapproject;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tewanir on 4/4/18.
 */

public class WeatherDownloadHandler extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        try {
            URL url = new URL("https://api.darksky.net/forecast/dbe14e9739345a778530ace9085eb192/" + urls[0] + "," + urls[1]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = in.read();
            String result = "";

            while (data != -1){
                char character = (char) data;
                result += character;
                data = reader.read();
            }

            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        if (result.equals("invalid entry")){
            MapsActivity.temp.setText("ER");
            return;
        }



        try {
            JSONObject jsonobj = new JSONObject(result);
            JSONObject weatherdata = new JSONObject(jsonobj.getString("currently"));

            int temp = (int) Double.parseDouble(weatherdata.getString("temperature"));

            MapsActivity.temp.setText(String.valueOf(temp + "°F"));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}