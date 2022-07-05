package com.rajasoftwarelabs.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();


        String name = intent.getStringExtra(MainActivity.NAME_KEY);
        TextView nameTextView = findViewById(R.id.name_text_view);
        nameTextView.setText(getString(R.string.name_activity_text, name));
    }
}
