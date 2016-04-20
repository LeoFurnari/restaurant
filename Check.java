import java.sql.*;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/*
 * Check class holds all information for guest checks and payment
 */
public class Check {
	int checkNum; 
	private static ArrayList<Item> itemList;
	private static double subTotal;
	private static double total;
	private static double tax;
	private static final double taxRate = .06; //Sales tax rate for Michigan, can be changed for other places
	private static double discounts;
	private static double amountDue;
	private static double changeDueBack;
	private static boolean isCheckClosed;

	private VBox checkDisplay = new VBox();  //Perhaps have checkDisplay be updated every time getCheckDisplay() is called


	/*
	constructor opens the check by setting isCheckClosed to false
	 */

	public Check(){
		isCheckClosed = false;
		itemList = new ArrayList<Item>();
	}

	//This method prints the check as a receipt to the console (Or to a printer)
	public void print(){        
		/*
		 * Fix this later for formatting
		 */
		for(int i = 0; i < itemList.size(); i++){
			itemList.get(i);
			itemList.get(i);
			System.out.println(Item.getName() + ": $" +Item.getPrice());
		}
	}

	/*
	 * addItem takes newItem as a parameter and appends it to the check
	 */
	public void addItem(Item newItem){
		itemList.add(newItem);
		checkDisplay.getChildren().add(new Label(newItem.toString()));
		


	}

	/*
	 * calculateTotal() updates the total, discounts, and subTotal
	 */
	private static double calculateTotal(){
		subTotal = 0;
		for(int i = 0; i < itemList.size(); i++){
			itemList.get(i);
			subTotal += Item.getPrice();
		}
		tax = subTotal * taxRate;
		total = subTotal + tax;
		return total;
	}


	/*
	 * payCash deducts payment from amountDue. Then, if amountDue is reduced to 0,
	 * or below, change is generated, amountDue is set to 0, and the check is closed.
	 */
	private void payCash(double payment){
		amountDue = amountDue - payment;
		if(amountDue <= 0 ){
			changeDueBack = amountDue *- 1;
			amountDue = 0;
			closeCheck();
		}

	}
	/*
	 * closeCheck changes isCheckClosed to true
	 */
	private void closeCheck(){
		isCheckClosed = true;
	}

	private void payCredit(){
		/*
		 * Implement credit gratuity?
		 */

	}

	public VBox getDisplay(){
		return this.checkDisplay;
	}
	public static ResultSet queryBuilder(double itemPrice, String itemName, String itemType) throws ClassNotFoundException, SQLException{
		Connection conn = null;
//		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pos_database",
					"postgres", "123");
			Statement statement = conn.createStatement();
		
//			statement.executeUpdate("IF NOT EXISTS CREATE TABLE Item "
//					+ "("
//					+"Price decimal(10,2) NOT NULL, " 
//					+"Name varchar(100) NOT NULL, "
//					+"Type varchar(100) NOT NULL"
//					+ ");"
//					);
//			statement.executeUpdate("Item EXISTS INSERT INTO Item VALUES ("
//								+"'"+ Item.getPrice() + "',"
//								+"'"+ Item.getName() + "',"
//								+"'"+ Item.getType() + "'" 
//								+");"
//			);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println(e.getClass().getName()+": "+e.getMessage());
//			System.exit(0);
//		}
//		System.out.println("Opened database successfully");
		 ResultSet tableData = statement.executeQuery("select * from pg_tables where schemaname='public'");
		System.out.println(tableData);
		 return tableData;
	}

	public static void main(String[]args) throws ClassNotFoundException, SQLException{
		double price = Item.getPrice();
		String name = Item.getName();
		String type = Item.getType();
		queryBuilder(price, name, type);

	}
}