package Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserGroupTransaction {

	private String userGroupTransactionId;
	private User user;
	private Double amountPaid;
	private Group group;
	
	public UserGroupTransaction(User user, Double amountPaid, Group group) {
		this.user = user;
		this.amountPaid = amountPaid;
		this.group = group;
		this.userGroupTransactionId = UUID.randomUUID().toString();
	}

	public String getUserGroupTransactionId() {
		return userGroupTransactionId;
	}

	public void setUserGroupTransactionId(String userGroupTransactionId) {
		this.userGroupTransactionId = userGroupTransactionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
