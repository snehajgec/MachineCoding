package Entity;

import java.util.UUID;

public class Group {

	private String groupName;
    private String groupId;
    
    public Group(String groupName) {
    	this.groupId = UUID.randomUUID().toString();
    	this.groupName = groupName;
    }

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
    
    
}
