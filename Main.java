import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        MinHeap test = new MinHeap();
     // System.out.println(test.isEmpty());
        //test.insert(new Ride(0, LocalTime.parse("15:33:33"), 342, 942, "kyle,barker"));
        Ride[] rides = new Ride[6];  // Adjust the size if needed

        
        rides[0] = new Ride(2, LocalTime.parse("18:10:55"), 200, 700);
      rides[1] = new Ride(1, LocalTime.parse("23:22:12"), 700, 999);
      rides[2] = new Ride(3, LocalTime.parse("09:22:12"), 100, 999);

//rides[11].addPassenger("Mariam, Owen, Jayda");
        
        test.heapify(rides, 11);
        rides[1].addPassenger("Kyle,Jayden,Mariam");
       // System.out.println(test.isEmpty());
      //  test.peek();
        test.dump();
      

       // System.out.println(rides[2].compareTo(rides[]));
       
        
        


    }

}