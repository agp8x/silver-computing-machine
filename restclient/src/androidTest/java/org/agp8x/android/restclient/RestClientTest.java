package org.agp8x.android.restclient;

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.agp8x.android.restclient.data.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clemensk on 11.10.16.
 */
public class RestClientTest {
    RestClient client;

    @Before
    public void setUp() throws Exception {
        while (client==null){
        client = RestClient.getInstance(InstrumentationRegistry.getTargetContext());}
    }

    @After
    public void tearDown() throws Exception {
        //client.destroy();
        client = null;
    }

   /* @Test
    public void send() throws Exception {

    }

    @Test
    public void sendRequest() throws Exception {

    }*/

    @Test
    public void login() throws Exception {
        Log.d("ASDF", "start login test");
        User user = new User("agp8x", "admin");
        String url = "http://192.168.2.53:8080/api-auth-token/";
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Log.e("ASDF", error.toString());
            }
        };
        Log.d("ASDF", "send login test");
        client.login(user, url, errorListener);
        Thread.sleep(5000);
        Log.d("ASDF", "done login test");
    }

}