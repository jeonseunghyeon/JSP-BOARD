package BoardCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Board.BoardBean;
import dbconnection.DBConnection;

public class CategoryBalladeDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	
	public CategoryBalladeDAO() {
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DBConnection.getConnection();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	

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
			
			return ""; 
		}
		
		 public int getNext() {
			 
			 String sql = "select BalladeID from Ballade order by BalladeID desc";
			 
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
		 
		 public int write(String balladeTitle, String u_ID, String balladeContent) {
			 String sql = "insert into Ballade values(?,?,?,?,?,?)";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext());
				 pstmt.setString(2, balladeTitle);
				 pstmt.setString(3, u_ID);
				 pstmt.setString(4, getDate());
				 pstmt.setString(5, balladeContent);
				 pstmt.setInt(6, 1);
				 return pstmt.executeUpdate();
				 
			 }catch (Exception e) {
				e.printStackTrace();
			}
			 return -1; 
		 }
		 
		 
		 public ArrayList<CategoryBalladeBean> getList(int pageNumber){
			 String sql = "select * from Ballade where BalladeID < ? and BalladeAvailable = 1 order by BalladeID desc limit 10";
			 ArrayList<CategoryBalladeBean> list = new ArrayList<CategoryBalladeBean>();
			 
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				 rs = pstmt.executeQuery();
				 while(rs.next()) {
					 CategoryBalladeBean BalladeBean = new CategoryBalladeBean();
					 BalladeBean.setBalladeID(rs.getInt(1));
					 BalladeBean.setBalladeTitle(rs.getString(2));
					 BalladeBean.setUserID(rs.getString(3));
					 BalladeBean.setBalladeDate(rs.getString(4));
					 BalladeBean.setBalladeContent(rs.getString(5));
					 BalladeBean.setBalladeAvailable(rs.getInt(6));
					 list.add(BalladeBean);
				 }
			 }catch (Exception e) {
				e.printStackTrace();
			}return list;
			 
		 }
		 
		 public boolean nextPage(int pageNumber) {
			 String sql = "select * from Ballade where BalladeID < ? and BalladeAvailable = 1";
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
		 
		 public CategoryBalladeBean getBallade(int BalladeID) {
			 
			 String sql = "select * from ballade where BalladeID =?";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, BalladeID);
				 rs = pstmt.executeQuery();
				 
				 if(rs.next()) {
					 CategoryBalladeBean BalladeBean = new CategoryBalladeBean();
					 BalladeBean.setBalladeID(rs.getInt(1));
					 BalladeBean.setBalladeTitle(rs.getString(2));
					 BalladeBean.setUserID(rs.getString(3));
					 BalladeBean.setBalladeDate(rs.getString(4));
					 BalladeBean.setBalladeContent(rs.getString(5));
					 BalladeBean.setBalladeAvailable(rs.getInt(6));
					 return BalladeBean;
				 }
				 
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			 
			 
			 return null;
			 
		 }
		 

}
