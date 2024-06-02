import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

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
            if (r.passengerNames[0] != null) {
                boolean rideID = checkId(r.rideID);
                if (rideID == true) {
                    heap[size] = r;
                    upheap(size);
                    size++;
                }
            }
        }

    }

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
        heap[size - 1] = null; // Remove the ride from the heap
        size--; // Decrement the size of the heap

        // Restore heap properties if necessary
        if (posToRemove < size) {
            downheap(posToRemove);
            upheap(posToRemove); // Ensure the heap is valid
        }
    }

    public void dump() {
        for (Ride ride : heap) {
            if (ride != null) {
                System.out.println(ride.toString());
            }
        }
    }

    private String outputRide(Ride[] rides, int i) {
        String out = "";
        Ride ride = rides[i];
        out += "--- Ride: " + ride.rideID + " -------\n";
        out += "Time : " + ride.requestTime + "\n";
        out += "Start ID : " + ride.startLocation + "\n";
        out += "End ID : " + ride.endLocation + "\n";
        out += "Passengers:\n";
        for (String item : ride.passengerNames) {
            if (item != null) {
                out += item + "\n";
            }
        }
        out += "--------------------\n";
        return out;
    }

    public void peek() {
        if (isEmpty()) {
            System.out.println("Error: Cannot peek heap as it contains no items");
        } else {
            // outputRide(heap, 1);
            System.out.println(heap[1].toString());

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

    public Ride[] optimise() {
        ArrayList<Ride> sortedRides = new ArrayList<>(Arrays.asList());
        sortedRides = returnHeapInOrder();

        Duration timeDifferenceA;
        Duration timeDifferenceB;
        // Ride[] sortedRides = sort();

        int passengerCountRideB = 0;
        int passengerCountRideA = 0;
        // Ride primaryRide = sortedRides.get(1);

        for (int i = 0; i < sortedRides.size(); i++) {
            Ride ride = sortedRides.get(i);

            if (ride == null)
                continue;

            for (int j = i + 1; j < sortedRides.size(); j++) {
                Ride ride2 = sortedRides.get(j);

                if (ride2 == null)
                    continue;

                timeDifferenceA = Duration.between(ride.requestTime, ride2.requestTime);
                timeDifferenceB = Duration.between(ride2.requestTime, ride.requestTime);

                if (ride.startLocation == ride2.startLocation && ride.endLocation == ride2.endLocation) {

                    if (timeDifferenceA.toMinutes() <= 10 && timeDifferenceB.toMinutes() <= 10) {
                        passengerCountRideB = passengerCount(ride.passengerNames);
                        passengerCountRideA = passengerCount(ride2.passengerNames);

                        if (passengerCountRideA + passengerCountRideB <= MAX_PASSENGER_CAPACITY) {
                            sortedRides = combineRides(ride, ride2, sortedRides);
                            // NEED TO REMOVE RIDES
                            // sortedRides.set(i, null); // Mark the first ride for removal
                            // sortedRides.set(j, null); // Mark the second ride for removal
                            sortedRides.remove(ride);
                            sortedRides.remove(ride2);

                            i = -1;
                            break; // Move to the next ride after combining
                        }
                    }
                }
            }

        }
        Ride[] rideArray = sortedRides.toArray(new Ride[0]);
        heapify(rideArray, rideArray.length);

        sortedRides = returnHeapInOrder();

        rideArray = sortedRides.toArray(new Ride[0]);

        return rideArray;
    }

    private ArrayList<Ride> combineRides(Ride primaryRide, Ride secondaryRide, ArrayList<Ride> sortedRides) {
        String combinedNames = "";
        LocalTime newRequestTime;
        if (primaryRide.compareTo(secondaryRide) <= 1) {
            newRequestTime = primaryRide.requestTime;
        } else {
            newRequestTime = secondaryRide.requestTime;
        }
        Ride newRide = new Ride(primaryRide.rideID, newRequestTime, primaryRide.startLocation,
                secondaryRide.endLocation);
        combinedNames = combinePassengers(primaryRide, secondaryRide);
        newRide.addPassenger(combinedNames);

        sortedRides.add(newRide);

        return sortedRides;
    }

    private int passengerCount(String[] stringArray) {
        int passengerCount = 0;

        for (String string : stringArray) {
            if (string != null) {
                passengerCount++;
            }
        }
        return passengerCount;
    }

    private String combinePassengers(Ride primaryRide, Ride secondaryRide) {
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
