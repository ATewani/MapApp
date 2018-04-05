package com.example.mapproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap2;
    static TextView cityLabel;
    static Marker marker;
    static TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cityLabel = (TextView) findViewById(R.id.citylabel);
        count = (TextView) findViewById(R.id.count);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap2 = googleMap;
        boolean success = mMap2.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));

        LatLng sydney = new LatLng(-34, 151);
        marker = mMap2.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        marker.setVisible(false);

        final Random rand = new Random();

        final HashMap<String, int[]> map = new HashMap<String, int[]>();

        int[] coords = {30, 97};
        map.put("Austin", coords);

        int[] coords1 = {-34, 151};
        map.put("Sydney", coords1);

        int[] coords2 = {42, 12};
        map.put("Rome", coords2);

        int[] coords3 = {51, 0};
        map.put("London", coords3);

        int[] coords4 = {19, 73};
        map.put("Mumbai", coords4);

        int[] coords5 = {31, 121};
        map.put("Shanghai", coords5);

        int[] coords6 = {-26, 28};
        map.put("Johannesburg", coords6);

        final Map<Integer, String> namemap = new HashMap<>();
        namemap.put(1, "Austin");
        namemap.put(2, "Sydney");
        namemap.put(3, "Rome");
        namemap.put(4, "London");
        namemap.put(5, "Mumbai");
        namemap.put(6, "Shanghai");
        namemap.put(7, "Johannesburg");

        int citynum = 1 + rand.nextInt(7);

        cityLabel.setText(namemap.get(citynum));









        //mMap2.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap2.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                marker.setPosition(latLng);
                marker.setVisible(true);

                String city = cityLabel.getText().toString();
                int[] ans = map.get(city);

                Double dist = SphericalUtil.computeDistanceBetween(latLng, new LatLng(ans[0], ans[1]));
                if (dist<1000000){
                    Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_SHORT).show();
                    int score = Integer.parseInt(count.getText().toString()) + 1;
                    count.setText(Integer.toString(score));

                } else{
                    Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_SHORT).show();
                }

                int citynum = 1 + rand.nextInt(7);
                cityLabel.setText(namemap.get(citynum));





            }
        });




    }
}
