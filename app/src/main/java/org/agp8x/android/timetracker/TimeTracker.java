package org.agp8x.android.timetracker;

import android.content.res.ObbInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.agp8x.android.restclient.GsonRequest;
import org.agp8x.android.restclient.RestClient;
import org.agp8x.android.restclient.data.RestObject;
import org.agp8x.android.restclient.data.User;
import org.agp8x.android.timetracker.data.Record;
import org.json.JSONObject;

public class TimeTracker extends AppCompatActivity {

    private RestClient<? extends RestObject> rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_tracker);
        rest = RestClient.getInstance(getApplicationContext());
        /*final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://192.168.2.53:8000/api/records/", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        });*/
        final GsonRequest<User> request = new GsonRequest<>(
                Request.Method.GET,
                "http://192.168.2.53:8000/api/records/",
                User.class,
                rest.getHeaders(),
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rest.send(request);
            }
        });
    }
}
