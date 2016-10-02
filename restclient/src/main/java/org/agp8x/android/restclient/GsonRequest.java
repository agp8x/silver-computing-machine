package org.agp8x.android.restclient;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import org.agp8x.android.restclient.data.RestObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by clemensk on 02.10.16.
 */

public class GsonRequest<T extends RestObject> extends Request<T> {
    private final Class<T> cls;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;
    private final Gson gson;

    public GsonRequest(int method, String url, Class<T> cls, Map<String, String> headers, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.cls = cls;
        this.headers = headers;
        this.listener = listener;
        this.gson=new Gson();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers!=null? headers:super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, cls), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
