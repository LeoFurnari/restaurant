package application;

import java.util.ArrayList;
/*
 * Check class holds all information for guest checks and payment
 */
public class Check {
	int checkNum; 
	ArrayList<Item> itemList;
	private double subTotal;
	private double total;
	private double discounts;
	private double amountDue;
	
	
	/*
	contructor
	*/
	
	public Check(){
		
	}
	
	public String print(){         //This method prints the check as a reciept to the console (Or to a printer)
		return null;
	}
	
	private double calculateTotal(){
		return 0;
	}
	/*
	 * SQL code 
	 * 
	String returnedThing; //button return thing: item name
	int orderNum; //button return thing: item order number
	double price; //button return thing: item price
	String insertString = "INSERT INTO Item VALUES " + returnedThing;
	String selectString = "SELECT FROM Item VALUES " + orderNum;
	*/
	
	/*
	 * Payment methods to clear guest-debt to restaurant
	 */
	private void payCash(){
		
	}
	
	private void payCredit(){
		/*
		 * Implement credit gratuity?
		 */
		
	}
	
}
