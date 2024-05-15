import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        MinHeap test = new MinHeap();
        Ride r = new Ride(1, LocalTime.parse("15:33:33"), 642,455, "Jake,Caleb,Tèa");
        Ride a = new Ride(2, LocalTime.parse("14:33:33"), 642,555, "Kyle,Ryan,Anke");
        Ride s = new Ride(3, LocalTime.parse("14:33:33"), 642,555, "Thomas,Iris");
        Ride as = new Ride(4, LocalTime.parse("14:33:33"), 642,555, "Jayden,Hanna");
        //test.insert(new Ride(0, LocalTime.parse("15:33:33"), 342, 942, "kyle,barker"));


        test.insert(new Ride(5, LocalTime.parse("15:33:33"), 342, 942, "Kyle,Adam,Benjamin"));
test.insert(new Ride(8, LocalTime.parse("16:45:22"), 215, 731, "Emma,Sophia,Matthew"));
test.insert(new Ride(3, LocalTime.parse("10:10:10"), 500, 1000, "John,Hannah,David"));
test.insert(new Ride(7, LocalTime.parse("12:20:05"), 150, 800, "Alice,James,Lily"));
test.insert(new Ride(1, LocalTime.parse("09:05:30"), 400, 600, "Michael,Samantha,Ethan"));
test.insert(new Ride(10, LocalTime.parse("14:15:00"), 275, 925, "Sarah,Noah,Olivia"));
test.insert(new Ride(4, LocalTime.parse("11:30:45"), 700, 1100, "Chris,Grace,Lucas"));
test.insert(new Ride(9, LocalTime.parse("13:40:20"), 320, 780, "Emily,Daniel,Ava"));
test.insert(new Ride(6, LocalTime.parse("08:55:15"), 450, 850, "Matthew,Chloe,Elijah"));
test.insert(new Ride(0, LocalTime.parse("17:20:10"), 600, 950, "Olivia,Liam,Ella"));
test.insert(new Ride(2, LocalTime.parse("18:10:55"), 200, 700, "Daniel,Mia,William"));
test.insert(new Ride(11, LocalTime.parse("19:25:40"), 380, 820, "Jessica,Benjamin,Abigail"));


        test.dump();
        


    }

}