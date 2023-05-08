package application;

import View.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Model.HealthRecord;
import Model.User;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller {
	User user;
	Stage primaryStage;
	BorderPane root;
	private MainMenuBar menuBar;
	RecordsTable recordsTable;

	public Controller(Stage primaryStage) {
		this.primaryStage = primaryStage;
		user = new User();
		root = new BorderPane();
		this.menuBar = new MainMenuBar(this);
		this.recordsTable = new RecordsTable();
		LoginScreen();
	}

	
	public void login(LoginScreen loginScreen) {
		String username = loginScreen.getUsername();
		String password = loginScreen.getPassword();

		user.setUsername(username);
		user.setPassword(password);

		if (user.isValidLogin()) {
			loginScreen.showError("");
			// do something here if the login is valid
		} else {
			loginScreen.showError("Invalid login credentials");
		}
	}
	private void LoginScreen() {
		LoginScreen loginScreen = new LoginScreen();
		loginScreen.start(primaryStage);
		Scene scene = new Scene(loginScreen.getRoot(),400,400);
		primaryStage.setScene(scene);
        primaryStage.show();
		loginScreen.login.setOnAction(event -> {
			login(loginScreen);
		});
		loginScreen.createAccount.setOnAction(event -> {
			CreateAccountScreen();
		});
	}

	private void CreateAccountScreen() {
		CreateAccountScreen createAccountScreen = new CreateAccountScreen();
		createAccountScreen.start(primaryStage);
		Scene scene = new Scene(createAccountScreen.getRoot(), 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		createAccountScreen.login.setOnAction(event -> {
			String userName = createAccountScreen.getUsername();
			String password = createAccountScreen.getPassword();
			String conPassword =createAccountScreen.getConPassword();
			String firstName = createAccountScreen.getFirstName();
			String lastName = createAccountScreen.getLastName();
			LocalDate dob = createAccountScreen.getDOB();
			if(newUser(userName, password, conPassword, firstName, lastName, dob));{
				mainScreen();
			}  {
				showError();
			}
		});
		createAccountScreen.Back.setOnAction(event -> {
			LoginScreen();

		});

	}
	
	private void mainScreen() {
		mainScreen mainScreen = new mainScreen();
		mainScreen.start(primaryStage);
		this.root = mainScreen.getRoot();
		this.root.setTop(this.menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showError() {
		// TODO Auto-generated method stub
		
	}

	

	private void profileScreen() {
		ProfileScreen ps = new ProfileScreen();
		ps.setUser(user);
		ps.start(primaryStage);
		this.root = ps.getRoot();
		this.root.setTop(this.menuBar.getMenuBar());
		Scene scene = new Scene(this.root,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void editProfileScreen() {
		EditProfile ep = new EditProfile();
		ep.setUser(user);
		ep.start(primaryStage);
		this.root = ep.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene scene = new Scene(this.root,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		ep.save.setOnAction(event -> {
			String username = ep.getUsername();
			String firstName = ep.getFirstName();
			String lastName = ep.getLastName();
			LocalDate dob = ep.getDOB();
			System.out.println(username);
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(dob.toString());
			user.editProfile(username, firstName,lastName,dob);
		});
		
	}



	//TODO implement a checker to make sure none of the fields entered are empty and also confirm that the passwords match
	private boolean newUser(String userName, String password, String conPassword, String firstName, String lastName,
			LocalDate dob) {
			user.user(userName,password,firstName,lastName,dob);
			return true;
	}


	private void createRecordScreen() {
		CreateRecord cr = new CreateRecord();
		cr.start(primaryStage);
		this.root = cr.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene scene = new Scene(this.root,500,500);
		primaryStage.setScene(scene);
        primaryStage.show();
		cr.save.setOnAction(event -> {
			System.out.println("6");
			 String weight = cr.getWeight();
			 String temp = cr.getTemp();
			 String highBP = cr.getHighBP();
			 String lowBP = cr.getLowBP();
			 String note = cr.getNote();
			 System.out.println("7");
			 user.createRecord(weight, temp, highBP, lowBP, note);
			 System.out.println("8");
			 createRecordScreen();
			 showSaved();
		});
		}
	
	private void showSaved() {
		ShowSaved showSaved = new ShowSaved();
		showSaved.start(primaryStage);
	}
	private void editRecord() {
		EditRecord ed = new EditRecord();
		ed.start(primaryStage);
		TableView table = recordsTable.getTable(user.getRecords());
		this.root = ed.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		this.root.setCenter(table);
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		table.setOnMouseClicked(event -> {
	        if (event.getClickCount() == 2 && table.getSelectionModel().getSelectedItem() != null) {
	            Model.HealthRecord selectedRecord = (Model.HealthRecord) table.getSelectionModel().getSelectedItem();
	            editPopUp(selectedRecord);
	        }
	    });
	}
	private void deleteRecrods() {
		DeleteRecords dr = new DeleteRecords();
		dr.start(primaryStage);
		TableView table = recordsTable.getTable(user.getRecords());
		this.root = dr.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		this.root.setCenter(table);
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		table.setOnMouseClicked(event -> {
	        if (event.getClickCount() == 2 && table.getSelectionModel().getSelectedItem() != null) {
	            Model.HealthRecord selectedRecord = (Model.HealthRecord) table.getSelectionModel().getSelectedItem();
	            confirmDelete(selectedRecord);
	            
	            user.deleteRecord(selectedRecord);
	        }
	    });
	}
	
	private void confirmDelete(HealthRecord selectedRecord) {
		ConfirmDelete cd = new ConfirmDelete();
        cd.prep(selectedRecord);
        cd.start(primaryStage);
        cd.no.setOnAction(event ->{ 
        cd.popupStage.close();
        deleteRecrods();
	});
        cd.yes.setOnAction(event ->{
        	cd.popupStage.close();
        	user.deleteRecord(selectedRecord);
        	deleteRecrods();

        });
	}
	
	private void editPopUp(HealthRecord selectedRecord) {
		EditPopUp ed = new EditPopUp();
		ed.prep(selectedRecord);
		ed.start(primaryStage);
	       ed.no.setOnAction(event ->{ 
	           ed.popupStage.close();
	           deleteRecrods();
	   	});
	           ed.yes.setOnAction(event ->{
	        	 String weight = ed.getWeight();
	  			 String temp = ed.getTemp();
	  			 String highBP = ed.getHighBP();
	  			 String lowBP = ed.getLowBP();
	  			 String note = ed.getNote();
	           	ed.popupStage.close();
	           	System.out.println("before change");
	           	user.editRecord(selectedRecord,weight, temp, highBP, lowBP, note);
	           	
	           	editRecord();

	           });
	   	}
	


	private void viewRecordsScreen() {
		ViewRecords vr = new ViewRecords();
		vr.start(primaryStage);
		this.root = vr.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		this.root.setCenter(recordsTable.getTable(user.getRecords()));
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
		


		public void handleMenuItemClick(MenuItem menuItem) {
			String menuText = (menuItem.getParentMenu()).getText() + " " + menuItem.getText();
		    switch (menuText) {
		        case "Records Create":
		            System.out.println("Create Record");
		            createRecordScreen();
		            break;
		        case "Records Edit":
		        	System.out.println("Edit Record");
		        	editRecord();
		            break;
		        case "Records Delete":
		        	System.out.println("Delte Record");
		        	deleteRecrods();
		            break;
		        case "Records View":
		        	System.out.println("View Record");
		        	viewRecordsScreen();
		            break;
		        case "Records Export":
		        	System.out.println("Export Records");
		        	break;
		        case "Profile View":
		        	System.out.println("View Profile");
		        	profileScreen();
		            break;
		        case "Profile Edit":
		        	System.out.println("Edit Profile");
		        	editProfileScreen();
		            break;
		        case "Profile Logout":
		        	this.user = null;
		        	LoginScreen();
		        	System.out.println("About");
		        	break;
		        default:
		            break;
		    }
		}
}


		

		
	

