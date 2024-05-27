import java.time.LocalTime;

public class Main {

  public static void main(String[] args) {


    MinHeap testHeap = new MinHeap();
    Ride ride3 = new Ride(1, LocalTime.parse("00:00:00"), 34, 342);
    Ride ride2 = new Ride(2, LocalTime.parse("00:00:01"), 34, 342);
    Ride ride1 = new Ride(3, LocalTime.parse("00:00:02"), 34, 342);
    ride1.addPassenger("1");
    ride2.addPassenger("1,2");
    ride3.addPassenger("1,2,3");
    testHeap.insert(ride1);
    testHeap.insert(ride2);
    testHeap.insert(ride3);

    testHeap.optimise();

  

}}