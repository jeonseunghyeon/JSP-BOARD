package BoardCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Board.BoardBean;
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
		 
		 String sql = "select DanceID from dance order by DanceID desc";
		 
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
	 
	 public int write(String danceTitle, String u_ID, String danceContent) {
		 String sql = "insert into dance values(?,?,?,?,?,?)";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNewNext());
			 pstmt.setString(2, danceTitle);
			 pstmt.setString(3, u_ID);
			 pstmt.setString(4, getDate());
			 pstmt.setString(5, danceContent);
			 pstmt.setInt(6, 1);
			 return pstmt.executeUpdate();
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return -1; 
	 }
	 
	 public ArrayList<CategoryDanceBean> getList(int pageNumber){
		 String sql = "select * from dance where danceID < ? and danceAvailable = 1 order by danceID desc limit 10";
		 ArrayList<CategoryDanceBean> list = new ArrayList<CategoryDanceBean>();
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 CategoryDanceBean danceBean = new CategoryDanceBean();
				 danceBean.setDanceID(rs.getInt(1));
				 danceBean.setDanceTitle(rs.getString(2));
				 danceBean.setUserID(rs.getString(3));
				 danceBean.setDanceDate(rs.getString(4));
				 danceBean.setDanceContent(rs.getString(5));
				 danceBean.setDanceAvailable(rs.getInt(6));
				 list.add(danceBean);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}return list;
		 
	 }
	 
	 public boolean nextPage(int pageNumber) {
		 String sql = "select * from dance where danceID < ? and danceAvailable = 1";
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
	 
	 public CategoryDanceBean getDance(int danceID) {
		 
		 String sql = "select * from dance where danceID =?";
		 
		 try {
			 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, danceID);
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
	
	 public int getNewNext() {
		 String sql = "select danceID from dance order by danceID desc";
		 
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
	 
	 
	 public int update(int danceID, String danceTitle, String danceContent) {
		 String sql = "update board set boardTitle =?, boardContent = ?, where boardID = ?";
		 
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, danceTitle);
			 pstmt.setString(2, danceContent);
			 pstmt.setInt(3, danceID);
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return -1;
	 }
	 
	 public int delete(int danceID) {
		 
		 String sql = "update dance set danceAvailable = 0 where danceID = ?";
		 try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, danceID);
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 
		 }
		 
		 return -1;
	 }
	
		 
		 
		 

}
