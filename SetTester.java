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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
 * Implemeneting all 3 operations in terms of one another could create
 * circular method calls and might cause infinite recursion. The abstract class
 * cannot directly create a an actual like concrete result set without referring
 * to its specific implemenetaiton. Leaving some of the operations abstract
 * lets subclasses construct the rsult sets their way, while other operations
 * can use those implementations. For exampe, intersections uses difference twice,
 * as it does A intersection B  = A difference (A difference B)
 *
 * Experiment Results:
 *
 * Book labels:
 * Book 1: Alice's Adventures in Wonderland
 * Book 2: Frankenstein
 * Book 3: Pride and Prejudice
 * Book 4: War and Peace
 *
 * Sorted Set
 * File    Size (kb)  Size Change Factor Total Words  Increase from Previous Row  Unique Words  Inc. Prev. Row  Actual Time       Inc. Prev. Row
 * Book 1  176        -                  29569        -                           5973          -               0.243318209 sec.  -
 * Book 2  451        2.56x              78106        2.64x                       12178         2.04x           0.279862414 sec.  1.15x
 * Book 3  774        1.72x              130416       1.67x                       14699         1.21x           0.428284008 sec.  1.53x
 * Book 4  3400       4.39x              566333       4.34x                       41967         2.86x           0.990318674 sec.  2.31x
 *
 * Unsorted Set
 * File    Size (kb)  Size Change Factor Total Words  Increase from Previous Row  Unique Words  Inc. Prev. Row  Actual Time        Inc. Prev. Row
 * Book 1  176        -                  29569        -                           5973          -               0.437812653 sec.   -
 * Book 2  451        2.56x              78106        2.64x                       12178         2.04x           1.840740114 sec.   4.20x
 * Book 3  774        1.72x              130416       1.67x                       14699         1.21x           1.885592690 sec.   1.02x
 * Book 4  3400       4.39x              566333       4.34x                       41967         2.86x           21.575230019 sec.  11.44x
 *
 * Java HashSet
 * File    Size (kb)  Size Change Factor Total Words  Increase from Previous Row  Unique Words  Inc. Prev. Row  Actual Time       Inc. Prev. Row
 * Book 1  176        -                  29569        -                           5973          -               0.042079499 sec.  -
 * Book 2  451        2.56x              78106        2.64x                       12178         2.04x           0.141625178 sec.  3.37x
 * Book 3  774        1.72x              130416       1.67x                       14699         1.21x           0.157011570 sec.  1.11x
 * Book 4  3400       4.39x              566333       4.34x                       41967         2.86x           1.823618881 sec.  11.61x
 *
 * Java TreeSet
 * File    Size (kb)  Size Change Factor Total Words  Increase from Previous Row  Unique Words  Inc. Prev. Row  Actual Time       Inc. Prev. Row
 * Book 1  176        -                  29569        -                           5973          -               0.049066439 sec.  -
 * Book 2  451        2.56x              78106        2.64x                       12178         2.04x           0.117627890 sec.  2.40x
 * Book 3  774        1.72x              130416       1.67x                       14699         1.21x           0.438121505 sec.  3.72x
 * Book 4  3400       4.39x              566333       4.34x                       41967         2.86x           0.604541048 sec.  1.38x
 *
 * Questions:
 * 1. The Big O of the process text methods.
 * UnsortedSet: O(N*M), since each add may search through up to M elements for a duplicate
 * SortedSet: O(N*M) worst case order, since each add uses binary search, but inseritng a new word can shift up to M elements in
 * the arraylist
 * HashSet: O(N) as each add takes an amortized O(1) time with well-distributed hash code.
 * TreeSet: O(N*log(M)), since each add takes O(log(M))time ebcause the tree stays balanced
 * 2. The Big O of add, for a set containing some M number of elements
 * UnsortedSet: O(M) worst case, due to the duplicate search
 * SortedSet: O(M) worst case, as binary search is O(log(M)), but shifting elements for isnertion is O(M).
 * HashSet: amoritzed O(1) if its well distributed
 * TreeSet: O(log(M)) because it uses a balanced search tree
 * 3. TreeSet prints elements in sorted order. For these String sets, it uses the strings' natural, case-sensitive ordering.
 * Hashset has no guaranteed order, since it uses hash codes to organize elements into buckets, but does not sort by hash code
 * code. It's output may look random, although it may not be rnadomly generated.
 *
 */

