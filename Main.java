import java.time.LocalTime;

public class Main {

  public static void main(String[] args) {
MinHeap testHeap = new MinHeap();

    Ride ride1 = new Ride(1, LocalTime.parse("08:15:30"), 200, 700);
    Ride ride2 = new Ride(2, LocalTime.parse("12:45:00"), 300, 800);
    Ride ride3 = new Ride(3, LocalTime.parse("00:23:12"), 150, 600);
    Ride ride4 = new Ride(3, LocalTime.parse("02:23:12"), 150, 600);
    Ride ride5 = new Ride(3, LocalTime.parse("09:23:12"), 150, 600);

    testHeap.insert(ride1);
    testHeap.insert(ride2);    
    testHeap.insert(ride3);
    testHeap.insert(ride4);
    testHeap.insert(ride5);


    Ride[] sRides = testHeap.sort();
    
      System.out.println(sRides[1].requestTime);
    

}}