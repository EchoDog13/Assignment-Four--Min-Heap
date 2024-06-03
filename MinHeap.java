/**
 * This program creates an implementation of a min heap that works to create an efficent ride sharing platform. It has the functionality of inserting rides, removing ride, heapifying, sorting rides and returning in time order. It also has a peek method to show the next ride, an is empty method to show if the heap contains any items as well as an optimise feature to create efficent ride schedules
 */

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Public class of MinHeap class
 */
public class MinHeap {
    //Intitalised Ride Array to create a heap based on a 1 based index array structure
    Ride[] heap;
    //Tracks the size of the heap
    int size = 1;

    //Number of cars 
    private static final int DEFAULT_CAPACITY = 20;
    //Number of passegners per car
    private static final int MAX_PASSENGER_CAPACITY = 6;
    //Time between rides in which they can be combined
    private static final int TIME_TO_COMBINE = 10;


    public MinHeap() {
        this.heap = new Ride[DEFAULT_CAPACITY + 1]; // Add 1 to allow index 1-based heap
    }


    /**
     * Heapify method turns array into heap, replacing any items in existing heap
     * @param rides array to heapify
     * @param rideNum number of items in array to heapify
     */
    public void heapify(Ride[] rides, int rideNum) {
        // If more than 20 rides are provided, do not modify the heap
        if (rideNum > DEFAULT_CAPACITY) {
            return;
        }
        size = 1;
        // Reinitialize with default capacity
        this.heap = new Ride[DEFAULT_CAPACITY + 1]; 
        for (Ride ride : rides) {
            //Inserts all non null elements in array
            if (ride != null) {
                insert(ride);
            }
        }
    }

