package View;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewRecords {
	BorderPane root;

	public void start(Stage primaryStage) {
		BorderPane bp = new BorderPane();
	    primaryStage.setTitle("My Medical - Records - View");
	   this.root = bp;
}
	public BorderPane getRoot() {
		return root;
}
	}
