import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Main {

  public static void main(String[] args) {

    MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(1, LocalTime.parse("01:09:01"), 34, 342);
        ride2.addPassenger("A,B,C");
        Ride ride1 = new Ride(1, LocalTime.parse("01:33:01"), 34, 342);
        ride1.addPassenger("A,B,C");
        Ride ride3 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        ride2.addPassenger("A,B,C");
        Ride ride4 = new Ride(2, LocalTime.parse("01:33:01"), 34, 342);
        ride1.addPassenger("A,B,C");
        
        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);
        testHeap.insert(ride4);
        testHeap.dump();

  }

}
