package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 * Check class holds all information for guest checks and payment. It is a List of Items, fields that hold information on money,
 * and a CheckDisplay, which is handled in the CheckDisplay class.
 */
public class Check {

	/*
	 * itemList field and a unique checkNum identifier
	 */
	int checkNum, tableNum;
	protected ArrayList<Item> itemList;
	protected ListView<String> checkList;
	private boolean isCheckClosed;
	protected String serverName = "Andrew B";
	

	/*
	 * Fields pertaining to $
	 */
	protected double subTotal, total, tax, discounts, amountDue, changeDueBack;
	protected final double taxRate = .06; // Sales tax rate for Michigan, can be
										// changed for other places

	public Check() {
		/*
		 * constructor opens the check by setting isCheckClosed to false,
		 * initializes the itemList
		 */

		isCheckClosed = false;
		checkList = new ListView<>();
		itemList = new ArrayList<Item>();

		/*
		 * Implement assignment for checkNum and tableNum later. literal values
		 * are used right now for display purposes
		 */
		checkNum = 3512;
		tableNum = 13;

	}

	public void print() {

		// This method prints the check as a reciept to the console (Or to a
		// printer)

	}

	public void addItem(Item newItem) {

		/*
		 * addItem takes newItem as a parameter and appends it to the check.
		 */

		// Item is added to ItemList and display is updated
		itemList.add(newItem);
		checkList.getItems().add(newItem.toString());
		// Total is recalculated
		calculateTotal();

	}

	public Item getLastItem() {

		return itemList.get(itemList.size() - 1);

	}
	
	public ListView getListView(){
		return this.checkList;
	}

	public boolean isCheckEmpty() {
		return itemList.isEmpty();
	}

	private void payCash(double payment) {
		/*
		 * payCash deducts payment from amountDue. Then, if amountDue is reduced
		 * to 0, or below, change is generated, amountDue is set to 0, and the
		 * check is closed.
		 */

		amountDue = amountDue - payment;
		if (amountDue <= 0) {
			changeDueBack = amountDue * -1;
			amountDue = 0;
			closeCheck();
		}

	}

	private void payCredit() {
		/*
		 * Implement credit gratuity?
		 */

	}

	private void calculateTotal() {
		/*
		 * calculateTotal() updates the total, discounts, and subTotal
		 */

		subTotal = 0;
		for (int i = 0; i < itemList.size(); i++) {
			subTotal += itemList.get(i).getPrice();
		}
		tax = subTotal * taxRate;
		total = subTotal + tax;
	}

	private void closeCheck() {
		/*
		 * closeCheck changes isCheckClosed to true
		 */
		isCheckClosed = true;
	}

	/*
	 * SQL code
	 * 
	 * String returnedThing; //button return thing: item name int orderNum;
	 * //button return thing: item order number double price; //button return
	 * thing: item price String insertString = "INSERT INTO Item VALUES " +
	 * returnedThing; String selectString = "SELECT FROM Item VALUES " +
	 * orderNum;
	 */

}
