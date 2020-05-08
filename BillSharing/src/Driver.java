import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Service.BillSharingService;

public class Driver {

	public static void main(String[] args) throws Exception {
		BillSharingService billSharingService = new BillSharingService();

		billSharingService.createGroup("Flipkart_Party");
		billSharingService.addPersons("Flipkart_Party", Arrays.asList("Ashish", "Devrat", "Anmol", "Abhishek"));
		billSharingService.createGroup("School_Friends");
		billSharingService.addPersons("School_Friends", Arrays.asList("Ashish", "Rohit"));
		
		Map<String, Double> userTransactionList = new HashMap<String, Double>();
		userTransactionList.put("Ashish", 40D);
		userTransactionList.put("Devrat", 160D);
		
		billSharingService.addTransaction("Flipkart_Party", userTransactionList);
		
		userTransactionList = new HashMap<String, Double>();
		userTransactionList.put("Ashish", 30D);
		userTransactionList.put("Abhishek", 60D);
		userTransactionList.put("Anmol", 110D);		
		
		billSharingService.addTransaction("Flipkart_Party", userTransactionList);
		
		userTransactionList = new HashMap<String, Double>();
		userTransactionList.put("Ashish", 100D);
		userTransactionList.put("Rohit", 60D);
		
		billSharingService.addTransaction("School_Friends", userTransactionList);
		
		System.out.println("Balance of Ashish in group : " + billSharingService.getBalanceOfPersonInGroup("Flipkart_Party", "Ashish"));
		
		Map<String, Double> mapUser = billSharingService.getBalanceOfPersonsInGroup("Flipkart_Party"); 
		for(String user: mapUser.keySet()) {
			System.out.println("Balance of "  + user  + " in group : " + mapUser.get(user));
		}
		
		System.out.println("Balance of Ashish in all group : " + billSharingService.getBalanceAcrossAllGroups("Ashish"));
	}

}
