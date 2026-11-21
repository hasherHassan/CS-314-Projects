/*  Student information for assignment:
 *
 *  On MY honor, HASHER,
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1: Hasher Hassan
 *  UTEID: hmh3233
 *  email address: hmh3233@my.utexas.edu
 *  TA name: Jason Oh
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 */
public class UnsortedSet<E> extends AbstractSet314<E> {

    private ArrayList<E> con;

    /**
     * pre: none
     * post: create an empty set
     * O(1)
     */
    public UnsortedSet() {
        con = new ArrayList<>();
    }

    /**
     * pre: item != null
     * post: add item unless an equal element is already presented,
     * returns true if added, false otherwise
     * O(N) worst case
     */
    @Override
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (contains(item)) {
            return false;
        }
        con.add(item);
        return true;
    }

    /**
     * pre: none
     * post: removes all elements from set
     * O(N)
     */
    @Override
    public void clear() {
        con.clear();
    }

    /**
     * pre: none
     * post: returns an iterator over this set's elements
     * O(1)
     */
    @Override
    public Iterator<E> iterator() {
        return con.iterator();
    }

    /**
     * pre: none
     * post: returns number of elements in this set
     * O(1)
     */
    @Override
    public int size() {
        return con.size();
    }

    /**
     * pre: otherSet != null
     * post: reutrns a new UnsortedSet with elements in both sets
     * Doesn't change either input sets
     * O(N(M+1)) when otherSet contain's method uses linear search
     */
    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Set cannot be null");
        }
        UnsortedSet<E> result = new UnsortedSet<>();
        for (E item : this) {
            if (otherSet.contains(item)) {
                result.con.add(item);
            }
        }
        return result;
    }

    /**
     * pre: otherSet != null
     * post: return a new set containing all elements from both set
     * without changing either input
     * O(N+MN+M^2) worst case if using addAll from AbstractSet314
     * O(N^2) when both input sizes are N
     */
    @Override
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Set cannot be null");
        }
        UnsortedSet<E> result = new UnsortedSet<>();
        for (E item : this) {
            result.con.add(item);
        }
        result.addAll(otherSet);
        return result;
    }

    /**
     * pre: otherSet != null
     * post: return a new set that contains elements in this that are
     * not in the otherSet without changing either input
     * O(N(M+1)) worst case when otherSet.contains is a lienar search
     * O(N^2) when both input sizes are N
     */
    @Override
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Set cannot be null");
        }
        UnsortedSet<E> result = new UnsortedSet<>();
        for (E item : this) {
            if (!otherSet.contains(item)) {
                result.con.add(item);
            }
        }
        return result;
    }

}
