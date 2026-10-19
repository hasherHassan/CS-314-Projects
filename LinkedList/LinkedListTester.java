/*  Student information for assignment:
 *
 *  On my honor, Hasher, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Hasher
 *  email address: hmh3233@my.utexas.edu
 *  UTEID: hmh3233
 *  Number of slip days used on this assignment: 0
 */

/* 
 * Add at end              ArrayList     30000, 60000, 120000, 240000, 480000
 *                                       0.0839, 0.8897, 0.9518, 1.8272, 4.2637
 *                         LL314         30000, 60000, 120000, 240000, 480000
 *                                       0.1793, 0.1584, 0.5422, 0.6758, 1.5542
 *
 * Add at front            ArrayList     2000, 4000, 8000, 16000, 32000
 *                                       0.0311, 0.1087, 0.3691, 1.9680, 8.4653
 *                         LL314         10000, 20000, 40000, 80000, 160000
 *                                       0.0201, 0.0360, 0.0596, 0.1183, 0.2656
 *
 * Remove from front       ArrayList     2000, 4000, 8000, 16000, 32000
 *                                       0.0259, 0.0916, 0.3486, 1.8486, 7.9810
 *                         LL314         5000, 10000, 20000, 40000, 80000
 *                                       0.0058, 0.0117, 0.0229, 0.0484, 0.1017
 *
 * Get random element      ArrayList     10000, 20000, 40000, 80000, 160000
 *                                       0.0229, 0.0581, 0.1223, 0.3360, 0.8831
 *                         LL314         1000, 2000, 4000, 8000, 16000
 *                                       0.0508, 0.1984, 0.8405, 3.5014, 15.5264
 *
 * Get all using iterator  ArrayList     50000, 100000, 200000, 400000, 800000
 *                                       0.0247, 0.0327, 0.0515, 0.0924, 0.1566
 *                         LL314         50000, 100000, 200000, 400000, 800000
 *                                       0.0302, 0.0654, 0.1157, 0.2168, 0.3895
 *
 * Get all using get       ArrayList     100000, 200000, 400000, 800000, 1600000
 *                                       0.0204, 0.0729, 0.0843, 0.1732, 0.4175
 *                         LL314         1000, 2000, 4000, 8000, 16000
 *                                       0.0749, 0.2152, 1.1428, 4.5876, 20.6226
 * All the timings are in seconds, and the average time is the total time / N.
 * Then, the average time per operation is the total time / (100 * N).
 * Timings are totals across 100 repetitions.
 *
 * LL314 was faster when adding or removing at the front, even though it used a
 * greater N amount of elements. LL314 was also faster at adding at the end of
 * 4/5 of the tested sizes, since at N = 480000, LL314 took 1.5542 seconds, which
 * is faster compared to the ArrayList time of 4.2637 seconds. However, ArrayList
 * was faster for random access and getting all elements using get even with much
 * larger lists than used of LL314. ArrayList was also faster when getting all
 * elements using an Iterator at every tested size. Both have the same expected
 * grwoth for adding at the end and traversing with an iterator, as they are both
 * O(N) for N operations.
 *
 * Big O for N operations and 1 operation
 *                          ArrayList (N, 1) LL314 (N, 1)
 * Add at end               O(N), O(1)       O(N), O(1)
 * Add at front             O(N^2), O(N)     O(N), O(1)
 * Remove from front        O(N^2), O(N)     O(N), O(1)
 * Get random element       O(N), O(1)       O(N^2), O(N)
 * Get all using iterator   O(N), O(1)       O(N), O(1)
 * Get all using get        O(N), O(1)       O(N^2), O(N)
 *
 * LL314 adding to the front and removing from the front show around linear growth
 * because they update only a fixed number of references, which is N times. For
 * example, removing from the front increased from 0.0484 to 0.1017 seconds when
 * N doubled from 40000 to 80000. Thus, each individual operation takes O(1) time.
 * However, ArrayList adding to the front and removing from the front show around
 * quadratic growth because each operation has to shift elements in the array.
 * Adding to the front increased from 1.9680 to 8.4653 from 16000 to 32000, which is
 * roughly 4 times, or O(N^2), so a single call is O(N). LL314 get a random element
 * and get all using get show around quadratic growth because finding an element
 * means having to traverse through the nodes. Random access increased from 3.5014 to
 * 15.5264 seconds when N doubled. Getting all using get increased from 4.5876 to 20.6226.
 * ArrayList accesses elements directly by index, so N get calls are expected to take
 * O(N) total time, with each call being O(1). Getting all using iterator shows around
 * linear growth for both because for both, each step goes directly forward to the next
 * element. Adding at the end is also linear growth for both because of the lack of shifting.
 * ArrayList adding at the end is amortized O(1), as resizing is O(N) but gets less frequent
 * as capacity grows.
 */


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

