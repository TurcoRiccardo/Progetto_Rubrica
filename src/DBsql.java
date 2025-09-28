import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;


public class DBsql {
    private static Connection conn;


    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            Properties props = new Properties();


            props.load(new FileInputStream(Main.PROPERTIES_FILE));
            String host = props.getProperty("db.host");
            String port = props.getProperty("db.port");
            String dbName = props.getProperty("db.name");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}