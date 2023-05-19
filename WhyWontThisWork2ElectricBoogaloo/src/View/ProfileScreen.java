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
	    
	    GridPane gp = new GridPane();
		if (user.getProfilePicture() != null) {
        Image profilePicture = new Image(user.getProfilePicture());
        ImageView imageView = new ImageView(profilePicture);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        gp.add(imageView, 0, 4, 2, 1);
    }
	    gp.add(username, 0,1);
	    gp.add(firstName,0,2);
	    gp.add(lastName,0,3);
	    gp.add(dob,0,4);
	    
	    gp.add(setUsername,2,1);
	    gp.add(setFirstName,2,2);
	    gp.add(setLastName,2,3);
	    gp.add(setDOB,2,4);
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

    }
