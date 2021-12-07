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
			 
			 String sql = "select HiphopID from Hiphop order by HiphopID desc";
			 
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
		 
		 public int write(String HiphopTitle, String u_ID, String HiphopContent) {
			 String sql = "insert into Hiphop values(?,?,?,?,?,?)";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext());
				 pstmt.setString(2, HiphopTitle);
				 pstmt.setString(3, u_ID);
				 pstmt.setString(4, getDate());
				 pstmt.setString(5, HiphopContent);
				 pstmt.setInt(6, 1);
				 return pstmt.executeUpdate();
				 
			 }catch (Exception e) {
				e.printStackTrace();
			}
			 return -1; 
		 }
		 
		 
		 public ArrayList<CategoryHiphopBean> getList(int pageNumber){
			 String sql = "select * from Hiphop where HiphopID < ? and HiphopAvailable = 1 order by HiphopID desc limit 10";
			 ArrayList<CategoryHiphopBean> list = new ArrayList<CategoryHiphopBean>();
			 
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				 rs = pstmt.executeQuery();
				 while(rs.next()) {
					 CategoryHiphopBean HiphopBean = new CategoryHiphopBean();
					 HiphopBean.setHiphopID(rs.getInt(1));
					 HiphopBean.setHiphopTitle(rs.getString(2));
					 HiphopBean.setUserID(rs.getString(3));
					 HiphopBean.setHiphopDate(rs.getString(4));
					 HiphopBean.setHiphopContent(rs.getString(5));
					 HiphopBean.setHiphopAvailable(rs.getInt(6));
					 list.add(HiphopBean);
				 }
			 }catch (Exception e) {
				e.printStackTrace();
			}return list;
			 
		 }
		 
		 public boolean nextPage(int pageNumber) {
			 String sql = "select * from Hiphop where HiphopID < ? and HiphopAvailable = 1";
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
}
