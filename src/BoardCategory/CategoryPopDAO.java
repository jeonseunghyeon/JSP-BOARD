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
			 
			 String sql = "select PopID from Pop order by PopID desc";
			 
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
		 
		 public int write(String PopTitle, String u_ID, String PopContent) {
			 String sql = "insert into Pop values(?,?,?,?,?,?)";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext());
				 pstmt.setString(2, PopTitle);
				 pstmt.setString(3, u_ID);
				 pstmt.setString(4, getDate());
				 pstmt.setString(5, PopContent);
				 pstmt.setInt(6, 1);
				 return pstmt.executeUpdate();
				 
			 }catch (Exception e) {
				e.printStackTrace();
			}
			 return -1; 
		 }
		 
		 
		 public ArrayList<CategoryPopBean> getList(int pageNumber){
			 String sql = "select * from Pop where PopID < ? and PopAvailable = 1 order by PopID desc limit 10";
			 ArrayList<CategoryPopBean> list = new ArrayList<CategoryPopBean>();
			 
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				 rs = pstmt.executeQuery();
				 while(rs.next()) {
					 CategoryPopBean PopBean = new CategoryPopBean();
					 PopBean.setPopID(rs.getInt(1));
					 PopBean.setPopTitle(rs.getString(2));
					 PopBean.setUserID(rs.getString(3));
					 PopBean.setPopDate(rs.getString(4));
					 PopBean.setPopContent(rs.getString(5));
					 PopBean.setPopAvailable(rs.getInt(6));
					 list.add(PopBean);
				 }
			 }catch (Exception e) {
				e.printStackTrace();
			}return list;
			 
		 }
		 
		 public boolean nextPage(int pageNumber) {
			 String sql = "select * from Pop where PopID < ? and PopAvailable = 1";
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
		 
		 
 public CategoryPopBean getPop(int PopID) {
			 
			 String sql = "select * from Pop where PopID =?";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, PopID);
				 rs = pstmt.executeQuery();
				 
				 if(rs.next()) {
					 CategoryPopBean PopBean = new CategoryPopBean();
					 PopBean.setPopID(rs.getInt(1));
					 PopBean.setPopTitle(rs.getString(2));
					 PopBean.setUserID(rs.getString(3));
					 PopBean.setPopDate(rs.getString(4));
					 PopBean.setPopContent(rs.getString(5));
					 PopBean.setPopAvailable(rs.getInt(6));
					 return PopBean;
				 }
				 
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			 
			 
			 return null;
			 
		 }

}
