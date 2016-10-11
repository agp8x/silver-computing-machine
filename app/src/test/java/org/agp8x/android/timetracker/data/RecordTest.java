package org.agp8x.android.timetracker.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.agp8x.android.restclient.adapters.DateTimeTypeAdapter;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clemensk on 02.10.16.
 */
public class RecordTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter());
        gson = b.create();
    }

    @Test
    public void testSerialization() throws Exception {
        Record r = new Record(new DateTime(), new DateTime(), "");
        System.out.println(gson.toJson(r));
    }

    @Test
    public void testDeserialization() throws Exception {
        Record r;
        String json = "{\"start\":\"2016-10-11T12:19:00.600+02:00\",\"end\":\"2016-10-11T12:19:00.719+02:00\",\"desc\":\"\"}";
        r = gson.fromJson(json, Record.class);
        System.out.println(r);
    }
}