package oy.tol.tra;
public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] itemArray;
    private int top;
    private int bottom;
    private int size;
    private int capacity;
    public QueueImplementation(int capacity) {
            this.capacity = capacity;
            itemArray = new Object[capacity];
            top = 0;
            bottom = 0;
            size = 0;
        }
   /**
    * For querying the current capacity of the queue.
    @return The number of elements the queue can currently hold.
    */
    public int capacity() {
        return capacity;
    }
   /**
    * Add an element to the queue.
    * @param element The element to add, must not be null.
    * @throws QueueAllocationException If the reallocation for the queue failed in case queue needs reallocation.
    * @throws NullPointerException If the element is null.
    */
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {  
        if (element == null) {  
            throw new NullPointerException("Element cannot be null.");  
        }  
        if (size + 1 >=capacity) {  
            int newCapacity = capacity * 2;   
            Object[] newItemArray = new Object[newCapacity];   
            for (int i = 0; i < size; i++) {  
                newItemArray[i] = itemArray[top + i];  
            }  
            itemArray = newItemArray;  
            capacity = newCapacity;  
            top = 0;  
            bottom = size;  
        }  
        itemArray[bottom] = element;  
        bottom = bottom + 1;  
        size = size + 1;  
    }    
       /**
        * Removes an element from the queue.
        * @return The element from the head of the queue.
        * @throws QueueIsEmptyException If the queue is empty.
        */
       public E dequeue() throws QueueIsEmptyException{
            if (isEmpty()) {
                throw new QueueIsEmptyException("Queue is empty.");
            }
            E element = (E) itemArray[top];
            itemArray[top] = null;
            top = (top + 1) % capacity;
            size = size -1;
            return element;
        }
       /**
        * Returns the element at the head of the queue, not removing it from the queue.
        * @return The element in the head of the queue.
        * @throws QueueIsEmptyException If the queue is empty.
        */
       public E element() throws QueueIsEmptyException {
            if (isEmpty()) {
                throw new QueueIsEmptyException("Queue is empty.");
            }
            return (E) itemArray[top];
        }
    
    
       /**
        * Returns the count of elements currently in the queue.
        * @return Count of elements in the queue.
        */
       public int size(){
            return size;
       }
    
    
       /**
        * Can be used to check if the queue is empty.
        * @return True if the queue is empty, false otherwise.
        */
       public boolean isEmpty(){
            return size==0;
       }
    
       
       /**
        * Resets the queue to empty state, removing the objects.
        */
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
    