public class SetTester {

    public static void main(String[] args) {

        //UnsortedSet
        UnsortedSet<Integer> unsorted = new UnsortedSet<>();
        // Test 1: add
        if (unsorted.add(4)) {
            System.out.println("Passed test 1");
        } else {
            System.out.println("Failed test 1");
        }
        unsorted.add(9);
        // Test 2: contains
        if (unsorted.contains(4)) {
            System.out.println("Passed test 2");
        } else {
            System.out.println("Failed test 2");
        }
        // Test 3: size
        if (unsorted.size() == 2) {
            System.out.println("Passed test 3");
        } else {
            System.out.println("Failed test 3");
        }
        UnsortedSet<Integer> unsortedOther = new UnsortedSet<>();
        unsortedOther.add(9);
        // Test 4: containsAll
        if (unsorted.containsAll(unsortedOther)) {
            System.out.println("Passed test 4");
        } else {
            System.out.println("Failed test 4");
        }
        unsortedOther.add(15);
        ISet<Integer> unsortedResult = unsorted.union(unsortedOther);
        // Test 5: union
        if (unsortedResult.size() == 3 && unsortedResult.contains(4)
                && unsortedResult.contains(9) && unsortedResult.contains(15)) {
            System.out.println("Passed test 5");
        } else {
            System.out.println("Failed test 5");
        }
        unsortedResult = unsorted.intersection(unsortedOther);
        // Test 6: intersection
        if (unsortedResult.size() == 1 && unsortedResult.contains(9)) {
            System.out.println("Passed test 6");
        } else {
            System.out.println("Failed test 6");
        }
        unsortedResult = unsorted.difference(unsortedOther);
        // Test 7: difference
        if (unsortedResult.size() == 1 && unsortedResult.contains(4)) {
            System.out.println("Passed test 7");
        } else {
            System.out.println("Failed test 7");
        }
        UnsortedSet<Integer> unsortedEqual = new UnsortedSet<>();
        unsortedEqual.add(9);
        unsortedEqual.add(4);
        // Test 8: equals
        if (unsorted.equals(unsortedEqual)) {
            System.out.println("Passed test 8");
        } else {
            System.out.println("Failed test 8");
        }
        UnsortedSet<Integer> unsortedSingle = new UnsortedSet<>();
        unsortedSingle.add(12);
        Iterator<Integer> unsortedIterator = unsortedSingle.iterator();
        // Test 9: iterator
        if (unsortedIterator.hasNext() && unsortedIterator.next() == 12) {
            System.out.println("Passed test 9");
        } else {
            System.out.println("Failed test 9");
        }
        // Test 10: toString
        if (unsortedSingle.toString().equals("(12)")) {
            System.out.println("Passed test 10");
        } else {
            System.out.println("Failed test 10");
        }
        // Test 11: addAll
        if (unsorted.addAll(unsortedOther) && unsorted.contains(15)) {
            System.out.println("Passed test 11");
        } else {
            System.out.println("Failed test 11");
        }
        // Test 12: remove
        if (unsorted.remove(9) && !unsorted.contains(9)) {
            System.out.println("Passed test 12");
        } else {
            System.out.println("Failed test 12");
        }
        unsorted.clear();
        // Test 13: UnsortedSet clear
        if (unsorted.size() == 0) {
            System.out.println("Passed test 13");
        } else {
            System.out.println("Failed test 13");
        }

        //Sorted Set
        SortedSet<Integer> sorted = new SortedSet<>();
        // Test 14: add
        if (sorted.add(4)) {
            System.out.println("Passed test 14");
        } else {
            System.out.println("Failed test 14");
        }
        sorted.add(9);
        // Test 15: contains
        if (sorted.contains(4)) {
            System.out.println("Passed test 15");
        } else {
            System.out.println("Failed test 15");
        }
        // Test 16: size
        if (sorted.size() == 2) {
            System.out.println("Passed test 16");
        } else {
            System.out.println("Failed test 16");
        }
        SortedSet<Integer> sortedOther = new SortedSet<>();
        sortedOther.add(9);
        // Test 17: containsAll
        if (sorted.containsAll(sortedOther)) {
            System.out.println("Passed test 17");
        } else {
            System.out.println("Failed test 17");
        }
        sortedOther.add(15);
        ISet<Integer> sortedResult = sorted.union(sortedOther);
        // Test 18: union
        if (sortedResult.size() == 3 && sortedResult.contains(4)
                && sortedResult.contains(9) && sortedResult.contains(15)) {
            System.out.println("Passed test 18");
        } else {
            System.out.println("Failed test 18");
        }
        sortedResult = sorted.intersection(sortedOther);
        // Test 19: intersection
        if (sortedResult.size() == 1 && sortedResult.contains(9)) {
            System.out.println("Passed test 19");
        } else {
            System.out.println("Failed test 19");
        }
        sortedResult = sorted.difference(sortedOther);
        // Test 20: difference
        if (sortedResult.size() == 1 && sortedResult.contains(4)) {
            System.out.println("Passed test 20");
        } else {
            System.out.println("Failed test 20");
        }
        SortedSet<Integer> sortedEqual = new SortedSet<>();
        sortedEqual.add(9);
        sortedEqual.add(4);
        // Test 21: equals
        if (sorted.equals(sortedEqual)) {
            System.out.println("Passed test 21");
        } else {
            System.out.println("Failed test 21");
        }
        SortedSet<Integer> sortedSingle = new SortedSet<>();
        sortedSingle.add(71);
        Iterator<Integer> sortedIterator = sortedSingle.iterator();
        // Test 22: iterator
        if (sortedIterator.hasNext() && sortedIterator.next() == 71) {
            System.out.println("Passed test 22");
        } else {
            System.out.println("Failed test 22");
        }
        // Test 23: min
        if (sorted.min() == 4) {
            System.out.println("Passed test 23");
        } else {
            System.out.println("Failed test 23");
        }
        // Test 24: max
        if (sorted.max() == 9) {
            System.out.println("Passed test 24");
        } else {
            System.out.println("Failed test 24");
        }
        // Test 25: addAll
        if (sorted.addAll(sortedOther) && sorted.contains(15)) {
            System.out.println("Passed test 25");
        } else {
            System.out.println("Failed test 25");
        }
        // Test 26: remove
        if (sorted.remove(9) && !sorted.contains(9)) {
            System.out.println("Passed test 26");
        } else {
            System.out.println("Failed test 26");
        }
        sorted.clear();
        // Test 27: clear
        if (sorted.size() == 0) {
            System.out.println("Passed test 27");
        } else {
            System.out.println("Failed test 27");
        }
        //
        // CS314 Students. Uncomment this section when ready to
        // run your experiments
        // try {
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // }
        // catch(Exception e) {
        // System.out.println("Unable to change look and feel");
        // }
        // Scanner sc = new Scanner(System.in);
        // String response = "";
        // do {
        // largeTest();
        // System.out.print("Another file? Enter y to do another file: ");
        // response = sc.next();
        // } while( response != null && response.length() > 0
        // && response.substring(0,1).equalsIgnoreCase("y") );

    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. "
                + "You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
            int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set) {
                System.out.println(o);
            }
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evaluating the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            Scanner s = null;
            try {
                s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }

        return text.toString();
    }
}