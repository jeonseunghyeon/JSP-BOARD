package dbconnection;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public  static Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {
        Connection conn = null;
        String URL = "jdbc:mysql://localhost:3306/jsp_web_commu";
        String ID = "root";

        String Password = "2468"; // 본인 패스워드

      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, ID, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
