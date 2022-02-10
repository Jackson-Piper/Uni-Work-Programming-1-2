package model;

public class RoomCollection {
	
	private Room[] rooms;
	private final int MAX_ROOM = 50;
	int roomCounter;
	int noSuite;
	int noStandard;
	
	public RoomCollection() {
		rooms = new Room[MAX_ROOM];
		roomCounter = 0;
		noStandard = 001;
		noSuite =001;
	}
	

	//add a room
	public void newRoom(String roomType, int noBed, String featSum){
		String roomID = getNextID(roomType);
		if(roomType.equalsIgnoreCase("Standard")){		
			rooms[roomCounter] = new StandardRoom(roomID, noBed, featSum);
			roomCounter++;
			noStandard++;
		}
		else{
			rooms[roomCounter] = new Suite(roomID, noBed, featSum);
			roomCounter++;
			noSuite++;
		}
	}


	private String getNextID(String roomType) {
		
		if(roomType.equalsIgnoreCase("standard")){
			return "R"+noStandard;
		}
		else{
			return "S"+noSuite;
		}

	}

	//maybe grab day month year from econtroller
	public void rentRoom(String roomId, String custID, int day, int month, int year, int rentDays){
		int roomNumber = getRoomNumber(roomId);
		DateTime rentDateTemp = new DateTime(day, month, year);
		DateTime estReturnDate = new DateTime(rentDateTemp, rentDays);
		rooms[roomNumber].rentRoom( custID,  rentDateTemp,  estReturnDate);
	}


	private int getRoomNumber(String roomIDSearch) {
		for(int i = 0; i<roomCounter;i++){
		
		if(rooms[i].getRoomID().equalsIgnoreCase(roomIDSearch)){
			return i;
		}
		}
		return 0;
	}

	//return a room
	public void returnRoom(String roomIDSearch, int day, int month, int year){
		DateTime returnDate = new DateTime(day, month, year);
		int roomNumber = getRoomNumber(roomIDSearch);
		rooms[roomNumber].returnRoom(returnDate);


	}

	//maintain a room
	public void maintenance(String roomIDSearch){
		int roomNumber = getRoomNumber(roomIDSearch);
		rooms[roomNumber].maintainRoom();

	}

	public void completeMaintance(String roomIDSearch){
		int roomNumber = getRoomNumber(roomIDSearch);
		rooms[roomNumber].completeMaintance();
	}

	//read from file

	//write to file
	
}
