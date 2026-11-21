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
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet314<E> {

    private ArrayList<E> con;

    /**
     * pre: none
     * post: creates an empty SortedSet
     * O(1)
     */
    public SortedSet() {
        con = new ArrayList<>();
    }

    /**
     * pre: other != null
     * post: reate a copy of other that is sorted.<br>
     * @param other != null
     * O(NlogN) worst case
     * O(N) for already sorted set
     */
    public SortedSet(ISet<E> other) {
        setTest(other);
        con = new ArrayList<>();
        for (E item : other) {
            con.add(item);
        }
        if (!(other instanceof SortedSet<?>)) {
            ArrayList<E> temp = new ArrayList<>(con);
            mergeSort(temp, 0, con.size() - 1);
        }
    }

    /**
     * pre: temp != null, 0 <= low, high < temp.size()
     * if low >= high, then the range doesn't need any sorting
     * post: sorts the unSortedSet
     * O(N log N)
     * Merge sort adapted from class faster-sorting slides
     */
    private void mergeSort(ArrayList<E> temp, int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergeSort(temp, low, middle);
            mergeSort(temp, middle + 1, high);
            merge(temp, low, middle + 1, high);
        }
    }

    /**
     * pre: temp != null, 0 <= left <= right, left <= right <= rightEnd
     * post: merges the values together for each half
     * O(N), where N is the combined length of the two ranges
     * Merge adapted from slide 14 of Topic17FastSorting
     */
    private void merge(ArrayList<E> temp, int left, int right, int rightEnd) {
        int start = left;
        int leftEnd = right - 1;
        int dest = left;
        while (left <= leftEnd && right <= rightEnd) {
            if (con.get(left).compareTo(con.get(right)) <= 0) {
                temp.set(dest, con.get(left++));
            } else {
                temp.set(dest, con.get(right++));
            }
            dest++;
        }
        while (left <= leftEnd) {
            temp.set(dest++, con.get(left++));
        }
        while (right <= rightEnd) {
            temp.set(dest++, con.get(right++));
        }
        for (int i = start; i <= rightEnd; i++) {
            con.set(i, temp.get(i));
        }
    }

    /**
     * <br> pre: size() != 0
     * post: return the smallest element in this SortedSet
     * @return the smallest element in this SortedSet.
     * O(1)
     */
    public E min() {
        if (con.isEmpty()) {
            throw new IllegalArgumentException("Cannot have empty set.");
        }
        return con.get(0);
    }

    /**
     * <br> pre: size() != 0
     * post: return the largest element in this SortedSet
     * @return the largest element in this SortedSet.
     * O(1)
     */
    public E max() {
        if (con.isEmpty()) {
            throw new IllegalArgumentException("Cannot have empty set.");
        }
        return con.get(con.size() - 1);
    }

    /**
     * Add an item to this set.
     * <br> pre: item != null
     * @param item the item to be added to this set. item may not equal null.
     * post: true if this set changed as a result of this operation,
     * false otherwise.
     * O(N)
     */
    @Override
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        int position = lowerBound(item);
        if (position < con.size() && con.get(position).compareTo(item) == 0) {
            return false;
        }
        con.add(position, item);
        return true;
    }

    /**
     * pre: item != null
     * post: returns first index whose element is not less
     * than item, or the set size if none exists
     * O(log(N))
     */
    private int lowerBound(E item) {
        int low = 0;
        int high = con.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (con.get(mid).compareTo(item) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * A union operation. Add all items of other that
     * are not already present in this set to this set.
     * pre: other != null
     * @param other != null
     * post: true if this set changed as a result of this operation,
     * false otherwise.
     * O(N + M), if other set is sorted
     * O(N + MlogM) if not sorted.
     */
    @Override
    public boolean addAll(ISet<E> other) {
        setTest(other);
        int oldSize = con.size();
        SortedSet<E> combined = (SortedSet<E>) union(other);
        if (combined.size() == oldSize) {
            return false;
        }
        con = combined.con;
        return true;
    }

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     * O(N)
     */
    public void clear() {
        con.clear();
    }

    /**
     * Determine if item is in this set.
     * <br>pre: item != null
     * @param item element whose presence is being tested.
     * Item may not equal null.
     * post: true if this set contains the specified item, false otherwise.
     * O(log(N + 1))
     */
    @Override
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        int position = lowerBound(item);
        return position < con.size() && con.get(position).compareTo(item) == 0;
    }

    /**
     * pre: other != null
     * post: returns existing sortedSet directly, otherwise returns a sorted copy
     * O(1) if sorted
     * O(N log N) if not
     */
    private SortedSet<E> sortedVersion(ISet<E> other) {
        setTest(other);
        if (other instanceof SortedSet<?>) {
            return (SortedSet<E>) other;
        }
        return new SortedSet<>(other);
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * post: true if this set contains all of the elements in otherSet,
     * false otherwise.
     * O(N + M) if otherSet is sorted
     * O(N + MlogM) if not sorted
     */
    public boolean containsAll(ISet<E> otherSet) {
        setTest(otherSet);
        if (otherSet.size() > size()) {
            return false;
        }
        SortedSet<E> other = sortedVersion(otherSet);
        int left = 0;
        int right = 0;
        while (left < con.size() && right < other.con.size()) {
            int comparison = con.get(left).compareTo(other.con.get(right));
            if (comparison > 0) {
                return false;
            } else if (comparison == 0) {
                right++;
            }
            left++;
        }
        return right == other.con.size();
    }

    /**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or other are altered as a result of this operation.
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     * O(N + M) if otherSet is sorted
     * O(N + MlogM) if not sorted
     * Merge adapted from class slides
     */
    @Override
    public ISet<E> union(ISet<E> otherSet) {
        setTest(otherSet);
        SortedSet<E> other = sortedVersion(otherSet);
        SortedSet<E> result = new SortedSet<>();
        int left = 0, right = 0;
        while (left < con.size() && right < other.con.size()) {
            int comparison = con.get(left).compareTo(other.con.get(right));
            if (comparison <= 0) {
                result.con.add(con.get(left++));
                if (comparison == 0) {
                    right++;
                }
            } else {
                result.con.add(other.con.get(right++));
            }
        }
        while (left < con.size()) {
            result.con.add(con.get(left++));
        }
        while (right < other.con.size()) {
            result.con.add(other.con.get(right++));
        }
        return result;
    }

    /**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set
     * and otherSet.
     * Neither this set or other are altered as a result of this operation.
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     * O(N + M) if otherSet is sorted
     * O(N + MlogM) if not sorted
     * Merge adapted from class slides
     */
    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        setTest(otherSet);
        SortedSet<E> other = sortedVersion(otherSet);
        SortedSet<E> result = new SortedSet<>();
        int left = 0;
        int right = 0;
        while (left < con.size() && right < other.con.size()) {
            int comparison = con.get(left).compareTo(other.con.get(right));
            if (comparison < 0) {
                left++;
            } else if (comparison > 0) {
                right++;
            } else {
                result.con.add(con.get(left));
                left++;
                right++;
            }
        }
        return result;
    }

    /**
     * Create a new set that is the difference of this set and otherSet.
     * Return an ISet of elements that are in this Set but not in otherSet.
     * Also called the relative complement.
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z]
     * then A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W].
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or other are altered as a result of this operation.
     * @param otherSet != null
     * @return a set that is the difference of this set and other
     * O(N + M) if otherSet is sorted
     * O(N + MlogM) if not sorted
     * Merge adapted from class slides
     */
    @Override
    public ISet<E> difference(ISet<E> otherSet) {
        setTest(otherSet);
        SortedSet<E> other = sortedVersion(otherSet);
        SortedSet<E> result = new SortedSet<>();
        int left = 0;
        int right = 0;
        while (left < con.size() && right < other.con.size()) {
            int comparison = con.get(left).compareTo(other.con.get(right));
            if (comparison < 0) {
                result.con.add(con.get(left));
                left++;
            } else if (comparison > 0) {
                right++;
            } else {
                left++;
                right++;
            }
        }
        while (left < con.size()) {
            result.con.add(con.get(left));
            left++;
        }
        return result;
    }

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set
     * post: true if other is a Set and has the same elements as this set
     * O(N) for another SortedSet
     * O(NM) for another ISet
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ISet<?>)) {
            return false;
        }
        ISet<?> otherSet = (ISet<?>) other;
        if (size() != otherSet.size()) {
            return false;
        }
        if (other instanceof SortedSet<?>) {
            SortedSet<?> sorted = (SortedSet<?>) other;
            for (int i = 0; i < con.size(); i++) {
                if (!con.get(i).equals(sorted.con.get(i))) {
                    return false;
                }
            }
            return true;
        }
        for (Object item : otherSet) {
            if (!con.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * post: an Iterator object for the elements of this set
     * O(1)
     */
    @Override
    public Iterator<E> iterator() {
        return con.iterator();
    }


    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * post: true if this set changed as a result of this operation,
     * false otherwise
     * O(N)
     */
    @Override
    public boolean remove(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        int position = lowerBound(item);
        if (position == con.size() || con.get(position).compareTo(item) != 0) {
            return false;
        }
        con.remove(position);
        return true;
    }

    /**
     * Return the number of elements of this set.
     * pre: none
     * post: the number of items in this set
     * O(1)
     */
    public int size() {
        return con.size();
    }

    /**
     * pre: none
     * post: throws IllegalArgumentException if other is null
     * O(1)
     */
    private void setTest(ISet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Set cannot be null");
        }
    }
}
