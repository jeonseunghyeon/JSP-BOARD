package BoardCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			 
			 String sql = "select EdmID from Edm order by EdmID desc";
			 
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
		 
		 public int write(String EdmTitle, String u_ID, String EdmContent) {
			 String sql = "insert into Edm values(?,?,?,?,?,?)";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext());
				 pstmt.setString(2, EdmTitle);
				 pstmt.setString(3, u_ID);
				 pstmt.setString(4, getDate());
				 pstmt.setString(5, EdmContent);
				 pstmt.setInt(6, 1);
				 return pstmt.executeUpdate();
				 
			 }catch (Exception e) {
				e.printStackTrace();
			}
			 return -1; 
		 }
		 
		 
		 public ArrayList<CategoryEdmBean> getList(int pageNumber){
			 String sql = "select * from Edm where EdmID < ? and EdmAvailable = 1 order by EdmID desc limit 10";
			 ArrayList<CategoryEdmBean> list = new ArrayList<CategoryEdmBean>();
			 
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				 rs = pstmt.executeQuery();
				 while(rs.next()) {
					 CategoryEdmBean EdmBean = new CategoryEdmBean();
					 EdmBean.setEdmID(rs.getInt(1));
					 EdmBean.setEdmTitle(rs.getString(2));
					 EdmBean.setUserID(rs.getString(3));
					 EdmBean.setEdmDate(rs.getString(4));
					 EdmBean.setEdmContent(rs.getString(5));
					 EdmBean.setEdmAvailable(rs.getInt(6));
					 list.add(EdmBean);
				 }
			 }catch (Exception e) {
				e.printStackTrace();
			}return list;
			 
		 }
		 
		 public boolean nextPage(int pageNumber) {
			 String sql = "select * from Edm where EdmID < ? and EdmAvailable = 1";
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
		 
public CategoryEdmBean getEdm(int EdmID) {
			 
			 String sql = "select * from edm where edmID =?";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, EdmID);
				 rs = pstmt.executeQuery();
				 
				 if(rs.next()) {
					 CategoryEdmBean EdmBean = new CategoryEdmBean();
					 EdmBean.setEdmID(rs.getInt(1));
					 EdmBean.setEdmTitle(rs.getString(2));
					 EdmBean.setUserID(rs.getString(3));
					 EdmBean.setEdmDate(rs.getString(4));
					 EdmBean.setEdmContent(rs.getString(5));
					 EdmBean.setEdmAvailable(rs.getInt(6));
					 return EdmBean;
				 }
				 
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			 
			 
			 return null;
			 
		 }


}
