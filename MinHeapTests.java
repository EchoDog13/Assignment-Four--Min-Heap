
//Import statements 
import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

/**
 * Tests to check the implementation of a Min Heap for a ride sharing
 * implementation
 */

public class MinHeapTests {

    // stores the output of the console output in standard out
    private final PrintStream standardOut = System.out;
    // capture the output of the information written to console
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Before each test is run, sets output print stream to outputStreamCaptor to
     * capture output.
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

    /**
     * Tests if it can correctly compare times, first item is less than the second
     * item
     */
    @Test
    @DisplayName("Comparator Test: First item is less than second item, large time difference")
    public void testComparatorFirstLessThanSecondLarge() {
        Ride[] rides = new Ride[6];
        rides[0] = new Ride(2, LocalTime.parse("00:00:00"), 200, 700);
        rides[1] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
        Assert.assertEquals(-1, rides[0].compareTo(rides[1]));
    }

    /**
     * Tests if it can correctly compare times, first item is less than the second
     * item
     */
    @Test
    @DisplayName("Comparator Test: First item is less than second item, small time difference")
    public void testComparatorFirstLessThanSecondSmall() {
        Ride[] rides = new Ride[6];
        rides[0] = new Ride(2, LocalTime.parse("12:00:01"), 200, 700);
        rides[1] = new Ride(1, LocalTime.parse("12:00:02"), 700, 999);
        Assert.assertEquals(-1, rides[0].compareTo(rides[1]));
    }

    /**
     * Tests if it can correctly compare times, first item is greater than the
     * second item, with a large time difference
     */
    @Test
    @DisplayName("Comparator Test: First item is greater than second item, large time difference")
    public void testComparatorGreatherThanSecondLarge() {
        Ride[] rides = new Ride[6];
        rides[1] = new Ride(2, LocalTime.parse("00:00:00"), 200, 700);
        rides[0] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
        Assert.assertEquals(1, rides[0].compareTo(rides[1]));
    }

    /**
     * Tests if it can correctly compare times, first item is greater than the
     * second item, with a small time difference
     */
    @Test
    @DisplayName("Comparator Test: First item is greater than second item, small time difference")
    public void testComparatorFirstGreaterThanSecondSmall() {
        Ride[] rides = new Ride[6];
        rides[1] = new Ride(2, LocalTime.parse("10:00:01"), 200, 700);
        rides[0] = new Ride(1, LocalTime.parse("10:00:02"), 700, 999);
        Assert.assertEquals(1, rides[0].compareTo(rides[1]));
    }

    /**
     * Tests if it can correctly compare times, first item is equal to the second
     * item
     */

    @Test
    @DisplayName("Comparator Test: First item is equal to second item")
    public void testComparatorFirstEqualToSecond() {
        Ride[] rides = new Ride[6];
        rides[1] = new Ride(2, LocalTime.parse("10:00:01"), 200, 700);
        rides[0] = new Ride(1, LocalTime.parse("10:00:01"), 700, 999);
        Assert.assertEquals(0, rides[0].compareTo(rides[1]));
    }

    /**
     * Tests if it can correctly compare times, first item is equal to the second
     * item
     */
    @Test
    @DisplayName("Comparator Test: First item is equal to second item")
    public void testComparatorFirstEqualSecond() {
        Ride[] rides = new Ride[6];
        rides[1] = new Ride(2, LocalTime.parse("23:59:59"), 200, 700);
        rides[0] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
        Assert.assertEquals(0, rides[0].compareTo(rides[1]));
    }

    /** INSERT TESTS */

    /**
     * Inserts a valid ride to an empty heap
     */
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

    /**
     * Inserts a valid ride to an empty heap
     */

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

