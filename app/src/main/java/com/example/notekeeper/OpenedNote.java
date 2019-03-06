package com.example.notekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OpenedNote extends AppCompatActivity {
    LinearLayout rootLayout;
    TextView tvTitle, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opened_note);
        rootLayout = findViewById(R.id.rootLayout);
        tvTitle = findViewById(R.id.tvTitle);
        tvDesc = findViewById(R.id.tvDesc);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String title = extras.getString("Title");
        String description = extras.getString("Description");
        int color = extras.getInt("Color", -1);
        tvTitle.setText(title);
        tvDesc.setText(description);
        rootLayout.setBackgroundColor(color);
    }
}