public class LinkedListTester {

    public static void main(String[] args) {
        final int EXPECTED_SIZE = 3;
        final int EXPECTED_SIZE_2 = 4;
        final int STOPPING_INDEX = 3;

        // test 1: constructor
        LL314<String> list = new LL314<>();
        if (list.toString().equals("[]") && list.size() == 0) {
            System.out.println("Passed test 1");
        } else {
            System.out.println("Failed test 1");
        }

        // test 2: constructor
        list = new LL314<>();
        LL314<String> other = new LL314<>();
        list.add("A");
        if (list.toString().equals("[A]") && other.size() == 0
                &&  other.toString().equals("[]")) {
            System.out.println("Passed test 2");
        } else {
            System.out.println("Failed test 2");
        }

        //test 3: add
        list = new LL314<>();
        list.add("A");
        if (list.toString().equals("[A]") && list.size() == 1) {
            System.out.println("Passed test 3");
        } else {
            System.out.println("Failed test 3");
        }

        //test 4: add
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        if (list.toString().equals("[A, B, C]") && list.size() == EXPECTED_SIZE) {
            System.out.println("Passed test 4");
        } else {
            System.out.println("Failed test 4");
        }

        // test 5: addFirst
        list = new LL314<>();
        list.addFirst("A");
        if (list.toString().equals("[A]") && list.size() == 1) {
            System.out.println("Passed test 5");
        } else {
            System.out.println("Failed test 5");
        }

        // test 6: addFirst
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.addFirst("C");
        if (list.toString().equals("[C, A, B]") && list.size() == EXPECTED_SIZE) {
            System.out.println("Passed test 6");
        } else {
            System.out.println("Failed test 6");
        }

        // test 7: removeFirst
        list = new LL314<>();
        list.add("A");
        String removed = list.removeFirst();
        if (removed.equals("A") && list.toString().equals("[]")
                && list.size() == 0) {
            System.out.println("Passed test 7");
        } else {
            System.out.println("Failed test 7");
        }

        // test 8: removeFirst
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        removed = list.removeFirst();
        if (removed.equals("A") && list.toString().equals("[B, C]")
                && list.size() == 2) {
            System.out.println("Passed test 8");
        } else {
            System.out.println("Failed test 8");
        }

        // test 9: removeLast
        list = new LL314<>();
        list.add("A");
        removed = list.removeLast();
        if (removed.equals("A") && list.toString().equals("[]")
                && list.size() == 0) {
            System.out.println("Passed test 9");
        } else {
            System.out.println("Failed test 9");
        }

        // test 10: removeLast
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        removed = list.removeLast();
        if (removed.equals("C") && list.toString().equals("[A, B]")
                && list.size() == 2) {
            System.out.println("Passed test 10");
        } else {
            System.out.println("Failed test 10");
        }

        // test 11: insert
        list = new LL314<>();
        list.insert(0, "A");
        if (list.toString().equals("[A]") && list.size() == 1) {
            System.out.println("Passed test 11");
        } else {
            System.out.println("Failed test 11");
        }

        // test 12: insert
        list = new LL314<>();
        list.add("A");
        list.add("C");
        list.insert(1, "B");
        if (list.toString().equals("[A, B, C]") && list.size() == EXPECTED_SIZE) {
            System.out.println("Passed test 12");
        } else {
            System.out.println("Failed test 12");
        }

        // test 13: get
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        if (list.get(0).equals("A") && list.toString().equals("[A, B, C]")
                && list.size() == EXPECTED_SIZE) {
            System.out.println("Passed test 13");
        } else {
            System.out.println("Failed test 13");
        }

        // test 14: get
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        if (list.get(2).equals("C") && list.toString().equals("[A, B, C]")
                && list.size() == EXPECTED_SIZE) {
            System.out.println("Passed test 14");
        } else {
            System.out.println("Failed test 14");
        }

        // test 15: set
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        removed = list.set(0, "F");
        if (removed.equals("A") && list.toString().equals("[F, B, C]")
                && list.size() == EXPECTED_SIZE) {
            System.out.println("Passed test 15");
        } else {
            System.out.println("Failed test 15");
        }

        // test 16: set
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        removed = list.set(2, "F");
        if (removed.equals("C") && list.toString().equals("[A, B, F]")
                && list.size() == EXPECTED_SIZE) {
            System.out.println("Passed test 16");
        } else {
            System.out.println("Failed test 16");
        }

        // test 17: remove(int a)
        list = new LL314<>();
        list.add("A");
        removed = list.remove(0);
        if (removed.equals("A") && list.toString().equals("[]")
                && list.size() == 0) {
            System.out.println("Passed test 17");
        } else {
            System.out.println("Failed test 17");
        }

        // test 18: remove(int a)
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        removed = list.remove(1);
        if (removed.equals("B") && list.toString().equals("[A, C]")
                && list.size() == 2) {
            System.out.println("Passed test 18");
        } else {
            System.out.println("Failed test 18");
        }

        // test 19: remove(E element)
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("A");
        boolean changed = list.remove("A");
        if (changed && list.toString().equals("[B, A]")
                && list.size() == 2) {
            System.out.println("Passed test 19");
        } else {
            System.out.println("Failed test 19");
        }

        // test 20: remove(E element)
        list = new LL314<>();
        list.add("A");
        list.add("B");
        changed = list.remove("X");
        if (!changed && list.toString().equals("[A, B]")
                && list.size() == 2) {
            System.out.println("Passed test 20");
        } else {
            System.out.println("Failed test 20");
        }

        // test 21: size
        list = new LL314<>();
        if (list.size() == 0) {
            System.out.println("Passed test 21");
        } else {
            System.out.println("Failed test 21");
        }

        // test 22: size
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.remove(0);
        if (list.size() == 2) {
            System.out.println("Passed test 22");
        } else {
            System.out.println("Failed test 22");
        }

        // test 23: indexOf(E item)
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("A");
        if (list.indexOf("A") == 0) {
            System.out.println("Passed test 23");
        } else {
            System.out.println("Failed test 23");
        }

        // test 24: indexOf(E item)
        list = new LL314<>();
        list.add("A");
        list.add("B");
        if (list.indexOf("X") == -1) {
            System.out.println("Passed test 24");
        } else {
            System.out.println("Failed test 24");
        }

        // test 25: indexOf(E item, int index)
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("A");
        if (list.indexOf("A", 1) == 2) {
            System.out.println("Passed test 25");
        } else {
            System.out.println("Failed test 25");
        }

        // test 26: indexOf(E item, int index)
        list = new LL314<>();
        list.add("A");
        list.add("B");
        if (list.indexOf("A", 1) == -1) {
            System.out.println("Passed test 26");
        } else {
            System.out.println("Failed test 26");
        }

        // test 27: getSublist
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        IList<String> subList = list.getSubList(1, 3);
        if (subList.size() == 2 && subList.toString().equals("[B, C]")
                && list.toString().equals("[A, B, C, D]") && list.size() == EXPECTED_SIZE_2) {
            System.out.println("Passed test 27");
        } else {
            System.out.println("Failed test 27");
        }

        // test 28: getSublist
        list = new LL314<>();
        list.add("A");
        list.add("B");
        subList = list.getSubList(2, 2);
        if (subList.size() == 0 && subList.toString().equals("[]")
                && list.toString().equals("[A, B]") && list.size() == 2) {
            System.out.println("Passed test 28");
        } else {
            System.out.println("Failed test 28");
        }

        // test 29: removeRange
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.removeRange(1, 3);
        if (list.toString().equals("[A, D]") && list.size() == 2) {
            System.out.println("Passed test 29");
        } else {
            System.out.println("Failed test 29");
        }

        // test 30: removeRange
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.removeRange(0, 3);
        if (list.toString().equals("[]") && list.size() == 0) {
            System.out.println("Passed test 30");
        } else {
            System.out.println("Failed test 30");
        }

        // test 31: makeEmpty
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.makeEmpty();
        if (list.toString().equals("[]") && list.size() == 0) {
            System.out.println("Passed test 31");
        } else {
            System.out.println("Failed test 31");
        }

        // test 32: makeEmpty
        list = new LL314<>();
        list.makeEmpty();
        if (list.toString().equals("[]")) {
            System.out.println("Passed test 32");
        } else {
            System.out.println("Failed test 32");
        }

        // test 33; toString
        list = new LL314<>();
        if (list.toString().equals("[]")) {
            System.out.println("Passed test 33");
        } else {
            System.out.println("Failed test 33");
        }

        // test 34; toString
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        if (list.toString().equals("[A, B, C]")) {
            System.out.println("Passed test 34");
        } else {
            System.out.println("Failed test 34");
        }

        // test 35: equals
        list = new LL314<>();
        list.add("A");
        list.add("B");
        other = new LL314<>();
        other.add("A");
        other.add("B");
        if (list.equals(other)) {
            System.out.println("Passed test 35");
        } else {
            System.out.println("Failed test 35");
        }

        // test 36: equals
        list = new LL314<>();
        list.add("A");
        list.add("B");
        other = new LL314<>();
        other.add("B");
        other.add("A");
        if (!list.equals(other)) {
            System.out.println("Passed test 36");
        } else {
            System.out.println("Failed test 36");
        }

        // test 37: iterator
        list = new LL314<>();
        Iterator<String> it = list.iterator();
        if (it != null && !it.hasNext()) {
            System.out.println("Passed test 37");
        } else {
            System.out.println("Failed test 37");
        }

        // test 38: iterator
        list = new LL314<>();
        list.add("A");
        list.add("B");
        it = list.iterator();
        Iterator<String> it2 = list.iterator();
        it.next();
        if (it != it2 && it.next().equals("B") && it2.next().equals("A")
                && !it.hasNext() && it2.hasNext()) {
            System.out.println("Passed test 38");
        } else {
            System.out.println("Failed test 38");
        }

        // test 39: hasNext
        list = new LL314<>();
        it = list.iterator();
        if (!it.hasNext()) {
            System.out.println("Passed test 39");
        } else {
            System.out.println("Failed test 39");
        }

        // test 40: hasNext
        list = new LL314<>();
        list.add("A");
        it = list.iterator();
        boolean hadNext = it.hasNext();
        it.next();
        if (hadNext && !it.hasNext()) {
            System.out.println("Passed test 40");
        } else {
            System.out.println("Failed test 40");
        }

        // test 41: next
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        it = list.iterator();
        if (it.next().equals("A") && it.next().equals("B")
                && it.next().equals("C") && !it.hasNext()) {
            System.out.println("Passed test 41");
        } else {
            System.out.println("Failed test 41");
        }

        // test 42: next
        list = new LL314<>();
        list.add("A");
        it = list.iterator();
        if (it.next().equals("A")) {
            System.out.println("Passed test 42");
        } else {
            System.out.println("Failed test 42");
        }

        // test 43: iterator remove
        list = new LL314<>();
        list.add("A");
        list.add("B");
        list.add("C");
        it = list.iterator();
        it.next();
        it.next();
        it.remove();
        if (list.toString().equals("[A, C]") && list.size() == 2
                && it.next().equals("C") && !it.hasNext()) {
            System.out.println("Passed test 43");
        } else {
            System.out.println("Failed test 43");
        }

        // test 44: iterator remove
        list = new LL314<>();
        list.add("A");
        it = list.iterator();
        it.next();
        it.remove();
        if (list.toString().equals("[]") && list.size() == 0
                && !it.hasNext()) {
            System.out.println("Passed test 44");
        } else {
            System.out.println("Failed test 44");
        }

        // comparison();
    }


