public class login{

    ArrayList<User> Users = new ArrayList<>();
    User currentUser;
    public void main(String[] Args){
    loadUsers()
    mainMenu()
    }
        public void mainMenu(){
        System.out.println("1)\tLog In");
        System.out.println("2)\tCreate an Account");
        System.out.println("3)\tExit");

        Scanner sc = new Scanner(System.in);
        String nav = sc.next();
        switch(nav){
            case "1"{
                System.out.println("Please enter your username: ")
                String username = sc.nextline();
                while(!validateUser(username)){
                    System.out.println("Error user doesnt exists");
                    System.out.println("Please check username or enter 'Menu' to return to main menu");
                    username = sc.nextLine();
                    if(username.equalsIgnoreCase("Menu")){
                        mainMenu();
                    }                
                }

                User userTemp = findUser(username);
                System.out.println("Please enter your password: ")
                String password = sc.nextLine();
                if(password.equals(user.getPassword())){
                    currentUser = userTemp;
                    login();
                }else{
                    System.out.println("Error wrong password or email")
                }

            }   
            case "2"{
                System.out.println("Please enter a username for the account: ");
                String username = sc.nextLine();
                System.out.println("Please enter a password for the account: ");
                String password = sc.nextLine();
                System.out.println("Please confirm the password: ");
                String passwordConfirm = sc.nextLine();
                System.out.println("Please enter your first name: ");
                String firstName = sc.nextLine();
                System.out.println("Please enter your surname: ");
                String surname = sc.nextLine();
                int userID = getNextID();
                User newUser = new Users(userID,username, password, firstName, lastName);
                user.add(newUser);
                currentUser = newUser;
                login(newUser)
            }
            case "3"{
                saveData();
                System.exit(0);
            }
        }

    }

    public void loginMenu(){
        System.out.println("--------------------");
        System.out.println(currentUser.getName()+" "+currentUser.getSurname());
        System.out.println("--------------------");
        System.out.println("1)\tUser Details");
        System.out.println("2)\tView records");
        System.out.println("3)\tCreate a Record");
        System.out.println("4)\tEdit Record");
        System.out.println("5)\tExit");
        System.out.println("6)\tLog Out");
         System.out.println("--------------------");
    }

    public void login(){
        loginMenu();
        String nav = sc.nextLine();
        switch(nav){
            case "1"{
                userDetails();
            }
            case "2"{
                viewRecords();
            }
            case "3"{
                createRecord();
            }
            case "4"{
                editRecord();
            }
            case "5"{
                saveData();
                System.exit(0);
            }
            case "6"{
                mainMenu();
            }
        }
    }

    public void createRecord(){
        Double weight;
        Double tempreture;
        Double bloodPresure;
        String note;
        System.out.println("Enter Weight (Kg) [Y/N]: ");
        String nav = sc.nextLine();
        if(yesVal(nav)){
        System.out.println("Weight: ");
        weight = sc.nextLine();
        }
        System.out.println("Enter Tempreture (Celcius) [Y/N]: ");
        if(yesVal(nav)){
        System.out.println("Tempreture: ");
        tempreture = sc.nextLine();
        }
        System.out.println("Enter Blood pressure (mmHg) [Y/N]: ");
        if(yesVal(nav)){
        System.out.println("Blood Pressure: ");
        bloodPresure = sc.nextLine();
        }
        System.out.println("Would you like to add a note (50 Words) [Y/N]:");
        if(yesVal(nav)){
        System.out.println("Note: ");
        note = sc.nextLine();
        }
        DateTime date = getDateTime();
        String recordID = getNextRecodID();
        System.out.println("--------------------------");
        System.out.println("Record:\t"+recordID);
        System.out.println("Weight:\t"+weight);
        System.out.println("Tempreture:\t"+tempreture);
        System.out.println("Blood Presure:\t"+bloodPresure);
        System.out.println("Note:\t"+note);
        System.out.println("--------------------------");
        System.out.println("Would you like to add this record [Y/N]");
        String commit = sc.nextLine();
        if(isYes(commit)){
        Record newRecord = new Record(weight, tempreture, bloodPresure, note);
        currentUser.addNewRecord(newRecord);
        System.out.println("Reccord Sucessfully added");
        System.out.println("Reeturning to menu");
        login();
        }else{
            System.out.println("Record Deleted");
            System.out.println("Returning to menu");
        }


    }

    public void userDetails(){
         System.out.println("User Name : \t" + currentUser.getUserName());
         System.out.printlm("First Name: \t"+ currentUser.getFirstName());
         System.out.println("Surname: \t"+ currentUser.getSurname());
         System.out.println("Edit? [Y/N]");
         String nav = sc.nextline();
         switch(nav){
            case "y""Y"{
                System.out.println("1)\t First Name");
                System.out.println("2)\t Surname");
                System.out.printlm("3)\t Back")
                System.out.println("--------------------");
                System.out.println("Please select one to edit: ");
                nav = sc.nextLine();
                switch (nav){
                    case "1"{
                        editName();
                        userDetails();
                    }
                    case "2"{
                        editSurname();
                        userDeatils();
                    }
                    case "3"{
                        userDeatils();
                    }
                }
               }
               case "N""n"{
                login();
               }
            }
         }
    }

    public void editName(){
        System.out.println("Change "+currentUser.getName()+" to");
        String name = sc.nextLine();
        user.setName(name);
    }

    public void editSurname(){
        System.out.println("Change "+currentUser.getSurname()+" to");
        String name = sc.nextLine();
        currentUser.setSurname(name);
    }

    public void viewRecords(User user){
        System.out.println(currentUser.getPrintRecords());
    }

    public User findUser(String username){
        for (User user : Users){
            if(username.equals(user.getUsername)){
                return user;
            }
        }
    }

    public boolean validateUser(String username){
        for (User user : Users){
            if(username.equals(user.getUsername)){
                return true;
            }else{
                continue;
            }
        }
        return false;
    }

    public String getNextID(){
        return Users.size() + 1
    }

}