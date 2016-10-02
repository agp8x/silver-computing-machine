package org.agp8x.android.restclient;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.agp8x.android.restclient.data.RestObject;
import org.agp8x.android.restclient.data.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clemensk on 02.10.16.
 */

public class RestClient<T extends RestObject> {
    private static RestClient instance;
    private final RequestQueue requestQueue;
    private final Map<String, String> headers;
    public final Response.Listener<T> loginListener;

    private RestClient(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        loginListener = new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {

            }
        };
    }

    public static RestClient getInstance(Context context) {
        if (instance == null) {
            instance = new RestClient(context);
        }
        return instance;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void send(Request<? extends RestObject> request) {
        requestQueue.add(request);
    }

    public <E extends T> void sendRequest(int method, String url, Class<E> cls, Response.Listener<E> listener, Response.ErrorListener errorListener) {
        GsonRequest<E> request = new GsonRequest<>(method, url, cls, headers, listener, errorListener);
        requestQueue.add(request);
    }

    public void login(User user, String url, Response.ErrorListener errorListener) {
        sendRequest(Request.Method.POST, url,(Class<T>) User.class, loginListener, errorListener);
    }
}
