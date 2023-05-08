package View;


import java.time.LocalDate;

import Model.User;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class EditProfile extends Application{	
	private BorderPane root;
	protected User user;
	private TextField username;
	private TextField firstName;
	private TextField lastName;
	private DatePicker dob;
	public Button save;
    
	@Override
	public void start(Stage primaryStage){
	BorderPane bp = new BorderPane();
    primaryStage.setTitle("My Medical - Profile - Edit");
    bp.setCenter(getPage());
    save = new Button();
    save.setText("Save");
    GridPane gp = new GridPane();
    gp.add(save, 0, 0);
    GridPane.setHalignment(save, HPos.CENTER);
    gp.setAlignment(Pos.CENTER);
    bp.setBottom(gp);
    this.root = bp;
}
	
	
	public GridPane getPage() {
		
		Text username = new Text ("Username");
	    Text firstName = new Text("First Name");
	    Text lastName = new Text("Surname");
	    Text dob = new Text("Date of Birth");
	    
	    this.username = new TextField();
	    this.username.setText(user.getUsername());
	    this.firstName = new TextField();
	    this.firstName.setText(user.getFirstName());

	    this.lastName = new TextField();
	    this.lastName.setText(user.getlastName());

	    this.dob = new DatePicker();
	    this.dob.setValue(user.getDOBLD());
	 
	    GridPane leftGP = new GridPane();
	    leftGP.add(username, 0,0,1,1);
	    leftGP.add(firstName,0,1,1,1);
	    leftGP.add(lastName,0,2,1,1);
	    leftGP.add(dob,0,3,1,1);
	    
	    leftGP.add(this.username,2,0,1,1);

	    leftGP.add(this.firstName,2,1,1,1);
	    leftGP.add(this.lastName,2,2,1,1);
	    leftGP.add(this.dob,2,3,1,1);
	    leftGP.setAlignment(Pos.CENTER);
	    leftGP.setHgap(10);
	    leftGP.setVgap(15);
	    
	    
	    return leftGP;
	}
	
	public void setUser(User user) {
		this.user= user;
	}
	
	public BorderPane getRoot() {
		return this.root;
	}
	
	 public String getUsername() {
			return this.username.getText();
		 }
		 
		 public String getFirstName() {
			 return this.firstName.getText();
		 }
		 public String getLastName() {
			 return this.lastName.getText();
		 }
		 public LocalDate getDOB() {
			 return this.dob.getValue();


		 }
}
