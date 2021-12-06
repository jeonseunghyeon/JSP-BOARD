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
<<<<<<< HEAD
			String Password = "2468";
=======
			String Password = "chun3032";
>>>>>>> branch 'master' of https://github.com/jeonseunghyeon/JSP-BOARD.git
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, ID, Password);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//�ۼ����� �޼ҵ�
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
		
		return ""; //�����ͺ��̽� ����
	}
	
	//�Խñ� ��ȣ �ο� �޼ҵ�
	 public int getNext() {
		 
		 String sql = "select boardID from board order by boardID desc";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if(rs.next()) {
				 return rs.getInt(1)+1;
			 }
			 return 1; // ù ��° �Խù��� ���
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return -1; //�����ͺ��̽� ����
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
		 return -1; //�����ͺ��̽� ����
	 }
	
	
	

}
