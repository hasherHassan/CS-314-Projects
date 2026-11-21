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

public abstract class AbstractSet314<E> implements ISet<E> {
    /**
     * pre: item != null
     * post: add an item, return true if set changes, false otherwise
     */
    @Override
    public abstract boolean add(E item);

    /**
     * pre: otherSet != null
     * post: add elements of otherSet, return true if set changes, false otherwise
     */
    @Override
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Set cannot be null");
        }
        if (otherSet == this) {
            return false;
        }
        boolean isChanged  = false;
        for (E item : otherSet) {
            if (add(item)) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    /**
     * pre: none
     * post: removes all elements from the set
     */
    public void clear() {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /**
     * pre: item != null
     * post: return whether item is present
     */
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (E value : this) {
            if (value.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * pre: otherSet != null
     * post: returns whether every element of otherSet is present
     */
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Set cannot be null");
        }
        for (E item : otherSet) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * pre: otherSet != null
     * post: return a new set containing elements in this set but not otherSet
     */
    public abstract ISet<E> difference(ISet<E> otherSet);

    /**
     * pre: none
     * post: returns true when other is an ISet contining exactly same elements,
     * false otherwise
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ISet<?>)) {
            return false;
        }
        ISet<?> otherSet = (ISet<?>) other;
        if (size() != otherSet.size()) {
            return false;
        }
        for (E item : this) {
            boolean found = false;
            Iterator<?> it = otherSet.iterator();
            while (!found && it.hasNext()) {
                found = item.equals(it.next());
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    /**
     * pre: otherSet != null
     * post: returnsa new set containing elements in both sets
     */
    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Set cannot be null");
        }
        ISet<E> result = difference(otherSet);
        return difference(result);
    }

    /**
     * pre: none
     * post: returns an iterator over the elements of this set
     */
    @Override
    public abstract Iterator<E> iterator();

    /**
     * pre: item != null
     * post: remove item, return whether the set changed
     */
    public boolean remove(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(item)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * pre: none
     * post: returns size
     */
    @Override
    public int size() {
        int count = 0;
        for (E item : this) {
            count++;
        }
        return count;
    }

    /**
     * pre: otherSet != null
     * post: return a new set containing elements in either set,
     * without any duplicates
     */
    @Override
    public abstract ISet<E> union(ISet<E> otherSet);

    /**
     * Return a String version of this set.
     * Format is (e1, e2, ... en)
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}
