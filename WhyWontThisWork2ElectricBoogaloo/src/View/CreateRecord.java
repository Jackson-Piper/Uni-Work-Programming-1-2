package View;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateRecord {
	
		TextField weight;
		TextField temp;
		TextField highBP;
		TextField lowBP;
		TextField note;
		public Button save;
		BorderPane root;
	
	public void start(Stage primaryStage) {
		
		BorderPane borderPane = new BorderPane();
	    primaryStage.setTitle("My Medical - Record - Create");
	    borderPane.setCenter(getPage());
	    borderPane.setBottom(getButton());
	    
	    this.root = borderPane;
	}
	
	private GridPane getButton() {
	    this.save = new Button();
	    save.setText("Save");
	    GridPane.setHalignment(save, HPos.CENTER);
	     GridPane buttonGP = new GridPane();   
	    buttonGP.add(save, 0, 0); 
	    buttonGP.setAlignment(Pos.CENTER);
	    buttonGP.setHgap(10);
	    buttonGP.setVgap(15);
		return buttonGP;
	}

	public GridPane getPage() {
		 Text weight = new Text ("Weight (kg)");
		    Text Temp = new Text ("Tempreture (Celcius)");
		    Text highBP= new Text("Blood Pressure High");
		    Text lowBP = new Text("Blood Pressure Low");
		    Text note = new Text("Note");
		    
		    GridPane leftGP = new GridPane();
		    leftGP.add(weight, 0,0,1,1);
		    leftGP.add(Temp, 0,1,1,1);
		    leftGP.add(highBP,0,2,1,1);
		    leftGP.add(lowBP,0,3,1,1);
		    leftGP.add(note,0,4,1,1);
		    
		    
		    this.weight = new TextField();
		    this.weight.setText("0.0kg");

		    this.temp = new TextField();
		    this.temp.setText("0.0");

		    this.highBP = new TextField();
		    this.highBP.setText("0");

		    this.lowBP = new TextField();
		    this.lowBP.setText("0");

		    this.note = new TextField();

		    leftGP.add(this.weight,2,0,1,1);
		    leftGP.add(this.temp,2,1,1,1);
		    leftGP.add(this.highBP,2,2,1,1);
		    leftGP.add(this.lowBP,2,3,1,1);
		    leftGP.add(this.note,2,4,1,1);
		    leftGP.setAlignment(Pos.CENTER);
		    leftGP.setHgap(10);
		    leftGP.setVgap(15);
	
		    return leftGP;
	
	}
	
		 public BorderPane getRoot() {
		    	return root;
		    }
		 public String getWeight() {
			return this.weight.getText();
		 }
		 public String getTemp() {
			 return this.temp.getText();
		 }
		 public String getHighBP() {
			 return this.highBP.getText();
		 }
		 public String getLowBP() {
			 return this.lowBP.getText();
		 }
		 public String getNote() {
			 return this.note.getText();
		 }
}
