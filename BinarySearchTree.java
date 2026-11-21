/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Hasher, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: hmh3233
 *  email address: hmh3233@my.utexas.edu
 *  TA name: Jason Oh
 *  Number of slip days I am using: 0
 */

import java.util.List;
import java.util.ArrayList;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    private int size;

    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     *  This code came from the lecture
     *  @throws IllegalArgumentException if value is null
     */
    public boolean add(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        int originalSize = size;
        root = addHelp(value, root);
        return originalSize != size;
    }

    /**
     * pre: none
     * post: adds a new value to the tree if no duplicate of val
     * This code came from lecture
     */
    private BSTNode<E> addHelp(E val, BSTNode<E> n) {
        if (n == null) {
            size++;
            return new BSTNode<>(val);
        }
        int dir = val.compareTo(n.data);
        if (dir < 0) {
            n.left = addHelp(val, n.left);
        } else if (dir > 0) {
            n.right = addHelp(val, n.right);
        }
        return n;
    }

    /**
     *  Remove a specified item from this Binary Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     *  This code came from lecture
     *  @throws IllegalArgumentException if value is null
     */
    public boolean remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        int originalSize = size;
        root = removeHelp(value, root);
        return originalSize != size;
    }

    /**
     * pre: none
     * post: removes a value from the tree if val exists
     * This code came from lecture
     */
    private BSTNode<E> removeHelp(E val, BSTNode<E> n) {
        if (n == null) {
            return null;
        }
        int dir = val.compareTo(n.data);
        if (dir < 0) {
            n.left = removeHelp(val, n.left);
        } else if (dir > 0) {
            n.right = removeHelp(val, n.right);
        } else {
            size--;
            if (n.left == null && n.right == null) {
                n = null;
            } else if (n.right == null) {
                n = n.left;
            } else if (n.left == null) {
                n = n.right;
            } else {
                n.data = maxHelp(n.left);
                n.left = removeHelp(n.data, n.left);
                // the recursive call also decreases size, so compensate
                // so only the originally requested removal counts.
                size++;
            }
        }
        return n;
    }

    /**
     * pre: n != null
     * post: finds the max of the subtree starting from n
     * This code came from lecture
     */
    private E maxHelp(BSTNode<E> n) {
        while (n.right != null) {
            n = n.right;
        }
        return n.data;
    }


    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @return true if value is present in this tree, false otherwise
     *  @throws IllegalArgumentException if value is null
     */
    public boolean isPresent(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        return presentHelper(value, root);
    }

    /**
     * pre: none
     * post: returns true if a specific value is in the tree, false otherwise
     */
    private boolean presentHelper(E value, BSTNode<E> n) {
        if (n == null) {
            return false;
        }
        int dir = value.compareTo(n.data);
        if (dir < 0) {
            return presentHelper(value, n.left);
        } else if (dir > 0) {
            return presentHelper(value, n.right);
        }
        return true;
    }


    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     *  Return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @return the height of this tree or -1 if the tree is empty
     *  This code came from lecture
     */
    public int height() {
        return heightHelp(root);
    }

    /**
     * pre: none
     * post: returns the height of the tree
     * This code came from lecture
     */
    private int heightHelp(BSTNode<E> n) {
        if (n == null) {
            return -1;
        }
        return 1 + Math.max(heightHelp(n.left), heightHelp(n.right));
    }

    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order.
     *  If the tree is empty return an empty List
     *  @return a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {
        List<E> result = new ArrayList<>();
        getAllHelper(result, root);
        return result;
    }

    /**
     * pre: none
     * post: adds all of the values of the tree into the list
     */
    private void getAllHelper(List<E> result, BSTNode<E> n) {
        if (n != null) {
            getAllHelper(result, n.left);
            result.add(n.data);
            getAllHelper(result, n.right);
        }
    }



    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     * @throws IllegalArgumentException if tree is empty
     */
    public E max() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree cannot be empty.");
        }
        return maxHelp(root);
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     * @throws IllegalArgumentException if tree is empty
     */
    public E min() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree cannot be empty.");
        }
        return minHelper(root);
    }

    /**
     * pre: none
     * post: returns the min value of the tree
     */
    private E minHelper(BSTNode<E> n) {
        if (n == null) {
            return null;
        }
        if (n.left == null) {
            return n.data;
        }
        return minHelper(n.left);
    }

    /**
     * An add method that implements the add algorithm iteratively
     * instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree,
     * otherwise do nothing.
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add,
     * false otherwise.
     * @throws IllegalArgumentException if data is null
     */
    public boolean iterativeAdd(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            root = new BSTNode<>(data);
        } else if (!iterativeAddHelper(data)) {
            return false;
        }
        size++;
        return true;
    }

    /**
     * pre: none
     * post: returns true if no duplicate of the data is in the tree and
     * it successfully adds the data to the tree, false otherwise
     */
    private boolean iterativeAddHelper(E data) {
        BSTNode<E> n = root;
        while (true) {
            int dir = data.compareTo(n.data);
            if (dir == 0) {
                return false;
            }
            if (dir < 0) {
                if (n.left == null) {
                    n.left = new BSTNode<>(data);
                    return true;
                }
                n = n.left;
            } else {
                if (n.right == null) {
                    n.right = new BSTNode<>(data);
                    return true;
                }
                n = n.right;
            }
        }
    }


    /**
     * <br>pre: 0 <= kth < size()
     * post: Return the "kth" element in this Binary Search Tree. If
     * kth = 0 the smallest value (minimum) is returned. If kth = 1
     * the second smallest value is returned, and so forth.
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     * @throws IllegalArgumentException if kth < 0 or kth >= size
     */
    public E get(int kth) {
        if (kth < 0 || kth >= size) {
            throw new IllegalArgumentException("kth is out of bounds");
        }
        // Share the visited element count across the recursive calls
        return getHelper(root, kth, new int[1]);
    }

    /**
     * pre: count != null, count.length >= 1, count[0]
     * is the number of elements already visited in ascending order
     * post: returns the element at index kth if found in the subtree
     * rooted at n, otherwise returns null
     */
    private E getHelper(BSTNode<E> n, int kth, int[] count) {
        if (n == null) {
            return null;
        }
        E result = getHelper(n.left, kth, count);
        if (result != null) {
            return result;
        }
        if (count[0] == kth) {
            return n.data;
        }
        count[0]++;
        // updates count[0] as elements are visited
        return getHelper(n.right, kth, count);
    }


    /**
     * <tt>pre: value</tt> != null<br>
     * post: Return a List with all values in this Binary Search Tree
     * that are less than the parameter <tt>value</tt>.
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than
     * the parameter value. If there are no values in this tree less
     * than value return an empty list. The elements of the list are
     * in ascending order.
     * @throws IllegalArgumentException if value is null
     */
    public List<E> getAllLessThan(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        List<E> result = new ArrayList<>();
        getAllLessThanHelper(result, root, value);
        return result;
    }

    /**
     * pre: result != null, value != null
     * post: adds all values less than value from the subtree n to
     * result in ascending order
     */
    private void getAllLessThanHelper(List<E> result, BSTNode<E> n, E value) {
        if (n != null) {
            getAllLessThanHelper(result, n.left, value);
            if (n.data.compareTo(value) < 0) {
                result.add(n.data);
                getAllLessThanHelper(result, n.right, value);
            }
        }
    }


    /**
     * <tt>pre: value</tt> != null<br>
     * post: Return a List with all values in this Binary Search Tree
     * that are greater than the parameter <tt>value</tt>.
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater
     *  than the parameter value. If there are no values in this tree
     * greater than value return an empty list.
     * The elements of the list are in ascending order.
     * @throws IllegalArgumentException if value is null
     */
    public List<E> getAllGreaterThan(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        List<E> result = new ArrayList<>();
        getAllGreaterThanHelper(result, root, value);
        return result;
    }

    /**
     * pre: none
     * post: returns a list of all of the elements in the tree greater than value
     */
    private void getAllGreaterThanHelper(List<E> result, BSTNode<E> n, E value) {
        if (n != null) {
            if (n.data.compareTo(value) > 0) {
                getAllGreaterThanHelper(result, n.left, value);
                result.add(n.data);
            }
            getAllGreaterThanHelper(result, n.right, value);
        }
    }



    /**
     * <br>pre: none
     * post: Return the number of nodes in this tree at the specified depth.
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        return numNodesAtDepthHelper(root, d);
    }

    /**
     * pre: none
     * post: counts nodes at depth d form node n, returns 0
     * for an empty subtree or negative depth
     */
    private int numNodesAtDepthHelper(BSTNode<E> n, int d) {
        if (n == null || d < 0) {
            return 0;
        }
        if (d == 0) {
            return 1;
        }
        return numNodesAtDepthHelper(n.left, d - 1) + numNodesAtDepthHelper(n.right, d - 1);
    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     * post: prints a vertical representation of the binary tree
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if (n != null) {
            printTree(n.right, spaces + "   ");
            System.out.println(spaces + n.data);
            printTree(n.left, spaces + "   ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        private BSTNode() {
            this(null);
        }

        private BSTNode(E initValue) {
            this(null, initValue, null);
        }

        private BSTNode(BSTNode<E> initLeft,
                E initValue,
                BSTNode<E> initRight) {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