    /**
     * Add passenger names to a ride
     */
    @Test
    @DisplayName("Add Passenger Names")
    public void addPassengerNames1() {
        MinHeap testHeap = new MinHeap();
        Ride ridetoAdd = new Ride(1, LocalTime.parse("23:00:12"), 34, 33);
        ridetoAdd.addPassenger("A,B,C");
        testHeap.insert(ridetoAdd);
        // Output
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

    /**
     * Add passenger names to a ride
     */
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

    /**
     * Insert multiple valid rides into heap
     */
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
     * Should reject and non add in any items
     */
    @Test
    @DisplayName("Insert Heapify Array with more than 20 items")
    public void heapifyMoreThan20Items() {
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
        testHeap.dump();
        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
    }

    /**
     * Insert Heapify Array with 20 items into empty heap, should intsert iems
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
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Insert items into heap using heapify
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

        for (Ride ride : rides) {
            ride.addPassenger("A,B");
        }
        testHeap.heapify(rides, 20);
        testHeap.dump();

        Assert.assertEquals("""
                --- Ride: 82 -------
                Time : 02:39:14
                Start ID : 205
                End ID : 705
                Passengers:
                A
                B
                --------------------
                --- Ride: 99 -------
                Time : 03:50:20
                Start ID : 350
                End ID : 850
                Passengers:
                A
                B
                --------------------
                --- Ride: 57 -------
                Time : 08:15:30
                Start ID : 200
                End ID : 700
                Passengers:
                A
                B
                --------------------
                --- Ride: 34 -------
                Time : 06:12:34
                Start ID : 320
                End ID : 770
                Passengers:
                A
                B
                --------------------
                --- Ride: 88 -------
                Time : 04:45:30
                Start ID : 360
                End ID : 860
                Passengers:
                A
                B
                --------------------
                --- Ride: 17 -------
                Time : 09:13:55
                Start ID : 290
                End ID : 790
                Passengers:
                A
                B
                --------------------
                --- Ride: 76 -------
                Time : 18:03:23
                Start ID : 330
                End ID : 780
                Passengers:
                A
                B
                --------------------
                --- Ride: 63 -------
                Time : 07:37:11
                Start ID : 265
                End ID : 765
                Passengers:
                A
                B
                --------------------
                --- Ride: 72 -------
                Time : 10:50:05
                Start ID : 180
                End ID : 680
                Passengers:
                A
                B
                --------------------
                --- Ride: 86 -------
                Time : 05:23:12
                Start ID : 150
                End ID : 600
                Passengers:
                A
                B
                --------------------
                --- Ride: 42 -------
                Time : 12:45
                Start ID : 300
                End ID : 800
                Passengers:
                A
                B
                --------------------
                --- Ride: 23 -------
                Time : 14:05:10
                Start ID : 275
                End ID : 725
                Passengers:
                A
                B
                --------------------
                --- Ride: 51 -------
                Time : 13:57:19
                Start ID : 210
                End ID : 710
                Passengers:
                A
                B
                --------------------
                --- Ride: 61 -------
                Time : 21:35:47
                Start ID : 225
                End ID : 675
                Passengers:
                A
                B
                --------------------
                --- Ride: 28 -------
                Time : 20:28:49
                Start ID : 195
                End ID : 695
                Passengers:
                A
                B
                --------------------
                --- Ride: 15 -------
                Time : 19:30:45
                Start ID : 250
                End ID : 750
                Passengers:
                A
                B
                --------------------
                --- Ride: 14 -------
                Time : 11:44:33
                Start ID : 315
                End ID : 815
                Passengers:
                A
                B
                --------------------
                --- Ride: 90 -------
                Time : 15:18:06
                Start ID : 185
                End ID : 685
                Passengers:
                A
                B
                --------------------
                --- Ride: 37 -------
                Time : 23:25:42
                Start ID : 355
                End ID : 855
                Passengers:
                A
                B
                --------------------
                --- Ride: 45 -------
                Time : 17:22:48
                Start ID : 240
                End ID : 740
                Passengers:
                A
                B
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Is Empty Test on Empty stack test
     */
    @Test
    @DisplayName("Is Empty on Empty stack")
    public void isEmptyOnEmptyStack() {
        MinHeap testHeap = new MinHeap();
        Assertions.assertEquals(true, testHeap.isEmpty());
    }

    /**
     * Is Empty on non empty stack test, should return flase
     */
    @Test
    @DisplayName("Is Empty on non empty heap")
    public void isEmptyOnNonEmptyHeap() {
        MinHeap testHeap = new MinHeap();
        Ride rideToAdd = new Ride(1, LocalTime.parse("12:23:22"), 12, 233);
        rideToAdd.addPassenger("A,B,C");
        testHeap.insert(rideToAdd);
        Assertions.assertFalse(testHeap.isEmpty());
    }

    /**
     * Is Empty on heap with multiple items, should return false.
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

    /**
     * Is empty on modified heap, should return true
     */
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
    /**
     * Peek on empty stack
     */
    @Test
    @DisplayName("Peek on empty stack")
    public void peekEmptyStack() {

        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
    }

    /**
     * Peek at modified heap, should return ""
     */
    @Test
    @DisplayName("Peek on stack with item added and removed")
    public void peekEmptyStackModified() {
        MinHeap testHeap = new MinHeap();
        Ride ride1 = new Ride(1, LocalTime.parse("12:23:22"), 34, 342);
        testHeap.insert(ride1);
        testHeap.remove(ride1);
        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
    }

    /**
     * Peek on stack with 1 item, should return single item
     */
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

    /**
     * Peek on stack with single item, should return item
     */
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
     * Sort Test: Check if it sorts the rides into chronological order correclty
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

        for (Ride ride : sortedRides) {
            System.out.println(ride.toString());
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
     * Sort empty stack, should output ""
     */

    @Test
    @DisplayName("Sort Empty items")
    public void sortEmptyStack() {
        MinHeap testHeap = new MinHeap();
        Ride[] sortedRides = testHeap.sort();

        for (Ride ride : sortedRides) {
            System.out.println(ride);
        }

        Assert.assertEquals("", outputStreamCaptor.toString().trim());

    }

    /**
     * Sort stack with a single item, should output item
     */
    @Test
    @DisplayName("Sort Single item")
    public void sortSingleItem() {
        MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(1, LocalTime.parse("00:23:22"), 34, 342);
        ride2.addPassenger("John,Jane");

        testHeap.insert(ride2);

        Ride[] sortedRides = testHeap.sort();

        System.out.println(sortedRides[0].toString());
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

    /**
     * Sort stack with 20 items, should sort stack into chronological order and
     * output in that order
     */
    @Test
    @DisplayName("Sort Full Heap of 20 items")
    public void sortFullHeap() {
        MinHeap testHeap = new MinHeap();

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

        for (Ride ride : rides) {
            ride.addPassenger("A,B");
        }
        testHeap.heapify(rides, 20);
        Ride[] sortedRides = testHeap.sort();
        for (Ride ride : sortedRides) {
            if (ride != null) {
                System.out.println(ride.toString());
            }
        }
        Assertions.assertEquals("""
                --- Ride: 82 -------
                Time : 02:39:14
                Start ID : 205
                End ID : 705
                Passengers:
                A
                B
                --------------------
                --- Ride: 99 -------
                Time : 03:50:20
                Start ID : 350
                End ID : 850
                Passengers:
                A
                B
                --------------------
                --- Ride: 88 -------
                Time : 04:45:30
                Start ID : 360
                End ID : 860
                Passengers:
                A
                B
                --------------------
                --- Ride: 86 -------
                Time : 05:23:12
                Start ID : 150
                End ID : 600
                Passengers:
                A
                B
                --------------------
                --- Ride: 34 -------
                Time : 06:12:34
                Start ID : 320
                End ID : 770
                Passengers:
                A
                B
                --------------------
                --- Ride: 63 -------
                Time : 07:37:11
                Start ID : 265
                End ID : 765
                Passengers:
                A
                B
                --------------------
                --- Ride: 57 -------
                Time : 08:15:30
                Start ID : 200
                End ID : 700
                Passengers:
                A
                B
                --------------------
                --- Ride: 17 -------
                Time : 09:13:55
                Start ID : 290
                End ID : 790
                Passengers:
                A
                B
                --------------------
                --- Ride: 72 -------
                Time : 10:50:05
                Start ID : 180
                End ID : 680
                Passengers:
                A
                B
                --------------------
                --- Ride: 14 -------
                Time : 11:44:33
                Start ID : 315
                End ID : 815
                Passengers:
                A
                B
                --------------------
                --- Ride: 42 -------
                Time : 12:45
                Start ID : 300
                End ID : 800
                Passengers:
                A
                B
                --------------------
                --- Ride: 51 -------
                Time : 13:57:19
                Start ID : 210
                End ID : 710
                Passengers:
                A
                B
                --------------------
                --- Ride: 23 -------
                Time : 14:05:10
                Start ID : 275
                End ID : 725
                Passengers:
                A
                B
                --------------------
                --- Ride: 90 -------
                Time : 15:18:06
                Start ID : 185
                End ID : 685
                Passengers:
                A
                B
                --------------------
                --- Ride: 45 -------
                Time : 17:22:48
                Start ID : 240
                End ID : 740
                Passengers:
                A
                B
                --------------------
                --- Ride: 76 -------
                Time : 18:03:23
                Start ID : 330
                End ID : 780
                Passengers:
                A
                B
                --------------------
                --- Ride: 15 -------
                Time : 19:30:45
                Start ID : 250
                End ID : 750
                Passengers:
                A
                B
                --------------------
                --- Ride: 28 -------
                Time : 20:28:49
                Start ID : 195
                End ID : 695
                Passengers:
                A
                B
                --------------------
                --- Ride: 61 -------
                Time : 21:35:47
                Start ID : 225
                End ID : 675
                Passengers:
                A
                B
                --------------------
                --- Ride: 37 -------
                Time : 23:25:42
                Start ID : 355
                End ID : 855
                Passengers:
                A
                B
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Sorting boundary test with closely related times
     */
    @Test
    @DisplayName("Sort closely related times")
    public void sortCloselyRelatedTimes() {
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

        Ride[] sortedRides = testHeap.sort();
        for (Ride ride : sortedRides) {
            System.out.println(ride.toString());
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

    /**
     * Remove an item from the heap, should output heap without selected ride which
     * is removed
     */
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

    /**
     * Remove multiple items from a stack in a row
     */
    @Test
    @DisplayName("Remove multiple items")
    public void removeMultipleItems() {
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

    /* Optimise Tests */

    /**
     * Optimise emtpy heap
     */
    @Test
    @DisplayName("")
    public void optimiseEmptyHeap() {
        MinHeap testHeap = new MinHeap();

        // Ride ride1 = new Ride(1, LocalTime.parse("08:15:30"), 52, 22);
        // ride1.addPassenger("1,2,3");
        // testHeap.insert(ride1);
        // testHeap.insert(ride1);
        Ride[] s = new Ride[20];

        s = testHeap.optimise();

        for (Ride ride : s) {
            if (ride != null) {
                System.out.println(ride.toString());
            }
        }

        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
    }

    /**
     * Optimise heap with 1 item
     */
    @Test
    @DisplayName("Optimise single item heap")
    public void optimiseSingleItem() {
        MinHeap testHeap = new MinHeap();

        Ride ride1 = new Ride(1, LocalTime.parse("08:15:30"), 52, 22);
        ride1.addPassenger("1,2,3");
        testHeap.insert(ride1);

        Ride[] s = new Ride[20];

        s = testHeap.optimise();

        for (Ride ride : s) {
            if (ride != null) {
                System.out.println(ride.toString());
            }
        }

        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 08:15:30
                Start ID : 52
                End ID : 22
                Passengers:
                1
                2
                3
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Optimise heap with multiple items, some which can be combined. 3x times
     * within 10 minutes and all overlap
     */
    @Test
    @DisplayName("Optimise multiple items and combine rides")
    public void optimiseCombineMultipleRides() {
        MinHeap testHeap = new MinHeap();
        Ride[] rides = new Ride[20];
        Ride ride3 = new Ride(3, LocalTime.parse("01:11:00"), 34, 342);
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        Ride ride1 = new Ride(1, LocalTime.parse("01:00:00"), 34, 342);
        Ride ride4 = new Ride(1, LocalTime.parse("01:09:00"), 34, 342);

        ride1.addPassenger("Person A");
        ride2.addPassenger("Person B,Person C");
        ride3.addPassenger("Téa,Caleb");
        ride4.addPassenger("Jamie,Jacob");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);
        testHeap.insert(ride4);

        rides = testHeap.optimise();

        for (Ride ride : rides) {
            if (ride != null) {
                System.out.println(ride);
            }
        }

        Assertions.assertEquals("""
                --- Ride: 1 -------
                Time : 01:00
                Start ID : 34
                End ID : 342
                Passengers:
                Person A
                Person B
                Person C
                --------------------
                --- Ride: 3 -------
                Time : 01:11
                Start ID : 34
                End ID : 342
                Passengers:
                Téa
                Caleb
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Optimise heap with multiple items, some which can be combined. 3x times
     * within 10 overlap and not all overlap
     */
    @Test
    @DisplayName("Optimise multiple items and combine rides")
    public void optimiseCombineMultipleRidesOverTimeBoundary() {
        MinHeap testHeap = new MinHeap();
        Ride[] rides = new Ride[20];
        Ride ride3 = new Ride(3, LocalTime.parse("01:11:00"), 34, 342);
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        Ride ride1 = new Ride(1, LocalTime.parse("00:00:00"), 34, 342);
        Ride ride4 = new Ride(4, LocalTime.parse("12:59:59"), 34, 342);

        ride1.addPassenger("Person A");
        ride2.addPassenger("Person B,Person C");
        ride3.addPassenger("Téa,Caleb");
        ride4.addPassenger("Jamie,Jacob");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);
        testHeap.insert(ride4);

        rides = testHeap.optimise();
        for (Ride ride : rides) {
            System.out.println(ride.toString());
        }
        Assert.assertEquals("""
                --- Ride: 1 -------
                Time : 00:00
                Start ID : 34
                End ID : 342
                Passengers:
                Person A
                --------------------
                --- Ride: 2 -------
                Time : 01:09:01
                Start ID : 34
                End ID : 342
                Passengers:
                Person B
                Person C
                Téa
                Caleb
                --------------------
                --- Ride: 4 -------
                Time : 12:59:59
                Start ID : 34
                End ID : 342
                Passengers:
                Jamie
                Jacob
                --------------------""", outputStreamCaptor.toString().trim());

    }

    /**
     * Reject optimise due to more than 6 passengers overall
     */
    @Test
    @DisplayName("Reject combine due to passenger count")
    public void optimiseRejectCombineHighPassengerCount() {

        MinHeap testHeap = new MinHeap();
        Ride[] rides = new Ride[20];

        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        Ride ride1 = new Ride(1, LocalTime.parse("01:00:00"), 34, 342);

        ride1.addPassenger("A,B,C,D");
        ride2.addPassenger("E,F,G");

        testHeap.insert(ride1);
        testHeap.insert(ride2);

        rides = testHeap.optimise();

        for (Ride ride : rides) {
            if (ride != null) {
                System.out.println(ride.toString());
            }
        }

        Assert.assertEquals("""
                --- Ride: 1 -------
                Time : 01:00
                Start ID : 34
                End ID : 342
                Passengers:
                A
                B
                C
                D
                --------------------
                --- Ride: 2 -------
                Time : 01:09:01
                Start ID : 34
                End ID : 342
                Passengers:
                E
                F
                G
                --------------------""", outputStreamCaptor.toString().trim());

    }

    /**
     * Optimise heap with multiple items, some which can be combined. 2x times
     * within 10 minutes and not all overlap
     */
    @Test
    @DisplayName("Combine and non combine rides on optimise")
    public void optimiseCombineAndNonCombineRides() {

        MinHeap testHeap = new MinHeap();
        Ride[] rides = new Ride[20];
        Ride ride3 = new Ride(3, LocalTime.parse("01:11:00"), 34, 342);
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        Ride ride1 = new Ride(1, LocalTime.parse("02:00:00"), 34, 342);
        Ride ride4 = new Ride(4, LocalTime.parse("11:09:00"), 34, 342);

        ride1.addPassenger("Person A");
        ride2.addPassenger("Person B,Person C");
        ride3.addPassenger("Téa,Caleb");
        ride4.addPassenger("Jamie,Jacob");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);
        testHeap.insert(ride4);

        // testHeap.dump();
        // System.out.println(ride1.toString());
        rides = testHeap.optimise();

        for (Ride ride : rides) {
            if (ride != null) {
                System.out.println(ride.toString());
            }
        }
        Assert.assertEquals("""
                --- Ride: 2 -------
                Time : 01:09:01
                Start ID : 34
                End ID : 342
                Passengers:
                Person B
                Person C
                Téa
                Caleb
                --------------------
                --- Ride: 1 -------
                Time : 02:00
                Start ID : 34
                End ID : 342
                Passengers:
                Person A
                --------------------
                --- Ride: 4 -------
                Time : 11:09
                Start ID : 34
                End ID : 342
                Passengers:
                Jamie
                Jacob
                --------------------""", outputStreamCaptor.toString().trim());

    }

    /**
     * Optimise with different locations IDs, should reject optimisation and return
     * as normal
     */
    @Test
    @DisplayName("Optimise with different location IDs")
    public void optimiseDifferentLocationIDs() {
        MinHeap testHeap = new MinHeap();
        Ride[] rides = new Ride[20];
        Ride ride3 = new Ride(3, LocalTime.parse("01:11:00"), 35, 342);
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);

        ride2.addPassenger("Person B,Person C");
        ride3.addPassenger("Téa,Caleb");

        testHeap.insert(ride2);
        testHeap.insert(ride3);

        rides = testHeap.optimise();

        for (Ride ride : rides) {
            if (ride != null) {
                System.out.println(ride.toString());

            }
        }

        Assertions.assertEquals("""
                --- Ride: 2 -------
                Time : 01:09:01
                Start ID : 34
                End ID : 342
                Passengers:
                Person B
                Person C
                --------------------
                --- Ride: 3 -------
                Time : 01:11
                Start ID : 35
                End ID : 342
                Passengers:
                Téa
                Caleb
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Test to string functionality without passengers, should still print
     */
    @Test
    @DisplayName("To String without Passengers")
    public void toStringNoPassengers() {
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        System.out.println(ride2.toString());

        Assert.assertEquals("""
                --- Ride: 2 -------
                Time : 01:09:01
                Start ID : 34
                End ID : 342
                Passengers:
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Test toString funcatility with passengers, should print passenger names as
     * well as other information
     */
    @Test
    @DisplayName("To String with Passengers")
    public void toStringPassengers() {
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        ride2.addPassenger("A,B,C");
        System.out.println(ride2.toString());

        Assert.assertEquals("""
                --- Ride: 2 -------
                Time : 01:09:01
                Start ID : 34
                End ID : 342
                Passengers:
                A
                B
                C
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Insert ride without passenger names, should reject and fail to insert it
     */
    @Test
    @DisplayName("Insert without Passenger Names with 1 Ride")
    public void invalidNonPassenger1() {
        MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);

        testHeap.insert(ride2);
        Assert.assertEquals("", outputStreamCaptor.toString().trim());

    }

    /**
     * Insert ride without passenger names, should reject and fail to insert it for
     * two rides
     */
    @Test
    @DisplayName("Insert without Passenger Names with 2 Rides")
    public void invalidNonPassenger2Rides() {
        MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        Ride ride1 = new Ride(2, LocalTime.parse("01:33:01"), 34, 342);

        testHeap.insert(ride2);
        testHeap.insert(ride1);
        Assert.assertEquals("", outputStreamCaptor.toString().trim());

    }

    /**
     * Remove an item from the heap that does not exist, should fail to remove and
     * return.
     */
    @Test
    @DisplayName("Remove an item in the heap")
    public void removeItemNotInHeap() {
        MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        ride2.addPassenger("A,B,C");
        Ride ride1 = new Ride(1, LocalTime.parse("01:33:01"), 34, 342);
        ride1.addPassenger("A,B,C");
        testHeap.insert(ride2);
        testHeap.remove(ride1);
        testHeap.dump();
        Assert.assertEquals("""
                --- Ride: 2 -------
                Time : 01:09:01
                Start ID : 34
                End ID : 342
                Passengers:
                A
                B
                C
                --------------------""", outputStreamCaptor.toString().trim());

    }

    /**
     * Remove item that is not in heap when heap contains another item, should fail
     * to remove and return.
     */
    @Test
    @DisplayName("")
    public void removeItemNotInHeap2() {
        MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        ride2.addPassenger("A,B,C");
        Ride ride1 = new Ride(1, LocalTime.parse("01:33:01"), 34, 342);
        ride1.addPassenger("A,B,C");
        testHeap.insert(ride1);
        testHeap.remove(ride2);
        testHeap.dump();
        Assert.assertEquals("""
                --- Ride: 1 -------
                Time : 01:33:01
                Start ID : 34
                End ID : 342
                Passengers:
                A
                B
                C
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Test inserting a ride that has a matching ID to another existing ride, should
     * fail to insert second ride
     */
    @Test
    @DisplayName("Insert With Duplicate IDs")
    public void duplicateId1() {
        MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(1, LocalTime.parse("01:09:01"), 34, 342);
        ride2.addPassenger("A,B,C");
        Ride ride1 = new Ride(1, LocalTime.parse("01:33:01"), 34, 342);
        ride1.addPassenger("A,B,C");
        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.dump();
        Assert.assertEquals("""
                --- Ride: 1 -------
                Time : 01:33:01
                Start ID : 34
                End ID : 342
                Passengers:
                A
                B
                C
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Test inserting a ride that has a matching ID to another existing ride, should
     * fail to insert second ride
     */
    @Test
    @DisplayName("Insert with Multiple duplicate IDs")
    public void duplicateId2() {
        MinHeap testHeap = new MinHeap();
        Ride ride2 = new Ride(1, LocalTime.parse("01:09:01"), 34, 342);
        ride2.addPassenger("A,B,C");
        Ride ride1 = new Ride(1, LocalTime.parse("01:33:01"), 34, 342);
        ride1.addPassenger("A,B,C");
        Ride ride3 = new Ride(2, LocalTime.parse("01:09:01"), 34, 342);
        ride2.addPassenger("A,B,C");
        Ride ride4 = new Ride(2, LocalTime.parse("01:33:01"), 34, 342);
        ride1.addPassenger("A,B,C");
        ride2.addPassenger("A,B,C");
        ride3.addPassenger("A,B,C");
        ride4.addPassenger("A,B,C");

        testHeap.insert(ride1);
        testHeap.insert(ride2);
        testHeap.insert(ride3);
        testHeap.insert(ride4);
        testHeap.dump();
        Assert.assertEquals("""
                --- Ride: 2 -------
                Time : 01:09:01
                Start ID : 34
                End ID : 342
                Passengers:
                A
                B
                C
                --------------------
                --- Ride: 1 -------
                Time : 01:33:01
                Start ID : 34
                End ID : 342
                Passengers:
                A
                B
                C
                --------------------""", outputStreamCaptor.toString().trim());
    }

    /**
     * Insert an item into a heap that is at capcaity, should fail to insert, and
     * maintain existing heap
     */
    @Test
    @DisplayName("Insert to full heap")
    public void insertOnFullHeap() {
        MinHeap testHeap = new MinHeap();

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

        for (Ride ride : rides) {
            ride.addPassenger("A,B");
        }
        testHeap.heapify(rides, 20);

        Ride extraRide = new Ride(1, LocalTime.parse("00:39:14"), 205, 705);
        extraRide.addPassenger("A,B,C");
        testHeap.insert(extraRide);

        testHeap.peek();
        Assertions.assertEquals("""
                --- Ride: 82 -------
                Time : 02:39:14
                Start ID : 205
                End ID : 705
                Passengers:
                A
                B
                --------------------""", outputStreamCaptor.toString().trim());
    }

}
