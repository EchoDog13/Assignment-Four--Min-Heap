import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        MinHeap test = new MinHeap();
      System.out.println(test.isEmpty());
        //test.insert(new Ride(0, LocalTime.parse("15:33:33"), 342, 942, "kyle,barker"));
        Ride[] rides = new Ride[12];  // Adjust the size if needed

        rides[0] = new Ride(5, LocalTime.parse("15:33:33"), 342, 942, "Kyle,Adam,Benjamin");
        rides[1] = new Ride(8, LocalTime.parse("16:45:22"), 215, 731, "Emma,Sophia,Matthew");
        rides[2] = new Ride(3, LocalTime.parse("10:10:10"), 500, 1000, "John,Hannah,David");
        rides[3] = new Ride(7, LocalTime.parse("12:20:05"), 150, 800, "Alice,James,Lily");
        rides[4] = new Ride(1, LocalTime.parse("09:05:30"), 400, 600, "Michael,Samantha,Ethan");
        rides[5] = new Ride(10, LocalTime.parse("14:15:00"), 275, 925, "Sarah,Noah,Olivia");
        rides[6] = new Ride(4, LocalTime.parse("11:30:45"), 700, 1100, "Chris,Grace,Lucas");
        rides[7] = new Ride(9, LocalTime.parse("13:40:20"), 320, 780, "Emily,Daniel,Ava");
        rides[8] = new Ride(6, LocalTime.parse("08:55:15"), 450, 850, "Matthew,Chloe,Elijah");
        rides[9] = new Ride(0, LocalTime.parse("17:20:10"), 600, 950, "Olivia,Liam,Ella");
        rides[10] = new Ride(2, LocalTime.parse("18:10:55"), 200, 700, "Daniel,Mia,William");
        rides[11] = new Ride(11, LocalTime.parse("19:25:40"), 380, 820, "Jessica,Benjamin,Abigail");

        
        test.heapify(rides, 11);
        System.out.println(test.isEmpty());
        test.dump();
      
       
        
        


    }

}