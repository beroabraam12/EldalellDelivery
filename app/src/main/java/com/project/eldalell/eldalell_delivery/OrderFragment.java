package com.project.eldalell.eldalell_delivery;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.project.eldalell.R;

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


/**
 *
 */
public class OrderFragment extends Fragment {

    View v;


    private RecyclerView mrecycleview;


    public static ArrayList<Orders> OrderList;


    public OrderFragment() {
    }

    RequestQueue requestQueue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: DataBase for Orders
        requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        OrderList = getOrders(requestQueue,getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_order, container, false);
        mrecycleview = (RecyclerView) v.findViewById(R.id.recicle_view);

        return v;


    }
    private ArrayList<Orders> getOrders(RequestQueue requestQueue, Context context){
        final ArrayList<Orders> orderss = new ArrayList<>();
        Connection connection = new Connection();
        String ShopID = MainActivity.delivery.getShop_id();
        StringRequest request = new StringRequest(Request.Method.GET, connection.getGetOrders() + ShopID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray Order = jsonObject.getJSONArray("Orders");
                            for (int i=0; i<Order.length();i++){
                                JSONObject object = Order.getJSONObject(i);
                                Orders currentOrder = new Orders();
                                currentOrder.setId(object.getString("id"));
                                currentOrder.setSubtotal(Float.parseFloat(object.getString("sub_total")));
                                currentOrder.setTotal(Float.parseFloat(object.getString("total")));
                                currentOrder.setDeliveryCost();
                                currentOrder.setNote_for_delivery(object.getString("note_for_delivery"));
                                currentOrder.setDelivery_man_accepted(object.getString("delivery_man_accepted"));
                                currentOrder.setOrder_done(object.getString("order_done"));
                                currentOrder.setUser_id(object.getString("user_id"));
                                currentOrder.setShop_id(object.getString("shop_id"));
                                currentOrder.setAddress_id(object.getString("address_id"));
                                orderss.add(currentOrder);
                            }
                            RecycleViewAdaptor recycleViewAdaptor = new RecycleViewAdaptor(getContext(), OrderList);
                            mrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
                            mrecycleview.setAdapter(recycleViewAdaptor);
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
        return orderss;
    }


}
