package util;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HbaseOutFormat {
    private static Admin admin;
    private static Connection conn = null;

    static {
        try {
            Configuration configuration = HBaseConfiguration.create();
          //  configuration.set("hbase.zookeeper.property.clientPort", String.valueOf(Parameter.PARAMETERS.get("hbase.zookeeper.property.clientPort")));
           // configuration.set("hbase.zookeeper.quorum", String.valueOf(Parameter.PARAMETERS.get("hbase.zookeeper.quorum")));
            //集群配置↓
            configuration.set("hbase.zookeeper.quorum", "172.17.0.56");
            configuration.set("hbase.master", "172.17.0.56:60000");
            conn = ConnectionFactory.createConnection(configuration, new ThreadPoolExecutor(8, 32, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2048), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void insertData(String tableName, JSONObject log) {
        TableName tablename = TableName.valueOf(tableName);
        Put put = new Put((log.getString("transactionId")).getBytes());
        put.addColumn("log".getBytes(), "content".getBytes(), log.getString("content").getBytes());
        //HTable table = new HTable(conn.getConfiguration(),tablename);已弃用
        Table table = null;
        try {
            table = conn.getTable(tablename);
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (table != null) {
                try {
                    table.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}