package Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbconnection.DBConnection;

public class BoardDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	public BoardDAO() {
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DBConnection.getConnection();
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
			 return 1; 
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return -1; 
	 }
	 
	 public int write(int boID,String boardTitle, String u_ID, String boardContent) {
		 String sql = "insert into board values(?,?,?,?,?,?,?)";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, boID);
			 pstmt.setInt(2, getNext());
			 pstmt.setString(3, boardTitle);
			 pstmt.setString(4, u_ID);
			 pstmt.setString(5, getDate());
			 pstmt.setString(6, boardContent);
			 pstmt.setInt(7, 1);
			 return pstmt.executeUpdate();
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return -1; //�����ͺ��̽� ����
	 }
	 
	 public ArrayList<BoardBean> getList(int boID,int pageNumber){
		 String sql = "select * from board where boardID < ? and boID = ? boardAvailable = 1 order by boardID desc limit 10";
		 ArrayList<BoardBean> list = new ArrayList<BoardBean>();
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, boID);
			 pstmt.setInt(2, getNext() - (pageNumber - 1) * 10);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 BoardBean boardbean = new BoardBean();
				 boardbean.setBoID(rs.getInt(1));
				 boardbean.setBoardID(rs.getInt(2));
				 boardbean.setBoardTitle(rs.getString(3));
				 boardbean.setUserID(rs.getString(4));
				 boardbean.setBoardDate(rs.getString(5));
				 boardbean.setBoardContent(rs.getString(6));
				 boardbean.setBoardAvailable(rs.getInt(7));
				 list.add(boardbean);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}return list;
		 
	 }
	 
	 public boolean nextPage(int pageNumber) {
		 String sql = "select * from board where boardID < ? and boardAvailable = 1";
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 return true;
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return false;
	 }
	 
	 public BoardBean getboard(int boardID) {
		 
		 String sql = "select * from board where boardID =?";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, boardID);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 BoardBean boardbean = new BoardBean();
				 boardbean.setBoardID(rs.getInt(1));
				 boardbean.setBoardTitle(rs.getString(2));
				 boardbean.setUserID(rs.getString(3));
				 boardbean.setBoardDate(rs.getString(4));
				 boardbean.setBoardContent(rs.getString(5));
				 boardbean.setBoardAvailable(rs.getInt(6));
				 return boardbean;
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 
		 return null;
		 
	 }
	
	
	

}

