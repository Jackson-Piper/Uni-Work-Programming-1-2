public void createUserTable(){

    //create url for database;
    String url;

    String sql = "CREATE TABLE IF NOT EXISTS user (\n
                    Username text PRIMARY KEY,\n
                    Password text NOT NULL,\n
                    FirstName Text NOT NULL,\n
                    LastName Text NOT NULL\n
                    DOB Text NOT NULL\n
                    )";

    try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.CreateStatement()) {
                stmt.execute(sql);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }


}

public void createRecordTable(){
    String url;

    String sql = "CREATE TABLE IF NOT EXISTS record (\n
                    RecordID Text PRIMARY KEY,\n
                    Weight Float,\n
                    Temp Float,\n
                    HighBP integer,\n
                    LowBP integer,\n
                    Note Text,\n
                    Date Text\n
                    FOREIGN KEY (Username) REFERANCES user(Username)
                    )";

                    try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.CreateStatement()) {
                stmt.execute(sql);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
}

public void insertRecord(String Weight, String Temp, String HighBP, String LowBP, String Date, String Username, String url){
    String sql = "INSERT INTO record(Weight, Temp, HighBP, LowBP, Date, Username) VALUES(?,?,?,?,?,?)";
    try(Connection conn = DriverManager.getConnection(url);)
            {PreparedStatement stmt = conn.preparedStatement(sql);
    stmt.setString(1, Weight);
    stmt.setString(2, Temp);
    stmt.setString(3, HighBP);
    stmt.setString(4, LowBP);
    stmt.setString(5, Date);
    stmt.setString(6, Username);
                stmt.execute();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

    
}

public ResultSet profileData(String username, String url){
    String sql = "SELECT Username, FirstName, LastName, DOB FROM user WHERE Username = ?";
    try(Connection conn = DriverManager.getConnection(url);)
            {PreparedStatement stmt = conn.preparedStatement(sql);
                stmt.setString(1, username);
            return stmt.executeQueary();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
}

public void updateProfile(String username, String firstName, String lastName, String DOB, String url){
    String sql = "UPDATE user SET Username = ?, FirstName = ?, LastName = ?, DOB = ? WHERE Username = ?";
    try(Connection conn = DriverManager.getConnection(url);){
    PreparedStatement stmt = coonectiion.preparedStatement(sql);
    stmt.setString(1, username);
    stmt.setstring(2, firstName);
    stmt.setString(3, lastName);
    stmt.setString(4, DOB);
    stmt.setString(5, username);
    stmt.execute();
    }catch (SQLException e){
                System.out.println(e.getMessage());
    }


}


// It makes it easier to encapsulate the data, manage it, and manipulate it, 
// and it also makes it easier to add additional functionality to the application, 
// such as sorting or filtering the data.
public ResultSet tableViewData(String username, String url){
    String sql = "SELECT weight, Temp, HighBP, LowBP, Note, Date FROM record WHERE Username = ?";
      try(Connection conn = DriverManager.getConnection(url);){
    PreparedStatement stmt = coonectiion.preparedStatement(sql);
    stmt.setString(1, username);
    return stmt.execute();
    }catch (SQLException e){
                System.out.println(e.getMessage());
    }
}

public void updateRecord(String newWeight, String newTemp, String newHighBP, String newLowBP, String recordID, String url){
    String sql = "UPDATE record SET Weight = ?, Temp = ?, HighBP = ?, LowBP=? WHERE RecordID = ?"
     try(Connection conn = DriverManager.getConnection(url);){
    PreparedStatement stmt = coonectiion.preparedStatement(sql);
    stmt.setString(1, newWeight);
    stmt.setstring(2, newTemp);
    stmt.setString(3, newHighBP);
    stmt.setString(4, newLowBP);
    stmt.setString(5, recordID);
    stmt.execute();
    }catch (SQLException e){
                System.out.println(e.getMessage());
    }
}

public void deleteRecord(String recordID String url){
    String sql = "DELETE FROM record WHERE RecordID = ?";
     try(Connection conn = DriverManager.getConnection(url);){
    PreparedStatement stmt = coonectiion.preparedStatement(sql);
    stmt.setString(1, recordID);
    stmt.execute();
    }catch (SQLException e){
                System.out.println(e.getMessage());
    }
}