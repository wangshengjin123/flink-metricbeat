package util;

import org.apache.flink.api.common.io.OutputFormat;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class HBaseOutputFormat implements OutputFormat<Tuple2<String, String>> {
    private static final Logger logger = LoggerFactory.getLogger(HBaseOutputFormat.class);

    private org.apache.hadoop.conf.Configuration conf = null;
    private Connection conn = null;
    private Table table = null;

    @Override
    public void configure(Configuration parameters) {
    }

    @Override
    public void open(int taskNumber, int numTasks) throws IOException {
//        conn = HBaseConnection.getHBaseConn();
        conn = ConnectionFactory.createConnection(null);

        table = conn.getTable(TableName.valueOf(""));
    }

    @Override
    public void writeRecord(org.apache.flink.api.java.tuple.Tuple2<String, String> record) throws IOException {
        Put put = new Put(Bytes.toBytes(record.f0));
        put.addColumn(Bytes.toBytes(""), Bytes.toBytes("test1"), Bytes.toBytes(record.f1));
        table.put(put);
    }

    @Override
    public void close() throws IOException {
        if (table != null) {
            table.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
