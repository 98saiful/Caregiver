package com.sazs.fyptest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getInfo();
    }

    private void getInfo(){
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        Integer age = intent.getExtras().getInt("age");
        String gender = intent.getExtras().getString("gender");
        Integer height = intent.getExtras().getInt("height");
        Integer weight = intent.getExtras().getInt("weight");
        String location = intent.getExtras().getString("location");
        String info = intent.getExtras().getString("info");
        String note = intent.getExtras().getString("note");

        TextView tvName = findViewById(R.id.name);
        TextView tvAge = findViewById(R.id.age);
        TextView tvGender = findViewById(R.id.gender);
        TextView tvHeight = findViewById(R.id.height);
        TextView tvWeight = findViewById(R.id.weight);
        TextView tvLocation = findViewById(R.id.location);
        TextView tvInfo = findViewById(R.id.info);
        TextView tvNote = findViewById(R.id.note);

        tvName.setText(name);
        tvAge.setText((String.valueOf(age)));
        tvGender.setText(gender);
        tvHeight.setText((String.valueOf(height)));
        tvWeight.setText((String.valueOf(weight)));
        tvLocation.setText(location);
        tvInfo.setText((info));
        tvNote.setText(note);

    }
}
