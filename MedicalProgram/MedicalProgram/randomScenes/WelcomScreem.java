public void loginScreen(){

        BorderPane bp = new BorderPane();
        primaryStage.setTitle("My Medical");

        Menu Record = new Menu("Records");
        MenuItem Create = new MenuItem("Create");
        MenuItem Edit = new MenuItem("Edit");
        MenuItem Delete = new MenuItem("Delet");
        MenuItem View = new MenuItem("View");

        Record.getItems().add(Create);
        Record.getItems().add(Edit);
        Record.getItems().add(Delete);
        Record.getItems().add(View);

        Menu Profile = new Menu("Profile");
        MenuItem Edit = new MenuItem("Edit");
        MenuItem View = new MenuItem("View");

        Profile.getItems().add(Edit);
        Profile.getItems().add(View);

        Menu Export = new Menu("Export");

        Menu About = new Menu("About");
        
        Menu LogOut = new Menu("Log Out");

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(Record);
        menuBar.getMenus().add(Profile);
        menuBar.getMenus().add(Edit);
        menuBar.getMenus().add(View);

        bp.setTop(menuBar);
        Scene scene = new Scene(bp, 500,500);
        return scene;



}