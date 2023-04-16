import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class login{

    ArrayList<User> Users = new ArrayList<>();
    User currentUser;
    Scanner sc = new Scanner(System.in);
    public void main(String[] Args){
    
    //need to implement
    loadUsers();
    mainMenu();
    }
        public void mainMenu(){
        String nav="";
        switch(nav){
            case "1":{
                String username = sc.nextLine();
                while(!validateUser(username)){
                    username = sc.nextLine();
                    if(username.equalsIgnoreCase("Menu")){
                        mainMenu();
                    }                
                }

                User userTemp = findUser(username);
                String password = sc.nextLine();
                if(password.equals(userTemp.getPassword())){
                    currentUser = userTemp;
                    login();
                }else{
                    error();
                }

            }   
            case "2":{
                String username = sc.nextLine();
                String password = sc.nextLine();
                String passwordConfirm = sc.nextLine();
                String firstName = sc.nextLine();
                String surname = sc.nextLine();
                String userID = getNextID();
                User newUser = new User(userID, username, password, firstName, surname);
                Users.add(newUser);
                currentUser = newUser;
                login();
            }
            case "3":{
                saveData();
                System.exit(0);
            }
        }

    }

    private void error() {
        System.out.println("error");
        }


    public void loginMenu(){
        //System.out.println("--------------------");
        //System.out.println(currentUser.getName()+" "+currentUser.getSurname());
        //System.out.println("--------------------");
        //System.out.println("1)\tUser Details");
        //System.out.println("2)\tView records");
        //System.out.println("3)\tCreate a Record");
        //System.out.println("4)\tEdit Record");
        //System.out.println("5)\tExit");
        //System.out.println("6)\tLog Out");
        //System.out.println("--------------------");
    }

    public void login(){
        loginMenu();
        String nav = sc.nextLine();
        switch(nav){
            case "1":{
                userDetails();
            }
            case "2":{
                viewRecords();
            }
            case "3":{
                createRecord();
            }
            case "4":{
                editRecord();
            }
            case"5":{
                exportData();
            }
            case "6":{
                saveData();
                System.exit(0);
            }
            case "7":{
                mainMenu();
            }
        }
    }



    public void editRecord(){
        int recordIDTemp = sc.nextInt();
        if(!validRecord(recordIDTemp)){
            login();
        }
        Record edit = currentUser.getRecord(recordIDTemp);

        Double weight = edit.getWeight();
        Double tempreture = edit.getTempreture();
        Double bloodPressureH = edit.getBloodPressureH();
        double bloodPressureL = edit.getBloodPressureL();
        String note = edit.getNote();
        String nav = sc.nextLine();
        if(yesVal(nav)){
        weight = sc.nextDouble();
        }
        if(yesVal(nav)){
        tempreture = sc.nextDouble();
        }
        if(yesVal(nav)){
        bloodPressureH = sc.nextDouble();
        }
        if(yesVal(nav)){
        bloodPressureL = sc.nextDouble();
        }
        if(yesVal(nav)){
        note = sc.nextLine();
        }
        String commit = sc.nextLine();
        if(yesVal(nav)){
        edit.updateRecord(weight,tempreture,bloodPressureH, bloodPressureL,note);
        login();
        }else{
        }
        

    }


    private boolean yesVal(String nav) {
        return false;
    }
    //Needs to be a better way
    private boolean validRecord(int recordIDTemp) {
        ArrayList<Record> temp = currentUser.getRecords();
        for(Record record : temp){
            if(record.getID() == recordIDTemp){
                return true;
            }
            continue;
        };
        return false;
    }






    public void createRecord(){
        Double weight = 0.0;
        Double tempreture = 0.0;
        Double bloodPressureH= 0.0;
        Double bloodPressureL= 0.0;
        String note = "";
        String nav = sc.nextLine();
        if(yesVal(nav)){
        weight = sc.nextDouble();
        }
        if(yesVal(nav)){
        tempreture = sc.nextDouble();
        }
        if(yesVal(nav)){
        bloodPressureH = sc.nextDouble();
        }

        if(yesVal(nav)){
            bloodPressureL = sc.nextDouble();
            }
        if(yesVal(nav)){
        note = sc.nextLine();
        }
        String date = getDate();
        String recordID = currentUser.getnextRecordID();
        String commit = sc.nextLine();
        if(yesVal(commit)){
        Record newRecord = new Record(recordID, weight, tempreture, bloodPressureH, bloodPressureL, note, date);
        currentUser.createRecord(newRecord);
        login();
        }else{
            login();
        }
    }

    
    public void userDetails(){
         String nav = sc.nextLine();
         switch(nav){
            case "y","Y":{
                nav = sc.nextLine();
                switch (nav){
                    case "1":{
                        editName();
                        userDetails();
                    }
                    case "2":{
                        editSurname();
                        userDetails();
                    }
                    case "3":{
                        userDetails();
                    }
                }
               }
               case "N","n":{
                login();
               }
            }
         }
    

    public void editName(){
        String name = sc.nextLine();
        currentUser.setName(name);
    }

    public void editSurname(){

        String name = sc.nextLine();
        currentUser.setSurname(name);
    }

    public void viewRecords(){
        ArrayList<Record> userRecords = currentUser.getRecords();
        for(Record record: userRecords){
            System.out.println(record.getData() +"\n");
        }
        login();
    }

    public User findUser(String username){
        for (User user : Users){
            if(username.equals(user.getUsername())){
                return user;
            }
        }
        return null;
    }

    public boolean validateUser(String username){
        for (User user : Users){
            if(username.equals(user.getUsername())){
                return true;
            }else{
                continue;
            }
        }
        return false;
    }

    public String getNextID(){
       String nextID = String.valueOf(Users.size()) + 1;
       return nextID;
    }

        public static String getDate() {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = today.format(formatter);
            return formattedDate;
        }

            private void exportData(){
            try{
            BufferedWriter csv = new BufferedWriter(new FileWriter("records.csv"));
            String data="";
            ArrayList<Record> userrecords = currentUser.getRecords();
            for(Record record: userrecords){
                data += record.getData() +"\n";
            }
            csv.write(data);
            csv.close();
            login();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        private void loadUsers() {
            try{
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.dat"));
                while (true) {
                    try {
                        ArrayList<User> user = (ArrayList<User>) in.readObject();
                        Users.addAll(user);
                    } catch (EOFException e) {
                        break;
                    }
                }
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void saveData() {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.dat"));
                out.writeObject(Users);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

}
