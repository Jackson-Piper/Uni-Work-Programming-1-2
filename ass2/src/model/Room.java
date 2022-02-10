package model;

public abstract class Room {

    DateTime rentDate;
    DateTime estReturnDate;
    DateTime actReturnDate;
    String roomID;
    int noBed;
    String featSum;
    String status;
    double lateFeeRate;
    RentalRecord[] rentalRecords;
    double rate;
    private static final int MAX_RECORDS = 10;

    public Room(String roomID, int noBed, String featSum) {
        
        this.roomID = roomID;
        this.noBed = noBed;
        this.featSum = featSum;
        status = "avaliable";
        setRate();
        setLateFeeRate();

        rentalRecords = new RentalRecord[MAX_RECORDS];
    }

    public void rentRoom(String custID, DateTime rentDateTemp, DateTime estReturnDate) {
        status = "rented";
        rentDate = rentDateTemp;
        this.estReturnDate = estReturnDate;
        for (int x = 0; x <= 10; x++) {
            rentalRecords[x + 1] = rentalRecords[x];
        }
        String recordID = roomID + "_" + custID + "_" + rentDate;
        rentalRecords[0] = new RentalRecord(recordID, rentDate, estReturnDate);
    }

    // need to extend depening on the type of room
    public void returnRoom(DateTime returnDate) {
        this.actReturnDate = returnDate;
        int noDaysRented = DateTime.diffDays(actReturnDate, rentDate);
        double rentalFee = 0;
        double lateReturnFee = 0;
        for (int i = 0; i < noDaysRented; i++) {
            if (i < DateTime.diffDays(estReturnDate, rentDate)) {
                rentalFee += rate;
            } else {
                lateReturnFee = +getLateFeeRate();
            }
        }
        rentalRecords[0] = new RentalRecord(rentalRecords[0].getId(), rentalRecords[0].getRentDate(),
                rentalRecords[0].getEstimatedReturnDate(), returnDate,
                rentalFee, lateReturnFee);
        status = "avaliable";
    }

    public void setLateFeeRate() {
    }

    public double getLateFeeRate() {
        return lateFeeRate;
    }

    public String getRoomID(){
        return roomID;
    }


    public boolean roomAvalibility() {
        if (status.equalsIgnoreCase("avaliable")) {
            return true;
        } else {
            return false;
        }
    }

    public void maintainRoom() {
        status = "maintenance";
    }

    public void completeMaintance() {
        status = "avaliable";
    }

    public void setRate() {}

    public double getRate() {
        return rate;
    }
    public String getRentalDetails(){
     String details;
        for (int i = 0; i<10; i++){
            if(rentalRecords[i]!=null)
                details=+rentalRecords[i].getDetails()+"\n--------------------------------------\n";
            }
    }
}
