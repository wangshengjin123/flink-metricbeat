package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import Model_Mem.MemoryModel;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

public class PhoenixWriter extends RichSinkFunction<MemoryModel> {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
/*    String username = "xxxxxx";
    String password = "xxxxxx";*/
//没成功
    String drivername = "org.apache.phoenix.jdbc.PhoenixDriver";
    String url = "jdbc:phoenix:172.17.0.56:2181";
    Properties props = new Properties();
    @Override
    public void invoke(MemoryModel memoryModel) throws Exception {
        Class.forName(drivername);
        connection = DriverManager.getConnection(url, props);
        String sql = "insert into test ( free,total.used.pct) values(?,?,?,?);" ;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }

    }
}