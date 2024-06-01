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

    public String toString(){

        String out = "";
        out += "--- Ride: " + rideID + " -------\n";
        out += "Time : " + requestTime + "\n";
        out += "Start ID : " + startLocation + "\n";
        out += "End ID : " + endLocation + "\n";
        out += "Passengers:\n";
        for (String item : passengerNames) {
            if (item != null) {
                out += item + "\n";
            }
        }
        out += "--------------------";
        return out;
    }
    }

