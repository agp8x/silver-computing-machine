package org.agp8x.android.timetracker.data;

import org.agp8x.android.restclient.data.RestObject;
import org.joda.time.DateTime;

/**
 * Created by clemensk on 02.10.16.
 */

public class Record implements RestObject {
    DateTime start;
    DateTime end;
    String desc;

    public Record() {
    }

    public Record(DateTime start, DateTime end, String desc) {
        this.start = start;
        this.end = end;
        this.desc = desc;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
