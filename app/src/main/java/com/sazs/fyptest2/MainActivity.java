package com.sazs.fyptest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sazs.fyptest2.adapter.OrderAdapter;
import com.sazs.fyptest2.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_GUIDE = "http://lrgs.ftsm.ukm.my/users/a166118/FYP/orderlist_cg.php";

    List<Order> orderList;
    SessionManager sessionManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        recyclerView = findViewById(R.id.order_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderList = new ArrayList<>();

        loadOrder();

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
            case R.id.action_profile:
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);
                return true;
//            case R.id.action_order:
//                Intent intent2 = new Intent(this, OrderDetail.class);
//                startActivity(intent2);
//                return true;
            case R.id.logout:
                sessionManager.logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadOrder(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GUIDE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject order = jsonArray.getJSONObject(i);

                                orderList.add(new Order(
                                        order.getInt("order_id"),
                                        order.getString("elderly_name"),
                                        order.getInt("elderly_age"),
                                        order.getString("elderly_phone"),
                                        order.getString("elderly_gender"),
                                        order.getInt("elderly_height"),
                                        order.getInt("elderly_weight"),
                                        order.getString("elderly_location"),
                                        order.getString("elderly_info"),
                                        order.getString("elderly_note"),
                                        order.getString("user_name")

                                ));
                            }

                            OrderAdapter adapter = new OrderAdapter(MainActivity.this, orderList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("hahaha");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("hihihi");
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
