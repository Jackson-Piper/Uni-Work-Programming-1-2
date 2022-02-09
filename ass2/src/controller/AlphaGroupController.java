package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
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
		menu();
		Scanner sc = new Scanner(System.in);
	
	}

	public void menu(){
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
		System.out.println("Thank you for using the program, the application will now terminate");
		System.exit(0);
	}

	

	private void addRoom() {
		view.typeOfRoom();
		String roomType = sc.nextLine();
			while(!(roomType.equalsIgnoreCase("S"))&&!(roomType.equalsIgnoreCase("R"))){
				view.InvalidChoice();
				view.typeOfRoom();
				roomType=sc.nextLine();
			}
		int noBeds;
		do {
			view.noBeds();
			while (!sc.hasNextInt()) {
				view.invalidNumber();
				sc.next();
			}
			noBeds = sc.nextInt();
			sc.nextLine();
			if (noBeds !=1 && noBeds !=2 && noBeds !=4 &&noBeds !=1) {
				view.InvalidChoice();
			}
		}while(noBeds !=1 && noBeds !=2 && noBeds !=4 &&noBeds !=1);

		view.roomFeatures();
		String featSum = sc.nextLine();
		while (featSum.trim().isEmpty()){
			view.invalidString();
			featSum = sc.nextLine();
		}

		model.newRoom(roomType, noBeds, featSum);
		menu();
	}
	
	private void rentRoom() {
	}

	private void returnRoom() {
		view.getRoomID();
		String roomID = sc.nextLine();

		//turn this into its own method for validation testing
		while(!(Character.toString(roomID.charAt(0)).equals("R")) && !(Character.toString(roomID.charAt(0)).equals("S"))){
			view.invalidString();
			roomID = sc.nextLine();
		}

		view.getDate();
		String rentDate = sc.nextLine();
		boolean valid = false;
		while (valid){
			dateValidator(rentDate);
			view.invalidDate();
			rentDate =sc.nextLine();
		}
		String str[] = rentDate.split("/");
		int day = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int year = Integer.parseInt(str[3]);

		model.returnRoom(roomID, day, month, year);
		menu();

	}

	private boolean dateValidator(String rentDate) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
          sdf.parse(rentDate);

          int currentYear = Year.now().getValue();
          String str[] = rentDate.split("/");
          int year = Integer.parseInt(str[2]);

          if(year > currentYear || year < currentYear-100){
            return false; 
          }

        } catch (NumberFormatException | ParseException ex){

          return false;  // Returns false if parsing fails (in case of bad input).
        }

        return true; // Returns true for valid date Strings
     }

	private void maintenance() {
	}

	private void completMaint() {
	}

	private void displayRooms() {
	}

	private void saveToFile() {
	}

	private void readFile() {
	}

}