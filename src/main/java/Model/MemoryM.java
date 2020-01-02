package Model;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class MemoryM {
    private String host_name;
    private String metrictype;
    private String pct;
    @JSONField(format="yyyy-MM-ddTHH:mm:ssZ")
    public Timestamp time;

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getMetrictype() {
        return metrictype;
    }

    public void setMetrictype(String metrictype) {
        this.metrictype = metrictype;
    }

    public String getPct() {
        return pct;
    }

    public void setPct(String pct) {
        this.pct = pct;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
