package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {

 public static Connection getConnection() throws SQLException {
    try { Class.forName("com.mysql.cj.jdbc.Driver"); }
    catch (ClassNotFoundException e) { throw new RuntimeException("MySQL JDBC Driver not found", e); }

    String url  = System.getenv("DB_URL");   // jdbc:mysql://xxx.rds.amazonaws.com:3306/todoApp?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&useUnicode=true&characterEncoding=UTF-8
    String user = System.getenv("DB_USER");
    String pass = System.getenv("DB_PASS");
    return DriverManager.getConnection(url, user, pass);
 }

}
