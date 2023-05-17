package View;


import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
public class ShowSaved extends Application {
	@Override
	public void start(Stage primaryStage) {
	
	Stage popupStage = new Stage();

    Label label = new Label("Saved");
    Button okButton = new Button("OK");
   

    BorderPane bp = new BorderPane();
    bp.setCenter(label);
    bp.setBottom(okButton);
    BorderPane.setMargin(okButton, new Insets(10));
    bp.setAlignment(okButton, Pos.CENTER);;

    Scene scene = new Scene(bp, 200, 100);
    popupStage.setScene(scene);

    popupStage.setTitle("Popup Window");
    popupStage.centerOnScreen();
    okButton.setOnAction(event -> popupStage.close());
    popupStage.show();
}
}
