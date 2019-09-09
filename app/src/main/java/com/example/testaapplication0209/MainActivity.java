package com.example.testaapplication0209;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    private void Login(){

        String url = "http://apifrapi.tg1portal.it/loginuser_module";


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("STATO HTTP", String.valueOf(response));

                String statoo = "";

                try {
                    JSONObject jresponse = new JSONObject(response);
                    JSONObject state = jresponse.getJSONObject("0");
                    JSONObject result = state.getJSONObject("Stato Information");
                    statoo = result.getString("Stato");

                    Log.d("RESULT ERROR", statoo);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

                Log.d("STATO STTOO", statoo);

                String inputU = etUsername.getText().toString().trim();
                String inputP = etPassword.getText().toString().trim();

                Log.d("AKNCAKCAC", inputU);

                if (statoo.equals("Error")){
                    Toast.makeText(MainActivity.this, "Username o Password errati.", Toast.LENGTH_SHORT).show();
                } else if (inputU.isEmpty() || inputP.isEmpty()){
                    Toast.makeText(MainActivity.this, "Perfavore i campi!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, LoggedActivity.class);
                    startActivity(intent);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Impossibile connettersi al server.", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user","astsicilia");
                params.put("password","astoff2018");
                params.put("secretKey","dwejdnwejkdnwkdjn");
                params.put("username_utente", etUsername.getText().toString().trim());
                params.put("password_utente", etPassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}