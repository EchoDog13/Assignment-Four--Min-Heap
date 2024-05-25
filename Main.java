import java.time.LocalTime;

public class Main {

  public static void main(String[] args) {
    MinHeap testHeap = new MinHeap();

    Ride rides[] = new Ride[20];
    rides[0] = new Ride(57, LocalTime.parse("00:15:30"), 200, 700);
    rides[1] = new Ride(42, LocalTime.parse("12:45:00"), 300, 800);
    rides[2] = new Ride(86, LocalTime.parse("05:23:12"), 150, 600);
    rides[3] = new Ride(15, LocalTime.parse("19:30:45"), 250, 750);
    rides[4] = new Ride(99, LocalTime.parse("03:50:20"), 350, 850);
    rides[5] = new Ride(23, LocalTime.parse("14:05:10"), 275, 725);
    rides[6] = new Ride(61, LocalTime.parse("21:35:47"), 225, 675);
    rides[7] = new Ride(34, LocalTime.parse("06:12:34"), 320, 770);
    rides[8] = new Ride(72, LocalTime.parse("10:50:05"), 180, 680);
    rides[9] = new Ride(45, LocalTime.parse("17:22:48"), 240, 740);
    rides[10] = new Ride(88, LocalTime.parse("04:45:30"), 360, 860);
    rides[11] = new Ride(17, LocalTime.parse("09:13:55"), 290, 790);
    rides[12] = new Ride(51, LocalTime.parse("13:57:19"), 210, 710);
    rides[13] = new Ride(76, LocalTime.parse("18:03:23"), 330, 780);
    rides[14] = new Ride(28, LocalTime.parse("20:28:49"), 195, 695);
    rides[15] = new Ride(63, LocalTime.parse("07:37:11"), 265, 765);
    rides[16] = new Ride(14, LocalTime.parse("11:44:33"), 315, 815);
    rides[17] = new Ride(90, LocalTime.parse("15:18:06"), 185, 685);
    rides[18] = new Ride(37, LocalTime.parse("23:25:42"), 355, 855);
    rides[19] = new Ride(82, LocalTime.parse("02:39:14"), 205, 705);
testHeap.heapify(rides, 19);
testHeap.dump();

  }

}