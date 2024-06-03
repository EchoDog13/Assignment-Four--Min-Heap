import java.time.LocalTime;
/**
 * Ride class used in the implementation of a minheap. Ride Class contains ride
 * details and is stored in a minheap, where it is used to enable ride sharing
 */
public class Ride implements Comparable<Ride> {
    int rideID;
    LocalTime requestTime;
    String[] passengerNames = new String[5];
    int startLocation;
    int endLocation;

    /**
     * @param rideID        unique ID of the ride as an integer
     * @param requestTime   time the ride was requested in the format of a LocalTime
     * @param startLocation integer representing the start location
     * @param endLocation   integer representing the end location of the ride
     */
    public Ride(int rideID, LocalTime requestTime, int startLocation, int endLocation) {

        this.rideID = rideID;
        this.requestTime = requestTime;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    /**
     * Comparetor used to override and compare the request time of the rides
     */
    @Override
    public int compareTo(Ride otherRide) {
        return this.requestTime.compareTo(otherRide.requestTime);
    }

    /**
     * Adds passenger names to the ride class
     * 
     * @param passengerNames the string of passenger names to be added, split by ','
     */
    public void addPassenger(String passengerNames) {
        this.passengerNames = passengerNames.split(",");
    }

    /**
     * toString method used to output the rides
     */
    public String toString() {
        // Initalises empty string to store information in
        String out = "";
        out += "--- Ride: " + rideID + " -------\n";
        out += "Time : " + requestTime + "\n";
        out += "Start ID : " + startLocation + "\n";
        out += "End ID : " + endLocation + "\n";
        out += "Passengers:\n";
        // Adds passenger names to the end of the string from the list
        for (String item : passengerNames) {
            if (item != null) {
                out += item + "\n";
            }
        }
        out += "--------------------";
        return out;
    }
}
