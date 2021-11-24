package sec.ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public UserDAO() {
		
		
		try {
			
			String URL ="jdbc:mysql://localhost:3306/jsp_web_commu";
			String id = "root";
			String password ="chun3032";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(URL,id,password);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int login(String u_id, String u_password) {
		String sql ="select u_Password from";
		
		
		
		
		
		return -2;
	}

}
