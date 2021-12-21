package BoardCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Board.BoardBean;
import dbconnection.DBConnection;

public class CategoryEdmDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	
public CategoryEdmDAO() {
		
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
		 
		 String sql = "select edmID from edm order by edmID desc";
		 
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
	 
	 public int write(String edmTitle, String u_ID, String edmContent) {
		 String sql = "insert into edm values(?,?,?,?,?,?)";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNewNext());
			 pstmt.setString(2, edmTitle);
			 pstmt.setString(3, u_ID);
			 pstmt.setString(4, getDate());
			 pstmt.setString(5, edmContent);
			 pstmt.setInt(6, 1);
			 return pstmt.executeUpdate();
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return -1; 
	 }
	 
	 public ArrayList<CategoryEdmBean> getList(int pageNumber){
		 String sql = "select * from edm where edmID < ? and edmAvailable = 1 order by edmID desc limit 10";
		 ArrayList<CategoryEdmBean> list = new ArrayList<CategoryEdmBean>();
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 CategoryEdmBean edmbean = new  CategoryEdmBean();
				 edmbean.setEdmID(rs.getInt(1));
				 edmbean.setEdmTitle(rs.getString(2));
				 edmbean.setUserID(rs.getString(3));
				 edmbean.setEdmDate(rs.getString(4));
				 edmbean.setEdmContent(rs.getString(5));
				 edmbean.setEdmAvailable(rs.getInt(6));
				 list.add(edmbean);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}return list;
		 
	 }
	 
	 public boolean nextPage(int pageNumber) {
		 String sql = "select * from edm where edmID < ? and edmAvailable = 1";
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
	 
	 public CategoryEdmBean getEdm(int edmID) {
		 
		 String sql = "select * from edm where edmID =?";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, edmID);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 CategoryEdmBean edmbean = new  CategoryEdmBean();
				 edmbean.setEdmID(rs.getInt(1));
				 edmbean.setEdmTitle(rs.getString(2));
				 edmbean.setUserID(rs.getString(3));
				 edmbean.setEdmDate(rs.getString(4));
				 edmbean.setEdmContent(rs.getString(5));
				 edmbean.setEdmAvailable(rs.getInt(6));
				 return edmbean;
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 
		 return null;
		 
	 }
	
	 public int getNewNext() {
		 String sql = "select edmID from edm order by edmID desc";
		 
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
	 
	 
	 public int update(int edmID, String edmTitle, String edmContent) {
		 String sql = "update edm set edmTitle =?, edmContent = ?, where edmID = ?";
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, edmTitle);
			 pstmt.setString(2, edmContent);
			 pstmt.setInt(3, edmID);
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return -1;
	 }
	 
	 public int delete(int edmID) {
		 
		 String sql = "update edm set edmAvailable = 0 where edmID = ?";
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, edmID);
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 
		 }
		 
		 return -1;
	 }
	


}
