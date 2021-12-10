package BoardCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbconnection.DBConnection;

public class CategoryDanceDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	
	public CategoryDanceDAO() {
		
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
			 
			 String sql = "select DanceID from Dance order by DanceID desc";
			 
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
		 
		 public int write(String DanceTitle, String u_ID, String DanceContent) {
			 String sql = "insert into Dance values(?,?,?,?,?,?)";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext());
				 pstmt.setString(2, DanceTitle);
				 pstmt.setString(3, u_ID);
				 pstmt.setString(4, getDate());
				 pstmt.setString(5, DanceContent);
				 pstmt.setInt(6, 1);
				 return pstmt.executeUpdate();
				 
			 }catch (Exception e) {
				e.printStackTrace();
			}
			 return -1; 
		 }
		 
		 
		 public ArrayList<CategoryDanceBean> getList(int pageNumber){
			 String sql = "select * from Dance where DanceID < ? and DanceAvailable = 1 order by DanceID desc limit 10";
			 ArrayList<CategoryDanceBean> list = new ArrayList<CategoryDanceBean>();
			 
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				 rs = pstmt.executeQuery();
				 while(rs.next()) {
					 CategoryDanceBean DanceBean = new CategoryDanceBean();
					 DanceBean.setDanceID(rs.getInt(1));
					 DanceBean.setDanceTitle(rs.getString(2));
					 DanceBean.setUserID(rs.getString(3));
					 DanceBean.setDanceDate(rs.getString(4));
					 DanceBean.setDanceContent(rs.getString(5));
					 DanceBean.setDanceAvailable(rs.getInt(6));
					 list.add(DanceBean);
				 }
			 }catch (Exception e) {
				e.printStackTrace();
			}return list;
			 
		 }
		 
		 public boolean nextPage(int pageNumber) {
			 String sql = "select * from Dance where DanceID < ? and DanceAvailable = 1";
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
		 
		 
		 public CategoryDanceBean getDance(int DanceID) {
			 
			 String sql = "select * from dance where DanceID =?";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, DanceID);
				 rs = pstmt.executeQuery();
				 
				 if(rs.next()) {
					 CategoryDanceBean DanceBean = new CategoryDanceBean();
					 DanceBean.setDanceID(rs.getInt(1));
					 DanceBean.setDanceTitle(rs.getString(2));
					 DanceBean.setUserID(rs.getString(3));
					 DanceBean.setDanceDate(rs.getString(4));
					 DanceBean.setDanceContent(rs.getString(5));
					 DanceBean.setDanceAvailable(rs.getInt(6));
					 return DanceBean;
				 }
				 
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			 
			 
			 return null;
			 
		 }
		 
		 
		 

}
