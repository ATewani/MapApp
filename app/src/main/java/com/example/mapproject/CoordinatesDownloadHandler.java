package com.example.mapproject;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tewanir on 4/4/18.
 */

public class CoordinatesDownloadHandler extends AsyncTask<String, Void, String> {
    public WeatherDownloadHandler task2 = new WeatherDownloadHandler();

    @Override
    protected String doInBackground(String... urls) {

        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + urls[0] + "&key=AIzaSyAUlFGchgutWsAPpQuZe4j14hwOep8s0wY");
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


        try {
            JSONObject jsonobj = new JSONObject(result);

            JSONObject res = ((JSONArray) jsonobj.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
            String lat = res.get("lat").toString();
            String lon = res.get("lng").toString();

            Log.e("ANJALI", "about to execute task 2");
            task2.execute(lat, lon);
            Log.e("ANJALI", "started to execute task 2");

            LatLng latlon = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));

            MapsActivity.marker.setPosition(latlon);
            MapsActivity.mMap.animateCamera(CameraUpdateFactory.newLatLng(latlon));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}