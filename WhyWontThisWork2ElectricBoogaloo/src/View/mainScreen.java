package View;

import javafx.application.Application;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class mainScreen extends Application{	
	private BorderPane root;
	public MenuItem ViewProfile;
	
	
	
	@Override
	public void start(Stage primaryStage){
	BorderPane bp = new BorderPane();
    primaryStage.setTitle("My Medical");
    this.root = bp;
}
	
	public BorderPane getRoot() {
		return this.root;
	}
    
    public static void main(String[] args) {
        launch(args);
    }

    }
