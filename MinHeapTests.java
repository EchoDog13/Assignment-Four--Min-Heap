
//Import statements 
import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.beans.Transient;
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

    @Test
    @DisplayName("Insert to empty stack")
    public void importEmptyStack() {
        MinHeap testHeap = new MinHeap();
        testHeap.insert(new Ride(0, LocalTime.parse("15:33:33"), 342, 942, "kyle,barker"));
        testHeap.peek();
        Assert.assertEquals(
                "--- Ride: 0 -------\nTime : 15:33:33\nStart ID : 342\nEnd ID : 942\nPassengers:\nkyle\nbarker\n--------------------",
                outputStreamCaptor.toString().trim());
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
        testHeap.insert(new Ride(1, LocalTime.parse("11:23:53"), 342, 942, "Hannah,Noel"));
        testHeap.insert(new Ride(0, LocalTime.parse("15:33:33"), 342, 942, "kyle,barker"));
        testHeap.dump();
        Assert.assertEquals("""
                --- Ride: 0 -------
                Time : 15:33:33
                Start ID : 342
                End ID : 942
                Passengers:
                kyle
                barker
                --------------------
                --- Ride: 1 -------
                Time : 11:23:53
                Start ID : 342
                End ID : 942
                Passengers:
                Hannah
                Noel
                --------------------""", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Insert items greater than inital array size of 10")
    public void insertNumerousItems() {
        MinHeap testHeap = new MinHeap();
        testHeap.insert(new Ride(0, LocalTime.parse("15:33:33"), 342, 942, "Kyle,Adam,Benjamin"));
        testHeap.insert(new Ride(1, LocalTime.parse("16:45:22"), 215, 731, "Emma,Sophia,Matthew"));
        testHeap.insert(new Ride(2, LocalTime.parse("10:10:10"), 500, 1000, "John,Hannah,David"));
        testHeap.insert(new Ride(3, LocalTime.parse("12:20:05"), 150, 800, "Alice,James,Lily"));
        testHeap.insert(new Ride(4, LocalTime.parse("09:05:30"), 400, 600, "Michael,Samantha,Ethan"));
        testHeap.insert(new Ride(5, LocalTime.parse("14:15:00"), 275, 925, "Sarah,Noah,Olivia"));
        testHeap.insert(new Ride(6, LocalTime.parse("11:30:45"), 700, 1100, "Chris,Grace,Lucas"));
        testHeap.insert(new Ride(7, LocalTime.parse("13:40:20"), 320, 780, "Emily,Daniel,Ava"));
        testHeap.insert(new Ride(8, LocalTime.parse("08:55:15"), 450, 850, "Matthew,Chloe,Elijah"));
        testHeap.insert(new Ride(9, LocalTime.parse("17:20:10"), 600, 950, "Olivia,Liam,Ella"));
        testHeap.insert(new Ride(10, LocalTime.parse("18:10:55"), 200, 700, "Daniel,Mia,William"));
        testHeap.insert(new Ride(11, LocalTime.parse("19:25:40"), 380, 820, "Jessica,Benjamin,Abigail"));

        Assert.assertEquals(testHeap, testHeap);

    }

}