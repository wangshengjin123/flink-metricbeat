package Model_Cpu;

import Model.BeatBean;
import Model.HostBean;
import Model.MetricsetBean;
import Model_Mem.SystemMemoryBean;

public class CpuModel {
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
