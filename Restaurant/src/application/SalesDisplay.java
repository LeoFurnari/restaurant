

package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/*
 * Notes from Andrew: The plan is to make the implementation of the Menu, Options area
 * to be similar to the CheckDisplay class. This should make the code here cleaner, and the
 * other areas easier to manage. However, for now, I have a button in the menu pane that
 * adds to the check, and the display updates automatically. I think the best option to divide 
 * up the work would be to have an area for each person, and then we can integrate them
 * into the display afterwards.
 */
public class SalesDisplay extends Display{
	Pane optionsPane = new Pane();
	Pane menuPane = new Pane();
	Pane topPane = new Pane();
	VBox checkDisplay = new VBox(1);
	BorderPane display = new BorderPane();
	Check currentCheck;
	
	public SalesDisplay(Check newCheck){
		this.currentCheck = newCheck;
		System.out.println("SalesDisplay constructor is happening");
		
		
		makeMenuArea();
		makeCheckDisplay();
		makeDisplay();
		style();
	}
	
	protected void style(){
		/*
		 * Reads in CSS for each component of the display
		 */
		topPane.setStyle(scanCSS("topPaneCSS.txt"));
		optionsPane.setStyle(scanCSS("optionsPaneCSS.txt"));
		display.setStyle(scanCSS("mainPaneCSS.txt"));
		
		/*
		 * Some other styling methods are called
		 */
		
		display.setMinWidth(500);
		display.setMinHeight(500);
		
		menuPane.setMinWidth(300);
		menuPane.setMinHeight(400);
		
		}
	
	

	
	private void makeMenuArea(){
		System.out.println("makeMenuArea is happening");
		menuPane.getChildren().add(new Label("menu Area"));
		
		Button pancake =  new Button("Pancake");
		pancake.setOnMouseClicked( e ->{
			currentCheck.addItem(new Item(1.99, "Pancake"));
			updateDisplay();
		});
		menuPane.getChildren().add(pancake);
	}

	public BorderPane getDisplay(){
		System.out.println("getDisplay() is returning display");
		return this.display;
	}

	@Override
	void makeDisplay() {
		
		
		System.out.println("menuPane is being placed into display.Center");
		display.setCenter(menuPane);
		display.setLeft(checkDisplay);
		display.setRight(optionsPane);
		display.setTop(topPane);
		
		
	}

	protected void makeCheckDisplay(){
		
		Label tableNum = new Label("Table: "+this.currentCheck.checkNum);
		tableNum.setPadding(new Insets(2));
		
		Label serverName = new Label("Server: " + this.currentCheck.serverName);
		serverName.setPadding(new Insets(2));
		VBox infoPane = new VBox();
		VBox paymentPane = new VBox();
		Label tax = new Label(String.format("%50s%5.2f", "Tax:   ", this.currentCheck.tax));
		Label subTotal = new Label(String.format("%47s%5.2f", "Subtotal:   ", this.currentCheck.subTotal));
		Label total = new Label(String.format("%50s%5.2f", "Total:  ", this.currentCheck.total));
		paymentPane.getChildren().addAll(subTotal, tax, new Label("                                     -----------------"), total);
		paymentPane.setStyle("-fx-background-color: lavender;");
		infoPane.getChildren().addAll(tableNum, serverName);
		infoPane.setStyle("-fx-background-color: lavender;");
		
		this.checkDisplay.getChildren().addAll(infoPane, currentCheck.getListView(),paymentPane);
		this.checkDisplay.setPadding(new Insets(20));
	}

	void updateCheckDisplay(){
		

		VBox paymentPane = new VBox();
		Label tax = new Label(String.format("%50s%5.2f", "Tax:   ", this.currentCheck.tax));
		Label subTotal = new Label(String.format("%47s%5.2f", "Subtotal:   ", this.currentCheck.subTotal));
		Label total = new Label(String.format("%50s%5.2f", "Total:  ", this.currentCheck.total));
		paymentPane.getChildren().addAll(subTotal, tax, new Label("                                     -----------------"), total);
		paymentPane.setStyle("-fx-background-color: lavender;");
		
		this.checkDisplay.getChildren().set(2, paymentPane);

		
		
	}
	
	@Override
	void updateDisplay() {
		updateCheckDisplay();
		
	}
	

	
}
