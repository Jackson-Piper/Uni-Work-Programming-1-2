package model;

public class RentalRecord {

   private String recordID;
   private DateTime rentDate;
   private DateTime estReturnDate;
   private DateTime actReturnDate;
   private double rentFee;
   private double lateFee;

   public RentalRecord(String id, DateTime rentDate, DateTime estReturn, DateTime returnDate,
         double rentalFee, double lateReturnFee) {

      this.recordID = id;
      this.rentDate = rentDate;
      this.estReturnDate = estReturn;
      this.actReturnDate = returnDate;
      this.rentFee = rentalFee;
      this.lateFee = lateReturnFee;
   }

   public RentalRecord(String id, DateTime rentDate, DateTime estReturn) {
      this(id, rentDate, estReturn, null, 0, 0);
   }

   @Override
   public String toString() {
      if (actReturnDate == null) {
         return String.format("%s:%s:%s:none:none:none", recordID, rentDate.getFormattedDate(),
               estReturnDate.getFormattedDate());
      } else {
         return String.format("%s:%s:%s:%s:%.2f:%.2f", recordID, rentDate.getFormattedDate(),
               estReturnDate.getFormattedDate(),
               actReturnDate.getFormattedDate(), rentFee, lateFee);
      }
   }

   // Need to format the string output for this wehn i get a chance beyond tabs
   public String getDetails() {
      String details[] = toString().split(":");
      String output = "Record ID :\t\t " + details[0]
            + "\nRent Date : \t\t" + details[1]
            + "\nEstimated Return Date \t: " + details[2]
            + "\nActual Return Date \t: " + details[3]
            + "\nRental Fee :\t\t " + details[4]
            + "\nLate Fee : \t\t" + details[5];
      return output;
   }

   public DateTime getRentDate() {
      return rentDate;
   }

   public void setRentDate(DateTime rentDate) {
      this.rentDate = rentDate;
   }

   public DateTime getEstimatedReturnDate() {
      return estReturnDate;
   }

   public void setEstimatedReturnDate(DateTime estimatedReturnDate) {
      this.estReturnDate = estimatedReturnDate;
   }

   public String getActualReturnDate() {
      return actReturnDate.getEightDigitDate();
   }

   public void setActualReturnDate(DateTime actualReturnDate) {
      this.actReturnDate = actualReturnDate;
   }

   public double getRentalFee() {
      return rentFee;
   }

   public void setRentalFee(double rentalFee) {
      this.rentFee = rentalFee;
   }

   public double getLateFee() {
      return lateFee;
   }

   public void setLateFee(double lateFee) {
      this.lateFee = lateFee;
   }

   public String getId() {
      return recordID;
   }
}