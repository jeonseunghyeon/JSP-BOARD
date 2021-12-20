package file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbconnection.DBConnection;

public class FileDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	public FileDAO() {
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DBConnection.getConnection();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public int upload(String fileName, String fileRealName, int bbsID) {
		String sql = "insert into board_file values(?,?,?)";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			pstmt.setInt(3,bbsID);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			
		}
		
		return -1;
	}

}
