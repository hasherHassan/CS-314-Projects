/*
 * Student information for assignment:
 * On my honor, Hasher, this programming assignment is my own work
 * and I have not provided this code to any other student.
 * UTEID: hmh3233
 * email address: hmh3233@my.utexas.edu
 * Number of slip days I am using: 0
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LL314<E> implements IList<E> {

    private DoubleListNode<E> first;

    private DoubleListNode<E> last;

    private int size;

    /*
     * Creates an empty list
     * pre: none
     * post: creates a new empty list
     * O(1)
     */
    public LL314() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Returns the size of the list
     * pre: none
     * post: returns size
     * O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Add item to the front of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(0) = item
     * O(1)
     *
     * @param item the data to add to the front of this list
     * @throws IllegalArgumentException if item is null
     */
    public void addFirst(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        DoubleListNode<E> newFirstNode = new DoubleListNode<>(null, item, first);
        if (size == 0) {
            last = newFirstNode;
        } else {
            first.prev = newFirstNode;
        }
        first = newFirstNode;
        size++;
    }

    /**
     * Add an item to the end of this list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(size() - 1) = item
     * @param item the data to be added to the end of this list,
     * O(1)
     * item != null
     * @throws IllegalArgumentException if item is null
     */
    public void add (E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (size == 0) {
            DoubleListNode<E> newNode = new DoubleListNode<>(first, item, null);
            first = newNode;
            last = newNode;
        } else {
            DoubleListNode<E> newNode = new DoubleListNode<>(last, item, null);
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Remove and return the first element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     * O(1)
     *
     * @return the old first element of this list
     * @throws IllegalArgumentException if list is empty
     */
    public E removeFirst() {
        if (size == 0) {
            throw new IllegalArgumentException("List is empty");
        }
        E oldData = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return oldData;
    }

    /**
     * Remove and return the last element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     * O(1)
     *
     * @return the old last element of this list
     * @throws IllegalArgumentException if list is empty
     */
    public E removeLast() {
        if (size == 0) {
            throw new IllegalArgumentException("List is empty");
        }
        E oldData = last.data;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return oldData;
    }

    /**
     * Return a String version of this list enclosed in
     * square brackets, []. Elements are in
     * are in order based on position in the
     * list with the first element
     * first. Adjacent elements are separated by comma's
     * @return a String representation of this IList
     * pre: none
     * post: returns a string version of the LL314
     * O(N)
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[").append(first.data);
        DoubleListNode<E> node = first.next;
        while (node != null) {
            sb.append(", ").append(node.data);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return the Node at the given position
     * <br>pre: 0 <= pos < size()
     * <br>post: return the node at the inputted position
     * O(N) worst case
     * @throws IllegalArgumentException if pos < 0 or pos >= size
     */
    private DoubleListNode<E> getNode (int pos) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        // Traverse from beginning of list if closer to beginning
        if (pos < size / 2) {
            int index = 0;
            DoubleListNode<E> node = first;
            while (index < pos) {
                node = node.next;
                index++;
            }
            return node;
        }
        // Traverse from end of list if closer to end
        int index = size - 1;
        DoubleListNode<E> node = last;
        while (pos < index) {
            node = node.prev;
            index--;
        }
        return node;
    }

    /**
     * Return the data of the node at the given position
     * <br>pre: 0 <= pos < size
     * <br>post: return the data of the node at the inputted position
     * O(N)
     * @throws IllegalArgumentException if pos < 0 or pos >= size
     */
    @Override
    public E get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        return getNode(pos).data;
    }

    /**
     * Change the data at the specified position in the list.
     * the old data at that position is returned.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: get(pos) = item, return the
     * old get(pos)
     * @param pos the position in the list to overwrite
     * @param item the new item that will overwrite the old item,
     * item != null
     * @return the old data at the specified position
     * O(N)
     * @throws IllegalArgumentException if pos < 0 or pos >= size or if item is null
     */
    @Override
    public E set(int pos, E item) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Invalid index");
        } else if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        DoubleListNode<E> node = getNode(pos);
        E data = node.data;
        node.data = item;
        return data;
    }

    /**
     * Insert an item at a specified position in the list.
     * <br>pre: 0 <= pos <= size(), item != null
     * <br>post: size() = old size() + 1, get(pos) = item,
     * all elements in the list with a positon >= pos have a
     * position = old position + 1
     * @param pos the position to insert the data at in the list
     * @param item the data to add to the list, item != null
     * O(N)
     * @throws IllegalArgumentException if pos < 0 or pos > size or if item is null
     */
    @Override
    public void insert(int pos, E item) {
        if (pos < 0 || pos > size) {
            throw new IllegalArgumentException("Invalid index");
        } else if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (pos == size) {
            add(item);
        } else if (pos == 0) {
            addFirst(item);
        } else {
            DoubleListNode<E> node = getNode(pos);
            DoubleListNode<E> newNode = new DoubleListNode<>(node.prev, item, node);
            node.prev.next = newNode;
            node.prev = newNode;
            size++;
        }
    }

    /**
     * Remove an element in the list based on position.
     * <br>pre: 0 <= pos < size()
     * <br>post: size() = old size() - 1, all elements of
     * list with a position > pos have a position = old position - 1
     * @param pos the position of the element to remove from the list
     * @return the data at position pos
     * O(N)
     * @throws IllegalArgumentException if pos < 0 or pos >= size 
     */
    @Override
    public E remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (pos == 0) {
            return removeFirst();
        } else if (pos == size - 1) {
            return removeLast();
        } else {
            DoubleListNode<E> node = getNode(pos);
            E oldVal = node.data;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
            return oldVal;
        }
    }

    /**
     * Remove the first occurrence of obj in this list.
     * Return <tt>true</tt> if this list changed
     * as a result of this call, <tt>false</tt> otherwise.
     * <br>pre: obj != null
     * <br>post: if obj is in this list the first occurrence
     * has been removed and size() = old size() - 1.
     * If obj is not present the list is not altered in any way.
     * @param obj The item to remove from this list. obj != null
     * @return Return <tt>true</tt> if this list changed
     * as a result of this call, <tt>false</tt> otherwise.
     * O(N)
     * @throws IllegalArgumentException if object is null
     */
    @Override
    public boolean remove(E obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(obj)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Find the position of an element in the list.
     * <br>pre: item != null
     * <br>post: return the index of the first element equal to item
     * or -1 if item is not present
     * @param item the element to search for in the list. item != null
     * @return return the index of the first element equal to item
     * or a -1 if item is not present
     * O(N)
     * @throws IllegalArgumentException if item is null
     */
    @Override
    public int indexOf(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        int index = 0;
        DoubleListNode<E> node = first;
        while (node != null) {
            if (node.data.equals(item)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    /**
     * Find the position of an element in the list starting
     * at a specified position.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: return the index of the first element equal
     * to item starting at pos
     * or -1 if item is not present from position pos onward
     * @param item the element to search for in the list. Item != null
     * @param pos the position in the list to start searching from
     * @return starting from the specified position
     * return the index of the first element equal to item
     * or a -1 if item is not present between pos
     * and the end of the list
     * O(N)
     * @throws IllegalArgumentException if pos < 0 or pos >= size or if item is null
     */
    @Override
    public int indexOf(E item, int pos) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Invalid index");
        } else if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        int index = pos;
        DoubleListNode<E> node = getNode(pos);
        while (node != null) {
            if (node.data.equals(item)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    /**
     * Return a sublist of elements in this list
     * from <tt>start</tt> inclusive to <tt>stop</tt> exclusive.
     * This list is not changed as a result of this call.
     * <br>pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * <br>post: return a list whose size is stop - start
     * and contains the elements at positions start through stop - 1
     * in this list.
     * @param start index of the first element of the sublist.
     * @param stop stop - 1 is the index of the last element
     * of the sublist.
     * @return a list with <tt>stop - start</tt> elements,
     * The elements are from positions <tt>start</tt> inclusive to
     * <tt>stop</tt> exclusive in this list.
     * If start == stop an empty list is returned.
     * O(N)
     * @throws IllegalArgumentException if start < 0, start > size,stop < start,
     * or stop > size
     */
    @Override
    public IList<E> getSubList(int start, int stop) {
        if (start < 0 || start > size) {
            throw new IllegalArgumentException("Invalid start index");
        } else if (stop < start || stop > size) {
            throw new IllegalArgumentException("Invalid end index");
        }
        LL314<E> list = new LL314<>();
        if (start == stop) {
            return list;
        } else {
            DoubleListNode<E> node = getNode(start);
            while (start < stop) {
                list.add(node.data);
                node = node.next;
                start++;
            }
        }
        return list;
    }

    /**
     * Remove all elements in this list from <tt>start</tt>
     * inclusive to <tt>stop</tt> exclusive.
     * <br>pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * <br>post: <tt>size() = old size() - (stop - start)</tt>
     * @param start position at beginning of range of elements
     * to be removed
     * @param stop stop - 1 is the position at the end
     * of the range of elements to be removed
     * O(N)
     * @throws IllegalArgumentException if start < 0, start > size,stop < start,
     * or stop > size
     */
    @Override
    public void removeRange(int start, int stop) {
        if (start < 0 || start > size) {
            throw new IllegalArgumentException("Invalid start index");
        } else if (stop < start || stop > size) {
            throw new IllegalArgumentException("Invalid end index");
        }
        if (start < stop) {
            DoubleListNode<E> before = start == 0 ? null : getNode(start - 1);
            DoubleListNode<E> after = stop == size ? null : getNode(stop);
            if (before == null) {
                first = after;
            } else {
                before.next = after;
            }
            if (after == null) {
                last = before;
            } else {
                after.prev = before;
            }
            size -= stop - start;
        }
    }

    /**
     * Return the list to an empty state.
     * <br>pre: none
     * <br>post: size() = 0
     * O(1)
     */
    @Override
    public void makeEmpty() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Determine if this IList is equal to other. Two
     * ILists are equal if they contain the same elements
     * in the same order.
     * pre: none
     * post: returns if the object is equal to the current IList
     * @return true if this IList is equal to other, false otherwise
     * O(N)
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IList<?>)) {
            return false;
        }
        IList<?> otherList = (IList<?>) other;
        if (otherList.size() != size) {
            return false;
        }
        DoubleListNode<E> node = first;
        Iterator<?> otherIterator = otherList.iterator();
        while (node != null) {
            if (!node.data.equals(otherIterator.next())) {
                return false;
            }
            node = node.next;
        }
        return true;
    }

    /**
     * Return an iterator for this list
     * pre: none
     * post: returns an iterator for the list
     * O(1)
     */
    public Iterator<E> iterator() {
        return new LLIterator();
    }

    private class LLIterator implements Iterator<E> {
        private boolean removeOk;
        private DoubleListNode<E> nodeWithNext;
        private DoubleListNode<E> lastReturned;

        /**
         * pre: none
         * post: nodeWithNext = first, lastReturned = null, removeOk = false;
         * O(1)
         */
        private LLIterator() {
            nodeWithNext = first;
            lastReturned = null;
        }

        /**
         * pre: none
         * post: returns true if another element remains; iterator is unchanged
         * O(1)
         */
        public boolean hasNext() {
            return nodeWithNext != null;
        }

        /**
         * pre: hasNext()
         * post: returns the next element and allows for the calling of the remove method
         * O(1)
         * @throws NoSuchElementException if no element remains
         */
        public E next() {
            if (nodeWithNext == null) {
                throw new NoSuchElementException("No node remains");
            }
            lastReturned = nodeWithNext;
            E oldVal = lastReturned.data;
            nodeWithNext = nodeWithNext.next;
            removeOk = true;
            return oldVal;
        }

        /**
         * pre: next() has successfully returned an element that the iterator has not removed yet
         * post: the element is removed, size = oldsize - 1, and another next() is needed for 
         * remove to be able to be called again
         * O(1)
         * @throws IllegalStateException if next() has not successfully returned an element or 
         * that element has already been removed
         */
        public void remove() {
            if (!removeOk) {
                throw new IllegalStateException("Must call next before calling remove");
            }
            if (lastReturned.prev == null) {
                first = lastReturned.next;
            } else {
                lastReturned.prev.next = lastReturned.next;
            }

            if (lastReturned.next == null) {
                last = lastReturned.prev;
            } else {
                lastReturned.next.prev = lastReturned.prev;
            }

            size--;
            removeOk = false;
            lastReturned = null;
        }
    }

    /**
     * A class that represents a node to be used in a linked list.
     * These nodes are doubly linked. All methods are O(1).
     *
     * @author Mike Scott
     * @version 9/25/2023
     */

    private static class DoubleListNode<E> {

        // the data to store in this node
        private E data;

        // the link to the next node (presumably in a list)
        private DoubleListNode<E> next;

        // the reference to the previous node (presumably in a list)
        private DoubleListNode<E> prev;

        /**
         * default constructor.
         * <br>pre: none
         * <br>post: data = null, next = null, prev = null
         * <br>O(1)
         */
        public DoubleListNode() {
            this(null, null, null);
        }

        /**
         * create a DoubleListNode that holds the specified data
         * and refers to the specified next and previous elements.
         * <br>pre: none
         * <br>post: this.data = data, this.next = next, this.prev = prev
         * <br>O(1)
         * @param prev the previous node
         * @param data the  data this DoubleListNode should hold
         * @param next the next node
         */
        public DoubleListNode(DoubleListNode<E> prev, E data, DoubleListNode<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}
