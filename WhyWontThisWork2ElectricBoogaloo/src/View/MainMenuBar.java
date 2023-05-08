package View;

import application.Controller;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenuBar {
	
	private MenuItem Create;
	private MenuItem Edit;
	private MenuItem Delete;
	private MenuItem View;
	protected MenuItem ViewProfile;
	private MenuItem EditProfile;
	protected MenuBar mb;
	private MenuItem Export;
	private MenuItem LogOut;
	
	
	public MainMenuBar(Controller controller) {

        Menu Record = new Menu("Records");
        this.Create = new MenuItem("Create");
        this.Edit = new MenuItem("Edit");
        this.Delete = new MenuItem("Delete");
        this.View = new MenuItem("View");
        this.Export = new MenuItem("Export");

        Record.getItems().add(this.Create);
        Record.getItems().add(this.Edit);
        Record.getItems().add(this.Delete);
        Record.getItems().add(this.View);
        Record.getItems().add(this.Export);

        Menu Profile = new Menu("Profile");
        this.EditProfile = new MenuItem("Edit");
        this.ViewProfile = new MenuItem("View");
        this.LogOut = new MenuItem("Logout");
        
        Profile.getItems().add(this.ViewProfile);
        Profile.getItems().add(this.EditProfile);
        Profile.getItems().add(this.LogOut);
        

        MenuBar menuBar = new MenuBar();
        
        menuBar.getMenus().add(Record);
        menuBar.getMenus().add(Profile);
        this.Create.setOnAction(event -> {
            controller.handleMenuItemClick((MenuItem) event.getSource());
        });
        this.Edit.setOnAction(event -> {
        	controller.handleMenuItemClick((MenuItem) event.getSource());
        });
        this.Delete.setOnAction(event -> {
        	controller.handleMenuItemClick((MenuItem) event.getSource());
        });
        this.View.setOnAction(event -> {
        	controller.handleMenuItemClick((MenuItem) event.getSource());
        });
        this.Export.setOnAction(event -> {
        	controller.handleMenuItemClick((MenuItem) event.getSource());
        });
        this.ViewProfile.setOnAction(event -> {
        	controller.handleMenuItemClick((MenuItem) event.getSource());
        });
        this.EditProfile.setOnAction(event -> {
        	controller.handleMenuItemClick((MenuItem) event.getSource());
        });
        this.LogOut.setOnAction(event -> {
        	controller.handleMenuItemClick((MenuItem) event.getSource());
        });
    	
        this.mb = menuBar;
	}
	public MenuBar getMenuBar() {
		return this.mb;
	}


}
