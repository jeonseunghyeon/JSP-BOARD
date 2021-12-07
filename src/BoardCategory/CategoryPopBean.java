package BoardCategory;

import java.util.Date;

public class CategoryPopBean {

	int PopID;
	String PopTitle;
	String userID;
	String PopDate;
	
	String PopContent;
	int PopAvailable;
	
	public String getPopDate() {
		return PopDate;
	}
	public void setPopDate(String popDate) {
		PopDate = popDate;
	}
	public int getPopID() {
		return PopID;
	}
	public void setPopID(int popID) {
		PopID = popID;
	}
	public String getPopTitle() {
		return PopTitle;
	}
	public void setPopTitle(String popTitle) {
		PopTitle = popTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getPopContent() {
		return PopContent;
	}
	public void setPopContent(String popContent) {
		PopContent = popContent;
	}
	public int getPopAvailable() {
		return PopAvailable;
	}
	public void setPopAvailable(int popAvailable) {
		PopAvailable = popAvailable;
	}
}
