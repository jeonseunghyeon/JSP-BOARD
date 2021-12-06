package dbconnection;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public  static Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        String server = "jdbc:mysql://localhost:3306/jdbc";
        String user_name = "root";
        String password = "2468";
//        String password = ""; // 본인 패스워드

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(server, user_name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
