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
	private static void calculateTotal(){
		subTotal = 0;
		for(int i = 0; i < itemList.size(); i++){
			itemList.get(i);
			subTotal += Item.getPrice();
		}
		tax = subTotal * taxRate;
		total = subTotal + tax;
		Item.setPrice(total);
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
	public static ResultSet queryBuilder(double itemPrice, String itemName, String itemType, int custNum) throws ClassNotFoundException, SQLException{
		Connection conn = null;

		//		try{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pos_database",
				"postgres", "123");
		DatabaseMetaData md = conn.getMetaData();
		ResultSet tables = md.getTables(null, null, "Item", null);
		Statement statement = conn.createStatement();
		if(!tables.next()){
			statement.executeUpdate("CREATE TABLE Item "
					+ "("
					+"Price decimal(10,2) NOT NULL, " 
					+"Name varchar(100) NOT NULL, "
					+"Type varchar(100) NOT NULL, "
					+"CustNum smallint NOT NULL);"
					);
		}
		else{
			statement.executeUpdate("INSERT INTO Item VALUES ("
					+"'"+ itemPrice + "',"
					+"'"+ itemName + "',"
					+"'"+ itemType + "'" 
					+"'" + custNum + "');"
					);
		}

		//		System.out.println("Opened database successfully");
		ResultSet tableData = statement.executeQuery("select * from Item");
		ResultSetMetaData rsmd = tableData.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		while (tableData.next()) {
		    for(int i = 1; i < columnsNumber + 1; i++)
		        System.out.print(tableData.getString(i) + " ");
		    System.out.println();
		}
		return tableData;
	}

	public static void main(String[]args) throws ClassNotFoundException, SQLException{
		double price = Item.getPrice();
		String name = Item.getName();
		String type = Item.getType();
		int custNum = Item.getCustNum();
		queryBuilder(price, name, type, custNum);

	}
}