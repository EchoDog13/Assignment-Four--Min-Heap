
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


/*COMPARTER TESTS */

@Test
@DisplayName("Comparator Test: First item is less than second item, large time difference")
public void testComparatorFirstLessThanSecondLarge(){
    Ride[] rides = new Ride[6];
    rides[0] = new Ride(2, LocalTime.parse("00:00:00"), 200, 700);
    rides[1] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
    Assert.assertEquals(-1, rides[0].compareTo(rides[1]));
}

@Test
@DisplayName("Comparator Test: First item is less than second item, small time difference")
public void testComparatorFirstLessThanSecondSmall(){
    Ride[] rides = new Ride[6];
    rides[0] = new Ride(2, LocalTime.parse("12:00:01"), 200, 700);
    rides[1] = new Ride(1, LocalTime.parse("12:00:02"), 700, 999);
    Assert.assertEquals(-1, rides[0].compareTo(rides[1]));
}

@Test
@DisplayName("Comparator Test: First item is greater than second item, large time difference")
public void testComparatorGreatherThanSecondLarge(){
    Ride[] rides = new Ride[6];
    rides[1] = new Ride(2, LocalTime.parse("00:00:00"), 200, 700);
    rides[0] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
    Assert.assertEquals(1, rides[0].compareTo(rides[1]));
}

@Test
@DisplayName("Comparator Test: First item is greater than second item, small time difference")
public void testComparatorFirstGreaterThanSecondSmall(){
    Ride[] rides = new Ride[6];
    rides[1] = new Ride(2, LocalTime.parse("10:00:01"), 200, 700);
    rides[0] = new Ride(1, LocalTime.parse("10:00:02"), 700, 999);
    Assert.assertEquals(1, rides[0].compareTo(rides[1]));
}

@Test
@DisplayName("Comparator Test: First item is equal to second item")
public void testComparatorFirstEqualToSecond(){
    Ride[] rides = new Ride[6];
    rides[1] = new Ride(2, LocalTime.parse("10:00:01"), 200, 700);
    rides[0] = new Ride(1, LocalTime.parse("10:00:01"), 700, 999);
    Assert.assertEquals(0, rides[0].compareTo(rides[1]));
}

@Test
@DisplayName("Comparator Test: First item is equal to second item")
public void testComparatorFirstEqualSecond(){
    Ride[] rides = new Ride[6];
    rides[1] = new Ride(2, LocalTime.parse("23:59:59"), 200, 700);
    rides[0] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
    Assert.assertEquals(0, rides[0].compareTo(rides[1]));
}

/**INSERT TESTS */

@Test
@DisplayName("Insert Valid Ride")
public void insertValidRide1(){
    MinHeap testHeap = new MinHeap();
    testHeap.insert(new Ride(57, LocalTime.parse("08:15:30"), 200, 700));
    testHeap.dump();
    Assert.assertEquals("""
--- Ride: 57 -------
Time : 08:15:30
Start ID : 200
End ID : 700
Passengers:
--------------------""", outputStreamCaptor.toString().trim());
}

@Test
@DisplayName("Insert multiple valids Ride")
public void insertMultipleRides(){
    MinHeap testHeap = new MinHeap();
    testHeap.insert(new Ride(57, LocalTime.parse("08:15:30"), 200, 700));
    testHeap.insert(new Ride(42, LocalTime.parse("12:45:00"), 300, 800));
    testHeap.insert(new Ride(86, LocalTime.parse("05:23:12"), 150, 600));
    testHeap.insert(new Ride(15, LocalTime.parse("19:30:45"), 250, 750));
    testHeap.insert(new Ride(99, LocalTime.parse("03:50:20"), 350, 850));
    testHeap.dump();
    Assertions.assertEquals("""
    --- Ride: 99 -------
    Time : 03:50:20
    Start ID : 350
    End ID : 850
    Passengers:
    --------------------
    --- Ride: 86 -------
    Time : 05:23:12
    Start ID : 150
    End ID : 600
    Passengers:
    --------------------
    --- Ride: 57 -------
    Time : 08:15:30
    Start ID : 200
    End ID : 700
    Passengers:
    --------------------
    --- Ride: 15 -------
    Time : 19:30:45
    Start ID : 250
    End ID : 750
    Passengers:
    --------------------
    --- Ride: 42 -------
    Time : 12:45
    Start ID : 300
    End ID : 800
    Passengers:
    --------------------""", 
    outputStreamCaptor.toString().trim());}

    /**
     * Insert Heapify Array with more than 20 items
     */
@Test
@DisplayName("Insert using Heapify Array of more than 20 rides")
public void heapifyInsertMoreThan20Items(){
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
rides[20] = new Ride(49, LocalTime.parse("01:30:25"), 290, 740);
testHeap.heapify(rides, 20);

Assert.assertEquals("", outputStreamCaptor.toString().trim());


}

 /**
     * Insert Heapify Array with more than 20 items
     */
    @Test
    @DisplayName("Insert using Heapify Array of 20 rides")
    public void heapifyInsert20Items(){
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
    testHeap.heapify(rides, 20);
    testHeap.dump();
    
    Assert.assertEquals("", outputStreamCaptor.toString().trim());
    
    
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
        testHeap.insert(new Ride(1, LocalTime.parse("11:23:53"), 522, 622));
        testHeap.insert(new Ride(0, LocalTime.parse("15:33:33"), 342, 942));
        testHeap.dump();
        Assert.assertEquals("""
            --- Ride: 1 -------
            Time : 11:23:53
            Start ID : 522
            End ID : 622
            Passengers:
            --------------------
            --- Ride: 0 -------
            Time : 15:33:33
            Start ID : 342
            End ID : 942
            Passengers:
            --------------------""", 
            outputStreamCaptor.toString().trim());
    }}

