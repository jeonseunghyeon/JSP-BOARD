package BoardCategory;

import java.util.Date;

public class CategoryBalladeBean {
	
	int BalladeID;
	String BalladeTitle;
	String userID;
	String BalladeDate;
	String BalladeContent;
	int BalladeAvailable;
	
	public int getBalladeID() {
		return BalladeID;
	}
	public void setBalladeID(int balladeID) {
		BalladeID = balladeID;
	}
	public String getBalladeTitle() {
		return BalladeTitle;
	}
	public void setBalladeTitle(String balladeTitle) {
		BalladeTitle = balladeTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBalladeDate() {
		return BalladeDate;
	}
	public void setBalladeDate(String balladeDate) {
		BalladeDate = balladeDate;
	}
	public String getBalladeContent() {
		return BalladeContent;
	}
	public void setBalladeContent(String balladeContent) {
		BalladeContent = balladeContent;
	}
	public int getBalladeAvailable() {
		return BalladeAvailable;
	}
	public void setBalladeAvailable(int balladeAvailable) {
		BalladeAvailable = balladeAvailable;
	}

}
