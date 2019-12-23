package Metric;

import DiskModel.DiskioModel;
import MemModel.MemoryModel;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
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
import java.util.Date;
import java.util.Properties;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;

import java.text.SimpleDateFormat;
import Hbase.HbaseConnectionPool;
import tool.ConnectionPoolConfig;

public class Metric {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties poro = new Properties();
        poro.setProperty("bootstrap.servers", "172.17.0.56:9092");
        poro.setProperty("group.id", "test1");
        FlinkKafkaConsumer011 kafkaConsumer011 = new FlinkKafkaConsumer011("hostmetric", new SimpleStringSchema(), poro);
        kafkaConsumer011.setStartFromEarliest();DataStreamSource<String> data = env.addSource(kafkaConsumer011);
        //DiskioModel
/*        SingleOutputStreamOperator filterData1 = data.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                //json转化为model对象
                DiskioModel Model1= JSON.parseObject(s, DiskioModel.class);
                //判断model中getDiskio是否为空，来判断kakfa的这一条数据是id的还是cpu的 还是内存的，来实现分类
                //System.out.println(Model.getSystem().getDiskio());
                if(Model1.getSystem().getDiskio()!=null)
                {
                    System.out.println("此条为磁盘数据");
                    return true;}
                else
                    return false;
            }
        });*/
        //MemoryModel
        SingleOutputStreamOperator filterData2 = data.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                //json转化为model对象
                MemoryModel Model2 = JSON.parseObject(s, MemoryModel.class);
                //判断model中getDiskio是否为空，来判断kakfa的这一条数据是id的还是cpu的 还是内存的，来实现分类
                //System.out.println(Model.getSystem().getDiskio());
                System.out.println(Model2.getSystem().getMemory());
                if(Model2.getSystem().getMemory()!=null)
                {
                    System.out.println("此条为内存数据");
                    return true;}
                else
                    return false;
            }
        });
        //对象转化为字符串
/*        SingleOutputStreamOperator<String> map1 = filterData1.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                //   JSONObject jsonObject = JSON.parseObject(s);
                //将model的对象转化为json
                DiskioModel system = JSON.parseObject(s, DiskioModel.class);
                return JSON.toJSONString(system);
            }
        });*/
        SingleOutputStreamOperator<String> map2 = filterData2.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                //   JSONObject jsonObject = JSON.parseObject(s);
                //将model的对象转化为json
                MemoryModel system = JSON.parseObject(s, MemoryModel.class);
                return JSON.toJSONString(system);
            }
        });

        //对象转化为字符串——部分
/*        SingleOutputStreamOperator<String> map_dd = map1.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                DiskioModel Model1= JSON.parseObject(s, DiskioModel.class);
                return JSON.toJSONString(Model1.getSystem().getDiskio());
            }
        });*/
        SingleOutputStreamOperator<String> map_ee = map2.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                MemoryModel system= JSON.parseObject(s, MemoryModel.class);
                return JSON.toJSONString(system.getSystem().getMemory().getUsed1().getPct());
            }
        });

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
        Properties sinkPoro= new Properties();
                   sinkPoro.setProperty("bootstrap.servers", "172.17.0.56:9092");
                   FlinkKafkaProducer011 memmetric = new FlinkKafkaProducer011("host", new SimpleStringSchema(), sinkPoro);
        map_ee.addSink(memmetric);
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
