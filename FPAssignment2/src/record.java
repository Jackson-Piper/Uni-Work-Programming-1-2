public class record implements recordInterface{

private String recordID;
private Double weight;
private Double tempreture;
private Double bloodPressureHigh;
private Double bloodPressureLow;
private String note;

public void record(String recordID, double weight, double tempreture, double bloodPressureHigh, double bloodPressureLow, String note){
    this.recordID = recordID;
    this.weight = weightID;
    this.tempreture = tempreture;
    this.bloodPressureHigh = bloodPressureHigh;
    this.bloodPressureLow = bloodPressureLow;
    this.note = note;
}
 
//interface
public void editRecord(Record record){
    
}

}