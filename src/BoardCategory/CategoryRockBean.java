package BoardCategory;

import java.util.Date;

public class CategoryRockBean {

	int RockID;
	String RockTitle;
	String userID;
	String RockDate;
	String RockContent;

	int RockAvailable;
	
	public String getRockDate() {
		return RockDate;
	}
	public void setRockDate(String rockDate) {
		RockDate = rockDate;
	}
	public int getRockID() {
		return RockID;
	}
	public void setRockID(int rockID) {
		RockID = rockID;
	}
	public String getRockTitle() {
		return RockTitle;
	}
	public void setRockTitle(String rockTitle) {
		RockTitle = rockTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getRockContent() {
		return RockContent;
	}
	public void setRockContent(String rockContent) {
		RockContent = rockContent;
	}
	public int getRockAvailable() {
		return RockAvailable;
	}
	public void setRockAvailable(int rockAvailable) {
		RockAvailable = rockAvailable;
	}
}
