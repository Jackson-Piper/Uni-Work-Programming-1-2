package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Scanner;

import model.RoomCollection;
import view.frontend;

public class AlphaGroupController {
	private RoomCollection model;
	private frontend view;
	private Scanner sc;

	public AlphaGroupController() {
		model = new RoomCollection();
		view = new frontend(this);
	}

	// you need to implement other methods

	public static void main(String[] args) {

		AlphaGroupController alphaGroup = new AlphaGroupController();
		alphaGroup.run();

	}

	public void run() {

		this.sc = new Scanner(System.in);
		menu();
		sc.close();
	}

	public void menu() {
		int menu;

		do {
			do {
				view.showMainMenu();
				while (!sc.hasNextInt()) {
					view.invalidNumber();
					sc.next();
				}
				menu = sc.nextInt();
				sc.nextLine();
				if (menu < 1 || menu > 9) {
					view.InvalidChoice();
				}
			} while (menu < 1 || menu > 9);

			if (menu == 1) {
				addRoom();

			} else if (menu == 2) {
				rentRoom();

			} else if (menu == 3) {
				returnRoom();

			} else if (menu == 4) {
				maintenance();

			} else if (menu == 5) {
				completMaint();

			} else if (menu == 6) {
				displayRooms();

			} else if (menu == 7) {
				saveToFile();

			} else if (menu == 8) {
				readFile();
			}

		} while (menu != 9);
		// parse to the front end
		System.out.println("Thank you for using the program, the application will now terminate");
		sc.close();
		System.exit(0);
	}

	private void addRoom() {
		view.typeOfRoom();
		String roomType = sc.nextLine();
		while (!(roomType.equalsIgnoreCase("s")) && !(roomType.equalsIgnoreCase("r"))) {
			view.InvalidChoice();
			view.typeOfRoom();
			roomType = sc.nextLine();
		}

		//error here makes them validate three times not sure why
		view.noBeds();
		while (!sc.hasNextInt()) {
			view.invalidNumber();
			sc.next();
		}
		int noBeds =sc.nextInt();
		sc.nextLine();
		while (noBedValidator(noBeds, roomType)){
			view.InvalidChoice();
			
			while (!sc.hasNextInt()) {
				view.invalidNumber();
				sc.nextLine();
			}
			noBeds =sc.nextInt();
		}

		view.roomFeatures();
		String featSum = sc.nextLine();
		while (featSum.trim().isEmpty()) {
			view.invalidString();
			featSum = sc.nextLine();
		}

		String roomID = model.newRoom(roomType, noBeds, featSum);
		view.roomSucessful(roomID);
		menu();
	}

	public boolean noBedValidator(int noBeds, String roomType) {
		if (roomType.equalsIgnoreCase("s")) {
			if (noBeds != 6) {
				return true;
			} else {
				return false;
			}
		} else if (roomType.equalsIgnoreCase("r")) {
			if (noBeds != 1 && noBeds != 2 && noBeds != 4) {
				return true;
			} else {
				return false;
			}
		} return true;

	}

	private void rentRoom() {
		view.getRoomID();
		String roomID = sc.nextLine();
		while (idValidator(roomID)) {
			view.InvalidChoice();
			roomID = sc.nextLine();
		}
		if (!roomAvaliable(roomID)) {
			view.roomUnavalible(roomID);
			menu();
		}
		view.getCustID();
		String custID = sc.nextLine();
		while (custIDValidator(custID)) {
			view.InvalidChoice();
			custID = sc.nextLine();
		}

		view.getDate();
		String rentDate = sc.nextLine();
		while (dateValidator(rentDate)) {
			view.invalidDate();
			rentDate = sc.nextLine();
		}
		String str[] = rentDate.split("/");
		int day = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int year = Integer.parseInt(str[3]);

		view.getNoDays();
		int rentDays = sc.nextInt();
		sc.nextLine();

		model.rentRoom(roomID, custID, day, month, year, rentDays);
		menu();
	}

	private boolean roomAvaliable(String roomID) {
		if (model.getRoomAvalibility(roomID).equalsIgnoreCase("Avaliable")) {
			return true;
		} else {
			return false;
		}
	}

	//needs to validate ------
	private boolean custIDValidator(String custID) {
		return false;
	}

	private void returnRoom() {
		view.getRoomID();
		String roomID = sc.nextLine();

		while (idValidator(roomID)) {
			view.invalidString();
			roomID = sc.nextLine();
		}
		String roomType = null;
		if (roomID.charAt(0) == 'S') {
			roomType = "Suite";
		} else {
			roomType = "Standard Room";
		}

		String rentDate = model.getRentDate(roomID);
		String estReturnDate = model.getEstReturnDate(roomID);

		view.getReturnDate(roomType, rentDate, estReturnDate);
		String rentReturnDate = sc.nextLine();
		while (dateValidator(rentReturnDate)) {
			view.invalidDate();
			rentReturnDate = sc.nextLine();
		}
		String str[] = rentReturnDate.split("/");
		int day = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int year = Integer.parseInt(str[3]);

		view.roomReturned(model.returnRoom(roomID, day, month, year), roomID);
		menu();

	}

	private boolean idValidator(String roomID) {
		if (!(Character.toString(roomID.charAt(0)).equals("r"))
				&& !(Character.toString(roomID.charAt(0)).equals("s"))) {
			return false;
		}

		if (Character.toString(roomID.charAt(0)).equals("r")) {
			if (model.getNoStandardRooms() < (Integer.parseInt(Character.toString(roomID.charAt(1))
					+ Character.toString(roomID.charAt(2)) + Character.toString(roomID.charAt(3))))) {
				return false;
			} else {
				return true;
			}
		} else {
			if (model.getNoSuiteRooms() < (Integer.parseInt(Character.toString(roomID.charAt(1))
					+ Character.toString(roomID.charAt(2)) + Character.toString(roomID.charAt(3))))) {
				return false;
			} else {
				return true;
			}
		}

	}

	private boolean dateValidator(String rentDate) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try {
			sdf.parse(rentDate);

			int currentYear = Year.now().getValue();
			String str[] = rentDate.split("/");
			int year = Integer.parseInt(str[2]);

			if (year < currentYear || year < (currentYear - 100)) {
				return false;
			}

		} catch (NumberFormatException | ParseException ex) {

			return false; // Returns false if parsing fails (in case of bad input).
		}

		return true; // Returns true for valid date Strings
	}

	private void maintenance() {
		view.getRoomID();
		String roomID = sc.nextLine();
		while (idValidator(roomID)) {
			view.InvalidChoice();
			roomID = sc.nextLine();
		}
		model.maintenance(roomID);
		menu();
	}

	private void completMaint() {
		view.getRoomID();
		String roomID = sc.nextLine();
		while (idValidator(roomID)) {
			view.InvalidChoice();
			roomID = sc.nextLine();
		}
		model.maintenance(roomID);
		model.completeMaintance(roomID);
		menu();
	}

	private void displayRooms() {
		view.displayRooms(model.displayRooms());
		menu();
	}

	private void saveToFile() {
	}

	private void readFile() {
	}

}
