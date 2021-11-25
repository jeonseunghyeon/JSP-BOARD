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
	
	public int login(String u_ID, String u_Password) {
		String sql ="select u_Password from member where u_ID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(0, u_ID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(u_Password)) {
					return 1;
				}else {
					return 0;
				} 
			}return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //¿À·ù
		
		
	}

}
