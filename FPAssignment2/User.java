public class User{

    private string userID;
    private string username;
    private string password;
    private string firstName;
    private string Surname;

    public class user(String userID, String username, String password, String firstName, String Surname){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.Surname = Surname;
    }

    public String getUsername(){
        return username;
    }

    public String getFirstName(){
        return firstName
    }

    public String getSurname(){
        return Surname;
    }

    public void setName(String name){
        this.firstName = name;
    }

    public void setSurname(String name){
        this.Surname = name;
    }

    public String getPassword(){
        return password;
    }





}