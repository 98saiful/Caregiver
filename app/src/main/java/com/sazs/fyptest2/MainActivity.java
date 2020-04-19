package com.sazs.fyptest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.sazs.fyptest2.session.SessionManager;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manu_main, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.item_search);
        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
//            case R.id.action_profile:
//                Intent intent = new Intent(this, ProfileActivity.class);
//                startActivity(intent);
//                return true;
//            case R.id.action_order:
//                Intent intent2 = new Intent(this, OrderStatusActivity.class);
//                startActivity(intent2);
//                return true;
            case R.id.logout:
                sessionManager.logout();
        }

        return super.onOptionsItemSelected(item);
    }
}
