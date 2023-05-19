package View;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ExportScreen extends Application {
BorderPane root;
File location
ChoiceBox fileType;
Button export;
	@Overide
	public void Start(primaryStage) {
        
        BorderPane bp = new BorderPane();
        primaryStage.setTitle("My Medical - Record - Export");

		fileType = new ChoiceBox();
        
        Label File = new Label("Please select What type of file yu would like to export.");
        
        fileType.getItems().add(".csv");
        fileType.getItems().add(".dat");
        
        Label locationText = new Label("Please select Where you would like to export it to.");
        Label locationLabel = new Label("No Location selected.");
        Button locationButton = new Button("Choose a location.");

        locationButton.setOnAction(e -> {
            File location = directoryChooser.showDialog(primaryStage);

            if (selectedDirectory != null) {
                locationLabel.setText(location.getAbsolutePath());
            } else {

                locationLabel.setText("No Location selected");
            }
        });

        export = new Button("Export Records");
        
        
        GridPane gp = New GridPane();

        gp.add(File, 0, 0);
        gp.add(fileType, 0, 1);
        
        gp.add(locationText,0,3);
        gp.add(locationLabel,0,4);
        gp.add(locationButton,0,5);

        gp.add(export,0,7);

        gp.setHgap(10);
	    gp.setVgap(15);
       
        gp.setAlignment(Pos.CENTER);

        bp.setCenter(gp)
        this.root = bp


	}

    public BoderPane getRoot(){
     return this.root;
    }

    public String getLocation(){
        return location.getAbsolutePath();
    }

    public String getFileType(){
        return fileType.getValue();
    }
}
