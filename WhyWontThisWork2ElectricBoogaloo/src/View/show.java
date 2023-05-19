package View;

public class show{

    public void show(BorderPane root, Stage primaryStage, Class class, MenuBar mb){
        cd.prep(selectedRecord);
		root = class.getRoot();
		root.setTop(mb);
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}