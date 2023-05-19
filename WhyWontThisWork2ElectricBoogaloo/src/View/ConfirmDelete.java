package View;

import Model.HealthRecord;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ConfirmDelete{
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
		    info.setHgap(10);
		    info.setVgap(15);
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
	
	public BorderPane getRoot() {
		return this.root;
	}

}
