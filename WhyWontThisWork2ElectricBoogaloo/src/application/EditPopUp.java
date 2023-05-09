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

public class EditSpecRecord extends Application {
	public BorderPane root;
	public Button yes;
	public Button no;
	public Stage popupStage;
	private TextField Weight;
	private TextField Temp;
	private TextField HighBP;
	private TextField LowBP;
	private TextField Note;

	@Override
	public void start(Stage primaryStage){
	BorderPane bp = new BorderPane();
    primaryStage.setTitle("My Medical - Record - Edit");
    bp.setCenter(getPage());
    save = new Button();
    save.setText("Save");
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

	public void prep(HealthRecord selectedRecord){
		this.Date = new Label();
		this.Date.setText(selectedRecord.getDate());
		
		this.Weight = new TextField();
	    this.Weight.setText(selectedRecord.getWeight());
	    
		this.Temp = new TextField();
	    this.Temp.setText(selectedRecord.getTemp());

	    this.HighBP = new TextField();
	    this.HighBP.setText(selectedRecord.getHighBP());

	    this.LowBP = new DatePicker();
	    this.LowBP.setValue(selectedRecord.getlowBP());

		this.Note = new TextField();
		this.Note.setText(selectedRecord.getNote());

	}

	public GridPane getPage() {
		Text date = new Text("Date:");
		Text Weight = new Text ("Weight:");
	    Text Temp = new Text("Temp:");
	    Text HighBP = new Text("HighBP:");
	    Text LowBP = new Text("LowBP:");
		Text Note = new Note("Note:")
	    
	
	    GridPane gp = new GridPane();
	    gp.add(Date, 0,0,1,1);
	    gp.add(Weight,0,1,1,1);
	    gp.add(Temp,0,2,1,1);
	    gp.add(HighBP,0,3,1,1);
		gp.add(LowBP,0,4,1,1);
		gp.add(Note,0,5,1,1);
	    
	    gp.add(this.Date,1,0,1,1);
		gp.add(this.Weight,1,1,1,1);
		gp.add(this.Temp,1,2,1,1);
		gp.add(this.HighBP,1,3,1,1);
		gp.add(this.LowBP,1,4,1,1);
		gp.add(this.Note,1,5,1,1);

	    gp.setAlignment(Pos.CENTER);
	    gp.setHgap(10);
	    gp.setVgap(15);
	    
	    
	    return gp;
	}
}
			
