package com.project.eldalell.eldalell_delivery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;


public class OrderNumActivity extends AppCompatActivity {
    private RecyclerView rv;


    public static List<orders_content> OrderContentList;

    TextView tvNote,OrderNum,Suptotal,DeliveryCost,Total;
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
        tvNote = findViewById(R.id.tvNote);
        btnOrderDone = findViewById(R.id.btnOrderDone);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Intent intent = getIntent();

        Bundle b = intent.getExtras();
        int i = b.getInt("position");


        order = OrderFragment.OrderList.get(i);


        OrderNum.setText(order.getId());
        Suptotal.setText(order.getSubtotal()+" EGP");
        DeliveryCost.setText(order.getDeliveryCost()+" EGP");
        Total.setText(order.getTotal()+" EGP");
        tvNote.setText(order.getNote_for_delivery());



        OrderContentList = new ArrayList<>();
//        OrderContentList.add(new orders_content("1", "Isis Roselle - 20 Bags", "10.95 EGP"));
//        OrderContentList.add(new orders_content("1", "Isis Roselle - 20 Bags", "10.95 EGP"));
//        OrderContentList.add(new orders_content("1", "Isis Roselle - 20 Bags", "10.95 EGP"));


        rv = findViewById(R.id.rvOrder_content);
        OrderContentAdaptor OrderContentAdaptor = new OrderContentAdaptor(OrderNumActivity.this, OrderContentList);
        rv.setLayoutManager(new LinearLayoutManager(OrderNumActivity.this));
        rv.setAdapter(OrderContentAdaptor);

        btnOrderDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderDone(requestQueue,OrderNumActivity.this);
            }
        });

    }

    private void getOrderDone(RequestQueue requestQueue, final Context context) {
        Connection connection = new Connection();
        String OrderID = order.getId();
        StringRequest request = new StringRequest(Request.Method.GET, connection.getOrderDone() + OrderID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
}
