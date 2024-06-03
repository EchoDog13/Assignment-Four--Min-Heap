import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
/**
 * 
 */
public class Main {

  public static void main(String[] args) {
    MinHeap testHeap = new MinHeap();
    Ride[] rides = new Ride[20];
    Ride ride3 = new Ride(3, LocalTime.parse("01:11:00"), 34, 342);
    Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
    Ride ride1 = new Ride(1, LocalTime.parse("01:00:00"), 34, 342);
    Ride ride4 = new Ride(1, LocalTime.parse("01:09:00"), 34, 342);

    ride1.addPassenger("Person A");
    ride2.addPassenger("Person B,Person C");
    ride3.addPassenger("Téa,Caleb");
    ride4.addPassenger("Jamie,Jacob");

    testHeap.insert(ride1);
    testHeap.insert(ride2);
    testHeap.insert(ride3);
    testHeap.insert(ride4);
    
    rides = testHeap.optimise();

    for (Ride ride : rides) {
        if (ride != null) {
            System.out.println(ride);
        }
      }}
}
