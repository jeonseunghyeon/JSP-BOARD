package Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	public BoardDAO() {
		
		try {
			
			String URL = "jdbc:mysql://localhost:3306/jsp_web_commu?useUnicode=true&characterEncoding=UTF-8";
			String ID = "root";
			String Password = "2468";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, ID, Password);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//占쌜쇽옙占쏙옙占쏙옙 占쌨소듸옙
	public String getDate() {
		String sql = "select now()";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return ""; //占쏙옙占쏙옙占싶븝옙占싱쏙옙 占쏙옙占쏙옙
	}
	
	//占쌉시깍옙 占쏙옙호 占싸울옙 占쌨소듸옙
	 public int getNext() {
		 
		 String sql = "select boardID from board order by boardID desc";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if(rs.next()) {
				 return rs.getInt(1)+1;
			 }
			 return 1; // 첫 占쏙옙째 占쌉시뱄옙占쏙옙 占쏙옙占�
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return -1; //占쏙옙占쏙옙占싶븝옙占싱쏙옙 占쏙옙占쏙옙
	 }
	 
	 public int write(String boardTitle, String u_ID, String boardContent) {
		 String sql = "insert into board values(?,?,?,?,?,?)";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNext());
			 pstmt.setString(2, boardTitle);
			 pstmt.setString(3, u_ID);
			 pstmt.setString(4, getDate());
			 pstmt.setString(5, boardContent);
			 pstmt.setInt(6, 1);
			 return pstmt.executeUpdate();
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return -1; //占쏙옙占쏙옙占싶븝옙占싱쏙옙 占쏙옙占쏙옙
	 }
	
	
	
}
