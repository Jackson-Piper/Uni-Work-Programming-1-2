import java.io.Serializable;

public class Record implements Serializable{

    private String id;
    private double weight;
    private double tempreture;
    private double bloodPressureHigh;
    private double bloodPressureLow;
    private String note;
    private String date;

    public Record(String recordID, Double weight, Double tempreture, Double bloodPressureH, Double bloodPressureL,
            String note, String date) {

                this.id = recordID;
                this.weight = weight;
                this.tempreture = tempreture;
                this.bloodPressureHigh = bloodPressureH;
                this.bloodPressureLow = bloodPressureL;
                this.note = note;
                this.date = date;
    }

    public int getID() {
        return Integer.parseInt(id);
    }

    public void updateRecord(Record updatedRecord) {
    }

    public Double getWeight() {
        return weight;
    }

    public Double getTempreture() {
        return tempreture;
    }

    public Double getBloodPressureH() {
        return bloodPressureHigh;
    }

    public Double getBloodPressureL(){
        return bloodPressureLow;
    }

    public String getNote() {
        return note;
    }

    public void updateRecord(Double weight, Double tempreture, Double bloodPressureH, double bloodPressureL,
            String note) {
            this.weight = weight;
            this.tempreture = tempreture;
            this.bloodPressureHigh = bloodPressureH;
            this.bloodPressureLow = bloodPressureL;
            this.note = note;
    }

    public String getData() {
        return id+","+weight+","+tempreture+","+bloodPressureHigh+","+bloodPressureLow+","+note+","+date;
    }
}
