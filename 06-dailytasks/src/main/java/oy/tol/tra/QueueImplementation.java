package main.java.oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] itemArray;
    private int top, bottom;
    private int size;
    private int capacity;

    public QueueImplementation(int capacity) {
        this.capacity = capacity;
        itemArray = new Object[capacity];

    }

    public int capacity() {
        return capacity;
    }

    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("It is null.");
        }
        if (bottom + 1 > capacity) {
            int newcapacity = capacity + 100;
            Object[] newitemArray = new Object[newcapacity];
            for (int i = 0; i < size; i++) {
                newitemArray[i] = itemArray[top + i];
            }
            itemArray = newitemArray;
            capacity = newcapacity;
            top = 0;
            bottom = size;
        }
        itemArray[bottom] = element;
        size = size + 1;
        bottom = bottom + 1;
    }

    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty.");
        }
        E element = (E) itemArray[top];
        itemArray[top] = null;
        top = (top + 1) % capacity;
        size = size - 1;
        return element;
    }

    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty.");
        }
        return (E) itemArray[top];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        itemArray = new Object[capacity];
        top = 0;
        bottom = 0;
        size = 0;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int index = (top + i) % capacity;
            builder.append(itemArray[index]);
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

}
