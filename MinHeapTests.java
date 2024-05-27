
//Import statements 
import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

public class MinHeapTests {

    /**
     * Importing Test
     * insert(Ride r)
     * empty heap
     * heap with item
     * heap as last node
     * heap in middle node
     * heap in root node
     * 
     * @return
     */

    // stores the output of the console output in standard out
    private final PrintStream standardOut = System.out;
    // capture the output of the information written to console
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Before each test is run, sets output print stream to outputStreamCaptor to
     * capture output. This is used to test the dump method
     */
    @BeforeEach
    public void setOut() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * resets the output of the print stream
     */
    @AfterEach
    public void restoreOut() {
        System.setOut(standardOut);
    }

    /* COMPARTER TESTS */

    @Test
    @DisplayName("Comparator Test: First item is less than second item, large time difference")
    public void testComparatorFirstLessThanSecondLarge() {
        Ride[] rides = new Ride[6];
        rides[0] = new Ride(2, LocalTime.parse("00:00:00"), 200, 700);
        rides[1] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
        Assert.assertEquals(-1, rides[0].compareTo(rides[1]));
    }

    @Test
    @DisplayName("Comparator Test: First item is less than second item, small time difference")
    public void testComparatorFirstLessThanSecondSmall() {
        Ride[] rides = new Ride[6];
        rides[0] = new Ride(2, LocalTime.parse("12:00:01"), 200, 700);
        rides[1] = new Ride(1, LocalTime.parse("12:00:02"), 700, 999);
        Assert.assertEquals(-1, rides[0].compareTo(rides[1]));
    }

    @Test
    @DisplayName("Comparator Test: First item is greater than second item, large time difference")
    public void testComparatorGreatherThanSecondLarge() {
        Ride[] rides = new Ride[6];
        rides[1] = new Ride(2, LocalTime.parse("00:00:00"), 200, 700);
        rides[0] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
        Assert.assertEquals(1, rides[0].compareTo(rides[1]));
    }

    @Test
    @DisplayName("Comparator Test: First item is greater than second item, small time difference")
    public void testComparatorFirstGreaterThanSecondSmall() {
        Ride[] rides = new Ride[6];
        rides[1] = new Ride(2, LocalTime.parse("10:00:01"), 200, 700);
        rides[0] = new Ride(1, LocalTime.parse("10:00:02"), 700, 999);
        Assert.assertEquals(1, rides[0].compareTo(rides[1]));
    }

    @Test
    @DisplayName("Comparator Test: First item is equal to second item")
    public void testComparatorFirstEqualToSecond() {
        Ride[] rides = new Ride[6];
        rides[1] = new Ride(2, LocalTime.parse("10:00:01"), 200, 700);
        rides[0] = new Ride(1, LocalTime.parse("10:00:01"), 700, 999);
        Assert.assertEquals(0, rides[0].compareTo(rides[1]));
    }

    @Test
    @DisplayName("Comparator Test: First item is equal to second item")
    public void testComparatorFirstEqualSecond() {
        Ride[] rides = new Ride[6];
        rides[1] = new Ride(2, LocalTime.parse("23:59:59"), 200, 700);
        rides[0] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
        Assert.assertEquals(0, rides[0].compareTo(rides[1]));
    }

    /** INSERT TESTS */

