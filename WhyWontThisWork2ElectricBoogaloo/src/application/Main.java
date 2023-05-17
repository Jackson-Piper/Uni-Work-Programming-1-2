package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private Controller controller;
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			System.out.println("1");
			controller = new Controller(primaryStage);
			System.out.println("4");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