    private static final int NUM_DOUBLINGS_OF_N = 5;
    private static final int NUM_REPEATS_OF_TEST = 100;

    // A method to be run to compare the LinkedList you are completing and the Java ArrayList class
    private static void comparison() {
        Stopwatch s = new Stopwatch();

        int initialN = 30000;
        addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 10000;
        addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 5000;
        removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 10000;
        getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 50000;
        getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
        getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 100000;
        getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

    }

    // These methods illustrate a failure to use polymorphism.
    // If the students had implemented the Java list interface there
    // could be a single method. Also we could use function objects to
    // reduce the awful repetition of code.
    private static void addEndArrayList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                s.start();
                for (int j = 0; j < n; j++) {
                    javaList.add(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: ArrayList", totalTimes, initialN);
    }

    private static void showResults(String title, double[] times, int initialN) {
        System.out.println();
        System.out.println("Number of times test run: " + NUM_REPEATS_OF_TEST);
        System.out.println(title);
        for (double time : times) {
            System.out.print("N = " + initialN + ", total time: ");
            System.out.printf("%7.4f\n", time);
            initialN *= 2;
        }
        System.out.println();
    }

    private static void addEndLinkedList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LL314<Integer> studentList = new LL314<>();
                s.start();
                for (int j = 0; j < n; j++) {
                    studentList.add(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: LinkedList", totalTimes, initialN);
    }

    private static void addFrontArrayList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                s.start();
                for (int j = 0; j < n; j++) {
                    javaList.add(0, j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: ArrayList", totalTimes, initialN);
    }

    private static void addFrontLinkedList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LL314<Integer> studentList = new LL314<>();
                s.start();
                for (int j = 0; j < n; j++) {
                    studentList.insert(0, j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: LinkedList", totalTimes, initialN);
    }

    private static void removeFrontArrayList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<String> javaList = new ArrayList<>();
                for(int j = 0; j < n; j++)
                    javaList.add(j + "");
                s.start();
                while (!javaList.isEmpty()) {
                    javaList.remove(0);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Removing from front: ArrayList", totalTimes, initialN);
    }

    private static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LL314<String> studentList = new LL314<>();
                for (int j = 0; j < n; j++) {
                    studentList.add(j + "");
                }
                s.start();
                while (studentList.size() != 0) {
                    studentList.removeFirst();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("removing from front: LinkedList", totalTimes, initialN);
    }

    private static void getRandomArrayList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            Random r = new Random();
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    javaList.add(j);
                }
                s.start();
                for (int j = 0; j < n; j++) {
                    total += javaList.get(r.nextInt(javaList.size()));
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: ArrayList", totalTimes, initialN);
    }

    private static void getRandomLinkedList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            Random r = new Random();
            for (int i = 0; i < numTests; i++) {
                LL314<Integer> studentList = new LL314<>();
                for (int j = 0; j < n; j++) {
                    studentList.add(j);
                }
                int total = 0;
                s.start();
                for (int j = 0; j < n; j++) {
                    total += studentList.get(r.nextInt(studentList.size()));
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: LinkedList", totalTimes, initialN);
    }

    private static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests) {

        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    javaList.add(j);
                }
                Iterator<Integer> it = javaList.iterator();
                s.start();
                int total = 0;
                while (it.hasNext()) {
                    total += it.next();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
    }

    private static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LL314<Integer> studentList = new LL314<>();
                for (int j = 0; j < n; j++) {
                    studentList.add(j);
                }
                Iterator<Integer> it = studentList.iterator();
                s.start();
                int total = 0;
                while (it.hasNext()) {
                    total += it.next();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
    }

    private static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    javaList.add(j);
                }
                s.start();
                int x = 0;
                for (int j = 0; j < javaList.size(); j++) {
                    x += javaList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: ArrayList", totalTimes, initialN);
    }

    private static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LL314<Integer> studentList = new LL314<>();
                for (int j = 0; j < n; j++) {
                    studentList.add(j);
                }
                s.start();
                int x = 0;
                for (int j = 0; j < studentList.size(); j++) {
                    x += studentList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: LinkedList", totalTimes, initialN);
    }
}
