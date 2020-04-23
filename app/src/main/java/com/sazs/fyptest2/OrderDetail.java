package com.sazs.fyptest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sazs.fyptest2.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderDetail extends AppCompatActivity {

    private static final String URL_READ = "http://lrgs.ftsm.ukm.my/users/a166118/FYP/read_order_cg.php";
    String insertURL = "http://lrgs.ftsm.ukm.my/users/a166118/FYP/confirm_order_cg.php";
    private static final String TAG = Profile.class.getSimpleName();
    String getId;
    TextView cgID, cgName;
    SessionManager sessionManager;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        ExtendedFloatingActionButton fab = findViewById(R.id.fab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        cgID = findViewById(R.id.cg_id);
        cgName = findViewById(R.id.cg_name);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });

        getInfo();
        getCaregiverDetail();
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

    private void getCaregiverDetail(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String strName = object.getString("name").trim();
                                    Integer intId = object.getInt("id");

                                    System.out.println(strName+"hihihi");
                                    cgName.setText(strName);
                                    cgID.setText((String.valueOf(intId)));
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Snackbar.make(getWindow().getDecorView().getRootView(), "Error Read "+e.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(getWindow().getDecorView().getRootView(), "Error Read "+error.toString(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        System.out.println(error+"hahaha");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void confirmOrder(){
        StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters  = new HashMap<String, String>();

                parameters.put("caregiver_id",cgID.getText().toString());
                parameters.put("caregiver_name",cgName.getText().toString());


                return parameters;
            }
        };

        requestQueue.add(request);

        Snackbar.make(getWindow().getDecorView().getRootView(), "Order Confirmed", Snackbar.LENGTH_LONG)
                .setCallback(new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        orderConfirmed();
                    }
                }).show();
    }

    private void orderConfirmed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
