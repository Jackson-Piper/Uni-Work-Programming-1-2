package View;

import java.time.LocalDate;

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
import javafx.stage.Stage;

public class CreateAccountScreen extends Application {
	
	public Button login;
	public Button Back;
	private BorderPane root;
	private TextField username;
	private PasswordField password;
	private PasswordField conPassword;
	private TextField firstName;
	private TextField lastName;
	private DatePicker dob;
	
	 @Override
	public void start(Stage primaryStage) {
	BorderPane borderPane = new BorderPane();
    primaryStage.setTitle("Create Acount");

    Text username = new Text ("Username");
    Text password = new Text ("Password");
    Text confirmPassword = new Text("Confirm Password");
    Text firstName = new Text("First Name");
    Text lastName = new Text("Surname");
    Text dob = new Text("Date of Birth");
    
    GridPane leftGP = new GridPane();
    leftGP.add(username, 0,0,1,1);
    leftGP.add(password, 0,1,1,1);
    leftGP.add(confirmPassword,0,2,1,1);
    leftGP.add(firstName,0,3,1,1);
    leftGP.add(lastName,0,4,1,1);
    leftGP.add(dob,0,5,1,1);
    
    
    this.username = new TextField();
    this.username.setText("Example@abc.com");

    this.password = new PasswordField();
    this.conPassword = new PasswordField();

    this.firstName = new TextField();
    this.firstName.setText("Eg.John");

    this.lastName = new TextField();
    this.lastName.setText("Eg.Doe");

    this.dob = new DatePicker();

    leftGP.add(this.username,2,0,1,1);
    leftGP.add(this.password,2,1,1,1);
    leftGP.add(this.conPassword,2,2,1,1);
    leftGP.add(this.firstName,2,3,1,1);
    leftGP.add(this.lastName,2,4,1,1);
    leftGP.add(this.dob,2,5,1,1);
    leftGP.setAlignment(Pos.CENTER);
    leftGP.setHgap(10);
    leftGP.setVgap(15);
    
    this.login = new Button();
    login.setText("Login");
    this.Back = new Button();
    Back.setText("Back");
    GridPane.setHalignment(Back, HPos.CENTER);
    GridPane.setHalignment(login, HPos.CENTER);
    

   
    
    GridPane buttonGP = new GridPane();   
    buttonGP.add(login, 0, 0);
    buttonGP.add(Back, 1, 0);
    buttonGP.setAlignment(Pos.CENTER);
    buttonGP.setHgap(10);
    buttonGP.setVgap(15);

    ImageView logo = new ImageView(new Image("C:\\Users\\jackson\\eclipse-workspaceUNI\\WhyWontThisWork\\tempLogo.JPG"));

    borderPane.setBottom(buttonGP);
    borderPane.setTop(logo);
    borderPane.setCenter(leftGP);
    
    BorderPane.setAlignment(logo, Pos.CENTER);
    
    BorderPane.setMargin(logo, new Insets(50, 0, 0, 0));
    BorderPane.setMargin(leftGP, new Insets(0, 0, 0, -10));
    BorderPane.setMargin(buttonGP, new Insets(0, 0, 15, 0));
    
    this.root = borderPane;
}
	 public BorderPane getRoot() {
	    	return root;
	    }
	 public String getUsername() {
		return this.username.getText();
	 }
	 public String getPassword() {
		 return this.password.getText();
	 }
	 public String getConPassword() {
		 return this.conPassword.getText();
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
