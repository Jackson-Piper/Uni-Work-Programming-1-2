public class login{
    public void main(String[] Args){
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
                User user = findUser(username);
                System.out.println("Please enter your password: ")
                String password = sc.nextLine();
                if(password.equals(user.getPassword())){
                    login(user);
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
                User newUser = new User(userID,username, password, firstName, lastName);
                user.add(newUser);
                login(newUser)
            }
            case "3"{
                System.exit(0);
            }
        }

    }

    public void loginMenu(User user){
        System.out.println("--------------------");
        System.out.println(user.getName()+" "+user.getSurname());
        System.out.println("--------------------");
        System.out.println("1)\tUser Details");
        System.out.println("2)\tView records");
        System.out.println("3)\tCreate a Record");
        System.out.println("4)\tEdit Record");
        System.out.println("5)\tExit");
        System.out.println("6)\tLog Out");
         System.out.println("--------------------");
    }

    public void login(User user){
        loginMenu(user);
        String nav = sc.nextLine();
        switch(nav){
            case "1"{
                userDetails(user);
            }
            case "2"{
                viewRecords(user);
            }
            case "3"{
                createRecord(user);
            }
            case "4"{
                editRecord(user);
            }
            case "5"{
                System.exit(0);
            }
            case "6"{
                mainMenu();
            }
        }
    }

    public void userDetails(User user){
         System.out.println("User Name : \t" + user.getUserName());
         System.out.printlm("First Name: \t"+ user.getFirstName());
         System.out.println("Surname: \t"+ user.getSurname());
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
                        editName(user);
                        userDetails(user);
                    }
                    case "2"{
                        editSurname(user);
                        userDeatils(user);
                    }
                    case "3"{
                        userDeatils(user);
                    }
                }
               }
               case "N""n"{
                login(user);
               }
            }
         }
    }

    public void editName(User user){
        System.out.println("Change "+user.getName()+" to");
        String name = sc.nextLine();
        user.setName(name);
    }

    public void editSurname(User user){
        System.out.println("Change "+user.getSurname()+" to");
        String name = sc.nextLine();
        user.setSurname(name);
    }

    public void viewRecords(User user){
        System.out.println(user.getPrintRecords());
    }

    public User findUser(String username){
        
    }

}