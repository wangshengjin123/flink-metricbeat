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

public class HbaseUtil {
    private static Admin admin;
    private static Connection conn = null;

    static {
        try {
            Configuration configuration = HBaseConfiguration.create();
           // configuration.set("hbase.zookeeper.property.clientPort", String.valueOf(Parameter.PARAMETERS.get("hbase.zookeeper.property.clientPort")));
          //  configuration.set("hbase.zookeeper.quorum", String.valueOf(Parameter.PARAMETERS.get("hbase.zookeeper.quorum")));
            //集群配置↓
            configuration.set("hbase.zookeeper.quorum", "172.17.0.56");
            configuration.set("hbase.master", "172.17.0.56:60000");
            conn = ConnectionFactory.createConnection(configuration, new ThreadPoolExecutor(8, 32, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2048), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建表
    public static void createTable(String tableNmae, String[] cols) {

        TableName tableName = TableName.valueOf(tableNmae);
        try {
            admin = conn.getAdmin();
            if (admin.tableExists(tableName)) {
                System.out.println("表已存在！");
            } else {
                HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
                for (String col : cols) {
                    HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                    hColumnDescriptor.setMaxVersions(3);
                    hTableDescriptor.addFamily(hColumnDescriptor);
                }
                admin.createTable(hTableDescriptor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void insertData(JSONObject log) {
        insertData("log:pinpoint-log", log);
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


    //根据rowKey进行查询
    public static JSONObject getDataByRowKey(String tableName, String rowKey) {
        Table table = null;
        JSONObject object = new JSONObject();
        try {
            table = conn.getTable(TableName.valueOf(tableName));
            Get get = new Get(rowKey.getBytes());
            //先判断是否有此条数据
            if (!get.isCheckExistenceOnly()) {
                Result result = table.get(get);
                for (Cell cell : result.rawCells()) {
                    String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                    String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    object.put(colName, value);
                }
            }
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
        return object;
    }

    //查询指定单cell内容
    public static String getCellData(String tableName, String rowKey, String family, String col) {
        Table table = null;
        try {
            table = conn.getTable(TableName.valueOf(tableName));
            String result = null;
            Get get = new Get(rowKey.getBytes());
            if (!get.isCheckExistenceOnly()) {
                get.addColumn(Bytes.toBytes(family), Bytes.toBytes(col));
                Result res = table.get(get);
                byte[] resByte = res.getValue(Bytes.toBytes(family), Bytes.toBytes(col));
                return result = Bytes.toString(resByte);
            } else {
                return result = "查询结果不存在";
            }
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
        return "出现异常";
    }

    //查询指定表名中所有的数据
    public static List<JSONObject> getAllData(String tableName) {

        Table table = null;
        List<JSONObject> list = new ArrayList<JSONObject>();
        try {
            table = conn.getTable(TableName.valueOf(tableName));
            ResultScanner results = table.getScanner(new Scan());
            for (Result result : results) {
                String id = new String(result.getRow());
                System.out.println("用户名:" + new String(result.getRow()));
                JSONObject object = new JSONObject();
                for (Cell cell : result.rawCells()) {
                    String row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                    //String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
                    String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                    String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    object.put("transactionId", row);
                    object.put(colName, value);
                }
                list.add(object);
            }
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
        return list;
    }

    //删除指定cell数据
    public static void deleteByRowKey(String tableName, String rowKey) {

        Table table = null;
        try {
            table = conn.getTable(TableName.valueOf(tableName));
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            //删除指定列
            //delete.addColumns(Bytes.toBytes("contact"), Bytes.toBytes("email"));
            table.delete(delete);
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

    //删除表
    public static void deleteTable(String tableName) {

        try {
            TableName tablename = TableName.valueOf(tableName);
            admin = conn.getAdmin();
            admin.disableTable(tablename);
            admin.deleteTable(tablename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getNoDealData(String tableName){
        Table table = null;
        try {
            table= conn.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            ResultScanner resutScanner = table.getScanner(scan);
            for(Result result: resutScanner){
                System.out.println("scan:  " + result);
            }
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
