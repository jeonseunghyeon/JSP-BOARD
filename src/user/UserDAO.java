package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbconnection.DBConnection;

public class UserDAO {
	
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public UserDAO() {
		
		try {
			
			conn = DBConnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int login(String u_ID, String u_Password) {
		String sql ="select u_Password from member where u_ID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_ID);
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
		return -2; //����
		
	}
	
	public int join(UserBean user) {
		
		String sql="insert into member(u_ID,u_Password,u_name) values(?,?,?)";

		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,user.getU_ID());
			pstmt.setString(2,user.getU_Password());
			pstmt.setString(3,user.getU_name());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}

}
