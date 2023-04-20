
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class loginStage extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");

        Text userName = new Text ("Username");
        Text password = new Text ("Password");

        TextField nameLogin = new TextField();
        nameLogin.setText("example@abc.com");

        Button login = new Button();
        Button createAccount = new Button();

        PasswordField passwordLogin = new PasswordField();

        GridPane gridPane = new GridPane();

        gridPane.add(userName, 0, 0, 1, 1);
        gridPane.add(nameLogin, 0, 1, 1, 1);
        gridPane.add(login, 0, 2, 1, 1);

        gridPane.add(password, 1, 0, 1, 1);
        gridPane.add(passwordLogin, 1, 1, 1, 1);
        gridPane.add(createAccount, 1, 2, 1, 1);

        Scene scene = new Scene(gridPane, 240, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

        createAccount.setOnAction(event ->{
            createAccountScreen();
        });

        login.setOnAction(event ->{
            String userLogin = nameLogin.getText();
            Stirng userPassword = passwordLogin.getText();
            if(controller.validateLogin(userLogin,userPassword)){

            }else{
                showLoginError();
            }
        });
    }

    
    public void createAccountScreen(Stage primaryStage){
        primaryStage.setTitle("Create Aacount");

        Text username = new Text ("Username");
        Text password = new Text ("Password");
        Text confirmPassword = new Text("Confirm Password");
        Text firstName = new Text("First Name");
        Text lastName = new Text("Surname");
        Text dob = new Text("Date of Birth");

        TextField newUsername = new TextField();
        newUsername.setText("Example@abc.com");

        PasswordField newPassword = new PasswordField();
        PasswordField confirmPassword = new PasswordField();

        TextField newFirstName = new TextField();
        newFirstName.setTtile("Eg.John");

        TextField newLastName = new TextField();
        newLastName.setTitle("Eg.Doe");

        DatePicker newDOB = new DatePicker();

        GridPane gridPane = new GridPane();

        gridPane.add(username, 0,0,1,1);
        gridPane.add(password, 1,0,1,1);
        girdPane.add(confirmPassword,2,0,1,1);
        gridPane.add(firstName,3,0,1,1);
        gridPane.add(lastname,4,0,1,1);
        gridPane.add(dob,5,0,1,1);

        gridPane.add(newUsername,0,1,1,1);
        gridpane.add(newPassword,1,1,1,1);
        gridPane.add(confirmPassword,2,1,1,1);
        gridPane.add(newFirstName,3,1,1,1);
        gridPane.add(newLastName,4,1,1,1);
        gridPane.add(newDob,5,1,1,1);

        gridPane.setHgap(10);
        gridPane.setVgap(15);

        Scene scene = new Scene(gridPane, 380, 380);
        primaryStage.setScene(scene) 
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}