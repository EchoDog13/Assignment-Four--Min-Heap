import java.time.LocalTime;


public class Ride implements Comparable<Ride> {
     int rideID;
     LocalTime requestTime;
     String[] passengerNames = new String[5];
     int startLocation;
     int endLocation;
     
    public Ride(int rideID, LocalTime requestTime, int startLocation, int endLocation) {
        this.rideID = rideID;
        this.requestTime = requestTime;
       // this.passengerNames =  passengerNames.split(",");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    @Override
    public int compareTo(Ride otherRide) {
        return this.requestTime.compareTo(otherRide.requestTime);
    }
    public void addPassenger(String passengerNames){

        this.passengerNames = passengerNames.split(",");

    }

    





}