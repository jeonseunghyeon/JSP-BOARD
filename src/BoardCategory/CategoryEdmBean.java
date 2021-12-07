package BoardCategory;

import java.util.Date;

public class CategoryEdmBean {
	
	int EdmID;
	String EdmTitle;
	String userID;
	String EdmDate;
	String EdmContent;
	int EdmAvailable;
	public int getEdmID() {
		return EdmID;
	}
	public void setEdmID(int edmID) {
		EdmID = edmID;
	}
	public String getEdmTitle() {
		return EdmTitle;
	}
	public void setEdmTitle(String edmTitle) {
		EdmTitle = edmTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getEdmDate() {
		return EdmDate;
	}
	public void setEdmDate(String edmDate) {
		EdmDate = edmDate;
	}
	public String getEdmContent() {
		return EdmContent;
	}
	public void setEdmContent(String edmContent) {
		EdmContent = edmContent;
	}
	public int getEdmAvailable() {
		return EdmAvailable;
	}
	public void setEdmAvailable(int edmAvailable) {
		EdmAvailable = edmAvailable;
	}

}
