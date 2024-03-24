package oy.tol.tra;
import java.util.function.Predicate;
import java.util.Arrays;
import java.util.Comparator;

public class Algorithms { 
    public static <K extends Comparable<K>, V> void fastSort(Pair<K, V>[] array) {  
        if (array == null || array.length <= 1) {  
            return;  
        }  
          
        quickSort(array, 0, array.length - 1);  
    }  
  
    private static <K extends Comparable<K>, V> void quickSort(Pair<K, V>[] array, int left, int right) {  
        if (left < right) {  
            int pivotIndex = partition(array, left, right);  
            quickSort(array, left, pivotIndex - 1);  
            quickSort(array, pivotIndex + 1, right);  
        }  
    }  
      
    private static <K extends Comparable<K>, V> int partition(Pair<K, V>[] array, int left, int right) {  
        Pair<K, V> pivot = array[right];   
        int i = left - 1; 
        for (int j = left; j < right; j++) {  
            if (array[j].compareTo(pivot) <= 0 ) {  
                i++;  
                swap(array, i, j);  
            }  
        }  
  
        swap(array, i + 1, right);  
        return i + 1; 
    }   
      
    private static <K extends Comparable<K>, V> void swap(Pair<K, V>[] array, int i, int j) {  
        Pair<K, V> temp = array[i];  
        array[i] = array[j];  
        array[j] = temp;  
    }
    public static void sortWithComparator(Person[] array, Comparator<Person> comparator) {  
        Arrays.sort(array, comparator);  
    }  

    public static <K extends Comparable<K>, V> int partitionByRule(Pair<K, V>[] array, int size, Predicate<Pair<K, V>> predicate) {  
        int i = 0;
        for (int j = 0; j < size; j++) {  
            if (predicate.test(array[j])) {   
                Pair<K, V> temp = array[i];  
                array[i] = array[j];  
                array[j] = temp;  
                i++;  
                } 
       }
       return i;
    }   
}
