package View;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DeleteRecords {
	BorderPane root;

	public void start(Stage primaryStage) {
		BorderPane bp = new BorderPane();
	    primaryStage.setTitle("My Medical - Records - Delete");
	   this.root = bp;
}
	public BorderPane getRoot() {
		return root;
}
	}

