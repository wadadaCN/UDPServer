import java.sql.*;

public class MySQLConnect {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.112.194.211:3306/test";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "password";

    static Connection conn = null;

    static {
        connect();
    }

    public static void connect() {
        Statement stmt = null;
        try{
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 创建表
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS alertServer(" +
                    "   DeviceMac VARCHAR(20) NOT NULL," +
                    "   DeviceInfo VARCHAR(10) NOT NULL," +
                    "   GPS_N INT UNSIGNED," +
                    "   GPS_E INT UNSIGNED," +
                    "   Warn_Sta INT UNSIGNED," +
                    "   PRIMARY KEY ( DeviceMac )" +
                    ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt.executeUpdate(sql);

            stmt.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
        }
    }

    public static Connection getConnection(){
        return conn;
    }
}
