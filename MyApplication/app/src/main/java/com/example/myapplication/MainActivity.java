package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);


        Button showFirstFragment = findViewById(R.id.showTime);
        Button showSecondFragment = findViewById(R.id.showDate);
        Button showThirdFragment = findViewById(R.id.showBattery);

        showFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                String strDate = mdformat.format(calendar.getTime());
                final Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if (!(fragment instanceof FirstFragment)) {
                    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    final FirstFragment firstFragment = FirstFragment.newInstance(String.valueOf(strDate));
                    fragmentTransaction.replace(R.id.fragmentContainer, firstFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        showSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if (!(fragment instanceof SecondFragment)) {
                    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    final SecondFragment secondFragment = SecondFragment.newInstance("07-07-2022");
                    fragmentTransaction.replace(R.id.fragmentContainer, secondFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        showThirdFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);

                if (!(fragment instanceof ThirdFragment)) {
                    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    final ThirdFragment thirdFragment = ThirdFragment.newInstance("Battery Level: 100%");
                    fragmentTransaction.replace(R.id.fragmentContainer, thirdFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }
    }

