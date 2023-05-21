 package View;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;

public class ExportScreen extends Application {
private BorderPane root;
private File location;
private ChoiceBox fileType;
public Button export;
	
	@Override
	public void start(Stage primaryStage) {
        
        BorderPane bp = new BorderPane();
        primaryStage.setTitle("My Medical - Record - Export");

		fileType = new ChoiceBox();
        
        Label File = new Label("Please select What type of file yu would like to export.");
        
        fileType.getItems().add(".csv");
        fileType.getItems().add(".dat");
        fileType.getItems().add(".text");
        
        Label locationText = new Label("Please select Where you would like to export it to.");
        Label locationLabel = new Label("No Location selected.");
        Button locationButton = new Button("Choose a location.");
        DirectoryChooser directoryChooser = new DirectoryChooser();

        locationButton.setOnAction(e -> {
            File location = directoryChooser.showDialog(primaryStage);

            if (location != null) {
                locationLabel.setText(location.getAbsolutePath());
            } else {

                locationLabel.setText("No Location selected");
            }
        });

        export = new Button("Export Records");
        
        
        GridPane gp = new GridPane();

        gp.add(File, 0, 0);
        gp.add(fileType, 0, 1);
        
        gp.add(locationText,0,3);
        gp.add(locationLabel,0,4);
        gp.add(locationButton,0,5);

        gp.add(export,0,7);

        gp.setHgap(10);
	    gp.setVgap(15);
       
        gp.setAlignment(Pos.CENTER);

        bp.setCenter(gp);
        this.root = bp;


	}

    public BorderPane getRoot(){
     return this.root;
    }

    public String getLocation(){
        return location.getAbsolutePath();
    }

    public String getFileType(){
        return (String) fileType.getValue();
    }

}