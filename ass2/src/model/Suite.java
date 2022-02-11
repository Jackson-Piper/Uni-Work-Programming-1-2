package model;

public class Suite extends Room {
        double rate;
        int dayTillMaint;
        double lateFee;

        // adding room is same same but different, starting with the defult count days
        // of days till it has to be maintained
        public Suite(String roomID, int noBed, String featSum) {

                super(roomID, noBed, featSum);
                rate = 999;
                dayTillMaint = 10;
                lateFee = 1099;
        }


        // rent a room the same way as the superclass no submethods are needed

        @Override
        public void returnRoom(DateTime returnDate) {
                actReturnDate = returnDate;
                int noDaysRented = DateTime.diffDays(actReturnDate, rentDate);
                double rentalFee = 0;
                double lateReturnFee = 0;
                for (int i = 0; i < noDaysRented; i++) {
                        if (i < DateTime.diffDays(estReturnDate, rentDate)) {
                                rentalFee += getRate();
                                dayTillMaint--;
                        } else {
                                lateReturnFee = +getLateFeeRate();
                                dayTillMaint--;
                        }
                }
                rentalRecords[0] = new RentalRecord(rentalRecords[0].getId(), rentalRecords[0].getRentDate(),
                                rentalRecords[0].getEstimatedReturnDate(), returnDate,
                                rentalFee, lateReturnFee);
                status = "avaliable";
        }

        @Override
        public void setRate() {
                if (dayTillMaint <= 3) {
                        rate = 699.3;
                } else {
                        rate = 999;
                }
        }

        @Override
        public double getRate() {
                setRate();
                return rate;
        }


        public double getLateFeeRate() {
                setLateFeeRate();
                return lateFeeRate;
            }

        
        public void setLateFeeRate() {
                if (dayTillMaint <= 3) {
                        lateFeeRate = 699.3;
                } else {
                        lateFeeRate = 999;
                }
        }

        @Override
        public boolean roomAvalibility() {
                if (dayTillMaint >= 0) {
                        status = "Needs Maintenance";
                        return false;
                } else if (status.equalsIgnoreCase("maintenance")) {
                        return false;
                }
                return true;
        }

        @Override
        public void completeMaintance() {
                status = "avaliable";
                dayTillMaint = 10;
        }

}