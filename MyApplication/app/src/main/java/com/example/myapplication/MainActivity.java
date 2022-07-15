package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity  {

    int level=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageButton addButton = (ImageButton) findViewById(R.id.add_button);
        ImageButton minusButton = (ImageButton) findViewById(R.id.minus_button);
        ImageButton offButton = (ImageButton) findViewById(R.id.off_button);

        ImageView images = (ImageView) findViewById(R.id.wifi_image);


        if (savedInstanceState != null) {
            level = savedInstanceState.getInt("level", 0);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
               level+=1;
               if(level>3){
                   level=3;
               }
               images.setImageLevel(level);
            }
        });


        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level-=1;
                if(level<0){
                    level=0;
                }
                images.setImageLevel(level);

            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level=0;
                images.setImageLevel(level);
            }
        });

    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("level",level);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        level = savedInstanceState.getInt("level", 1);
        ImageView images = (ImageView) findViewById(R.id.wifi_image);
        images.setImageLevel(level);
        super.onRestoreInstanceState(savedInstanceState);

    }
}

