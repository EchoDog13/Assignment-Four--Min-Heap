
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
public void testComparatorFirstEqualToSecondSmall(){
    Ride[] rides = new Ride[6];
    rides[1] = new Ride(2, LocalTime.parse("10:00:01"), 200, 700);
    rides[0] = new Ride(1, LocalTime.parse("10:00:01"), 700, 999);
    Assert.assertEquals(0, rides[0].compareTo(rides[1]));
}

@Test
@DisplayName("Comparator Test: First item is equal to second item")
public void testComparatorEqualToGreaterThanSecondSmall(){
    Ride[] rides = new Ride[6];
    rides[1] = new Ride(2, LocalTime.parse("23:59:59"), 200, 700);
    rides[0] = new Ride(1, LocalTime.parse("23:59:59"), 700, 999);
    Assert.assertEquals(0, rides[0].compareTo(rides[1]));
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