    @Test
    @DisplayName("Insert Valid Ride to Empty Heap #1")
    public void insertValidRide1() {
        MinHeap testHeap = new MinHeap();
        
        Ride ride1 = new Ride(57, LocalTime.parse("08:15:30"), 200, 700);
        ride1.addPassenger("Kyle,Jake,Hannah,Mariam");

        testHeap.insert(ride1);
        testHeap.dump();
        
        Assertions.assertEquals("""
                --- Ride: 57 -------
                Time : 08:15:30
                Start ID : 200
                End ID : 700
                Passengers:
                Kyle
                Jake
                Hannah
                Mariam
                --------------------""", outputStreamCaptor.toString().trim());
    }
    @Test
    @DisplayName("Insert Valid Ride to Empty Heap #2")
    public void insertValidRide2() {
        MinHeap testHeap = new MinHeap();
        
        Ride ride1 = new Ride(999, LocalTime.parse("12:12:12"), 494, 100);
        ride1.addPassenger("John,Emma,Owen");

        testHeap.insert(ride1);
        testHeap.dump();
        
        Assertions.assertEquals("""
                --- Ride: 999 -------
                Time : 12:12:12
                Start ID : 494
                End ID : 100
                Passengers:
                John
                Emma
                Owen
                --------------------""", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Add Passenger Names")
    public void addPassengerNames1() {
        MinHeap testHeap = new MinHeap();
        Ride ridetoAdd = new Ride(1, LocalTime.parse("23:00:12"), 34, 33);
        ridetoAdd.addPassenger("A,B,C");
        testHeap.insert(ridetoAdd);
        testHeap.dump();
        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 23:00:12
                Start ID : 34
                End ID : 33
                Passengers:
                A
                B
                C
                --------------------""", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Add Passenger Names 2")
    public void addPassengerNames2() {
        MinHeap testHeap = new MinHeap();
        Ride ridetoAdd = new Ride(1, LocalTime.parse("23:00:12"), 34, 33);
        ridetoAdd.addPassenger("John Doe,Guyon Espiner,Chris");
        testHeap.insert(ridetoAdd);
        testHeap.dump();
        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 23:00:12
                Start ID : 34
                End ID : 33
                Passengers:
                John Doe
                Guyon Espiner
                Chris
                --------------------""", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Insert multiple valids Ride")
    public void insertMultipleRides() {
        MinHeap testHeap = new MinHeap();
        
        Ride ride1 = new Ride(57, LocalTime.parse("08:15:30"), 200, 700);
        ride1.addPassenger("Kyle,Jake,Hannah,Mariam");
        
        Ride ride2 = new Ride(42, LocalTime.parse("12:45:00"), 300, 800);
        ride2.addPassenger("Liam,Emma,Owen");
        
        Ride ride3 = new Ride(86, LocalTime.parse("05:23:12"), 150, 600);
        ride3.addPassenger("Olivia,William,Ava");
        
        Ride ride4 = new Ride(15, LocalTime.parse("19:30:45"), 250, 750);
        ride4.addPassenger("Sophia,James,Amelia");
        
        Ride ride5 = new Ride(99, LocalTime.parse("03:50:20"), 350, 850);
        ride5.addPassenger("Ethan,Charlotte,Logan");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);
        testHeap.insert(ride4);
        testHeap.insert(ride5);

        testHeap.dump();
        
        Assertions.assertEquals("""
                --- Ride: 99 -------
                Time : 03:50:20
                Start ID : 350
                End ID : 850
                Passengers:
                Ethan
                Charlotte
                Logan
                --------------------
                --- Ride: 86 -------
                Time : 05:23:12
                Start ID : 150
                End ID : 600
                Passengers:
                Olivia
                William
                Ava
                --------------------
                --- Ride: 57 -------
                Time : 08:15:30
                Start ID : 200
                End ID : 700
                Passengers:
                Kyle
                Jake
                Hannah
                Mariam
                --------------------
                --- Ride: 15 -------
                Time : 19:30:45
                Start ID : 250
                End ID : 750
                Passengers:
                Sophia
                James
                Amelia
                --------------------
                --- Ride: 42 -------
                Time : 12:45
                Start ID : 300
                End ID : 800
                Passengers:
                Liam
                Emma
                Owen
                --------------------""",
                outputStreamCaptor.toString().trim());
    }


    /**
     * Insert Heapify Array with more than 20 items
     * Empty Heap
     */
    @Test
    @DisplayName("Insert Heapify Array with more than 20 items")
    public void heapifyMoreThan20Items(){
    MinHeap testHeap = new MinHeap();
        Ride rides[] = new Ride[21];
        rides[0] = new Ride(57, LocalTime.parse("08:15:30"), 200, 700);
        rides[0].addPassenger("Kyle,Jake,Hannah,Mariam");

        rides[1] = new Ride(42, LocalTime.parse("12:45:00"), 300, 800);
        rides[1].addPassenger("Liam,Emma,Owen");

        rides[2] = new Ride(86, LocalTime.parse("05:23:12"), 150, 600);
        rides[2].addPassenger("Olivia,William,Ava");

        rides[3] = new Ride(15, LocalTime.parse("19:30:45"), 250, 750);
        rides[3].addPassenger("Sophia,James,Amelia");

        rides[4] = new Ride(99, LocalTime.parse("03:50:20"), 350, 850);
        rides[4].addPassenger("Ethan,Charlotte,Logan");

        rides[5] = new Ride(23, LocalTime.parse("14:05:10"), 275, 725);
        rides[5].addPassenger("Isabella,Noah,Grace");

        rides[6] = new Ride(61, LocalTime.parse("21:35:47"), 225, 675);
        rides[6].addPassenger("Benjamin,Abigail,Elijah");

        rides[7] = new Ride(34, LocalTime.parse("06:12:34"), 320, 770);
        rides[7].addPassenger("Lucas,Mia,Mason");

        rides[8] = new Ride(72, LocalTime.parse("10:50:05"), 180, 680);
        rides[8].addPassenger("Evelyn,Alexander,Harper");

        rides[9] = new Ride(45, LocalTime.parse("17:22:48"), 240, 740);
        rides[9].addPassenger("Ella,Benjamin,Emily");

        rides[10] = new Ride(88, LocalTime.parse("04:45:30"), 360, 860);
        rides[10].addPassenger("Michael,Sofia,Logan");

        rides[11] = new Ride(17, LocalTime.parse("09:13:55"), 290, 790);
        rides[11].addPassenger("Alexander,Mia,James");

        rides[12] = new Ride(51, LocalTime.parse("13:57:19"), 210, 710);
        rides[12].addPassenger("Aiden,Ella,William");

        rides[13] = new Ride(76, LocalTime.parse("18:03:23"), 330, 780);
        rides[13].addPassenger("Harper,Liam,Avery");

        rides[14] = new Ride(28, LocalTime.parse("20:28:49"), 195, 695);
        rides[14].addPassenger("Sebastian,Scarlett,Matthew");

        rides[15] = new Ride(63, LocalTime.parse("07:37:11"), 265, 765);
        rides[15].addPassenger("Madison,Lucas,Amelia");

        rides[16] = new Ride(14, LocalTime.parse("11:44:33"), 315, 815);
        rides[16].addPassenger("Jackson,Emma,Lincoln");

        rides[17] = new Ride(90, LocalTime.parse("15:18:06"), 185, 685);
        rides[17].addPassenger("Chloe,Oliver,Ella");

        rides[18] = new Ride(37, LocalTime.parse("23:25:42"), 355, 855);
        rides[18].addPassenger("Luna,Ethan,Victoria");

        rides[19] = new Ride(82, LocalTime.parse("02:39:14"), 205, 705);
        rides[19].addPassenger("Gabriel,Harper,Grace");

        rides[20] = new Ride(49, LocalTime.parse("01:30:25"), 290, 740);
        rides[20].addPassenger("Ava,Jack,Nora");

        testHeap.heapify(rides, 21);
        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
    }

    /**
     * Insert Heapify Array with 20 items
     * Empty Heap
     */

     @Test
     @DisplayName("Insert 20 items using heapify")
     public void insert20ItemsUsingHeapify() {
         MinHeap testHeap = new MinHeap();
 
         Ride[] rides = new Ride[20];
         rides[0] = new Ride(57, LocalTime.parse("00:15:30"), 200, 700);
         rides[0].addPassenger("Kyle,Jake,Hannah,Mariam");
 
         rides[1] = new Ride(42, LocalTime.parse("12:45:00"), 300, 800);
         rides[1].addPassenger("Liam,Emma,Owen");
 
         rides[2] = new Ride(86, LocalTime.parse("05:23:12"), 150, 600);
         rides[2].addPassenger("Olivia,William,Ava");
 
         rides[3] = new Ride(15, LocalTime.parse("19:30:45"), 250, 750);
         rides[3].addPassenger("Sophia,James,Amelia");
 
         rides[4] = new Ride(99, LocalTime.parse("03:50:20"), 350, 850);
         rides[4].addPassenger("Ethan,Charlotte,Logan");
 
         rides[5] = new Ride(23, LocalTime.parse("14:05:10"), 275, 725);
         rides[5].addPassenger("Isabella,Noah,Grace");
 
         rides[6] = new Ride(61, LocalTime.parse("21:35:47"), 225, 675);
         rides[6].addPassenger("Benjamin,Abigail,Elijah");
 
         rides[7] = new Ride(34, LocalTime.parse("06:12:34"), 320, 770);
         rides[7].addPassenger("Lucas,Mia,Mason");
 
         rides[8] = new Ride(72, LocalTime.parse("10:50:05"), 180, 680);
         rides[8].addPassenger("Evelyn,Alexander,Harper");
 
         rides[9] = new Ride(45, LocalTime.parse("17:22:48"), 240, 740);
         rides[9].addPassenger("Ella,Benjamin,Emily");
 
         rides[10] = new Ride(88, LocalTime.parse("04:45:30"), 360, 860);
         rides[10].addPassenger("Michael,Sofia,Logan");
 
         rides[11] = new Ride(17, LocalTime.parse("09:13:55"), 290, 790);
         rides[11].addPassenger("Alexander,Mia,James");
 
         rides[12] = new Ride(51, LocalTime.parse("13:57:19"), 210, 710);
         rides[12].addPassenger("Aiden,Ella,William");
 
         rides[13] = new Ride(76, LocalTime.parse("18:03:23"), 330, 780);
         rides[13].addPassenger("Harper,Liam,Avery");
 
         rides[14] = new Ride(28, LocalTime.parse("20:28:49"), 195, 695);
         rides[14].addPassenger("Sebastian,Scarlett,Matthew");
 
         rides[15] = new Ride(63, LocalTime.parse("07:37:11"), 265, 765);
         rides[15].addPassenger("Madison,Lucas,Amelia");
 
         rides[16] = new Ride(14, LocalTime.parse("11:44:33"), 315, 815);
         rides[16].addPassenger("Jackson,Emma,Lincoln");
 
         rides[17] = new Ride(90, LocalTime.parse("15:18:06"), 185, 685);
         rides[17].addPassenger("Chloe,Oliver,Ella");
 
         rides[18] = new Ride(37, LocalTime.parse("23:25:42"), 355, 855);
         rides[18].addPassenger("Luna,Ethan,Victoria");
 
         rides[19] = new Ride(82, LocalTime.parse("02:39:14"), 205, 705);
         rides[19].addPassenger("Gabriel,Harper,Grace");
 
         testHeap.heapify(rides, 20);
         testHeap.dump();
         Assertions.assertEquals("""
                 --- Ride: 57 -------
                 Time : 00:15:30
                 Start ID : 200
                 End ID : 700
                 Passengers: 
                 Kyle
                 Jake
                 Hannah
                 Mariam
                 --------------------
                 --- Ride: 82 -------
                 Time : 02:39:14
                 Start ID : 205
                 End ID : 705
                 Passengers: 
                 Gabriel
                 Harper
                 Grace
                 --------------------
                 --- Ride: 86 -------
                 Time : 05:23:12
                 Start ID : 150
                 End ID : 600
                 Passengers: 
                 Olivia
                 William
                 Ava
                 --------------------
                 --- Ride: 34 -------
                 Time : 06:12:34
                 Start ID : 320
                 End ID : 770
                 Passengers: 
                 Lucas
                 Mia
                 Mason
                 --------------------
                 --- Ride: 99 -------
                 Time : 03:50:20
                 Start ID : 350
                 End ID : 850
                 Passengers: 
                 Ethan
                 Charlotte
                 Logan
                 --------------------
                 --- Ride: 17 -------
                 Time : 09:13:55
                 Start ID : 290
                 End ID : 790
                 Passengers: 
                 Alexander
                 Mia
                 James
                 --------------------
                 --- Ride: 76 -------
                 Time : 18:03:23
                 Start ID : 330
                 End ID : 780
                 Passengers: 
                 Harper
                 Liam
                 Avery
                 --------------------
                 --- Ride: 63 -------
                 Time : 07:37:11
                 Start ID : 265
                 End ID : 765
                 Passengers: 
                 Madison
                 Lucas
                 Amelia
                 --------------------
                 --- Ride: 72 -------
                 Time : 10:50:05
                 Start ID : 180
                 End ID : 680
                 Passengers: 
                 Evelyn
                 Alexander
                 Harper
                 --------------------
                 --- Ride: 88 -------
                 Time : 04:45:30
                 Start ID : 360
                 End ID : 860
                 Passengers: 
                 Michael
                 Sofia
                 Logan
                 --------------------
                 --- Ride: 42 -------
                 Time : 12:45
                 Start ID : 300
                 End ID : 800
                 Passengers: 
                 Liam
                 Emma
                 Owen
                 --------------------
                 --- Ride: 23 -------
                 Time : 14:05:10
                 Start ID : 275
                 End ID : 725
                 Passengers: 
                 Isabella
                 Noah
                 Grace
                 --------------------
                 --- Ride: 51 -------
                 Time : 13:57:19
                 Start ID : 210
                 End ID : 710
                 Passengers: 
                 Aiden
                 Ella
                 William
                 --------------------
                 --- Ride: 61 -------
                 Time : 21:35:47
                 Start ID : 225
                 End ID : 675
                 Passengers: 
                 Benjamin
                 Abigail
                 Elijah
                 --------------------
                 --- Ride: 28 -------
                 Time : 20:28:49
                 Start ID : 195
                 End ID : 695
                 Passengers: 
                 Sebastian
                 Scarlett
                 Matthew
                 --------------------
                 --- Ride: 15 -------
                 Time : 19:30:45
                 Start ID : 250
                 End ID : 750
                 Passengers: 
                 Sophia
                 James
                 Amelia
                 --------------------
                 --- Ride: 14 -------
                 Time : 11:44:33
                 Start ID : 315
                 End ID : 815
                 Passengers: 
                 Jackson
                 Emma
                 Lincoln
                 --------------------
                 --- Ride: 90 -------
                 Time : 15:18:06
                 Start ID : 185
                 End ID : 685
                 Passengers: 
                 Chloe
                 Oliver
                 Ella
                 --------------------
                 --- Ride: 37 -------
                 Time : 23:25:42
                 Start ID : 355
                 End ID : 855
                 Passengers: 
                 Luna
                 Ethan
                 Victoria
                 --------------------
                 --- Ride: 45 -------
                 Time : 17:22:48
                 Start ID : 240
                 End ID : 740
                 Passengers: 
                 Ella
                 Benjamin
                 Emily
                 --------------------"""
                 , outputStreamCaptor.toString().trim());
     }

    /**
     * 
     * Expected Behaviour is that the entire heap is replaced
     */
    @Test
    @DisplayName("Insert using Heapify Array of 20 rides into Full Heap")
    public void heapifyInsertNonEmpty20Items() {

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
        Ride rides[] = new Ride[20];
        rides[0] = new Ride(57, LocalTime.parse("08:15:30"), 200, 700);
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
        testHeap.heapify(rides, 20);
        testHeap.dump();

        Assert.assertEquals("""
                --- Ride: 82 -------
                Time : 02:39:14
                Start ID : 205
                End ID : 705
                Passengers:
                --------------------
                --- Ride: 99 -------
                Time : 03:50:20
                Start ID : 350
                End ID : 850
                Passengers:
                --------------------
                --- Ride: 57 -------
                Time : 08:15:30
                Start ID : 200
                End ID : 700
                Passengers:
                --------------------
                --- Ride: 34 -------
                Time : 06:12:34
                Start ID : 320
                End ID : 770
                Passengers:
                --------------------
                --- Ride: 88 -------
                Time : 04:45:30
                Start ID : 360
                End ID : 860
                Passengers:
                --------------------
                --- Ride: 17 -------
                Time : 09:13:55
                Start ID : 290
                End ID : 790
                Passengers:
                --------------------
                --- Ride: 76 -------
                Time : 18:03:23
                Start ID : 330
                End ID : 780
                Passengers:
                --------------------
                --- Ride: 63 -------
                Time : 07:37:11
                Start ID : 265
                End ID : 765
                Passengers:
                --------------------
                --- Ride: 72 -------
                Time : 10:50:05
                Start ID : 180
                End ID : 680
                Passengers:
                --------------------
                --- Ride: 86 -------
                Time : 05:23:12
                Start ID : 150
                End ID : 600
                Passengers:
                --------------------
                --- Ride: 42 -------
                Time : 12:45
                Start ID : 300
                End ID : 800
                Passengers:
                --------------------
                --- Ride: 23 -------
                Time : 14:05:10
                Start ID : 275
                End ID : 725
                Passengers:
                --------------------
                --- Ride: 51 -------
                Time : 13:57:19
                Start ID : 210
                End ID : 710
                Passengers:
                --------------------
                --- Ride: 61 -------
                Time : 21:35:47
                Start ID : 225
                End ID : 675
                Passengers:
                --------------------
                --- Ride: 28 -------
                Time : 20:28:49
                Start ID : 195
                End ID : 695
                Passengers:
                --------------------
                --- Ride: 15 -------
                Time : 19:30:45
                Start ID : 250
                End ID : 750
                Passengers:
                --------------------
                --- Ride: 14 -------
                Time : 11:44:33
                Start ID : 315
                End ID : 815
                Passengers:
                --------------------
                --- Ride: 90 -------
                Time : 15:18:06
                Start ID : 185
                End ID : 685
                Passengers:
                --------------------
                --- Ride: 37 -------
                Time : 23:25:42
                Start ID : 355
                End ID : 855
                Passengers:
                --------------------
                --- Ride: 45 -------
                Time : 17:22:48
                Start ID : 240
                End ID : 740
                Passengers:
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * ISEMPTY TEST:
     */
    /**
     * Is Empty Test on Empty stack
     */
    @Test
    @DisplayName("Is Empty on Empty stack")
    public void isEmptyOnEmptyStack() {
        MinHeap testHeap = new MinHeap();
        Assertions.assertEquals(true, testHeap.isEmpty());
    }

    /**
     * Is Empty on non empty stack
     */
    @Test
    @DisplayName("Is Empty on non empty stack")
    public void isEmptyOnNonEmptyStack() {
        MinHeap testHeap = new MinHeap();
        testHeap.insert(new Ride(1, LocalTime.parse("12:23:22"), 12, 233));
        Assertions.assertFalse(testHeap.isEmpty());
    }

    /**
     * Is Empty on stack with multiple items
     */
    @Test
    @DisplayName("Is Empty on non empty stack")
    public void isEmptyOnStackWithMultipleItems() {
        MinHeap testHeap = new MinHeap();
        Ride ride1 = new Ride(1, LocalTime.parse("12:23:22"), 34, 342);
        ride1.addPassenger("Kyle,Person B");
        testHeap.insert(ride1);
        Ride ride2 = new Ride(1, LocalTime.parse("02:23:22"), 34, 342);
        ride1.addPassenger("Kyle,Person B");
        testHeap.insert(ride2);
        Assertions.assertFalse(testHeap.isEmpty());
    }
    

    @Test
    @DisplayName("Is Empty on stack with item added and removed")
    public void isEmptyonModifiedStack() {
        MinHeap testHeap = new MinHeap();
        Ride ride1 = new Ride(1, LocalTime.parse("12:23:22"), 34, 342);
        testHeap.insert(ride1);
        testHeap.remove(ride1);
        Assertions.assertTrue(testHeap.isEmpty());
    }

    /** PEEK TESTS */

    @Test
    @DisplayName("Peek on empty stack")
    public void peekEmptyStack() {
        MinHeap testHeap = new MinHeap();
        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Peek on stack with item added and removed")
    public void peekEmptyStackModified() {
        MinHeap testHeap = new MinHeap();
        Ride ride1 = new Ride(1, LocalTime.parse("12:23:22"), 34, 342);
        testHeap.insert(ride1);
        testHeap.remove(ride1);
        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Peek non empty stack single item")
    public void peekNonEmptyStackSingle() {
        MinHeap testHeap = new MinHeap();
        Ride ride1 = new Ride(1, LocalTime.parse("12:23:22"), 34, 342);
        ride1.addPassenger("Kyle,Person B");
        testHeap.insert(ride1);
        testHeap.peek();
        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 12:23:22
                Start ID : 34
                End ID : 342
                Passengers:
                Kyle
                Person B
                --------------------""", outputStreamCaptor.toString().trim());

    }

    @Test
    @DisplayName("Peek non empty stack single item")
    public void peekMultipleItemStack() {
        MinHeap testHeap = new MinHeap();
        testHeap.insert(new Ride(3, LocalTime.parse("15:34:22"), 342, 754));
        Ride ride1 = new Ride(1, LocalTime.parse("00:23:22"), 34, 342);
        ride1.addPassenger("Frodo,Gandalf");
        testHeap.insert(new Ride(2, LocalTime.parse("06:23:34"), 234, 230));
        testHeap.insert(ride1);
        testHeap.peek();
        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 00:23:22
                Start ID : 34
                End ID : 342
                Passengers:
                Frodo
                Gandalf
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Tests that when items are imported, that they are imported and sorted
     * Hence Ride 1 is inserted, then Ride 0, and they should export Ride 0 then
     * Ride 1
     */
    @Test
    @DisplayName("Insert out of order items into stack with multiple items")
    public void importStackMultipleItems() {
        MinHeap testHeap = new MinHeap();
        Ride ride1 = new Ride(1, LocalTime.parse("11:23:53"), 522, 622);
        ride1.addPassenger("Harry,Hermione");
        testHeap.insert(ride1);

        Ride ride2 = new Ride(0, LocalTime.parse("15:33:33"), 342, 942);
        ride2.addPassenger("Ron,Dumbledore");
        testHeap.insert(ride2);

        testHeap.dump();
        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 11:23:53
                Start ID : 522
                End ID : 622
                Passengers:
                Harry
                Hermione
                --------------------
                --- Ride: 0 -------
                Time : 15:33:33
                Start ID : 342
                End ID : 942
                Passengers:
                Ron
                Dumbledore
                --------------------""",
                outputStreamCaptor.toString().trim());
    }

    /**
     * Sort Tests
     */
    @Test
    @DisplayName("Sort Multiple items")
    public void sortMultipleItems() {
        MinHeap testHeap = new MinHeap();
        
        Ride ride2 = new Ride(1, LocalTime.parse("00:23:22"), 34, 342);
        ride2.addPassenger("JohnDoe,JaneDoe");
        
        Ride ride3 = new Ride(2, LocalTime.parse("23:23:22"), 34, 342);
        ride3.addPassenger("AliceSmith,BobSmith");
        
        Ride ride1 = new Ride(3, LocalTime.parse("11:23:22"), 34, 342);
        ride1.addPassenger("CharlieBrown,SallyBrown");
        
        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);

        MinHeap proxy = new MinHeap();
        Ride[] sortedRides = testHeap.sort();
       
        for(int i = 0; i < 3; i++) {
            proxy.outputRide(sortedRides, i);
        }
        
        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 00:23:22
                Start ID : 34
                End ID : 342
                Passengers:
                JohnDoe
                JaneDoe
                --------------------
                --- Ride: 3 -------
                Time : 11:23:22
                Start ID : 34
                End ID : 342
                Passengers:
                CharlieBrown
                SallyBrown
                --------------------
                --- Ride: 2 -------
                Time : 23:23:22
                Start ID : 34
                End ID : 342
                Passengers:
                AliceSmith
                BobSmith
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Sort empty stack
     */

    @Test
    @DisplayName("Sort Empty items")
    public void sortEmptyStack() {
        MinHeap testHeap = new MinHeap();
        MinHeap proxy = new MinHeap();
        Ride[] sortedRides = testHeap.sort();

        for (Ride ride : sortedRides) {
            System.out.println(ride);
        }


        if (sortedRides[0] == null) {
            System.out.println("");
        } else {
            proxy.outputRide(sortedRides, 0);
        }

      //  Assert.assertEquals("", outputStreamCaptor.toString().trim());

    }

    @Test
    @DisplayName("Sort Single items")
    public void sortSingleItem() {
        MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(1, LocalTime.parse("00:23:22"), 34, 342);
        ride2.addPassenger("John,Jane");

        testHeap.insert(ride2);

        MinHeap proxy = new MinHeap();
        Ride[] sortedRides = testHeap.sort();
        proxy.outputRide(sortedRides, 0);

        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 00:23:22
                Start ID : 34
                End ID : 342
                Passengers:
                John
                Jane
                --------------------""", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Sort Full Heap of 20 items")
    public void sortFullHeap() {
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


        Ride[] sortedRides = testHeap.sort();
        for(int i= 0; i<20; i++){
            testHeap.outputRide(sortedRides, i);
          }

        Assertions.assertEquals("""
--- Ride: 9 -------
Time : 00:00:01
Start ID : 400
End ID : 900
Passengers:
--------------------
--- Ride: 15 -------
Time : 04:04:04
Start ID : 190
End ID : 710
Passengers:
--------------------
--- Ride: 3 -------
Time : 05:23:12
Start ID : 150
End ID : 600
Passengers:
--------------------
--- Ride: 16 -------
Time : 06:06:06
Start ID : 240
End ID : 760
Passengers:
--------------------
--- Ride: 17 -------
Time : 07:07:07
Start ID : 210
End ID : 730
Passengers:
--------------------
--- Ride: 18 -------
Time : 08:08:08
Start ID : 290
End ID : 790
Passengers:
--------------------
--- Ride: 1 -------
Time : 08:15:30
Start ID : 200
End ID : 700
Passengers:
--------------------
--- Ride: 5 -------
Time : 09:01:45
Start ID : 500
End ID : 1000
Passengers:
--------------------
--- Ride: 19 -------
Time : 09:09:09
Start ID : 310
End ID : 830
Passengers:
--------------------
--- Ride: 11 -------
Time : 10:10:10
Start ID : 200
End ID : 700
Passengers:
--------------------
--- Ride: 7 -------
Time : 11:15:20
Start ID : 220
End ID : 720
Passengers:
--------------------
--- Ride: 2 -------
Time : 12:45
Start ID : 300
End ID : 800
Passengers:
--------------------
--- Ride: 12 -------
Time : 13:13:13
Start ID : 320
End ID : 820
Passengers:
--------------------
--- Ride: 6 -------
Time : 14:20:10
Start ID : 180
End ID : 650
Passengers:
--------------------
--- Ride: 10 -------
Time : 15:45:30
Start ID : 250
End ID : 750
Passengers:
--------------------
--- Ride: 13 -------
Time : 17:17:17
Start ID : 230
End ID : 770
Passengers:
--------------------
--- Ride: 4 -------
Time : 19:30:45
Start ID : 250
End ID : 750
Passengers:
--------------------
--- Ride: 20 -------
Time : 20:20:20
Start ID : 260
End ID : 780
Passengers:
--------------------
--- Ride: 14 -------
Time : 21:21:21
Start ID : 270
End ID : 810
Passengers:
--------------------
--- Ride: 8 -------
Time : 23:59:59
Start ID : 300
End ID : 800
Passengers:
--------------------""", outputStreamCaptor.toString().trim());

    }

    @Test
    @DisplayName("Sort Heap and then access original heap")
    public void sortHeapThenAccess(){
        MinHeap testHeap = new MinHeap();
        
        Ride ride2 = new Ride(1, LocalTime.parse("00:23:22"), 34, 342);
        ride2.addPassenger("John,Jane");
        
        Ride ride3 = new Ride(2, LocalTime.parse("23:23:22"), 34, 342);
        ride3.addPassenger("Emily,Michael");
        
        Ride ride1 = new Ride(3, LocalTime.parse("11:23:22"), 34, 342);
        ride1.addPassenger("Sarah,David");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);

        MinHeap proxy = new MinHeap();
        testHeap.sort();
        testHeap.peek();
        Assertions.assertEquals("""
--- Ride: 1 -------
Time : 00:23:22
Start ID : 34
End ID : 342
Passengers:
John
Jane
--------------------""", outputStreamCaptor.toString().trim());
    }




    @Test
    @DisplayName("Sort closely related times")
    public void sortCloselyRelatedTimes(){
        MinHeap testHeap = new MinHeap();
        
        Ride ride2 = new Ride(1, LocalTime.parse("00:00:00"), 34, 342);
        ride2.addPassenger("John,Emma");
        
        Ride ride3 = new Ride(2, LocalTime.parse("00:00:01"), 34, 342);
        ride3.addPassenger("Michael,Sophia");
        
        Ride ride1 = new Ride(3, LocalTime.parse("00:00:02"), 34, 342);
        ride1.addPassenger("James,Olivia");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);

        MinHeap proxy = new MinHeap();
        Ride[] sortedRides = testHeap.sort();
        for(int i = 0; i < 3; i++) {
            proxy.outputRide(sortedRides, i);
        }
        
        Assertions.assertEquals("""
--- Ride: 1 -------
Time : 00:00
Start ID : 34
End ID : 342
Passengers:
John
Emma
--------------------
--- Ride: 2 -------
Time : 00:00:01
Start ID : 34
End ID : 342
Passengers:
Michael
Sophia
--------------------
--- Ride: 3 -------
Time : 00:00:02
Start ID : 34
End ID : 342
Passengers:
James
Olivia
--------------------""", outputStreamCaptor.toString().trim());
    }


    @Test
    @DisplayName("Remove Single Item")
    public void removeSingleItem() {
        MinHeap testHeap = new MinHeap();
        
        Ride ride1 = new Ride(1, LocalTime.parse("08:15:30"), 200, 700);
        ride1.addPassenger("Kyle,Jake,Hannah,Mariam");
        
        Ride ride2 = new Ride(2, LocalTime.parse("12:45:00"), 300, 800);
        ride2.addPassenger("Liam,Emma,Owen");
        
        Ride ride3 = new Ride(3, LocalTime.parse("05:23:12"), 150, 600);
        ride3.addPassenger("Olivia,William,Ava");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);

        testHeap.remove(ride2);
        testHeap.dump();
        Assertions.assertEquals("""
                --- Ride: 3 -------
                Time : 05:23:12
                Start ID : 150
                End ID : 600
                Passengers:
                Olivia
                William
                Ava
                --------------------
                --- Ride: 1 -------
                Time : 08:15:30
                Start ID : 200
                End ID : 700
                Passengers:
                Kyle
                Jake
                Hannah
                Mariam
                --------------------""", outputStreamCaptor.toString().trim());
    }


    @Test
    @DisplayName("Remove multiple items")
    public void removeMultipleItems(){
        MinHeap testHeap = new MinHeap();
        
        Ride ride1 = new Ride(1, LocalTime.parse("08:15:30"), 200, 700);
        ride1.addPassenger("Kyle,Jake,Hannah,Mariam");
        
        Ride ride2 = new Ride(2, LocalTime.parse("12:45:00"), 300, 800);
        ride2.addPassenger("Liam,Emma,Owen");
        
        Ride ride3 = new Ride(3, LocalTime.parse("05:23:12"), 150, 600);
        ride3.addPassenger("Olivia,William,Ava");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);

        testHeap.remove(ride2);
        testHeap.remove(ride3);
        testHeap.dump();
        Assertions.assertEquals("""
--- Ride: 1 -------
Time : 08:15:30
Start ID : 200
End ID : 700
Passengers:
Kyle
Jake
Hannah
Mariam
--------------------""", outputStreamCaptor.toString().trim());
    }
}
    
