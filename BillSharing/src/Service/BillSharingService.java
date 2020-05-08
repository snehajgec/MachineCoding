package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Entity.Group;
import Entity.User;
import Entity.UserGroup;
import Entity.UserGroupTransaction;

public class BillSharingService {

	private List<UserGroup> userGroupList2;
	private Map<String, Group> groupList;
	private Map<String, User> userList;
	private Map<Group, List<User>> userGroupList;
	private List<UserGroupTransaction> userGroupTransactionList;
	private Map<Group, List<UserGroupTransaction>> userGroupTransactionMap;

	public BillSharingService() {
		userGroupList2 = new ArrayList<UserGroup>();
		groupList = new HashMap<String, Group>();
		userList = new HashMap<String, User>();
		userGroupList = new HashMap<Group, List<User>>();
		userGroupTransactionList = new ArrayList<UserGroupTransaction>();
		userGroupTransactionMap = new HashMap<Group, List<UserGroupTransaction>>();
	}

	public void createGroup(String groupName) throws Exception {
		Group group;
		if (this.groupList.containsKey(groupName)) {
			throw new Exception("Group already exists!!");
		}
		group = new Group(groupName);
		this.groupList.put(groupName, group);
	}

	public void addPersons(String groupName, List<String> userList) {
		Group group = this.groupList.get(groupName);
		List<User> users = userList.stream().map(userName -> {
			User user;
			if (!this.userList.containsKey(userName)) {
				user = new User(userName);
				this.userList.put(userName, user);
			} else {
				user = this.userList.get(userName);
			}
			UserGroup userGroup = new UserGroup(user, group);
			this.userGroupList2.add(userGroup);
			return user;
		}).collect(Collectors.toList());

		this.userGroupList.put(group, users);
	}

	public void addTransaction(String groupName, Map<String, Double> userGroupTransactionList) {
		Group group = this.groupList.get(groupName);
		Boolean isGroupTrans = false;

		List<User> usersOfGroup = new ArrayList<User>();
		List<UserGroupTransaction> usersOfGroupTransaction = new ArrayList<UserGroupTransaction>();
		if (userGroupTransactionMap.containsKey(group)) {
			isGroupTrans = true;
			usersOfGroupTransaction = userGroupTransactionMap.get(group);
		}
		//List<UserGroupTransaction> userGroupTransactions = new ArrayList<UserGroupTransaction>();
		for (String temp : userGroupTransactionList.keySet()) {
			User user = this.userList.get(temp);
			Double amountPaid = 0D;
			Boolean isUserInGroup = false;
			UserGroupTransaction userGroupTransaction = null;
			if (isGroupTrans == true) {
				for (UserGroupTransaction tempUser : usersOfGroupTransaction) {
					if (tempUser.getUser().equals(user)) {
						isUserInGroup = true;
						tempUser.setAmountPaid(tempUser.getAmountPaid() + userGroupTransactionList.get(temp));
						userGroupTransaction = tempUser;
						break;
					}
				}
				if (isUserInGroup == false) {
					amountPaid = userGroupTransactionList.get(temp);
					userGroupTransaction = new UserGroupTransaction(user, amountPaid, group);
					this.userGroupTransactionList.add(userGroupTransaction);
					usersOfGroupTransaction.add(userGroupTransaction);
				}
			} else {
				amountPaid = userGroupTransactionList.get(temp);
				userGroupTransaction = new UserGroupTransaction(user, amountPaid, group);
				// maybe not needed
				this.userGroupTransactionList.add(userGroupTransaction);
				usersOfGroupTransaction.add(userGroupTransaction);
			}
		}

		this.userGroupTransactionMap.put(group, usersOfGroupTransaction);
	}

	public Double getBalanceOfPersonInGroup(String groupName, String userName) {
		Group group = this.groupList.get(groupName);
		double sum = 0;
		double amountPaidByUser = 0;
		List<UserGroupTransaction> userGroupTransactions = this.userGroupTransactionMap.get(group);
		for (UserGroupTransaction userGroupTransaction : userGroupTransactions) {
			sum = sum + userGroupTransaction.getAmountPaid();
			if (userGroupTransaction.getUser().getName().equals(userName)) {
				amountPaidByUser = userGroupTransaction.getAmountPaid();
			}
		}
		double indiContribution = sum / userGroupTransactions.size();
		return (amountPaidByUser - indiContribution);
	}

	public Map<String, Double> getBalanceOfPersonsInGroup(String groupName) {
		Group group = this.groupList.get(groupName);
		double sum = 0;
		List<UserGroupTransaction> userGroupTransactions = this.userGroupTransactionMap.get(group);
		Map<String, Double> userBalanceInGroup = new HashMap<String, Double>();
		for (UserGroupTransaction userGroupTransaction : userGroupTransactions) {
			sum = sum + userGroupTransaction.getAmountPaid();
			userBalanceInGroup.put(userGroupTransaction.getUser().getName(), 0D);
		}

		double indiContribution = sum / userGroupTransactions.size();

		for (UserGroupTransaction userGroupTransaction : userGroupTransactions) {
			userBalanceInGroup.put(userGroupTransaction.getUser().getName(),
					userGroupTransaction.getAmountPaid() - indiContribution);
		}

		return userBalanceInGroup;
	}

	public Double getBalanceAcrossAllGroups(String userName) {
		List<UserGroup> groupOfUser = this.userGroupList2.stream()
				.filter(temp -> temp.getUser().getName().equals(userName)).collect(Collectors.toList());
		Double sum = 0D;
		for (UserGroup userGroup : groupOfUser) {
			sum = sum + getBalanceOfPersonInGroup(userGroup.getGroup().getGroupName(), userName);
		}

		return sum;
	}
}
