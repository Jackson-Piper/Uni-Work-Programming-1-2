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
		noStandard = 1;
		noSuite =1;
	}
	

	//add a room
	public String newRoom(String roomType, int noBed, String featSum){
		String roomID = getNextID(roomType);
		if(roomType.equalsIgnoreCase("r")){		
			rooms[roomCounter] = new StandardRoom(roomID, noBed, featSum);
			roomCounter++;
			noStandard++;
		}
		else{
			rooms[roomCounter] = new Suite(roomID, noBed, featSum);
			roomCounter++;
			noSuite++;
		}
		return roomID;
	}


	private String getNextID(String roomType) {
		
		if(roomType.equalsIgnoreCase("r")){
			if(noStandard<10){
			return "R00"+noStandard;
			}else if(noStandard<100 &&noStandard>10){
				return "R0"+noStandard;
			}else{
				return "R"+noStandard;
			}
		}
		else{
			if(noSuite<10){
				return "S00" + noSuite;
			}else if(noSuite < 100 && noSuite>10){
				return "S0"+noSuite;
			}else{
				return "S"+noSuite;
			}
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
	public String returnRoom(String roomIDSearch, int day, int month, int year){
		DateTime returnDate = new DateTime(day, month, year);
		int roomNumber = getRoomNumber(roomIDSearch);
		rooms[roomNumber].returnRoom(returnDate);

		String roomReturned = rooms[roomNumber].getLastRentalDetails();
		return roomReturned;
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

	public int getNoStandardRooms(){	
		return noStandard;
	
	}
	
	public int getNoSuiteRooms(){
		return noSuite;
	}
	
	public String displayRooms(){
	    String roomsDisplay = "";
	    for (int i = 0; i< roomCounter; i++){
	     roomsDisplay += "Room ID: \t\t"+ rooms[i].getRoomID()+
	                  "\nNumber of Beds: \t" + rooms[i].getNoBeds()+
	                  "\nStatus:\t\t\t" + rooms[i].getStatus()+
	                  "\nLast Maintenance Date: "+rooms[i].getLastMaintance()+
	                  "\nFeature Summary: \t" + rooms[i].getFeatSum()+
	                  "\n---------------Rental Records---------------\n"+rooms[i].getRentalDetails();
	    
	    
		}
	    return roomsDisplay;
	}


	public String getRoomAvalibility(String roomID) {
		int roomNumber = getRoomNumber(roomID);
		return rooms[roomNumber].getStatus();
	}


	public String getRentDate(String roomID) {
		int roomNumber = getRoomNumber(roomID);

		DateTime lastRentDate = rooms[roomNumber].getLastRentDate();
		return lastRentDate.getFormattedDate();
	}


    public String getEstReturnDate(String roomID) {
		int roomNumber = getRoomNumber(roomID);

		DateTime lastEstRentDate = rooms[roomNumber].getLastEstRentDate();
		return lastEstRentDate.getFormattedDate();
    }


	//read from file

	//write to file
	
}