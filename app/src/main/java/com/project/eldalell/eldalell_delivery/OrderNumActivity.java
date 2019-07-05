package com.project.eldalell.eldalell_delivery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OrderNumActivity extends AppCompatActivity {
    private RecyclerView rv;

    public final static String MY_PREFS_NAME = "Order";
    public static ArrayList<Orders_content> OrderContentList;

    TextView tvNote,OrderNum,Suptotal,DeliveryCost,Total
            ,tvSubjectAddress,tvCityAddress,tvDistrictAddress,tvStreetAddress
            ,tvBuildingAddress,tvFloorAddress,tvApartmentAddress;
    Button btnOrderDone;
    RequestQueue requestQueue;
    Orders order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_num);

        OrderNum = findViewById(R.id.OrderNum);
        Suptotal = findViewById(R.id.Subtotal);
        DeliveryCost = findViewById(R.id.DeliveryCost);
        Total = findViewById(R.id.Total);
        tvSubjectAddress = findViewById(R.id.tvSubjectAddress);
        tvCityAddress = findViewById(R.id.tvCityAddress);
        tvDistrictAddress = findViewById(R.id.tvDistrictAddress);
        tvStreetAddress = findViewById(R.id.tvStreetAddress);
        tvBuildingAddress = findViewById(R.id.tvBuildingAddress);
        tvFloorAddress = findViewById(R.id.tvFloorAddress);
        tvApartmentAddress = findViewById(R.id.tvApartmentAddress);
        tvNote = findViewById(R.id.tvNote);
        btnOrderDone = findViewById(R.id.btnOrderDone);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        if (OrderFragment.fromOrder) {
            Intent intent = getIntent();

            Bundle b = intent.getExtras();
            int i = b.getInt("position");


            order = OrderFragment.OrderList.get(i);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("orderID", order.getId());
            editor.putFloat("orderTotal", order.getTotal());
            editor.putFloat("orderSubTotal", order.getSubtotal());
            editor.putString("Note", order.getNote_for_delivery());
            editor.putString("AddressID", order.getAddress_id());
            editor.apply();
            OrderFragment.fromOrder = false;
        }else {
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String orderID = prefs.getString("orderID", null);
            if (orderID != null) {
                float orderTotal = prefs.getFloat("orderTotal",0);
                float orderSubTotal = prefs.getFloat("orderSubTotal",0);
                String note = prefs.getString("Note",null);
                String AddressID = prefs.getString("AddressID",null);

                order = new Orders();
                order.setId(orderID);
                order.setNote_for_delivery(note);
                order.setTotal(orderTotal);
                order.setSubtotal(orderSubTotal);
                order.setAddress_id(AddressID);
                order.setDeliveryCost();
            }
        }

        OrderNum.setText(order.getId());
        Suptotal.setText(order.getSubtotal()+" EGP");
        DeliveryCost.setText(order.getDeliveryCost()+" EGP");
        Total.setText(order.getTotal()+" EGP");
        tvNote.setText(order.getNote_for_delivery());

        getAddress(requestQueue,OrderNumActivity.this);

        OrderContentList = getInvoice(requestQueue,OrderNumActivity.this);


        rv = findViewById(R.id.rvOrder_content);


        btnOrderDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderDone(requestQueue,OrderNumActivity.this);
            }
        });

    }

    private void getAddress(RequestQueue requestQueue, OrderNumActivity orderNumActivity) {
        Connection connection = new Connection();
        final Address addresss = new Address();
        addresss.setId(order.getAddress_id());
        final String AddressID = order.getAddress_id();
        StringRequest request = new StringRequest(Request.Method.GET, connection.getGetAddress()+AddressID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray address = object.getJSONArray("Address");
                            for (int i =0; i<address.length();i++){
                                JSONObject o = address.getJSONObject(i);
                                if (o.getString("id").equals(AddressID)){

                                    addresss.setAddress_subject(o.getString("address_subject"));
                                    addresss.setApartment_number(o.getString("apartment_number"));
                                    addresss.setBuliding_number(o.getString("buliding_number"));
                                    addresss.setDistrict_id(o.getString("district_id"));
                                    addresss.setFloor(o.getString("floor"));
                                    addresss.setUser_id(o.getString("user_id"));
                                    addresss.setCity(o.getString("city_name"));
                                    addresss.setDistrict(o.getString("district_name"));
                                    addresss.setStreet(o.getString("street"));

                                    tvStreetAddress.setText(addresss.getStreet());
                                    tvCityAddress.setText(addresss.getCity());
                                    tvBuildingAddress.setText(addresss.getBuliding_number());
                                    tvSubjectAddress.setText(addresss.getAddress_subject());
                                    tvFloorAddress.setText(addresss.getFloor());
                                    tvDistrictAddress.setText(addresss.getDistrict());
                                    tvApartmentAddress.setText(addresss.getApartment_number());


                                    break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    private void getOrderDone(RequestQueue requestQueue, final Context context) {
        Connection connection = new Connection();
        String OrderID = order.getId();
        StringRequest request = new StringRequest(Request.Method.GET, connection.getOrderDone() + OrderID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.remove("orderID");
                        editor.remove("orderTotal");
                        editor.remove("orderSubTotal");
                        editor.remove("Note");
                        editor.remove("AddressID");
                        editor.apply();
                        Intent intent = new Intent(context,MainActivity.class);
                        context.startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    private ArrayList<Orders_content> getInvoice(final RequestQueue requestQueue, final Context context){
        final ArrayList<Orders_content> invoices = new ArrayList<>();
        Connection connection = new Connection();
        String orderID = order.getId();
        StringRequest request = new StringRequest(Request.Method.GET, connection.getGetInvoice() + orderID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray invoicess = object.getJSONArray("InvoiceRow");
                            for (int i =0; i<invoicess.length();i++){
                                JSONObject invoice = invoicess.getJSONObject(i);
                                Orders_content orders = new Orders_content();
                                orders.setOrderCount(Integer.parseInt(invoice.getString("quantity")));
                                orders.setItem_shop_id(invoice.getString("item_shop_id"));

                                getInvoiceDetails(requestQueue,context,orders,invoices);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });
        requestQueue.add(request);
        return invoices;
    }

    private void getInvoiceDetails(final RequestQueue requestQueue, final Context context, final Orders_content invoices, final ArrayList<Orders_content> orrr) {
    Connection connection = new Connection();
        final String shopID = invoices.getItem_shop_id();
        final StringRequest request = new StringRequest(Request.Method.GET, connection.getGetItemswithItem() + shopID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray item = object.getJSONArray("item");
                            JSONObject o = item.getJSONObject(0);
                                invoices.setOrderName(o.getString("item_name"));
                                invoices.setOrderPrice(Float.parseFloat(o.getString("new_retail_price")));
                                OrderContentList.add(invoices);
                                OrderContentAdaptor OrderContentAdaptor = new OrderContentAdaptor(OrderNumActivity.this, OrderContentList);
                                rv.setLayoutManager(new LinearLayoutManager(OrderNumActivity.this));
                                rv.setAdapter(OrderContentAdaptor);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
}
