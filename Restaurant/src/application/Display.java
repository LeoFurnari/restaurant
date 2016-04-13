package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class Display<E extends Node>{
	
	
	
	/*
	 * Display is an abstract class that will enable all subclasses to generate its display component,
	 * and then be able to update it as well. Functionally, makeDisplay should be the base-case of the
	 * display, and updateDisplay will update the display node and all children of the node.
	 */
	
	public Display(){
	
	}
	
	protected String scanCSS(String filename){
		try{
			Scanner scan = new Scanner(new File(filename));
			scan.useDelimiter("[$]");
			String result = scan.next();
			scan.close();
			return result;
		}
		catch(FileNotFoundException e){
			System.out.println("File Does Not Exist");
		}
		return null;
		
	}
	
	abstract void makeDisplay() ;
	abstract void updateDisplay();
	abstract E getDisplay();
	protected abstract void style();

}
