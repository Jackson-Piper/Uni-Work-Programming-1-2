package application;

import View.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.ObjectInputStream;

import Model.HealthRecord;
import Model.User;

import javafx.scene.Scene;
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
	String url;
	ResultSet userInfo;

	public Controller(Stage primaryStage) {
		this.primaryStage = primaryStage;
		Users = new ArrayList<User>();
		root = new BorderPane();
		this.menuBar = new MainMenuBar(this);
		this.recordsTable = new RecordsTable();
		url = "jdbc:sqlite:C:\\Users\\jackson\\Desktop\\test.db";
		DatabaseController.createRecordTable(url);
		DatabaseController.createUserTable(url);
		LoginScreen();
	}
	
	private void LoginScreen() {
		LoginScreen loginScreen = new LoginScreen();
		loginScreen.start(primaryStage);
		Scene scene = new Scene(loginScreen.getRoot(), 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		loginScreen.login.setOnAction(event -> {
			System.out.println("Rerun");
			
			String username = loginScreen.getUsername();
			String password = loginScreen.getPassword();
			System.out.println(username);
			System.out.println(password);
			String error = validteLogin(username, password);
			
			ResultSet rs = DatabaseController.validLogin(username, password, url);
			System.out.println(rs);
			try {
				if(!rs.next()) {
					showErrorPopup("User or Password is Wrong");
				}else {
					System.out.println(DatabaseController.profileData(username, url).toString());
					 user = new User(DatabaseController.profileData(username, url));
					 mainScreen();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
						
			if(error!=null) {
				showErrorPopup(error);
			}
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
			String conPassword = createAccountScreen.getConPassword();
			String firstName = createAccountScreen.getFirstName();
			String lastName = createAccountScreen.getLastName();
			LocalDate dob = createAccountScreen.getDOB();
			String error = validateNewUser(userName, firstName, lastName, password, conPassword, dob);
			if (error == null) {
				newUser(userName, password, conPassword, firstName, lastName, dob);
			} else {
				showErrorPopup(error);
			}
		});
		createAccountScreen.Back.setOnAction(event -> {
			LoginScreen();

		});

	}

	private void mainScreen() {
		mainScreen mainScreen = new mainScreen();
		mainScreen.prep(user.getFirstName(), user.getlastName());
		mainScreen.start(primaryStage);
		this.root = mainScreen.getRoot();
		this.root.setTop(this.menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void profileScreen() {
		ProfileScreen ps = new ProfileScreen();
		ps.setUser(user);
		ps.start(primaryStage);
		this.root = ps.getRoot();
		this.root.setTop(this.menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void editProfileScreen() {
		EditProfile ep = new EditProfile();
		ep.setUser(user);
		ep.start(primaryStage);
		this.root = ep.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
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

			String error = validateEditUser(username, firstName, lastName, dob);
			if (error == null) {
				user.editProfile(username, firstName, lastName, dob);
				DatabaseController.updateProfile(username, firstName, lastName, user.getDOB(), user.getUserID(), url);
				System.out.println("Here 1");
				profileScreen();
				showSaved();
				
			}else{
				System.out.println("Here " +error);
				showErrorPopup(error);
			}
		});

	}

	// TODO implement a checker to make sure none of the fields entered are empty
	// and also confirm that the passwords match
	private void  newUser(String userName, String password, String conPassword, String firstName, String lastName,
			LocalDate dob) {
		String userID = UUID.randomUUID().toString();
		User user = new User(userID, userName, password, firstName, lastName, dob);
		this.user = user;
		System.out.println(user.getDOB());
		System.out.println(dob.toString());
		DatabaseController.addUser(userID, userName, password, firstName, lastName, user.getDOB(), this.url);
		mainScreen();
	}

	private void createRecordScreen() {
		CreateRecord cr = new CreateRecord();
		cr.start(primaryStage);
		this.root = cr.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		cr.save.setOnAction(event -> {
			System.out.println("6");
			String weight = cr.getWeight();
			String temp = cr.getTemp();
			String highBP = cr.getHighBP();
			String lowBP = cr.getLowBP();
			String note = cr.getNote();
			String error = validateRecordInput(weight, temp, highBP, lowBP, note);
			if (error != null) {
				showErrorPopup(error);
			} else {
				System.out.println("7");
				String recordID = UUID.randomUUID().toString();
				user.createRecord(recordID, weight, temp, highBP, lowBP, note);
				HealthRecord newRecord = getRecord(recordID);
				DatabaseController.insertRecord(newRecord.getID(), newRecord.getWeight(), newRecord.getTemp(), newRecord.getHighBP(), newRecord.getLowBP(), newRecord.getDate(), newRecord.getNote(), user.getUsername(), url);
				System.out.println("8");
				createRecordScreen();
				showSaved();
			}
		});
	}

	private HealthRecord getRecord(String recordID) {
		for (HealthRecord record : user.getRecords()) {
			if(record.getID().equals(recordID)) {
				return record;
			}
		}
		return null;
	}

	private void showErrorPopup(String error) {
		ErrorPopUp ep = new ErrorPopUp();
		ep.prep(error);
		ep.start(primaryStage);

	}

	private void showSaved() {
		ShowSaved showSaved = new ShowSaved();
		showSaved.start(primaryStage);
	}

	private void editRecord() {
		EditRecord ed = new EditRecord();
		ed.start(primaryStage);
		TableView table = recordsTable.getTable(DatabaseController.tableViewData(user.getUsername(), url));
		this.root = ed.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		this.root.setCenter(table);
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		table.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2 && table.getSelectionModel().getSelectedItem() != null) {
				Model.HealthRecord selectedRecord = (Model.HealthRecord) table.getSelectionModel().getSelectedItem();
				EditSpecRecord(selectedRecord);
			}
		});
	}

	private void deleteRecrods() {
		DeleteRecords dr = new DeleteRecords();
		dr.start(primaryStage);
		TableView table = recordsTable.getTable(DatabaseController.tableViewData(user.getUsername(), url));
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
			}
		});
	}

	private void exportRecordScreen(){
		ExportScreen ex = new ExportScreen();
		ex.start(primaryStage);
		this.root = ex.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		ex.export.setOnAction(Event ->{
			if(ex.getFileType.equals(".csv")){
				exportCSVFile(ex.getLocation());
			}else if(ex.getFileType.equals(".dat")){
				exportSerliazeFile(ex.getLocation());
			}else if(ex.getFileType.equals(null)){
				showErrorPopup("Please select a save file type");
			}
			exportRecord(ex.getLocation(), ex.getFileType());
		})
	}

	public void exportCSVFile(String location) {
        String filePath = location + "/records.csv";
		ArrayList<HealthRecord> data = DatabaseController.tableViewData;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header row
            writer.write("Weight, Temperature, Blood Pressure High, Blood Pressure Low, Note, Date");
            writer.newLine();

            for (HealthRecord record : data) {
                writer.write(record.getWeight() + ", ");
                writer.write(record.getTemperature() + ", ");
                writer.write(record.getBloodPressureHigh() + ", ");
                writer.write(record.getBloodPressureLow() + ", ");
                writer.write(record.getNote() + ", ");
                writer.write(record.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	    public void exportDATFile(String location) {
        String filePath = location + "/records.dat";
        ArrayList<HealthRecord> data = DatabaseController.tableViewData;
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




	private void confirmDelete(HealthRecord selectedRecord) {
		ConfirmDelete cd = new ConfirmDelete();
		cd.prep(selectedRecord);
		this.root = cd.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		cd.no.setOnAction(event -> {
			deleteRecrods();
		});
		cd.yes.setOnAction(event -> {
			user.deleteRecord(selectedRecord);
			DatabaseController.deleteRecord(selectedRecord.getID(), url);
			deleteRecrods();

		});
	}

	private void EditSpecRecord(HealthRecord selectedRecord) {
		EditSpecRecord ed = new EditSpecRecord();
		ed.prep(selectedRecord);
		ed.start(primaryStage);
		this.root = ed.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		Scene scene = new Scene(this.root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		ed.back.setOnAction(event -> {
			editRecord();
		});
		ed.save.setOnAction(event -> {
			String weight = ed.getWeight();
			String temp = ed.getTemp();
			String highBP = ed.getHighBP();
			String lowBP = ed.getLowBP();
			String note = ed.getNote();
			if (!(validateRecordInput(weight, temp, highBP, lowBP, note) == null)) {
				showErrorPopup(validateRecordInput(weight, temp, highBP, lowBP, note));
				editRecord();
			} else {
				user.editRecord(selectedRecord, weight, temp, highBP, lowBP, note);
				DatabaseController.updateRecord(weight, temp, highBP, lowBP, note, selectedRecord.getID(), url);
				editRecord();
			}
		});
	}

	private void viewRecordsScreen() {
		ViewRecords vr = new ViewRecords();
		vr.start(primaryStage);
		this.root = vr.getRoot();
		this.root.setTop(menuBar.getMenuBar());
		this.root.setCenter(recordsTable.getTable(DatabaseController.tableViewData(user.getUsername(), url)));
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
			exportRecordScreen();
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

	private String validateRecordInput(String weight, String temp, String highBp, String lowBp, String note) {
		// Validate the weight input
		try {
			Double.parseDouble(weight);
		} catch (NumberFormatException e) {
			return "Weight must be a valid decimal number";
		}

		// Validate the Temp input
		try {
			Double.parseDouble(temp);
		} catch (NumberFormatException e) {
			return "Temp must be a valid integer";
		}
		//Validate safe levels for tempreture
		if(Double.parseDouble(temp)>39 || Double.parseDouble(temp)<35){
			return "Please seek medical Attention for abnormal Tempreture";
		}
		// Validate the highBp input
		try {
			Integer.parseInt(highBp);
		} catch (NumberFormatException e1) {
			return "High BP must be a valid integer";
		}
		
		// Validate the lowBp input
		try {
			Integer.parseInt(lowBp);
		} catch (NumberFormatException e1) {
			return "Low BP must be a valid integer";
		}
		if (Integer.parseInt(lowBp) > Integer.parseInt(highBp)) {
			return "Low BP must be smaller than HighBp";
		}
		//based off the internet for unsafe levels of blood pressure
		if(Integer.parseInt(lowBp) > 110 || Integer.paseInt(highBP)> 180){
			return "Please seek medical Attention for dangerous Blood Pressure Levels";
		}

		// Validate the note input
		if (note.length() > 50) {
			return "Note must be no more than 50 characters long";
		}
		// Input is valid
		return null;
	}


	public String validateNewUser(String email, String firstName, String lastName, String password, String conPassword,
			LocalDate date) {
				//checks to make sure it is an email address that a user is signing up with
		String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		if (!(email.matches(regex))) {
			return "Invalid email address";
		}
		//making sure the email is unique 
		if (!(unique(email))) {
			return "Email already in use";
		}
		//making sure the firstname isnt null or whitespace
		if (firstName == null && firstName.length() > 0) {
			return "Please enter a valid Name";
		}
		//making sure the last name is null or whitesapce
		if (lastName == null && lastName.length() > 0) {
			return "Please enter a valid Name";
		}
		//making sure the date it before today at minium, easy to change for greater validation at the Clients behest
		if (date != null && !date.isBefore(LocalDate.now())) {
			return "Date must be before today's date";
		}
		//validating that the password isnt null and is above a certain length
		//only has to be done for the first password as the confirmation is checked agaisnt this
		if (password == null || password.length() < 8) {
			return "Invalid password. Must be atleast 8 characters";
		}
		//Validating if the two passwords match
		if (!(password.equals(conPassword))) {
			return "Passwords don't match";
		}
		return null;
	}

	public String validateEditUser(String email, String firstName, String lastName, LocalDate date) {
		String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		if (!(email.matches(regex))) {
			return "Invalid email address";
		}
		if (!(unique(email) || !(email.equals(user.getUsername())))) {
			return "Email already in use";
		}
		if (firstName == null && firstName.length() > 0) {
			return "Please enter a valid Name";
		}
		if (lastName == null && lastName.length() > 0) {
			return "Please enter a valid Name";
		}
		if (date != null && !date.isBefore(LocalDate.now())) {
			System.out.println(LocalDate.now());
			return "Date must be before today's date";
		}
		return null;
	}

	private boolean unique(String email) {
		for (User user : Users) {
			if (user.getUsername().equals(email)) {
				return false;
			}
		}
		return true;
	}
	
	private String validteLogin(String username, String password) {
			if(username == null) {
				return "Please enter a Username";
			}
			if(password == null) {
				return "Please Enter a Password";
			}
			return null;
	}
}

