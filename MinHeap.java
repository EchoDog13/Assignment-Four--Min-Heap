import java.util.Arrays;

public class MinHeap {
     Ride[] heap;
     int size = 1;
    private static final int DEFAULT_CAPACITY = 10;

    public MinHeap() {
        this.heap = new Ride[DEFAULT_CAPACITY];
       // size = 1;
    }

    public void insert(Ride r) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, size * 2); // Double the capacity if full
        }
        heap[size] = r;
        upheap(size);
        size++;}

    public void upheap (int pos){
        int posChild = pos;
        while(posChild >1){
            int posParent = posChild/2;
            if ((heap[posChild] != null) && (heap[posParent] != null)){
            if (heap[posChild].rideID < heap[posParent].rideID){
            swap(posChild, posParent);
            }
            posChild = posParent;
        }}
    }

    public void downheap(int pos) {
        int posParent = pos;

        while (posParent <= size / 2) {
            int leftChild = posParent * 2;
            int rightChild = leftChild + 1;
            int smallestChild = leftChild; // Start with left child by default

            // Check if right child exists and is smaller than the left child

            if ((heap[leftChild] != null) && (heap[rightChild]) !=null){
                if ( heap[rightChild].rideID < heap[leftChild].rideID) {
                smallestChild = rightChild;
                }

            }

            // Check if the smallest child is not null and compare parent with it
            if (heap[smallestChild]!= null){
            if (heap[posParent].rideID > heap[smallestChild].rideID) {
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
        while(heap[i] !=null) {
            // Start from index 1 since index 0 is unused
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
            System.out.println(item);
        }

        System.out.println("--------------------");

    }

    public void peek(){
    outputRide(1);
    }

    public boolean isEmpty(){

        if (heap[1] == null){
            return true;
        } else return false;
    }

}