    /**
     * Method to insert value into heap, manitaining min heap order
     * @param r ride to insert
     */
    public void insert(Ride r) {
        if (size <= DEFAULT_CAPACITY) {
            //checks ride has passenger names
            if (r.passengerNames[0] != null) {
                //checks no existing rides have the same ride ID
                boolean rideID = checkId(r.rideID);
                if (rideID == true) {
                    heap[size] = r;
                    //Maintain heap order
                    upheap(size);
                    size++;
                }
            }
        }

    }
    /**
     * Checks if parsed ride ID matches any other ride IDs in existing heap
     * @param r ride id to check against as an integer
     * @return boolean, true if there is no match and it can be inserted, false if it matches
     */
    private boolean checkId(int r) {
        for (Ride ride : heap) {
            if (ride != null) {
                if (ride.rideID == r) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method to maintain heap order on insert
     * @param pos starting position of upheap
     */
    private void upheap(int pos) {
        int posChild = pos;
        while (posChild > 1) {
            //calculate parent position
            int posParent = posChild / 2;
            if (heap[posChild] != null && heap[posParent] != null) {
                //checks if parent or child is greater
                if (heap[posChild].compareTo(heap[posParent]) < 0) {
                    swap(posChild, posParent);
                }
                //iterates up the heap to check order is maintained
                posChild = posParent;
            }
        }
    }

    /**
     * Used to maintian heap order on deletions. Checks if either child of parent is less than parent, swaps if true
     * @param pos starting position as an integer
     */
    private void downheap(int pos) {
        int posParent = pos;
        while (posParent * 2 < size) {

            //calculate parent and child positions
            int leftChild = posParent * 2;
            int rightChild = leftChild + 1;
            int smallestChild = leftChild; 

            //check if right child is smaller than parent
            if (rightChild < size && heap[rightChild] != null && heap[rightChild].compareTo(heap[leftChild]) < 0) {
                smallestChild = rightChild;
            }

            //check if left child is smaller than parent
            if (heap[smallestChild] != null && heap[posParent].compareTo(heap[smallestChild]) > 0) {
                //swaps to maintain heap order
                swap(posParent, smallestChild);
                posParent = smallestChild;
            } else {
                break;
            }
        }
    }

    /**
     Method to swap nodes around in heap to maintain min heap order
     * @param pos1 first position as an integer in heap array
     * @param pos2 first position as an integer in heap array
     */
    private void swap(int pos1, int pos2) {
        Ride tempValue = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tempValue;
    }

    /**
     * Method to remove a selected ride from the heap
     * @param rideToRemove the ride which is going to be removed 
     */
    public void remove(Ride rideToRemove) {
        if (size <= 1) {
            // If the heap is empty or only the placeholder is present, return
            return;
        }

        int posToRemove = -1;
        // Find the position of the ride to remove
        for (int i = 1; i < size; i++) {
            if (heap[i] != null && heap[i] == rideToRemove) {
                posToRemove = i; // Found the ride
                break;
            }
        }

        if (posToRemove == -1) {
            // Ride not found in the heap
            return;
        }

        // Swap the ride to remove with the last ride in the heap
        swap(posToRemove, size - 1);
        heap[size - 1] = null; // Remove the last ride from the heap
        size--; 

        // Restore heap properties if necessary
        if (posToRemove < size) {
            downheap(posToRemove);
            upheap(posToRemove); 
        }
    }

    /**
     * Method to print all items in the heap to the console
     */
    public void dump() {
        for (Ride ride : heap) {
            if (ride != null) {
                System.out.println(ride.toString());
            }
        }
    }

    /**
     * Prints the top item in the heap (the minimum) to the console
     */
    public void peek() {
        if (isEmpty()) {
            System.out.println("Error: Cannot peek heap as it contains no items");
        } else {
            // outputRide(heap, 1);
            System.out.println(heap[1].toString());
        }
    }

    /**
     * Checks if heap contains items
     * @return boolean, true if heap is emtpy, false if heap contains items  
     */
    public boolean isEmpty() {
        return heap[1] == null;
    }

    /**
     * Sorts the heap into chronological time order and returns array of sorted rides
     * @return returns array of sorted rides
     */

    public Ride[] sort() {
        //copy original heap
        Ride[] tempHeap = heap.clone(); 
        //array to store sorted rides
        Ride[] sortedRides = new Ride[size - 1]; // Array to hold the sorted rides

        int a = 0;
        //peeks item, then remvoes from heap to sort into time based order
        while (size > 1) { 
            sortedRides[a] = heap[1]; 
            remove(heap[1]); 
            a++;
        }
        // Restore the original heap
        heap = tempHeap; 
        return sortedRides;
    }

    /**
     * Method to combine rides that meet the following conditions:
     * - Have the same locations ID for the start and end
     * - Were requested within 10 Minutes of each other
     * - Have less than or equal to 6 passengers or less
     * @return returns array of combined and sorted rides
     */
    public Ride[] optimise() {
        ArrayList<Ride> sortedRides = new ArrayList<>(Arrays.asList());
        sortedRides = returnHeapInOrder();

        Duration timeDifferenceA;
        Duration timeDifferenceB;
        int passengerCountRideB = 0;
        int passengerCountRideA = 0;
        
        for (int i = 0; i < sortedRides.size(); i++) {
            Ride ride = sortedRides.get(i);

            if (ride == null)
                continue;

            for (int j = i + 1; j < sortedRides.size(); j++) {
                Ride ride2 = sortedRides.get(j);

                if (ride2 == null)
                    continue;
                
                    //calculate time difference between rides
                timeDifferenceA = Duration.between(ride.requestTime, ride2.requestTime);
                timeDifferenceB = Duration.between(ride2.requestTime, ride.requestTime);
                //check start and end locations match
                if (ride.startLocation == ride2.startLocation && ride.endLocation == ride2.endLocation) {
                    //check time difference is within range
                    if (timeDifferenceA.toMinutes() <= TIME_TO_COMBINE && timeDifferenceB.toMinutes() <= TIME_TO_COMBINE) {
                        passengerCountRideB = passengerCount(ride.passengerNames);
                        passengerCountRideA = passengerCount(ride2.passengerNames);
                        //check passenger capacity  
                        if (passengerCountRideA + passengerCountRideB <= MAX_PASSENGER_CAPACITY) {
                            sortedRides = combineRides(ride, ride2, sortedRides);
                           //removes rides from list to be sorted
                            sortedRides.remove(ride);
                            sortedRides.remove(ride2);

                            i = -1;
                            break; 
                        }
                    }
                }
            }
        }
        Ride[] rideArray = sortedRides.toArray(new Ride[0]);
        //restore heap with combined rides
        heapify(rideArray, rideArray.length);
        //create array of sorted rides
        sortedRides = returnHeapInOrder();

        rideArray = sortedRides.toArray(new Ride[0]);

        return rideArray;
    }

    /***
     * Combines the rides together into one rides
     * @param primaryRide first ride to combine
     * @param secondaryRide second ride to combine 
     * @param sortedRides array which stores rides
     * @return return the combined ride
     */
    private ArrayList<Ride> combineRides(Ride primaryRide, Ride secondaryRide, ArrayList<Ride> sortedRides) {
        String combinedNames = "";
        LocalTime newRequestTime;
        //Sets the request time to be the earlier of the two rides
        if (primaryRide.compareTo(secondaryRide) <= 1) {
            newRequestTime = primaryRide.requestTime;
        } else {
            newRequestTime = secondaryRide.requestTime;
        }

        Ride newRide = new Ride(primaryRide.rideID, newRequestTime, primaryRide.startLocation,
        secondaryRide.endLocation);
        //combine passenger names into an overall combined list of names
        combinedNames = combinePassengers(primaryRide, secondaryRide);
        //Add in new passengers
        newRide.addPassenger(combinedNames);
        //Interst new ride into rides list
        sortedRides.add(newRide);

        return sortedRides;
    }

    /**
     * Counts the number of passengers in each ride
     * @param stringArray string to count the number of passengers
     * @return return the number of passengers from the string
     */
    private int passengerCount(String[] stringArray) {
        int passengerCount = 0;

        for (String string : stringArray) {
            if (string != null) {
                passengerCount++;
            }
        }
        return passengerCount;
    }

    /**
     * Combines passengers into a single string
     * @param primaryRide first ride
     * @param secondaryRide second ride
     * @return returns a single combined string
     */
    private String combinePassengers(Ride primaryRide, Ride secondaryRide) {
        String overallString = "";
        for (String name : primaryRide.passengerNames) {
            overallString = overallString + name + ",";
        }
        for (String name : secondaryRide.passengerNames) {
            overallString = overallString + name + ",";
        }
        //Removes final comma from end of string
        overallString = overallString.substring(0, overallString.length() - 1);
        return overallString;
    }

    /**
     * returns a sorted array list of the rides in time order
     * @return returns an array list of the heap in order
     */
    private ArrayList<Ride> returnHeapInOrder() {
        ArrayList<Ride> heapInOrder = new ArrayList<>();
        for (int i = 0; i <= heapInOrder.size(); i++) {
            if (heap[1] != null) {
                heapInOrder.add(heap[1]);
                remove(heap[1]);
            }
        }
        return heapInOrder;
    }

}
