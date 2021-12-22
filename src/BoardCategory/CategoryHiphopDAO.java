package BoardCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbconnection.DBConnection;

public class CategoryHiphopDAO {

	private Connection conn;
	private ResultSet rs;
	
	
public CategoryHiphopDAO() {
		
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
		 
		 String sql = "select hiphopID from hiphop order by hiphopID desc";
		 
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
	 
	 public int write(String hiphopTitle, String u_ID, String hiphopContent) {
		 String sql = "insert into hiphop values(?,?,?,?,?,?)";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNewNext());
			 pstmt.setString(2, hiphopTitle);
			 pstmt.setString(3, u_ID);
			 pstmt.setString(4, getDate());
			 pstmt.setString(5, hiphopContent);
			 pstmt.setInt(6, 1);
			 return pstmt.executeUpdate();
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return -1; 
	 }
	 
	 public ArrayList<CategoryHiphopBean> getList(int pageNumber){
		 String sql = "select * from hiphop where hiphopID < ? and hiphopAvailable = 1 order by hiphopID desc limit 10";
		 ArrayList<CategoryHiphopBean> list = new ArrayList<CategoryHiphopBean>();
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 CategoryHiphopBean hiphopbean = new  CategoryHiphopBean();
				 hiphopbean.setHiphopID(rs.getInt(1));
				 hiphopbean.setHiphopTitle(rs.getString(2));
				 hiphopbean.setUserID(rs.getString(3));
				 hiphopbean.setHiphopDate(rs.getString(4));
				 hiphopbean.setHiphopContent(rs.getString(5));
				 hiphopbean.setHiphopAvailable(rs.getInt(6));
				 list.add(hiphopbean);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}return list;
		 
	 }
	 
	 public boolean nextPage(int pageNumber) {
		 String sql = "select * from hiphop where hiphopID < ? and hiphopAvailable = 1";
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
	 
	 public CategoryHiphopBean getHiphop(int hiphopID) {
		 
		 String sql = "select * from hiphop where hiphopID =?";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, hiphopID);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 CategoryHiphopBean hiphopbean = new  CategoryHiphopBean();
				 hiphopbean.setHiphopID(rs.getInt(1));
				 hiphopbean.setHiphopTitle(rs.getString(2));
				 hiphopbean.setUserID(rs.getString(3));
				 hiphopbean.setHiphopDate(rs.getString(4));
				 hiphopbean.setHiphopContent(rs.getString(5));
				 hiphopbean.setHiphopAvailable(rs.getInt(6));
				 return hiphopbean;
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 
		 return null;
		 
	 }
	
	 public int getNewNext() {
		 String sql = "select hiphopID from hiphop order by hiphopID desc";
		 
		 try {
			 
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			 	rs = pstmt.executeQuery();
			 	
			 	if(rs.next()) {
					return rs.getInt(1)+1;
				}
			 	
			 	return 1;
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return -1;
	 }
	 
	 
	 public int update(int hiphopID, String hiphopTitle, String hiphopContent) {
		 String sql = "update hiphop set hiphopTitle =?, hiphopContent = ?, where hiphopID = ?";
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, hiphopTitle);
			 pstmt.setString(2, hiphopContent);
			 pstmt.setInt(3, hiphopID);
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return -1;
	 }
	 
	 public int delete(int hiphopID) {
		 
		 String sql = "update hiphop set hiphopAvailable = 0 where hiphopID = ?";
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, hiphopID);
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 
		 }
		 
		 return -1;
	 }
}
