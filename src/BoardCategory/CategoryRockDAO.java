package BoardCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbconnection.DBConnection;

public class CategoryRockDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	
	public CategoryRockDAO() {
		
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
			 
			 String sql = "select RockID from Rock order by RockID desc";
			 
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
		 
		 public int write(String rockTitle, String u_ID, String rockContent) {
			 String sql = "insert into Rock values(?,?,?,?,?,?)";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext());
				 pstmt.setString(2, rockTitle);
				 pstmt.setString(3, u_ID);
				 pstmt.setString(4, getDate());
				 pstmt.setString(5, rockContent);
				 pstmt.setInt(6, 1);
				 return pstmt.executeUpdate();
				 
			 }catch (Exception e) {
				e.printStackTrace();
			}
			 return -1; 
		 }
		 
		 
		 public ArrayList<CategoryRockBean> getList(int pageNumber){
			 String sql = "select * from Rock where RockID < ? and RockAvailable = 1 order by RockID desc limit 10";
			 ArrayList<CategoryRockBean> list = new ArrayList<CategoryRockBean>();
			 
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				 rs = pstmt.executeQuery();
				 while(rs.next()) {
					 CategoryRockBean RockBean = new CategoryRockBean();
					 RockBean.setRockID(rs.getInt(1));
					 RockBean.setRockTitle(rs.getString(2));
					 RockBean.setUserID(rs.getString(3));
					 RockBean.setRockDate(rs.getString(4));
					 RockBean.setRockContent(rs.getString(5));
					 RockBean.setRockAvailable(rs.getInt(6));
					 list.add(RockBean);
				 }
			 }catch (Exception e) {
				e.printStackTrace();
			}return list;
			 
		 }
		 
		 public boolean nextPage(int pageNumber) {
			 String sql = "select * from Rock where RockID < ? and RockAvailable = 1";
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
		 
		 
 public CategoryRockBean getRock(int rockID) {
			 
			 String sql = "select * from Rock where RockID =?";
			 
			 try {
				 
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, rockID);
				 rs = pstmt.executeQuery();
				 
				 if(rs.next()) {
					 CategoryRockBean RockBean = new CategoryRockBean();
					 RockBean.setRockID(rs.getInt(1));
					 RockBean.setRockTitle(rs.getString(2));
					 RockBean.setUserID(rs.getString(3));
					 RockBean.setRockDate(rs.getString(4));
					 RockBean.setRockContent(rs.getString(5));
					 RockBean.setRockAvailable(rs.getInt(6));
					 return RockBean;
				 }
				 
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			 
			 
			 return null;
			 
		 }
 
 
 public int getNewNext() {
	 String sql = "select rockID from rock order by rockID desc";
	 
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
 
 
 public int update(int rockID, String rockTitle, String rockContent) {
	 String sql = "update rock set rockTitle =?, rockContent = ?, where rockID = ?";
	 
	 try {
		 PreparedStatement pstmt = conn.prepareStatement(sql);
		 pstmt.setString(1, rockTitle);
		 pstmt.setString(2, rockContent);
		 pstmt.setInt(3, rockID);
		 return pstmt.executeUpdate();
		 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return -1;
 }
 
 public int delete(int rockID) {
	 
	 String sql = "update rock set rockAvailable = 0 where rockID = ?";
	 try {
		 PreparedStatement pstmt = conn.prepareStatement(sql);
		 pstmt.setInt(1,rockID);
		 return pstmt.executeUpdate();
		 
	 }catch(Exception e) {
		 e.printStackTrace();
		 
	 }
	 
	 return -1;
 }

}
