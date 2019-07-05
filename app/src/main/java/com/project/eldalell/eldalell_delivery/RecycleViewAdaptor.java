package com.project.eldalell.eldalell_delivery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;


public class RecycleViewAdaptor extends RecyclerView.Adapter<RecycleViewAdaptor.MyviewHolder> {
    Context mContext;

    List<Orders> mData;

    RequestQueue requestQueue;

    public RecycleViewAdaptor(Context mContext, List<Orders> mData) {
        this.mContext = mContext;
        this.mData = mData;
        requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
    }


    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.order, viewGroup, false);
        MyviewHolder vHolder = new MyviewHolder(v);
        return vHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder myviewHolder, final int position) {


        final Orders orders = new Orders();
        orders.setId(mData.get(position).getId());

        orders.setSubtotal(mData.get(position).getSubtotal());
        orders.setTotal(mData.get(position).getTotal());
        orders.setDeliveryCost();

        myviewHolder.OrderNum.setText(orders.getId());
        myviewHolder.Subtotal.setText(orders.getSubtotal()+" EGP");
        myviewHolder.DeleveryCost.setText(orders.getDeliveryCost()+" EGP");
        myviewHolder.Total.setText(orders.getTotal()+" EGP");


        myviewHolder.btnTakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setDeliveryAccept(requestQueue,orders.getId());

                Intent go = new Intent(mContext, OrderNumActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                go.putExtras(bundle);
                OrderFragment.fromOrder = true;
                mContext.startActivity(go);

            }
        });


    }

    private void setDeliveryAccept(RequestQueue requestQueue, String id) {
        Connection connection = new Connection();
        StringRequest request = new StringRequest(Request.Method.GET, connection.getDeliveryTake()+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
        //        ImageView img;
        TextView OrderNum;
        TextView Subtotal;
        TextView DeleveryCost;
        TextView Total;
        TextView btnTakeOrder;

        public MyviewHolder(@NonNull View itemView) {


            super(itemView);
//            img = (ImageView) itemView.findViewById(R.id.profile_image);
            OrderNum = (TextView) itemView.findViewById(R.id.OrderNum);
            Subtotal = itemView.findViewById(R.id.Subtotal);
            DeleveryCost = itemView.findViewById(R.id.DeliveryCost);
            Total = itemView.findViewById(R.id.Total);
            btnTakeOrder = itemView.findViewById(R.id.btnTakeOrder);

        }
    }
}
