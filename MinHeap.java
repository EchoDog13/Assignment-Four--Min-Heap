import java.util.Arrays;

public class MinHeap {
     Ride[] heap;
     int size = 1;
    private static final int DEFAULT_CAPACITY = 20 + 1;

    public MinHeap() {
        this.heap = new Ride[DEFAULT_CAPACITY];
       // size = 1;
    }
    /**
     * 
     * @param rides Array of Ride Class to replace the existing heap
     * @param rideNum Number of rides contained in the rides array, maximum is 20
     * Indiviudal insert approach is used rather than directly replacing the array to check for null values and to ensure that the first index is left blank to enable array equation functionality to continue
     */
    public void heapify(Ride[] rides, int rideNum) {
        if (rideNum > 20) {
            // If more than 20 rides are provided, do not modify the heap
            return;
        }
       
        this.heap = new Ride[DEFAULT_CAPACITY + 1]; // Reinitialize with default capacity
        this.size = 0; // Reset the size


        for (Ride ride : rides) {
            if (ride != null) {
                insert(ride);
            }
        } 
    }

    public void insert(Ride r) {
        if (size <= 20) {
            
        
        if (size == heap.length) {
         //   heap = Arrays.copyOf(heap, size * 2); // Double the capacity if full
        }
            
        
        for (Ride ride : heap) {
            if (ride != null) {
            if (ride.rideID == r.rideID) {
                return;
            }}
        }
        heap[size] = r;
        upheap(size);
        size++;
        }}

    private void upheap (int pos){
        int posChild = pos;
        while(posChild >1){
            int posParent = posChild/2;
            if ((heap[posChild] != null) && (heap[posParent] != null)){
            if (heap[posChild].requestTime.compareTo(heap[posParent].requestTime)<0) {
                swap(posChild, posParent);
            }
            posChild = posParent;
        }}
    
    }

    private void downheap(int pos) {
        int posParent = pos;

        while (posParent <= size / 2) {
            int leftChild = posParent * 2;
            int rightChild = leftChild + 1;
            int smallestChild = leftChild; // Start with left child by default

            // Check if right child exists and is smaller than the left child

            if ((heap[leftChild] != null) && (heap[rightChild]) !=null){
                if ( heap[rightChild].requestTime.compareTo(heap[leftChild].requestTime)<0) {
                smallestChild = rightChild;
                }
            }

            // Check if the smallest child is not null and compare parent with it
            if (heap[smallestChild]!= null){
            if (heap[posParent].requestTime.compareTo(heap[smallestChild].requestTime)>0) {
                swap(posParent, smallestChild);
                posParent = smallestChild;
            } }else {
                break; // Parent is smaller or equal, no need to continue down
            }
        }
    }

    public void swap(int pos1, int pos2){
        Ride tempValue = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tempValue;
    }

    public void remove(Ride rideToRemove) {
        if (size == 0) {
            // If the heap is empty, return
            return;
        }

        int posToRemove = -1; // Initialize position to -1 (indicating not found)

        // Find the position of the ride to remove
        for (int i = 1; i <= size; i++) {
            if (heap[i].rideID == rideToRemove.rideID) {
                posToRemove = i; // Found the ride
                break;
            }
        }

        if (posToRemove == -1) {
            // Ride not found in the heap
            return;
         }

        // Swap the ride to remove with the last ride in the heap
       swap(posToRemove, size-1);
         heap[size-1] = null; // Remove the ride from the heap
        size--; // Decrement the size of the heap
    System.out.println(size);
        if (posToRemove <= size) {
           downheap(posToRemove);
        }
    }

    public void dump() {
        int i = 1;

        while (i < heap.length && heap[i] != null) {
            outputRide(i);
            i++;
        }
    }

    public void outputRide(int i){

        Ride ride = heap[i];
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

    public void peek(){
         if (isEmpty()==true) {
            System.out.println("Error: Cannot peek heap as it contain no items");
        }
        outputRide(1);
    }



    public boolean isEmpty() {
        return heap[1] == null;
    }


    public void sort(){
        upheap(1);
        downheap(1); 
    }

    public void optimise(){
        for (Ride ride : heap) {
            //if (ride.requestTime.compareTo()) {
                
          //  }
        }
    }

}