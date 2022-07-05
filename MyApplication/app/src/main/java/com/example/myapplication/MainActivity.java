package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActivityIntent();
    }

    private void setupActivityIntent() {
        final Button textViewButton = findViewById(R.id.test_view_button);
        final Button editViewButton = findViewById(R.id.edit_test_button);
        final Button imageViewButton = findViewById(R.id.image_view_button);

        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
                Intent testViewIntent = new Intent(MainActivity.this, TextActivity.class);
                startActivity(testViewIntent);
            }
        });

        editViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
                Intent editViewIntent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(editViewIntent);
            }
        });

        imageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
                Intent imageViewIntent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(imageViewIntent);
            }
        });
    }
}