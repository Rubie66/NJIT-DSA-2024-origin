package oy.tol.tra;

public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    // This should implement a hash table.

    private Pair<K, V>[] values = null;
    private int count = 0;
    private int collisionCount = 0;
    private int maxProbingSteps = 0;
    private int reallocationCount = 0;
    private static final double LOAD_FACTOR = 0.45;
    private static final int DEFAULT_CAPACITY = 20;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        // Assuming capacity means the count of elements to add, so multiplying by fill
        // factor.
        values = (Pair<K, V>[]) new Pair[(int) ((double) capacity * (1.0 + LOAD_FACTOR))];
        reallocationCount = 0;
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
    }

    @Override
    public int size() {
        // TODO: Implement this.
        return count;
    }

    /**
     * Prints out the statistics of the hash table.
     * Here you should print out member variable information which tell something
     * about your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class (int counters) in add() whenever a collision
     * happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function is not good.
     */
    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hash table load factor is %.2f%n", LOAD_FACTOR));
        builder.append(String.format("Hash table capacity is %d%n", values.length));
        builder.append(String.format("Current fill rate is %.2f%%%n", (count / (double) values.length) * 100.0));
        builder.append(String.format("Hash table had %d collisions when filling the hash table.%n", collisionCount));
        builder.append(String.format("Hash table had to probe %d times in the worst case.%n", maxProbingSteps));
        builder.append(String.format("Hash table had to reallocate %d times.%n", reallocationCount));
        return builder.toString();
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % values.length);
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null!");
        }
        // Checks if the LOAD_FACTOR has been exceeded --> if so, reallocates to a
        // bigger hashtable.
        if (shouldResize()) {
            reallocate((int) ((double) (values.length) * (1.0 / LOAD_FACTOR)));
        }
        // Remember to get the hash key from the Person,
        // hash table computes the index for the Person (based on the hash value),
        // if index was taken by different Person (collision), get new hash and index,
        // insert into table when the index has a null in it,
        // return true if existing Person updated or new Person inserted.
        int hash = computeHash(key);
        int index = getIndex(hash);
        int probingSteps = 0;
        while (values[index] != null && probingSteps < values.length) {
            if (values[index].getKey().equals(key)) {
                values[index] = new Pair<>(key, value);
                return true;
            }
            index = (index + 1) % values.length;
            probingSteps++;
            collisionCount++;
        }
        if (probingSteps > maxProbingSteps) {
            maxProbingSteps = probingSteps;
        }
        values[index] = new Pair<>(key, value);
        count++;
        return true;
    }

    private boolean shouldResize() {
        return (double) count * (1.0 + LOAD_FACTOR) >= values.length;
    }

    private int computeHash(K key) {
        return key.hashCode();
    }

    private int getIndex(int hash) {
        return hash % values.length;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        // Remember to check for null.
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null!");
        }
        // Must use same method for computing index as add method
        int hash = computeHash(key);
        int i = getIndex(hash);
        int j = 0;

        while (values[i] != null && j < values.length) {
            if (values[i].getKey().equals(key)) {
                return values[i].getValue();
            }
            i = (i + 1) % values.length;
            j = j + 1;
            maxProbingSteps = Math.max(maxProbingSteps, j);
        }
        return null;
    }

    @Override
    @java.lang.SuppressWarnings({ "unchecked" })
    public Pair<K, V>[] toSortedArray() {
        Pair<K, V>[] sorted = (Pair<K, V>[]) new Pair[count];
        int newIndex = 0;
        for (int index = 0; index < values.length; index++) {
            if (values[index] != null) {
                sorted[newIndex++] = new Pair<>(values[index].getKey(), values[index].getValue());
            }
        }
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @SuppressWarnings("unchecked")
    private void reallocate(int newSize) throws OutOfMemoryError {
        if (newSize < DEFAULT_CAPACITY) {
            newSize = DEFAULT_CAPACITY;
        }
        reallocationCount = reallocationCount + 1;
        Pair<K, V>[] oldPairs = values;
        this.values = (Pair<K, V>[]) new Pair[(int) ((double) newSize * (1.0 + LOAD_FACTOR))];
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
        for (int index = 0; index < oldPairs.length; index++) {
            if (oldPairs[index] != null) {
                add(oldPairs[index].getKey(), oldPairs[index].getValue());
            }
        }
    }

    @Override
    public void compress() throws OutOfMemoryError {
        int newCapacity = (int) (count * (1.0 / LOAD_FACTOR));
        if (newCapacity < values.length) {
            reallocate(newCapacity);
        }
    }

}
