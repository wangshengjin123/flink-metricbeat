package Metric;

import Model.CpuM;
import Model.DiskioM;
import Model.MemoryM;
import Model_Cpu.CpuModel;
import Model_Disk.DiskioModel;
import Model_Mem.*;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.util.Collector;
import org.apache.hadoop.hbase.client.Put;
import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;

import java.text.SimpleDateFormat;
import Hbase.HbaseConnectionPool;
import org.giiwa.core.base.Host;
import tool.ConnectionPoolConfig;

public class Metric {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties poro = new Properties();
        //ceshi:172.17.0.56:9092
        // aliyun:172.16.2.37:9092
        poro.setProperty("bootstrap.servers", "172.16.2.37:9092");
        poro.setProperty("group.id", "test1");
        FlinkKafkaConsumer011 kafkaConsumer011 = new FlinkKafkaConsumer011("hostmetric", new SimpleStringSchema(), poro);
        kafkaConsumer011.setStartFromEarliest();
        DataStreamSource<String> data = env.addSource(kafkaConsumer011);
        //DiskioModel
        SingleOutputStreamOperator filterData1 = data.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                //json转化为model对象


                //判断model中getDiskio是否为空，来判断kakfa的这一条数据是id的还是cpu的 还是内存的，来实现分类
                DiskioModel Model1 = JSON.parseObject(s, DiskioModel.class);

                if(Model1.getSystem().getDiskio()!=null)
                {
                    System.out.println("此条为磁盘数据");
                    return true;}
                else
                    return false;
            }
        });
        //MemoryModel
        SingleOutputStreamOperator filterData2 = data.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                //json转化为model对象
                // MemoryModel 和 MetricModel
                MemoryModel Model2 = JSON.parseObject(s, MemoryModel.class);
                //判断model中getDiskio是否为空，来判断kakfa的这一条数据是id的还是cpu的 还是内存的，来实现分类
                //System.out.println(Model.getSystem().getDiskio());
                //System.out.println(Model2.getSystem().getMemory());
                if(Model2.getSystem().getMemory()!=null)
                {
                    System.out.println("此条为内存数据");
                    return true;}
                else
                    return false;
            }
        });
        SingleOutputStreamOperator filterData3 = data.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                //json转化为model对象
                // MemoryModel 和 MetricModel
                CpuModel Model3 = JSON.parseObject(s, CpuModel.class);
                //判断model中getDiskio是否为空，来判断kakfa的这一条数据是id的还是cpu的 还是内存的，来实现分类
                //System.out.println(Model3.getSystem().getCpu());
                //if(Model3.getMetricset().getName()=="cpu")
                if(Model3.getSystem().getCpu()!=null)
                {
                    System.out.println("此条为cpu数据");
                    return true;}
                else
                    return false;
            }
        });
        //对象转化为字符串
        SingleOutputStreamOperator<String> map1 = filterData1.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                //   JSONObject jsonObject = JSON.parseObject(s);
                //将model的对象转化为json
                DiskioM system1 = JSON.parseObject(s, DiskioM.class);
                //设置时间
                Timestamp time1;
                String host_name;
                String metrictype;
                String io_time;
                String iostat_await;
                String iostat_busy;
                String iostat_time;
                String disk_name;
                JSONObject Model1_1= JSON.parseObject(s);


                time1=Model1_1.getTimestamp("@timestamp");
                host_name   =Model1_1.getJSONObject("host").getString("name");
                metrictype  =Model1_1.getJSONObject("metricset").getString("name");
                io_time     =Model1_1.getJSONObject("system").getJSONObject("diskio").getJSONObject("io").getString("time");
                iostat_await=Model1_1.getJSONObject("system").getJSONObject("diskio").getJSONObject("iostat").getString("await");
                iostat_busy =Model1_1.getJSONObject("system").getJSONObject("diskio").getJSONObject("iostat").getString("busy");
                iostat_time =Model1_1.getJSONObject("system").getJSONObject("diskio").getJSONObject("iostat").getString("service_time");
                disk_name   =Model1_1.getJSONObject("system").getJSONObject("diskio").getString("name");
                system1.setTime(time1);
                system1.setHost_name(host_name);
                system1.setMetrictype(metrictype);
                system1.setIo_time(io_time);
                system1.setIostat_await(iostat_await);
                system1.setIostat_busy(iostat_busy);
                system1.setIostat_time(iostat_time);
                system1.setDisk_name(disk_name);
                System.out.println("磁盘："+system1.getTime());
                return JSON.toJSONString(system1);
            }
        });
        SingleOutputStreamOperator<String> map2 = filterData2.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                //   JSONObject jsonObject = JSON.parseObject(s);
                //将model的对象转化为json
                MemoryM system2 = JSON.parseObject(s, MemoryM.class);
                //设置时间
                Timestamp time2;
                String host_name;
                String metrictype;
                String pct;

                JSONObject Model2_2= JSON.parseObject(s);
                time2=Model2_2.getTimestamp("@timestamp");
                //time2       =Model2_2.getTimestamp("time");
                host_name   =Model2_2.getJSONObject("host").getString("name");
                metrictype  =Model2_2.getJSONObject("metricset").getString("name");
                pct         =Model2_2.getJSONObject("system").getJSONObject("memory").getJSONObject("used").getString("pct");


                system2.setTime(time2);
                system2.setHost_name(host_name);
                system2.setMetrictype(metrictype);
                system2.setPct(pct);
                System.out.println("内存："+ system2.getTime());
                return JSON.toJSONString(system2);
            }
        });
        SingleOutputStreamOperator<String> map3 = filterData3.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                //   JSONObject jsonObject = JSON.parseObject(s);
                //将model的对象转化为json
                CpuM system3 = JSON.parseObject(s, CpuM.class);
                Timestamp time3;
                String host_name;
                String metrictype;
                String core;
                String idle_pct;
                String iowait_pct;
                String irq_pct;
                String nice_pct;
                String softirq_pct;
                String steal_pct;
                String system_pct;
                String total_pct;
                String user_pct;
                JSONObject Model3_3= JSON.parseObject(s);
                time3=Model3_3.getTimestamp("@timestamp");
                host_name   =Model3_3.getJSONObject("host").getString("name");
                metrictype  =Model3_3.getJSONObject("metricset").getString("name");

                core=Model3_3.getJSONObject("system").getJSONObject("cpu").getString("cores");
                idle_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("idle").getString("pct");
                iowait_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("iowait").getString("pct");
                irq_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("irq").getString("pct");
                nice_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("nice").getString("pct");
                softirq_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("softirq").getString("pct");
                steal_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("steal").getString("pct");
                system_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("system").getString("pct");
                total_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("total").getString("pct");
                user_pct =Model3_3.getJSONObject("system").getJSONObject("cpu").getJSONObject("user").getString("pct");
                system3.setTime(time3);
                system3.setHost_name(host_name);
                system3.setMetrictype(metrictype);
                system3.setCorenum(core);
                system3.setIdle_pct(idle_pct);
                system3.setIowait_pct(iowait_pct);
                system3.setIrq_pct(irq_pct);
                system3.setNice_pct(nice_pct);
                system3.setSoftirq_pct(softirq_pct);
                system3.setSteal_pct(steal_pct);
                system3.setSystem_pct(system_pct);
                system3.setTotal_pct(total_pct);
                system3.setUser_pct(user_pct);
                System.out.println("cpu："+ system3.getTime());
                return JSON.toJSONString(system3);
            }
        });

        //对象转化为字符串——部分
