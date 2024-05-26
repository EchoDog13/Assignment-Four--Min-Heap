import java.time.LocalTime;

public class Main {

  public static void main(String[] args) {
MinHeap testHeap = new MinHeap();


        testHeap.insert(new Ride(1, LocalTime.parse("08:15:30"), 200, 700));
        testHeap.insert(new Ride(2, LocalTime.parse("12:45:00"), 300, 800));
        testHeap.insert(new Ride(3, LocalTime.parse("05:23:12"), 150, 600));
        testHeap.insert(new Ride(4, LocalTime.parse("19:30:45"), 250, 750));
        testHeap.insert(new Ride(5, LocalTime.parse("09:01:45"), 500, 1000));
        testHeap.insert(new Ride(6, LocalTime.parse("14:20:10"), 180, 650));
        testHeap.insert(new Ride(7, LocalTime.parse("11:15:20"), 220, 720));
        testHeap.insert(new Ride(8, LocalTime.parse("23:59:59"), 300, 800));
        testHeap.insert(new Ride(9, LocalTime.parse("00:00:01"), 400, 900));
        testHeap.insert(new Ride(10, LocalTime.parse("15:45:30"), 250, 750));
        testHeap.insert(new Ride(11, LocalTime.parse("10:10:10"), 200, 700));
        testHeap.insert(new Ride(12, LocalTime.parse("13:13:13"), 320, 820));
        testHeap.insert(new Ride(13, LocalTime.parse("17:17:17"), 230, 770));
        testHeap.insert(new Ride(14, LocalTime.parse("21:21:21"), 270, 810));
        testHeap.insert(new Ride(15, LocalTime.parse("04:04:04"), 190, 710));
        testHeap.insert(new Ride(16, LocalTime.parse("06:06:06"), 240, 760));
        testHeap.insert(new Ride(17, LocalTime.parse("07:07:07"), 210, 730));
        testHeap.insert(new Ride(18, LocalTime.parse("08:08:08"), 290, 790));
        testHeap.insert(new Ride(19, LocalTime.parse("09:09:09"), 310, 830));
        testHeap.insert(new Ride(20, LocalTime.parse("20:20:20"), 260, 780));

  

    MinHeap proxy = new MinHeap();
    Ride[] sortedRides = testHeap.sort();

     for(int i= 0; i<20; i++){
      proxy.outputRide(sortedRides, i);
    }

    
    if (sortedRides[0] == null) {
        System.out.println("HHH");
    } else {
       // proxy.outputRide(sortedRides, 0);
    }
   //   System.out.println(sRides[1].requestTime);
   //   testHeap.dump();
    

}}