package view;
import controller.AlphaGroupController;

public class frontend {



    public frontend(AlphaGroupController alphaGroupController) {
    }

    public static void main(AlphaGroupController alphaGroupController) {
        
    }
   
private void addRoom() {
    }
   private void rentRoom() {
    }
 private void returnRoom() {
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



    public void showMainMenu() {
        System.out.println("**** AlphaGroup MAIN MENU ****" +
        "\n---------------------------------------" +
        "\n1. Add Room/Suite                        " +
        "\n2. Rent Room/Suite" +
        "\n3. Return Room/Suite" +
        "\n4. Maintenance" +
        "\n5. Complete Maintenance" +
        "\n6. Display All Rooms/Suites                        " +
        "\n7. Save All Data To A Text File" +
        "\n8. Read Data From A Text File" +
        "\n9. Exit Program" +
        "\n---------------------------------------" +
        "\nEnter your choice (1-9):");
    }

    public void invalidNumber() {
        System.out.println("Please enter a Number");
    }

    public void InvalidChoice() {
        System.out.println("That is not a valid option");

    }

    public void typeOfRoom() {
    }
}
