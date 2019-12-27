package Model_Cpu;

import Model.BeatBean;
import Model.HostBean;
import Model.MetricsetBean;
import Model_Mem.SystemMemoryBean;
import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class CpuModel {
    @JSONField(format="yyyy-MM-ddTHH:mm:ssZ")
    public Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    private SystemCpuBean system;
    private BeatBean beat;
    private HostBean host;
    private MetricsetBean metricset;

    public SystemCpuBean getSystem() {
        return system;
    }

    public void setSystem(SystemCpuBean system) {
        this.system = system;
    }

    public BeatBean getBeat() {
        return beat;
    }

    public void setBeat(BeatBean beat) {
        this.beat = beat;
    }

    public HostBean getHost() {
        return host;
    }

    public void setHost(HostBean host) {
        this.host = host;
    }

    public MetricsetBean getMetricset() {
        return metricset;
    }

    public void setMetricset(MetricsetBean metricset) {
        this.metricset = metricset;
    }


}
