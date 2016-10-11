package org.agp8x.android.timetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import org.agp8x.android.restclient.GsonRequest;
import org.agp8x.android.restclient.RestClient;
import org.agp8x.android.restclient.data.RestObject;
import org.agp8x.android.restclient.data.User;
import org.agp8x.android.timetracker.data.Record;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TimeTracker extends AppCompatActivity {

    private RestClient<? extends RestObject> rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_tracker);
        rest = RestClient.getInstance(getApplicationContext());
        Type listType = new TypeToken<ArrayList<Record>>() {
        }.getType();
        final GsonRequest request = new GsonRequest(
                Request.Method.GET,
                "http://192.168.2.53:8000/api/records/",
                listType,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        TextView tv = (TextView) findViewById(R.id.textView);
                        tv.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView tv = (TextView) findViewById(R.id.textView);
                        tv.setText(error.toString());
                    }
                });
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rest.send(request);
                Toast.makeText(TimeTracker.this, "asdf", Toast.LENGTH_SHORT).show();
            }
        });
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User("client", "client");
                String url = "http://192.168.2.53:8000/api-auth-token/";
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        Log.e("ASDF", error.toString());
                    }
                };
                rest.login(user, url, errorListener);
            }
        });
    }
}
