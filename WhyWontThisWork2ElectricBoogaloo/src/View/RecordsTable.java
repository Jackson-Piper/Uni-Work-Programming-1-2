package View;

import java.util.ArrayList;
import java.util.function.Consumer;

import Model.HealthRecord;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordsTable {
    public TableView<HealthRecord> getTable(ArrayList<HealthRecord> healthRecords) {
    		TableView<HealthRecord> table = new TableView();
    	for (HealthRecord records : healthRecords) {
    	    table.getItems().add(records);
    	}
    	    
    	TableColumn<HealthRecord, String> dateCol = new TableColumn<>("Date");
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    	
		TableColumn<HealthRecord, String> timeCol = new TableColumn<>("Time");
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    	    
    	TableColumn<HealthRecord, String> weightCol = new TableColumn<>("Weight (kg)");
    	weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
    	    
    	TableColumn<HealthRecord, String> tempCol = new TableColumn<>("Temperature (°C)");
    	tempCol.setCellValueFactory(new PropertyValueFactory<>("temp"));
    	    
    	TableColumn<HealthRecord, String> highBpCol = new TableColumn<>("High Blood Pressure");
    	highBpCol.setCellValueFactory(new PropertyValueFactory<>("highBP"));
    	    
    	TableColumn<HealthRecord, String> lowBpCol = new TableColumn<>("Low Blood Pressure");
    	lowBpCol.setCellValueFactory(new PropertyValueFactory<>("lowBP"));
    	    
    	TableColumn<HealthRecord, String> notesCol = new TableColumn<>("Notes");
    	notesCol.setCellValueFactory(new PropertyValueFactory<>("note"));
    	    
    	table.getColumns().addAll(dateCol, tempCol,  weightCol, tempCol, highBpCol, lowBpCol, notesCol);
    	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	return table;
    }
    
    public void addRowClickListener(TableView<Model.HealthRecord> table, Consumer<Model.HealthRecord> rowClickListener) {
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table.getSelectionModel().getSelectedItem() != null) {
                rowClickListener.accept(table.getSelectionModel().getSelectedItem());
            }
        });
}
}
    
