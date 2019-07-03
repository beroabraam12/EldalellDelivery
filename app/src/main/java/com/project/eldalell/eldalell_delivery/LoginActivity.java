package com.project.eldalell.eldalell_delivery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etPhoneLogin,etPasswordLogin;
    RequestQueue requestQueue;
    public static final String MY_PREFS_NAME = "tokenPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        etPhoneLogin= findViewById(R.id.etPhoneLogin);
        etPasswordLogin= findViewById(R.id.etPasswordLogin);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login(requestQueue,LoginActivity.this);

            }
        });


    }

    private void Login(RequestQueue requestQueue, final Context context) {
        Connection connection = new Connection();
        final String Phone = etPhoneLogin.getText().toString();
        final String password = etPasswordLogin.getText().toString();
        if (Phone.isEmpty() && password.isEmpty()){
            Toast.makeText(context, "Please Fill All Data", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest request = new StringRequest(Request.Method.POST, connection.getLoginDelivery(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
                                JSONObject Success = object.getJSONObject("success");
                                String token = Success.getString("token");
                                SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putString("token", token);
                                editor.apply();
                                Toast.makeText(context, token, Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(homeIntent);
                                finish();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error "+error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> param = new HashMap<>();
                        param.put("phone_number",Phone);
                        param.put("password",password);
                    return param;
                }
            };
            requestQueue.add(request);
        }

    }

    @Override
    protected void onStart() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String token = prefs.getString("token", null);
        if (token != null) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }
}
