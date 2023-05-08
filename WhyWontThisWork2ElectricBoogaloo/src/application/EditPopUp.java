package application;

import Model.HealthRecord;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditPopUp extends Application {
	public BorderPane root;
	public Button yes;
	public Button no;
	public Stage popupStage;
	private TextField Weight;
	private TextField Temp;
	private TextField HighBP;
	private TextField LowBP;
	private TextField Note;
	public void prep(HealthRecord selectedRecord) {
			Text confirm = new Text("Please Edit as Needed");
		 	Text Date = new Text ("Date : \t"+ selectedRecord.getDate());
		    Text Weight = new Text ("Weight");
		    Text Temp = new Text("Temp");
		    Text HighBP = new Text("HighBP");
		    Text LowBP = new Text("LowBP");
		    Text Note = new Text("Note");
		    
		    this.Weight = new TextField();
		    this.Weight.setText(selectedRecord.getWeight());

		    this.Temp = new TextField();
		    this.Temp.setText(selectedRecord.getTemp());

		    this.HighBP = new TextField();
		    this.HighBP.setText(selectedRecord.getHighBP());

		    this.LowBP = new TextField();
		    this.LowBP.setText(selectedRecord.getLowBP());

		    this.Note = new TextField();
		    this.Note.setText(selectedRecord.getNote());

		   
		    GridPane info = new GridPane();
		    info.add(confirm, 0, 0);
		    info.add(Date, 0, 1);
		    info.add(Weight, 0, 2);
		    info.add(Temp, 0, 3);
		    info.add(HighBP, 0, 4);
		    info.add(LowBP, 0, 5);
		    info.add(Note, 0, 6); 
		  
		    info.add(this.Weight,2,3,1,1);
		    info.add(this.Temp,2,4,1,1);
		    info.add(this.HighBP,2,5,1,1);
		    info.add(this.LowBP,2,6,1,1);
		    info.add(this.Note,2,7,1,1);
		    
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

	public String getWeight() {
		return Weight.getText();
	}
	
	public String getTemp() {
		return Temp.getText();
	}
	
	public String getHighBP() {
		return HighBP.getText();
	}
	
	public String getLowBP() {
		return LowBP.getText();
	}
	
	public String getNote() {
		return Note.getText();
	}

}

