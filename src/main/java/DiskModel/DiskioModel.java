package DiskModel;

import Model.BeatBean;
import Model.HostBean;
import Model.MetricsetBean;

public class DiskioModel{
    /**
     * {
     *   "@timestamp": "2019-12-04T06:40:38.212Z",
     *   "@metadata": {
     *     },
     *  "beat": {
     *    },
     *  "host": {
     *    },
     *  "system": {
     *    },
     *  "metricset": {
     *       },
     *   }
     */
    //尝试定义时间
/*    public Timestamp time;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = df.format(new Date());*/

    private SystemDiskioBean system;
    private BeatBean beat;
    private HostBean host;
    private MetricsetBean metricset;
    //对json中4个嵌套,对应4个bean，每个bean一个单独的文件

    @Override
    public String toString() {
        return "DiskioModel{" +
                "system='}'";
    }


/*    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
    public SimpleDateFormat getDf() {
        return df;
    }
    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }*/

    public SystemDiskioBean getSystem() {
        return system;
    }
    public void setSystem(SystemDiskioBean system) {
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
