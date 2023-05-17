package View;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ExportScreen {

	
	public void prep() {
		ChoiceBox fileType = new ChoiceBox();

        fileType.getItems().add(".CSV");
        fileType.getItems().add(".dat");
        fileType.getItems().add(".text");
        
        Label text = new Label("Please select What type of file yu would like to export.");
        
        
        GridPane g
	}
}
