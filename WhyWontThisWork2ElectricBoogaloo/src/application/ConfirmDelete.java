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
	public Button delete;
	public Button back;

	public void prep(HealthRecord selectedRecord) {
		 	this.Date = new Text ("Date : \t"+ selectedRecord.getDate());
		    this.Weight = new Text ("Weight : \t"+selectedRecord.getWeight());
		    this.Temp = new Text("Temp : \t"+selectedRecord.getTemp());
		    this.HighBP = new Text("HighBP : \t"+selectedRecord.getHighBP());
		    this.LowBP = new Text("LowBP : \t"+selectedRecord.getLowBP());
		    this.Note = new Text("Note : \t"+selectedRecord.getNote());
	}
	public GridPane getPage() {    
			GridPane info = new GridPane();
		    info.add(confirm, 0, 0);
		    info.add(Date, 0, 1);
		    info.add(Weight, 0, 2);
		    info.add(Temp, 0, 3);
		    info.add(HighBP, 0, 4);
		    info.add(LowBP, 0, 5);
		    info.add(Note, 0, 6);
		    info.setHgap(10);
		    info.setVgap(15);
			info.setAllignment(Pos.CENTER)
			return info;
	}

	@Override
	public void start(Stage primaryStage){
	BorderPane bp = new BorderPane();
    primaryStage.setTitle("My Medical - Record - Delete");
    bp.setCenter(getPage());
    delete = new Button();
    delete.setText("delete");
	back = new Button();
	back.setText("Back")
    GridPane gp = new GridPane();
    gp.add(save, 0, 0);
	gp.add(back, 1, 0);
	gp.setHgap(10);
	gp.setVgap(15);

    GridPane.setHalignment(HPos.CENTER);
    gp.setAlignment(Pos.CENTER);
    bp.setBottom(gp);
    this.root = bp;
	}

	public BorderPane getRoot(){
		return this.root;
	}

}
