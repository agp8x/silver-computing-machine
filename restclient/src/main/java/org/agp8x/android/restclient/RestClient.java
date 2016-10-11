package org.agp8x.android.restclient;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.agp8x.android.restclient.adapters.DateTimeTypeAdapter;
import org.agp8x.android.restclient.data.RestObject;
import org.agp8x.android.restclient.data.User;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clemensk on 02.10.16.
 */

public class RestClient<T extends RestObject> {
    private static RestClient instance;
    private final RequestQueue requestQueue;
    private final Map<String, String> headers;
    public final Response.Listener<JSONObject> loginListener;
    private Gson gson;

    private RestClient(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("foo-bar", "application/json");

        loginListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String token=response.getString("token");
                    headers.put("Authorization",String.format("Token %s", token));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(response);
                Log.e(Util.TAG, response.toString());
            }
        };

        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter());
        gson = b.create();
    }

    public static RestClient getInstance(Context context) {
        Log.d(Util.TAG, "get rest");
        if (instance == null) {
            Log.d(Util.TAG, "create rest");
            instance = new RestClient(context);
        }
        return instance;
    }


    public void destroy() {
        instance = null;
    }

    public void send(Request request) {
        if (request instanceof GsonRequest) {
            GsonRequest gsonRequest = (GsonRequest) request;
            ((GsonRequest) request).setHeaders(headers);
        }
        requestQueue.add(request);
    }

    public <E extends T> void sendRequest(int method, String url, Class<E> cls, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, RestObject obj) {
        //GsonRequest<E> request = new GsonRequest<>(method, url, cls, headers, listener, errorListener);
        JsonObjectRequest request= null;
        try {
            request = new MyJsonObjectRequest(method, url, new JSONObject(gson.toJson(obj)),listener,errorListener, headers);

            send(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void login(User user, String url, Response.ErrorListener errorListener) {
        Log.d(Util.TAG, "login rest");
        sendRequest(Request.Method.POST, url, (Class<T>) User.class, loginListener, errorListener, user);
    }
}
