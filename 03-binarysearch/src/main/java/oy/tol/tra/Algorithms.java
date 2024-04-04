package oy.tol.tra;

public class Algorithms {
    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            if (fromArray[mid].compareTo(aValue) == 0) {
                return mid;
            } else if (fromArray[mid].compareTo(aValue) < 0) {
                fromIndex = mid + 1;
            } else if (fromArray[mid].compareTo(aValue) > 0) {
                toIndex = mid - 1;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E pivot = array[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                E tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        E tmp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = tmp;
        return i + 1;
    }

    /**
     * public static <T extends Comparable<T>> void sort(T[] array) {
     * int i = 0;
     * while (i < array.length) {
     * int j = array.length - 1;
     * while (j > i) {
     * if (array[j].compareTo(array[j - 1]) < 0) {
     * T tmp = array[j];
     * array[j] = array[j - 1];
     * array[j - 1] = tmp;
     * }
     * j--;
     * }
     * i = i + 1;
     * }
     * }
     */

    public static <T extends Comparable<T>> void reverse(T[] array) {
        int i = 0;
        while (i < array.length / 2) {
            T temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
            i++;
        }
    }
}
