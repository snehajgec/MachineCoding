package Entity;

import java.util.List;
import java.util.UUID;

public class UserGroup {
	private String userGroupId;
	private User user;
	private Group group;
	
	public UserGroup(User user, Group group) {
		this.user = user;
		this.group = group;
		this.userGroupId = UUID.randomUUID().toString();
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
