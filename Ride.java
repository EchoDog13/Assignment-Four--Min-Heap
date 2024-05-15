import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Ride {
     int rideID;
     LocalTime requestTime;
     String[] passengerNames = new String[5];
     int startLocation;
     int endLocation;

    public Ride(int rideID, LocalTime requestTime, int startLocation, int endLocation, String passengerNames) {
        this.rideID = rideID;
        this.requestTime = requestTime;
        this.passengerNames =  passengerNames.split(",");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }


}