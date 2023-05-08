package application;

import Model.HealthRecord;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ConfirmDelete extends Application {
	public BorderPane root;
	public Button yes;
	public Button no;
	public Stage popupStage;
	public void prep(HealthRecord selectedRecord) {
			Text confirm = new Text("Please confirm you want to delete this record");
		 	Text Date = new Text ("Date : \t"+ selectedRecord.getDate());
		    Text Weight = new Text ("Weight : \t"+selectedRecord.getWeight());
		    Text Temp = new Text("Temp : \t"+selectedRecord.getTemp());
		    Text HighBP = new Text("HighBP : \t"+selectedRecord.getHighBP());
		    Text LowBP = new Text("LowBP : \t"+selectedRecord.getLowBP());
		    Text Note = new Text("Note : \t"+selectedRecord.getNote());
		    GridPane info = new GridPane();
		    info.add(confirm, 0, 0);
		    info.add(Date, 0, 1);
		    info.add(Weight, 0, 2);
		    info.add(Temp, 0, 3);
		    info.add(HighBP, 0, 4);
		    info.add(LowBP, 0, 5);
		    info.add(Note, 0, 6);
		    this.yes= new Button("Yes");
		    this.no= new Button("No");
		    
		    GridPane gpButton = new GridPane(); 
		    gpButton.add(yes, 0, 0);
		    gpButton.add(no, 1, 0);
		    gpButton.setHgap(10);
		    gpButton.setVgap(15);
		   

		    BorderPane bp = new BorderPane();
		    bp.setCenter(info);
		    info.setAlignment(Pos.CENTER);
		    BorderPane.setAlignment(info,Pos.CENTER);
		    bp.setBottom(gpButton);
		    BorderPane.setMargin(gpButton, new Insets(10));
		    BorderPane.setAlignment(gpButton, Pos.CENTER);;
		    this.root = bp;
	}

	@Override
	public void start(Stage primaryStage) {

		this.popupStage = new Stage();

	    Scene scene = new Scene(this.root, 300, 300);
	    this.popupStage.setScene(scene);

	    this.popupStage.setTitle("Confirm Deletion");
	    this.popupStage.centerOnScreen();
	    this.popupStage.show();
	}

}
