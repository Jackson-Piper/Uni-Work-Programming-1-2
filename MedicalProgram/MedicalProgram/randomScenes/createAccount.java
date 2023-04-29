public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        primaryStage.setTitle("Create Acount");

        Text username = new Text ("Username");
        Text password = new Text ("Password");
        Text confirmPassword = new Text("Confirm Password");
        Text firstName = new Text("First Name");
        Text lastName = new Text("Surname");
        Text dob = new Text("Date of Birth");
        
        GridPane leftGP = new GridPane();
        leftGP.add(username, 0,0,1,1);
        leftGP.add(password, 1,0,1,1);
        leftGP.add(confirmPassword,2,0,1,1);
        leftGP.add(firstName,3,0,1,1);
        leftGP.add(lastname,4,0,1,1);
        leftGP.add(dob,5,0,1,1);
        
        leftGP.setHgap(10);
        leftGP.setVgap(15);
        
        TextField newUsername = new TextField();
        newUsername.setText("Example@abc.com");

        PasswordField newPassword = new PasswordField();
        PasswordField confirmPassword = new PasswordField();

        TextField newFirstName = new TextField();
        newFirstName.setTtile("Eg.John");

        TextField newLastName = new TextField();
        newLastName.setTitle("Eg.Doe");

        DatePicker newDOB = new DatePicker();

        GridPane rightGP = new GridPane();
        rightGP.add(newUsername,0,1,1,1);
        rightGP.add(newPassword,1,1,1,1);
        rightGP.add(confirmPassword,2,1,1,1);
        rightGP.add(newFirstName,3,1,1,1);
        rightGP.add(newLastName,4,1,1,1);
        rightGP.add(newDob,5,1,1,1);
        
        rightGP.setHgap(10);
        rightGP.setVgap(15);

        Image logo = new ImageView(new Image("C:\\Users\\jackson\\eclipse-workspaceUNI\\WhyWontThisWork\\tempLogo.JPG"));

        borderPane.setTop(logo);
        borderPane.setLeft(leftGP);
        borderPane.setRight(rightGP);

        Scene scene = new Scene(borderPane, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }