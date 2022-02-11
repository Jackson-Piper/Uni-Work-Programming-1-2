package model;

public class StandardRoom extends Room {

    //adds a room
    public StandardRoom(String roomIDTemp, int noBed, String featSum) {
        super(roomIDTemp, noBed, featSum);
        setRate();
        setLateFeeRate();
    }


    //rents a room
    //can call from superclass its no different 


    //returns a room

    //maintence
    //can call from superclass its no different 

    
    public void setLateFeeRate() {
        lateFeeRate = rate * 1.35;
        
    }

    @Override
    public void setRate() {
        if (noBed == 1) {
            rate = 59;
        } else if (noBed == 2) {
            rate = 99;
        } else {
            rate = 199;
        }
    }
}
