package application;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EditRecord {
		BorderPane root;

		public void start(Stage primaryStage) {
			BorderPane bp = new BorderPane();
		    primaryStage.setTitle("My Medical - Records - Edit");
		   this.root = bp;
	}
		public BorderPane getRoot() {
			return root;
	}
		}