/*        SingleOutputStreamOperator<String> map_dd = map1.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                DiskioModel Model1= JSON.parseObject(s, DiskioModel.class);
                return JSON.toJSONString(Model1.getSystem().getDiskio());
            }
        });
        SingleOutputStreamOperator<String> map_ee = map2.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                MemoryModel system= JSON.parseObject(s, MemoryModel.class);
                return JSON.toJSONString(system.getSystem().getMemory().getUsed());
            }
        });*/

        //public Timestamp time;
 //       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String date = df.format(new Date());


/*        map_dd.rebalance().map(new MapFunction<String, Object>() {
            private static final long serialVersionUID = 1L;
            public String map(String value)throws IOException{
                System.out.println(value);
                writeIntoHBase(value);
                return null;
            }
        });*/

/*        map_ee.rebalance().map(new MapFunction<String, Object>() {
            private static final long serialVersionUID = 1L;
            public String map(String value)throws IOException{
                System.out.println(value);
                writeIntoHBase(value);
                return null;
            }
        });*/

//ceshi:172.17.0.56:9092
//aliyun:172.16.2.37:9092
        Properties sinkPoro1= new Properties();
        sinkPoro1.setProperty("bootstrap.servers", "172.16.2.37:9092");
        FlinkKafkaProducer011 hostio = new FlinkKafkaProducer011("hostio", new SimpleStringSchema(), sinkPoro1);
        map1.addSink(hostio);
        //
        Properties sinkPoro2= new Properties();
        sinkPoro2.setProperty("bootstrap.servers", "172.16.2.37:9092");
        FlinkKafkaProducer011 hostmem = new FlinkKafkaProducer011("hostmem", new SimpleStringSchema(), sinkPoro2);
        map2.addSink(hostmem);
        //
        Properties sinkPoro3= new Properties();
        sinkPoro3.setProperty("bootstrap.servers", "172.16.2.37:9092");
        FlinkKafkaProducer011 hostcpu = new FlinkKafkaProducer011("hostcpu", new SimpleStringSchema(), sinkPoro3);
        map3.addSink(hostcpu);
        //
        env.execute("job name");
}
    private static TableName tableName = TableName.valueOf("Flink2HBase");
    private static final String columnFamily = "info";
    public static void writeIntoHBase(String m)throws IOException{
        ConnectionPoolConfig config = new ConnectionPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);


        Configuration hbaseConfig = HBaseConfiguration.create();

        hbaseConfig = HBaseConfiguration.create();
        hbaseConfig.set("hbase.zookeeper.quorum", "172.17.0.56:2181");
        hbaseConfig.set("hbase.defaults.for.version.skip", "true");

        HbaseConnectionPool pool = null;

        try {
            pool = new HbaseConnectionPool(config, hbaseConfig);

            Connection con = pool.getConnection();

            Admin admin = con.getAdmin();

            if(!admin.tableExists(tableName)){
                admin.createTable(new HTableDescriptor(tableName).addFamily(new HColumnDescriptor(columnFamily)));
            }
            Table table = con.getTable(tableName);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Put put = new Put(org.apache.hadoop.hbase.util.Bytes.toBytes(df.format(new Date())));

            put.addColumn(org.apache.hadoop.hbase.util.Bytes.toBytes(columnFamily), org.apache.hadoop.hbase.util.Bytes.toBytes("test"),
                    org.apache.hadoop.hbase.util.Bytes.toBytes(m));

            table.put(put);
            table.close();
            pool.returnConnection(con);

        } catch (Exception e) {
            pool.close();
        }
    }


    }
