package Model;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;


public class CpuM {
    private String host_name;
    private String metrictype;
    @JSONField(format="yyyy-MM-ddTHH:mm:ssZ")
    public Timestamp time;
    private String corenum;
    private String idle_pct;
    private String iowait_pct;
    private String irq_pct;
    private String nice_pct;
    private String softirq_pct;
    private String steal_pct;
    private String system_pct;
    private String total_pct;
    private String user_pct;

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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getCorenum() {
        return corenum;
    }

    public void setCorenum(String corenum) {
        this.corenum = corenum;
    }

    public String getIdle_pct() {
        return idle_pct;
    }

    public void setIdle_pct(String idle_pct) {
        this.idle_pct = idle_pct;
    }

    public String getIowait_pct() {
        return iowait_pct;
    }

    public void setIowait_pct(String iowait_pct) {
        this.iowait_pct = iowait_pct;
    }

    public String getIrq_pct() {
        return irq_pct;
    }

    public void setIrq_pct(String irq_pct) {
        this.irq_pct = irq_pct;
    }

    public String getNice_pct() {
        return nice_pct;
    }

    public void setNice_pct(String nice_pct) {
        this.nice_pct = nice_pct;
    }

    public String getSoftirq_pct() {
        return softirq_pct;
    }

    public void setSoftirq_pct(String softirq_pct) {
        this.softirq_pct = softirq_pct;
    }

    public String getSteal_pct() {
        return steal_pct;
    }

    public void setSteal_pct(String steal_pct) {
        this.steal_pct = steal_pct;
    }

    public String getSystem_pct() {
        return system_pct;
    }

    public void setSystem_pct(String system_pct) {
        this.system_pct = system_pct;
    }

    public String getTotal_pct() {
        return total_pct;
    }

    public void setTotal_pct(String total_pct) {
        this.total_pct = total_pct;
    }

    public String getUser_pct() {
        return user_pct;
    }

    public void setUser_pct(String user_pct) {
        this.user_pct = user_pct;
    }
}
