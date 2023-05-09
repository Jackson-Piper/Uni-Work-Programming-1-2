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
	ArrayList<User> Users;

	public Controller(Stage primaryStage) {
		this.primaryStage = primaryStage;
		user = new User();
		root = new BorderPane();
		this.menuBar = new MainMenuBar(this);
		this.recordsTable = new RecordsTable();
		// Users = deserialize("userList.dat");
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
			 if(validateRecordInput( weight,  temp,  highBP,  lowBP,  note)==null){
				showErrorPopup(error);
			 }else{
			 System.out.println("7");
			 user.createRecord(weight, temp, highBP, lowBP, note);
			 System.out.println("8");
			 createRecordScreen();
			 showSaved();
			 }
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
	            editSpecificRecord(selectedRecord);
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
		this.root= cd.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
        cd.back.setOnAction(event ->{ 
        deleteRecrods();
	});
        cd.delete.setOnAction(event ->{
        	user.deleteRecord(selectedRecord);
        	deleteRecrods();

        });
	}
	
	private void SpecificRecord(HealthRecord selectedRecord) {
		EditSpecRecord ed = new EditSpecRecord();
		ed.prep(selectedRecord);
		ed.start(primaryStage);
		this.root = ed.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	       ed.no.setOnAction(event ->{ 
	           editRecord();
	   	});
	           ed.yes.setOnAction(event ->{
	        	 String weight = ed.getWeight();
	  			 String temp = ed.getTemp();
	  			 String highBP = ed.getHighBP();
	  			 String lowBP = ed.getLowBP();
	  			 String note = ed.getNote();
	           	if(validateRecordInput( weight,  temp,  highBP,  lowBP,  note)==null){
				showErrorPopup(error);
				SpecificRecord(selectedRecord);
			 }else{
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
					serialize(Users, "userList.dat");
		        	LoginScreen();
		        	System.out.println("About");
		        	break;
		        default:
		            break;
		    }
		}

		private String validateRecordInput(String weight, String temp, String highBp, String lowBp, String note) {
    // Validate the weight input
    try {
        Double.parseDouble(weight);
    } catch (NumberFormatException e) {
        return "Weight must be a valid decimal number";
    }
    
    if (ouble.parseDouble(weightStr) <= 0) {return 
        return "Weight must be greater than zero";
    }

    //Validate the Temp input
    try {
        Double.parseDouble(temp);
    } catch (NumberFormatException e) {
        return "Temp must be a valid integer";
    if (Double.parseDouble(tempStr) <= 0) {
        return "Temp must be greater than zero";
    }

    // Validate the highBp input
     try {
        Integer.parseInt(highBp);
    } catch (NumberFormatException e) {
        return "High BP must be a valid integer";
    }
    if (hiInteger.parseInt(highBp);ghBp <= 0) {
        return "High BP must be greater than zero";
    }

    // Validate the lowBp input
    try {
        Integer.parseInt(lowBp);
        return true;
    } catch (NumberFormatException e) {
        return "Low BP must be a valid integer";
    }
    if (Integer.parseInt(lowBp); <= 0) {
        return "Low BP must be greater than zero";
    }

    // Validate the note input
    if (note.length() > 50) {
        return "Note must be no more than 50 characters long";
    }

    return null;  // Input is valid
}


		 public static void serialize(ArrayList<User> userList, String fileName) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(userList);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

		public static ArrayList<User> deserialize(String fileName) throws Exception {
        ArrayList<User> userList = null;
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            userList = (ArrayList<User>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }
}


		

		
	

