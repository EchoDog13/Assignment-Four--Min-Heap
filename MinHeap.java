import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    Ride[] heap;
    int size = 1;
    private static final int DEFAULT_CAPACITY = 20;
    private static final int MAX_PASSENGER_CAPACITY = 6;

    public MinHeap() {
        this.heap = new Ride[DEFAULT_CAPACITY + 1]; // Add 1 to allow index 1-based heap
    }

    public void heapify(Ride[] rides, int rideNum) {
        if (rideNum > DEFAULT_CAPACITY) {
            // If more than 20 rides are provided, do not modify the heap
            return;
        }
        size = 1;
        this.heap = new Ride[DEFAULT_CAPACITY + 1]; // Reinitialize with default capacity
        for (Ride ride : rides) {
            if (ride != null) {
                insert(ride);
            }
        }
    }

    public void insert(Ride r) {
        if (size <= DEFAULT_CAPACITY) {
            if (r.passengerNames[0]!= null) {
            heap[size] = r;
            upheap(size);
            size++;
            }}
        }
    

    private void upheap(int pos) {
        int posChild = pos;
        while (posChild > 1) {
            int posParent = posChild / 2;
            if (heap[posChild] != null && heap[posParent] != null) {
                if (heap[posChild].compareTo(heap[posParent]) < 0) {
                    swap(posChild, posParent);
                }
                posChild = posParent;
            }
        }
    }

    private void downheap(int pos) {
        int posParent = pos;
        while (posParent * 2 < size) {
            int leftChild = posParent * 2;
            int rightChild = leftChild + 1;
            int smallestChild = leftChild; // Start with left child by default

            if (rightChild < size && heap[rightChild] != null && heap[rightChild].compareTo(heap[leftChild]) < 0) {
                smallestChild = rightChild;
            }

            if (heap[smallestChild] != null && heap[posParent].compareTo(heap[smallestChild]) > 0) {
                swap(posParent, smallestChild);
                posParent = smallestChild;
            } else {
                break; // Parent is smaller or equal, no need to continue down
            }
        }
    }

    public void swap(int pos1, int pos2) {
        Ride tempValue = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tempValue;
    }

    public void remove(Ride rideToRemove) {
        if (size <= 1) {
            // If the heap is empty or only the placeholder is present, return
            return;
        }

        int posToRemove = -1; // Initialize position to -1 (indicating not found)

        // Find the position of the ride to remove
        for (int i = 1; i < size; i++) {
            if (heap[i] != null && heap[i].rideID == rideToRemove.rideID) {
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
        heap[size - 1] = null; // Remove the ride from the heap
        size--; // Decrement the size of the heap

        // Restore heap properties if necessary
        if (posToRemove < size) {
            downheap(posToRemove);
            upheap(posToRemove); // Ensure the heap is valid
        }
    }

    public void dump() {
        int i = 1;
        while (i < heap.length && heap[i] != null) {
            outputRide(heap, i);
            i++;
        }
    }

    public void outputRide(Ride[] rides, int i) {
        Ride ride = rides[i];
        System.out.println("--- Ride: " + ride.rideID + " -------");
        System.out.println("Time : " + ride.requestTime);
        System.out.println("Start ID : " + ride.startLocation);
        System.out.println("End ID : " + ride.endLocation);
        System.out.println("Passengers:");
        for (String item : ride.passengerNames) {
            if (item != null) {
                System.out.println(item);
            }
        }
        System.out.println("--------------------");
    }

    public void peek() {
        if (isEmpty()) {
            System.out.println("Error: Cannot peek heap as it contains no items");
        } else {
            outputRide(heap, 1);
        }
    }

    public boolean isEmpty() {
        return heap[1] == null;
    }

    public Ride[] sort() {
        Ride[] tempHeap = heap.clone(); // Copy the original heap
        Ride[] sortedRides = new Ride[size - 1]; // Array to hold the sorted rides

        int a = 0;
        while (size > 1) { // Continue until the heap is empty
            sortedRides[a] = heap[1]; // Take the root (minimum element)
            remove(heap[1]); // Remove the root
            a++;
        }

        heap = tempHeap; // Restore the original heap
        return sortedRides;
    }

    public void optimise() {
        Duration timeDifference;
        Ride[] sortedRides = sort();
        int i  = 1;
        int j =0;
        int passengerCountRideB = 0;
        int passengerCountRideA = 0;
        Ride primaryRide = sortedRides[1];

        for (Ride ride : sortedRides) {

            timeDifference = Duration.between(primaryRide.requestTime, ride.requestTime);
            if (timeDifference.toMinutes() <=10) {
                passengerCountRideB = passengerCount(j, ride.passengerNames);
                passengerCountRideA = passengerCount(1, ride.passengerNames);

                if (passengerCountRideA + passengerCountRideB <= MAX_PASSENGER_CAPACITY) {
                    combineRides(primaryRide, ride);
                }

              
            }
            
            j++;
        }
       
        dump();


       
        // Method left for implementation as needed


    }


    private void combineRides(Ride primaryRide, Ride secondaryRide){
        String combinedNames = "";
        LocalTime newRequestTime;
        if (primaryRide.compareTo(secondaryRide) <= 1) {
            newRequestTime = primaryRide.requestTime;
        }
        else{
            newRequestTime = secondaryRide.requestTime;
        }
        Ride newRide = new Ride(55, newRequestTime, primaryRide.startLocation, secondaryRide.endLocation);
        combinedNames = combinePassengers(primaryRide, secondaryRide);
        newRide.addPassenger(combinedNames);
        remove(primaryRide);
        remove(secondaryRide);
        insert(newRide);
        System.out.println(primaryRide.passengerNames.toString());
    }



    private int passengerCount(int index, String[] stringArray){
        int passengerCount = 0;

 //for (int i = 0; i < MAX_PASSENGER_CAPACITY; i++) {
            for (String string : stringArray) {
                if (string!=null) {
                    passengerCount++;
                }
            }
       // }

        return passengerCount;
    }

    private String combinePassengers(Ride primaryRide, Ride secondaryRide){
        String overallString = "";
        for (String name : primaryRide.passengerNames) {
            overallString = overallString + name + ",";
        }
        for (String name : secondaryRide.passengerNames) {
            overallString = overallString + name + ",";
        }
        overallString = overallString.substring(0, overallString.length() - 1);
        return overallString;
    }
}
