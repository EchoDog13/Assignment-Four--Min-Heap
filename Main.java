import java.time.LocalTime;

public class Main {

  public static void main(String[] args) {


    MinHeap testHeap = new MinHeap();
    Ride[] rides = new Ride[20];

    Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
    Ride ride1 = new Ride(1, LocalTime.parse("01:00:00"), 34, 342);

    ride1.addPassenger("A,B,C,D");
    ride2.addPassenger("E,F,G,H");

    testHeap.insert(ride1);
    testHeap.insert(ride2);

    rides = testHeap.optimise();

    for (Ride ride : rides) {
        if (ride != null) {
         System.out.println(ride);
        }
   
//testHeap.dump();
 
   
  

      }}}