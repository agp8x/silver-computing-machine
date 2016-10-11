package org.agp8x.android.restclient.data;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clemensk on 11.10.16.
 */
public class UserTest {
    Gson gson;

    @Before
    public void setUp() throws Exception {
        gson=new Gson();
    }

    @Test
    public void testSerialization() throws Exception {
        User user = new User("a", "b");
        System.out.println(gson.toJson(user));
    }
}