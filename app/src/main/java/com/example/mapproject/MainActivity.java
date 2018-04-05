package com.example.mapproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void AtoC(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }

    public void CtoA(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
}
