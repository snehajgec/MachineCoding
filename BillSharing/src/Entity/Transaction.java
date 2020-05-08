package Entity;

import java.util.List;
import java.util.UUID;

public class Transaction {
	private String transactionId;
	private Group group;
	private List<UserGroupTransaction> userGroupTransactionList;
	
	public Transaction(Group group, List<UserGroupTransaction> userGroupTransactionList) {
		this.group = group;
		this.userGroupTransactionList = userGroupTransactionList;
		this.transactionId = UUID.randomUUID().toString();
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<UserGroupTransaction> getUserGroupTransactionList() {
		return userGroupTransactionList;
	}

	public void setUserGroupTransactionList(List<UserGroupTransaction> userGroupTransactionList) {
		this.userGroupTransactionList = userGroupTransactionList;
	}
}
