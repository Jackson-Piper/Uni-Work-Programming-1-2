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

public void profileData(String username){
    String sql = "SELECT Username, FirstName, LastName, DOB FROM user WHERE Username = ?";
    PreparedStatement stmt = coonectiion.preparedStatement(sql);
    stmt.setString(1, username);
    return stmt;
}

public void updateProfile(String username, String firstName, String lastName, String DOB){
    String sql = "UPDATE user SET Username = ?, FirstName = ?, LastName = ?, DOB = ? WHERE Username = ?";
    PreparedStatement stmt = coonectiion.preparedStatement(sql);
    stmt.setString(1, username);
    stmt.setstring(2, firstName);
    stmt.setString(3, lastName);
    stmt.setString(4, DOB);
    stmt.setString(5, username);
    return stmt;
}

public void tableViewData(){
    String sql = "SELECT weight, Temp, HighBP, LowBP, Note, Date FROM record WHERE Username = ?";
    PreparedStatement stmt = connection.preparedStatement(sql);
    stmt.setString(1, user.getUsername());
    return stmt;
}

public void updateRecord(String newWeight, String newTemp, String newHighBP, String newLowBP, String recordID){
    String sql = "UPDATE record SET Weight = ?, Temp = ?, HighBP = ?, LowBP=? WHERE RecordID = ?"
    PreparedStatement stmt = coonectiion.preparedStatement(sql);
    stmt.setString(1, newWeight);
    stmt.setstring(2, newTemp);
    stmt.setString(3, newHighBP);
    stmt.setString(4, newLowBP);
    stmt.setString(5, recordID);
    return stmt;

}

public void deleteRecord(String recordID){
    String sql = "DELETE FROM record WHERE RecordID = ?";
    PreparedStatement stmt = coonectiion.preparedStatement(sql);
    stmt.setString(1, recordID);
    return stmt;
}