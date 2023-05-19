package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ErrorPopUp extends Application {
	public Button ok;
	private Popup popup;
	BorderPane root;
    public void prep(String errorMessage) {
        Label errorLabel = new Label(errorMessage);
        errorLabel.setWrapText(true);
        
        this.ok = new Button("OK");
     
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.add(errorLabel, 0, 0);
        grid.add(ok, 0, 1);
        BorderPane bp = new BorderPane();
		bp.setCenter(grid);
		bp.setBottom(ok);
		BorderPane.setMargin(ok, new Insets(10));
		bp.setAlignment(ok, Pos.CENTER);;
		
		this.root = bp;
    }
	@Override
	public void start(Stage primaryStage) {
			
			Stage popupStage = new Stage();
		    Scene scene = new Scene(this.root, 200, 100);
		    popupStage.setScene(scene);
		    popupStage.setTitle("Error");
		    popupStage.centerOnScreen();
		    ok.setOnAction(event -> popupStage.close());
		    popupStage.show();
		}
	}
