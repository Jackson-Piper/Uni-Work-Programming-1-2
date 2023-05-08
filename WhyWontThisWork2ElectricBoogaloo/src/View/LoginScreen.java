package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private Label errorLabel;
    private Stage primaryStage;
    private BorderPane root;
    public Button login;
    public Button createAccount;

    @Override
    public void start(Stage primaryStage) {
		primaryStage.setTitle("Login");
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(0, 0, 0, 0));
        
        Text userName = new Text ("Username");
        Text password = new Text ("Password");

        TextField nameLogin = new TextField();
        nameLogin.setText("example@abc.com");

        this.login = new Button();
        login.setText("Login");
        this.createAccount = new Button();
        createAccount.setText("Create Account");

        PasswordField passwordLogin = new PasswordField();

        GridPane gridPane = new GridPane();

        gridPane.add(userName, 0, 0);
        gridPane.add(nameLogin, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(passwordLogin, 1, 1);
        gridPane.add(createAccount, 1, 3);
        gridPane.add(login, 1, 2);
        GridPane.setHalignment(createAccount, HPos.CENTER);
        GridPane.setHalignment(login, HPos.CENTER);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(15);
        
        
        ImageView logo = new ImageView(new Image("C:\\Users\\jackson\\eclipse-workspaceUNI\\WhyWontThisWork\\tempLogo.JPG"));
        
        
        bp.setCenter(gridPane);
        bp.setTop(logo);
        
        BorderPane.setAlignment(logo, Pos.CENTER);
        BorderPane.setMargin(logo, new Insets(50, 0, 0, 0));
        BorderPane.setMargin(gridPane, new Insets(-100, 0, 0, 0));
        createAccount.setOnAction(event ->{
        	System.out.println("Create Account");
//            loadCreatAccount();
        });

//        login.setOnAction(new EventHandler<ActionEvent>());
        System.out.println("5");
        this.root = bp;
    }
    
    public BorderPane getRoot() {
    	return root;
    }
    
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
