package oy.tol.tra;

public class Algorithms {
    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex){
        int mid = fromIndex + (toIndex - fromIndex) / 2;
        if(fromArray[mid].compareTo(aValue) == 0){
            return mid;
        }
        else if (fromArray[mid].compareTo(aValue) < 0){
             fromIndex = mid + 1;
        }
        else if (fromArray[mid].compareTo(aValue) > 0){
             toIndex = mid - 1;
        }
        }
        return -1;
    }

     public static <T extends Comparable<T>> void sort(T [] array) {
            int i = 0;
            while (i < array.length) {
               int j = array.length - 1;
               while (j > i) {
               if (array[j].compareTo(array[j-1])< 0) {
                  T tmp = array[j];
                  array[j] = array[j-1];
                  array[j-1] = tmp;
               }
               j--;
            }
            i = i + 1;
         }
     }
}
