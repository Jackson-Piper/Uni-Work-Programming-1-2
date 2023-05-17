package View;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class mainScreen extends Application{	
	private BorderPane root;
	private	Label welcome;
	
	
	@Override
	public void start(Stage primaryStage){
	BorderPane bp = new BorderPane();
    primaryStage.setTitle("My Medical");
    welcome.setPadding(new Insets(10));
    bp.setCenter(welcome);
    BorderPane.setAlignment(welcome, Pos.TOP_LEFT);
    bp.setPadding(new Insets(10.0));
    this.root = bp;
	}
	
	public void prep(String firstName, String lastName) {
		welcome = new Label(" Welcome "+firstName +" "+lastName);
	}
	
	public BorderPane getRoot() {
		return this.root;
	}
    
    public static void main(String[] args) {
        launch(args);
    }

    }
