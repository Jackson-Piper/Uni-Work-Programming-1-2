module WhyWontThisWork2ElectricBoogaloo {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens Model to javafx.base;
}
