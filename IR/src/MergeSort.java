
public class MergeSort {

	private static Comparable[] array;
    private static Comparable[] helper;
    
    private static int[] intArray;
    private static int[] intHelper;

    private static int N;
    
    public static void sort(Comparable[] values, int[] ids, int collectionSize){
    	intArray = ids;
    	array = values;
    	
        N = collectionSize;
        
        intHelper = new int[N];
        helper = new Comparable[N];
        mergesort(0, N - 1);
    }

    private static void mergesort(int low, int high) {
            //checks if low is smaller then high, if not then the array is sorted
            if (low < high) {
                    //get the index of the element which is in the middle
                    int middle = low + (high - low) / 2;
                    //sort the left side of the array
                    mergesort(low, middle);
                    //sort the right side of the array
                    mergesort(middle + 1, high);
                    //merge them
                    merge(low, middle, high);
            }
    }

    private static void merge(int low, int middle, int high) {

            //copy both parts into the helper array
            for (int i = low; i <= high; i++) {
                    helper[i] = array[i];
                    intHelper[i] = intArray[i];
            }

            int i = low;
            int j = middle + 1;
            int k = low;
            //copy the smallest values from either the left or the right side back
            //to the original array
            while (i <= middle && j <= high) {
                    if (helper[i].compareTo(helper[j]) <= 0) {
                            array[k] = helper[i];
                            intArray[k] = intHelper[i];
                            i++;
                    } else {
                            array[k] = helper[j];
                            intArray[k] = intHelper[j];
                            j++;
                    }
                    k++;
            }
            //copy the rest of the left side of the array into the target array
            while (i <= middle) {
                    array[k] = helper[i];
                    intArray[k] = intHelper[i];
                    k++;
                    i++;
            }

    }
}
