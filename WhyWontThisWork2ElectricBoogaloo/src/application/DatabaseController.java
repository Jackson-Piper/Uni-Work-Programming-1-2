package application;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.HealthRecord;

public class DatabaseController {

	public static void createUserTable(String url) {
		String sql = "CREATE TABLE IF NOT EXISTS user (\n" +"UserID text PRIMARY KEY,\n"+ "Username text NOT NULL,\n"
				+ "Password text NOT NULL,\n" + "FirstName Text NOT NULL,\n" + "LastName Text NOT NULL,\n"
				+ "DOB Text NOT NULL,\n"+"Picture BLOB\n" + ")";

		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try (Connection conn = DriverManager.getConnection(url)) {
				if (conn != null) {
					DatabaseMetaData meta = conn.getMetaData();
					System.out.println("The driver name is " + meta.getDriverName());
					System.out.println("A new database has been created.");
					createUserTable(url);
				}

			} catch (SQLException e1) {
				System.out.println("Create Table");
				System.out.println(e1.getMessage());
			}
		}
	}

	public static void createRecordTable(String url) {

		String sql = "CREATE TABLE IF NOT EXISTS record (\nRecordID Text PRIMARY KEY,\n" + "Weight Float,"
				+ "\nTemp Float," + "\nHighBP integer," + "\nLowBP integer," + "\nNote Text," + "\nDate Text NOT NULL,"+"\nTime text NOT NULL,"
				+ "Username text NOT NULL,\n" + "\nFOREIGN KEY (Username) REFERENCES user(Username)" + ")";

		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println("Create Table Record");
			System.out.println(e.getMessage());
		}
	}

	public static void addUser(String UserID, String userName, String password, String firstName, String lastName, String dob, String url) {

		String sql = "INSERT INTO user(UserID, Username, Password, firstName, lastName, dob) VALUES(?,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection(url)) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, UserID);
			stmt.setString(2, userName);
			stmt.setString(3, password);
			stmt.setString(4, firstName);
			stmt.setString(5, lastName);
			stmt.setString(6, dob);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProfile(String username, String firstName, String lastName, String DOB,String userID, String url) {
		String sql = "UPDATE user SET Username = ?, FirstName = ?, LastName = ?, DOB = ? WHERE UserID = ?";
		try (Connection conn = DriverManager.getConnection(url);) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, DOB);
			stmt.setString(5, userID);
			stmt.execute();
			System.out.println("Executed");
		} catch (SQLException e) {
			System.out.println("Here 2");
			System.out.println(e.getMessage());
		}
	}

	public static void updateRecord(String newWeight, String newTemp, String newHighBP, String newLowBP, String note, String recordID,
			String url) {
		String sql = "UPDATE record SET Weight = ?, Temp = ?, HighBP = ?, LowBP=?, Note=? WHERE RecordID = ?";
		try (Connection conn = DriverManager.getConnection(url);) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, newWeight);
			stmt.setString(2, newTemp);
			stmt.setString(3, newHighBP);
			stmt.setString(4, newLowBP);
			stmt.setString(5, note);
			stmt.setString(6, recordID);
			stmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertRecord(String recordID, String Weight, String Temp, String HighBP, String LowBP, String Date,String Time, String note, String Username,
			String url) {
		String sql = "INSERT INTO record(RecordID, Weight, Temp, HighBP, LowBP, Date, Time, Note, Username) VALUES(?,?,?,?,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection(url);) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, recordID);
			stmt.setString(2, Weight);
			stmt.setString(3, Temp);
			stmt.setString(4, HighBP);
			stmt.setString(5, LowBP);
			stmt.setString(6, Date);
			stmt.setString(7, Time);
			stmt.setString(8, note);
			stmt.setString(9, Username);
			stmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ResultSet validLogin(String username, String password, String url) {
		String sql = "SELECT Username, Password FROM user WHERE Username = ?";
		try (Connection conn = DriverManager.getConnection(url);) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            String storedHashedPassword = rs.getString("PasswordHash");
	            if (BCrypt.checkpw(password, storedHashedPassword)) {
		} catch (SQLException e) {
			System.out.println("valid Login");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static String[] profileData(String username, String url) {
		String sql = "SELECT Username, FirstName, LastName, DOB FROM user WHERE Username = ?";
		try (Connection conn = DriverManager.getConnection(url);) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			String[] user = {rs.getString("Username"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("DOB")};
			return user;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// It makes it easier to encapsulate the data, manage it, and manipulate it,
	// and it also makes it easier to add additional functionality to the
	// application,
	// such as sorting or filtering the data.
	public static ArrayList tableViewData(String username, String url) {
		String sql = "SELECT RecordID, Weight, Temp, HighBP, LowBP, Note, Date, Time FROM record WHERE Username = ?";
		try (Connection conn = DriverManager.getConnection(url);) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			ArrayList<HealthRecord> HealthRecords = new ArrayList<HealthRecord>();
			while(rs.next()) {
			HealthRecords.add(new HealthRecord(rs.getString("RecordID"), rs.getString("Weight"), rs.getString("Temp"),rs.getString("HighBP"), rs.getString("LowBP"), rs.getString("Note"), rs.getString("Date"), rs.getString("Time")));
			}
			return HealthRecords; 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void deleteRecord(String recordID, String url) {
		String sql = "DELETE FROM record WHERE RecordID = ?";
		try (Connection conn = DriverManager.getConnection(url);) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, recordID);
			stmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void updateProfile(String username, String firstName, String lastName, String dob, String userID,
			byte[] imageData, String url) {
		String sql = "UPDATE user SET Username = ?, FirstName = ?, LastName = ?, DOB = ?, Picture =? WHERE UserID = ?";
		try (Connection conn = DriverManager.getConnection(url);) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, dob);
			stmt.setBytes(5, imageData);
			stmt.setString(6, userID);
			stmt.execute();
			System.out.println("Executed");
		} catch (SQLException e) {
			System.out.println("Here 2");
			System.out.println(e.getMessage());
		}
		
	}
}
	