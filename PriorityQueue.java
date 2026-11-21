/*  Student information for assignment:
 *
 *  On MY honor, Hasher, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used) Hasher
 *  UTEID: hmh3233
 *  email address: hmh3233@eid.utexas.edu
 *  Grader name: Jason Oh
 *
 */

import java.util.ArrayList;

/**
 * A generic priority queue that removes the smallest element first and
 * preserves insertion order among elements that compare as equal
 */
public class PriorityQueue<E extends Comparable<? super E>> {

    private ArrayList<E> list;

    /**
     * pre: none
     * post: creates an empty priority queue
     */
    public PriorityQueue() {
        list = new ArrayList<>();
    }

    /**
     * pre: item != null
     * post: inserts item in ascending natural order, after
     * existing elements that compare as equal, increases the queue
     * size by one
     * @throws IllegalArgumentException if item is null
     */
    public void enqueue(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        int index = 0;
        while (index < list.size() && list.get(index).compareTo(item) <= 0) {
            index++;
        }
        list.add(index, item);
    }

    /**
     * pre: none
     * post: removes and returns the smallest element according to
     * compareTo, choosing the earliest inserted element among ties,
     * returns null if the queue is empty
     */
    public E dequeue() {
        if (list.size() == 0) {
            return null;
        }
        return list.remove(0);
    }

    /**
     * pre: none
     * post: returns the number of elements currently in the queue
     */
    public int size() {
        return list.size();
    }

    /**
     * pre: none
     * post: returns true if the queue contains no elements, otherwise false
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}