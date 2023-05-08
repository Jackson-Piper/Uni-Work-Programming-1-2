package View;

import Model.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class ProfileScreen extends Application{	
	private BorderPane root;
	protected User user;
    
	@Override
	public void start(Stage primaryStage){
	BorderPane bp = new BorderPane();
    primaryStage.setTitle("My Medical - Profile - View");
    bp.setCenter(getPage());
    this.root = bp;
}
	
	
	public GridPane getPage() {
		
		Text username = new Text ("Username");
	    Text firstName = new Text("First Name");
	    Text lastName = new Text("Surname");
	    Text dob = new Text("Date of Birth");
	    
	    Text setUsername = new Text(user.getUsername());
	    Text setFirstName = new Text(user.getFirstName());
	    Text setLastName = new Text(user.getlastName());
	    Text setDOB = new Text(user.getDOB());
	    
	    GridPane leftGP = new GridPane();
	    leftGP.add(username, 0,0,1,1);
	    leftGP.add(firstName,0,1,1,1);
	    leftGP.add(lastName,0,2,1,1);
	    leftGP.add(dob,0,3,1,1);
	    
	    leftGP.add(setUsername,2,0,1,1);
	    leftGP.add(setFirstName,2,1,1,1);
	    leftGP.add(setLastName,2,2,1,1);
	    leftGP.add(setDOB,2,3,1,1);
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

    }
