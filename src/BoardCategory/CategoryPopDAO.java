package BoardCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbconnection.DBConnection;

public class CategoryPopDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	
public CategoryPopDAO() {
		
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
		 
		 String sql = "select popID from pop order by popID desc";
		 
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
	 
	 public int write(String popTitle, String u_ID, String popContent) {
		 String sql = "insert into pop values(?,?,?,?,?,?)";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNewNext());
			 pstmt.setString(2, popTitle);
			 pstmt.setString(3, u_ID);
			 pstmt.setString(4, getDate());
			 pstmt.setString(5, popContent);
			 pstmt.setInt(6, 1);
			 return pstmt.executeUpdate();
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return -1; 
	 }
	 
	 public ArrayList<CategoryPopBean> getList(int pageNumber){
		 String sql = "select * from pop where popID < ? and popAvailable = 1 order by popID desc limit 10";
		 ArrayList<CategoryPopBean> list = new ArrayList<CategoryPopBean>();
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 CategoryPopBean popbean = new  CategoryPopBean();
				 popbean.setPopID(rs.getInt(1));
				 popbean.setPopTitle(rs.getString(2));
				 popbean.setUserID(rs.getString(3));
				 popbean.setPopDate(rs.getString(4));
				 popbean.setPopContent(rs.getString(5));
				 popbean.setPopAvailable(rs.getInt(6));
				 list.add(popbean);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}return list;
		 
	 }
	 
	 public boolean nextPage(int pageNumber) {
		 String sql = "select * from pop where popID < ? and popAvailable = 1";
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
	 
	 public CategoryPopBean getPop(int popID) {
		 
		 String sql = "select * from pop where popID =?";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, popID);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 CategoryPopBean popbean = new  CategoryPopBean();
				 popbean.setPopID(rs.getInt(1));
				 popbean.setPopTitle(rs.getString(2));
				 popbean.setUserID(rs.getString(3));
				 popbean.setPopDate(rs.getString(4));
				 popbean.setPopContent(rs.getString(5));
				 popbean.setPopAvailable(rs.getInt(6));
				 return popbean;
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 
		 return null;
		 
	 }
	
	 public int getNewNext() {
		 String sql = "select popID from pop order by popID desc";
		 
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
	 
	 
	 public int update(int popID, String popTitle, String popContent) {
		 String sql = "update pop set popTitle =?, popContent = ?, where popID = ?";
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, popTitle);
			 pstmt.setString(2, popContent);
			 pstmt.setInt(3, popID);
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return -1;
	 }
	 
	 public int delete(int popID) {
		 
		 String sql = "update pop set popAvailable = 0 where popID = ?";
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1,popID);
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 
		 }
		 
		 return -1;
	 }

}
