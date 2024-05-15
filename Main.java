import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        MinHeap test = new MinHeap();
        Ride r = new Ride(1, LocalTime.parse("15:33:33"), 642,455, "Jake,Caleb,Tèa");
        Ride a = new Ride(2, LocalTime.parse("14:33:33"), 642,555, "Kyle,Ryan,Anke");
        Ride s = new Ride(3, LocalTime.parse("14:33:33"), 642,555, "Thomas,co");
        Ride as = new Ride(4, LocalTime.parse("14:33:33"), 642,555, "Jayden,Hanna");

        test.insert(a);
        test.insert(r);
        test.insert(s);
        test.insert(as);

;


        test.dump();
        System.out.println("Post removal");
        test.remove(r);
        test.dump();


    }

}