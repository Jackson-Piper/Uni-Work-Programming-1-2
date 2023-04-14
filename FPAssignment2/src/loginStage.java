
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

        Text usernameT = new Text ("Username");
        Text passwordT = new Text ("Password");

        TextField usernameTF = new TextField();
        usernameTF.setText("example@abc.com");

        Button login = new Button();
        Button createAccount = new Button();

        PasswordField passwordF = new PasswordField();

        GridPane gridPane = new GridPane();

        gridPane.add(usernameT, 0, 0, 1, 1);
        gridPane.add(usernameFT, 0, 1, 1, 1);
        gridPane.add(login, 0, 2, 1, 1);

        gridPane.add(passwordT, 1, 0, 1, 1);
        gridPane.add(passwordF, 1, 1, 1, 1);
        gridPane.add(createAccount, 1, 2, 1, 1);

        Scene scene = new Scene(gridPane, 240, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}