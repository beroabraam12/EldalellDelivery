package com.project.eldalell.eldalell_delivery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentTransaction fragmentTransaction;
    public static Delivery delivery;
    RequestQueue requestQueue;
    public static final String MY_PREFS_NAME = "tokenPref";
    public static final String MY_PREFS_NAME_ORDER = "Order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, new HomeFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_order) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, new OrderFragment()).commit();

        } else if (id == R.id.nav_home) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, new HomeFragment()).commit();

        }else if(id == R.id.nav_Logout){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Delivery getDeliveryAPI(final String token, final Context context,
                                   final RequestQueue requestQueue) {
        final Delivery currentDelivery = new Delivery();
        final Connection connection = new Connection();
        StringRequest request = new StringRequest(Request.Method.GET, connection.getGetAuthDelivery(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject success = object.getJSONObject("success");
                    currentDelivery.setId(success.getString("id"));
                    currentDelivery.setFirst_name(success.getString("first_name"));
                    currentDelivery.setLast_name(success.getString("last_name"));
                    currentDelivery.setGender(success.getString("gender"));
                    currentDelivery.setPhone_number(success.getString("phone_number"));
                    currentDelivery.setNational_id_number(success.getString("national_id_number"));
                    currentDelivery.setImage(connection.getDeliveryHostIp()+success.getString("image"));
                    currentDelivery.setBirthday(success.getString("birthday"));
                    currentDelivery.setConfirm(success.getString("confirm"));
                    currentDelivery.setShop_id(success.getString("shop_id"));
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, new HomeFragment()).commit();
                    } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                MainActivity.delivery = getDeliveryAPI(token, context, requestQueue);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer " + token);
                headers.put("Accept", "application/json");

                return headers;
            }
        };
        requestQueue.add(request);
        return currentDelivery;
    }

    @Override
    protected void onStart() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String token = prefs.getString("token", null);
        if (token != null) {
            MainActivity.delivery = getDeliveryAPI(token, MainActivity.this, requestQueue);
            SharedPreferences pre = getSharedPreferences(MY_PREFS_NAME_ORDER, MODE_PRIVATE);
            String OrderID = pre.getString("orderID", null);
            if (OrderID != null){
                Intent intent = new Intent(MainActivity.this,OrderNumActivity.class);
                startActivity(intent);
            }
        }
        super.onStart();
    }
}
