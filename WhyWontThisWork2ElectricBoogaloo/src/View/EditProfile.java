
package View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class EditProfile extends Application{	
	private BorderPane root;
	protected User user;
	private TextField username;
	private TextField firstName;
	private TextField lastName;
	private DatePicker dob;
	public Button save;
	public Button uploadButton;
	private File image;
    
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

		Button upload = new Button("Upload Profile Picture");
    	Label fileLabel = new Label();

    	uploadButton.setOnAction(event -> {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png")
        );

        image = fileChooser.showOpenDialog(null);
        if (image != null) {
            fileLabel.setText("Selected File: " + image.getName());
        }
    });
	 
	    GridPane gp = new GridPane();
	    gp.add(username, 0,0);
	    gp.add(firstName,0,1);
	    gp.add(lastName,0,2);
	    gp.add(dob,0,3);
	    
	    gp.add(this.username,2,0);

	    gp.add(this.firstName,2,1);
	    gp.add(this.lastName,2,2);
	    gp.add(this.dob,2,3);

		gp.add(uploadButton, 0, 4);
    	gp.add(fileLabel, 2, 4);

	    gp.setAlignment(Pos.CENTER);
	    gp.setHgap(10);
	    gp.setVgap(15);
	    
	    
	    return gp;
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

	public byte[] getImageData(){
        try (FileInputStream fis = new FileInputStream(image)){
        	byte[] buffer = new byte[(int) image.length()];
            fis.read(buffer);
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
}