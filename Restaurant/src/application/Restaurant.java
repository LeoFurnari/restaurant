package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Restaurant extends Application{
	ArrayList<Check> checks;
	
	
	public Restaurant(){
		
	}
	@Override
	public void start(Stage primaryStage){
		//Two scenes. One being a prompt, and the other being a salesScene
		Stage window = primaryStage;
		Scene initialScene;
		Scene salesScene;
		
		Button table1 = new Button("Table 1");
		salesScene = new Scene(new SalesDisplay(new Check()).getDisplay());
		initialScene = new Scene(table1);
		table1.setOnAction(e -> {
			System.out.println("Inner Class is happening table1 has been clicked; sales Scene is being shown");
			window.setScene(salesScene);
			
		});
		
		window.setScene(initialScene);
		window.setTitle("Restaurant");
		window.show();
		
		
	
	}
	
	public static void main(String args[]){
		launch();
	}

}
