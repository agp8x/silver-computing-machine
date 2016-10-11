package org.agp8x.android.restclient;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.agp8x.android.restclient.adapters.DateTimeTypeAdapter;
import org.agp8x.android.restclient.data.RestObject;
import org.joda.time.DateTime;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by clemensk on 02.10.16.
 */

public class GsonRequest extends Request {
    private final Type cls;
    private Map<String, String> headers;
    private final Response.Listener listener;
    private final Gson gson;

    public GsonRequest(int method, String url, Type cls, Response.Listener listener, Response.ErrorListener errorListener) {
        this(method, url, cls, null, listener, errorListener);
    }
    public GsonRequest(int method, String url, Type cls, Map<String, String> headers, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.cls = cls;
        this.headers = headers;
        this.listener = listener;
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter());
        gson = b.create();
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers!=null? headers:super.getHeaders();
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            System.out.println(json);
            return Response.success(gson.fromJson(json, cls), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Object response) {
        listener.onResponse(response);
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
