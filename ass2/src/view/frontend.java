package view;
import controller.AlphaGroupController;

public class frontend {



    public frontend(AlphaGroupController alphaGroupController) {
    }

    public static void main(AlphaGroupController alphaGroupController) {
        
    }



    public void showMainMenu() {
        System.out.println("**** AlphaGroup MAIN MENU ****" +
        "\n---------------------------------------" +
        "\n1. Add Room/Suite" +
        "\n2. Rent Room/Suite" +
        "\n3. Return Room/Suite" +
        "\n4. Maintenance" +
        "\n5. Complete Maintenance" +
        "\n6. Display All Rooms/Suites" +
        "\n7. Save All Data To A Text File" +
        "\n8. Read Data From A Text File" +
        "\n9. Exit Program" +
        "\n---------------------------------------" +
        "\nEnter your choice (1-9):");
    }

    public void invalidNumber() {
        System.out.println("Invalid input - Please enter a Number: ");
    }

    public void InvalidChoice() {
        System.out.println("That is not a valid option - please try again");

    }

    public void typeOfRoom() {
        System.out.println("New room or suite? Enter your choice (r or s): ");
    }

    public void noBeds() {
        System.out.println("Enter number of beds: ");
    }

    public void roomFeatures() {
        System.out.println("Enter feature summary: ");
    }

    public void roomSucessful(String roomID){
        System.out.println("A new room has been created successfully. Room ID: "+roomID);
    }


    public void invalidString() {
        System.out.println("Inavlid input - Please try again");

    }

    public void getRoomID() {
        System.out.println("Enter Room ID: ");
    }

    public void roomUnavalible(String roomID){
        System.out.println("Room "+roomID+" is unavaliable");
    }

    public void getCustID() {
        System.out.println("Enter Customer ID: ");
    }

    public void getDate() {
        System.out.println("Enter Rent Date(dd/mm/yyyy): ");

    }

    public void invalidDate() {
        System.out.println("Invalid date entry - Please try again");
    }

    public void getNoDays() {
        System.out.println("Enter number of days to rent: ");
    }

    public void displayRooms(String displayRooms) {
        System.out.println(displayRooms);
    }

    public void getReturnDate(String roomType, String rentDate, String estReturnDate) {
        System.out.println("The " + roomType+" was rented on "+rentDate+", estimated return date: "+estReturnDate+".\nEnter the actual return date: ");
    }

    public void roomReturned(String returnRoom, String roomID) {
        System.out.println("Successfully returned room "+roomID+". The latest rental record has been\n updated as shown bewlow: \n"+returnRoom);
    }
}
