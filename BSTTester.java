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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 * Answer to questions.txt:
 * Time to insert 1000 items to BST - 0.002137663
 * Height of the tree - 20
 * Size of the tree - 1000
 * Average times of 10 experiments:
 * Insertions: 0.0008046467
 * Avg height of the tree: 21.3
 * Avg size of the tree: 1000.0
 * Random BST:
 * N         ave time (seconds)       min possible height     ave height     ave size
 * 1000      0.0009461722             9                       22.3           1000.0
 * 2000      0.0016454271             10                      23.6           2000.0
 * 4000      0.0034549090             11                      26.5           4000.0
 * 8000      0.0057109367             12                      28.9           8000.0
 * 16000     0.0113426310             13                      31.7           16000.0
 * 32000     0.0196098908             14                      34.6           31999.9
 * 64000     0.0331335953             15                      38.0           63999.7
 * 128000    0.1173137757             16                      40.1           127998.1
 * 256000    0.3407438153             17                      42.6           255992.8
 * 512000    0.7203382618             18                      46.4           511968.4
 * 1024000   1.4666499522             19                      49.6           1023880.9
 * Random Java TreeSet
 * N         ave time (seconds)
 * 1000      0.019798798
 * 2000      0.013314388
 * 4000      0.065036543
 * 8000      0.156384166
 * 16000     0.184905883
 * 32000     0.207551035
 * 64000     0.513102121
 * 128000    2.048996422
 * 256000    3.485900544
 * 512000    4.905971154
 * 1024000   12.569105778
 * The TreeSet avg times ranged from 0.0133 to 12.5691 sseconds.
 * In my experiment, Treeset was generally much slower than my BST
 * for every tested value of N. For example, at n = 1024000, TreeSet
 * avg time was 12.5691 seconds, while my BST avg time was 1.4666
 * seconds, making TreeSet about 8.6 times slower. Generally, TreeSet
 * seems to be slwoer than my BST, but this is not a conclusion, more of
 * an observation/trend.
 * Height after 1000 insertions: 999
 * Sorted BST:
 * N         ave time (seconds)       predicted height     ave size
 * 1000      0.010404664              999                  1000
 * 2000      0.009088408              1999                 2000
 * 4000      0.037570508              3999                 4000
 * 8000      0.126974947              7999                 8000
 * 16000     0.540055911              15999                16000
 * 32000     2.577791990              31999                32000
 * 64000     13.12803956              63999                64000
 * I predict 128000 will take 52.51, 256000 will take 210.05,
 * 512000 will take 840.19 seconds, because sorted insertion takes O(N^2) total time.
 * Sorted Tree Set:
 * N         ave time (seconds)
 * 1000      0.0021695485
 * 2000      0.0040927999
 * 4000      0.0022742992
 * 8000      0.0048236579
 * 16000     0.0073126405
 * 32000     0.0082444838
 * 64000     0.0158664764
 * TreeSet was much faster than my BST for every N. At N = 64000, TreeSet
 * averaged 0.01587 seconds, comapred with 13.12804 seconds for my BST,
 * about 827 times faster. The difference came from tree balancing.
 * My BST becomes a chain of right children when values are inserted in
 * ascending order, giving it height N - 1 and O(N^2) total insertion time.
 * TreeSet maintains a balanced tree, allowing O(log N) insertions and
 * O(NlogN) total insertion time.
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * @param args Not used
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(40);
        tree.add(20);
        tree.add(60);
        tree.add(10);
        tree.add(30);
        tree.add(50);
        tree.add(70);
        // Test 1: add
        boolean changed = tree.add(35);
        showTestResults(changed && tree.size() == 8 && tree.isPresent(35), 1);
        // Test 2: add
        changed = tree.add(35);
        showTestResults(!changed && tree.size() == 8, 2);
        // Test 3: remove
        changed = tree.remove(20);
        showTestResults(changed && tree.size() == 7 &&
                tree.getAll().equals(Arrays.asList(10, 30, 35, 40, 50, 60, 70)), 3);
        // Test 4: remove
        changed = tree.remove(47);
        showTestResults(!changed && tree.size() == 7, 4);
        // Test 5: isPresent
        showTestResults(tree.isPresent(30), 5);
        // Test 6: isPresent
        showTestResults(!tree.isPresent(47), 6);
        // Test 7: size
        showTestResults(tree.size() == 7, 7);
        // Test 8: size
        BinarySearchTree<Integer> empty = new BinarySearchTree<>();
        showTestResults(empty.size() == 0, 8);
        // Test 9: height
        showTestResults(tree.height() == 3, 9);
        // Test 10: height
        showTestResults(empty.height() == -1, 10);
        // Test 11: getAll
        showTestResults(tree.getAll().equals(
                Arrays.asList(10, 30, 35, 40, 50, 60, 70)), 11);
        // Test 12: getAll
        showTestResults(empty.getAll().isEmpty(), 12);
        // Test 13: max
        showTestResults(tree.max() == 70, 13);
        // Test 14: max
        BinarySearchTree<Integer> single = new BinarySearchTree<>();
        single.add(234);
        showTestResults(single.max() == 234, 14);
        // Test 15: min
        showTestResults(tree.min() == 10, 15);
        // Test 16: min
        showTestResults(single.min() == 234, 16);
        // Test 17: iterativeAdd
        BinarySearchTree<Integer> it = new BinarySearchTree<>();
        changed = it.iterativeAdd(40);
        showTestResults(changed && it.size() == 1
                && it.isPresent(40), 17);
        // Test 18: iterativeAdd
        changed = it.iterativeAdd(40);
        showTestResults(!changed && it.size() == 1, 18);
        // Test 19: get
        showTestResults(tree.get(0).equals(10), 19);
        // Test 20: get
        showTestResults(tree.get(6).equals(70), 20);
        // Test 21: getAllLessThan
        showTestResults(tree.getAllLessThan(40).equals(
                Arrays.asList(10, 30, 35)), 21);
        // Test 22: getAllLessThan
        showTestResults(tree.getAllLessThan(10).isEmpty(), 22);
        // Test 23: getAllGreaterThan
        showTestResults(tree.getAllGreaterThan(40).equals(
                Arrays.asList(50, 60, 70)), 23);
        // Test 24: getAllGreaterThan
        showTestResults(tree.getAllGreaterThan(70).isEmpty(), 24);
        // Test 25: numNodesAtDepth
        showTestResults(tree.numNodesAtDepth(0) == 1, 25);
        // Test 26: numNodesAtDepth
        showTestResults(tree.numNodesAtDepth(2) == 3, 26);

        randomBSTExperiment();
        System.out.println();
        randomTreeSetExperiment();
        System.out.println();
        sortedBSTExperiment();
        System.out.println();
        sortedTreeSetExperiment();
    }

    private static void randomBSTExperiment() {
        int runs = 10;
        int n = 1024000;
        Random r = new Random();
        Stopwatch s = new Stopwatch();
        for (int l = 1000; l <= n; l*=2) {
                double totalTime = 0.0;
                double totalHeight = 0.0;
                double totalSize = 0.0;
                for (int k = 0; k < runs; k++) {
                        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
                        s.start();
                        for (int i = 0; i < l; i++) {
                                tree.add(r.nextInt());
                        }
                        s.stop();
                        totalTime += s.time();
                        totalHeight += tree.height();
                        totalSize += tree.size();
                }
                System.out.println(l + " " + totalTime / runs + " " + totalHeight / runs + " " + totalSize / runs);
        }
    }

    private static void randomTreeSetExperiment() {
        int runs = 10;
        int n = 1024000;
        Random r = new Random();
        Stopwatch s = new Stopwatch();
        for (int l = 1000; l <= n; l*=2) {
                double totalTime = 0.0;
                for (int k = 0; k < runs; k++) {
                        TreeSet<Integer> tree = new TreeSet<>();
                        s.start();
                        for (int i = 0; i < l; i++) {
                                tree.add(r.nextInt());
                        }
                        s.stop();
                        totalTime += s.time();
                }
                System.out.println(l + " " + totalTime / runs);
        }
    }

    private static void sortedBSTExperiment() {
        int runs = 10;
        int n = 64000;
        Stopwatch s = new Stopwatch();
        for (int l = 1000; l <= n; l*=2) {
                double totalTime = 0.0;
                double totalSize = 0.0;
                for (int k = 0; k < runs; k++) {
                        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
                        s.start();
                        for (int p = 1; p <= l; p++) {
                                tree.iterativeAdd(p);
                        }
                        s.stop();
                        totalTime += s.time();
                        totalSize += tree.size();
                }
                System.out.println(l + " " + totalTime / runs + " " + totalSize / runs);
        }
    }

    private static void sortedTreeSetExperiment() {
        int runs = 10;
        int n = 64000;
        Stopwatch s = new Stopwatch();
        for (int l = 1000; l <= n; l*=2) {
                double totalTime = 0.0;
                for (int k = 0; k < runs; k++) {
                        TreeSet<Integer> t = new TreeSet<>();
                        s.start();
                        for (int p = 1; p <= l; p++) {
                                t.add(p);
                        }
                        s.stop();
                        totalTime += s.time();
                }
                System.out.println(l + " " + totalTime / runs);
        }
    }

    private static void showTestResults(boolean passed, int testNum) {
        if (passed) {
            System.out.println("Test " + testNum + " passed.");
        } else {
            System.out.println("TEST " + testNum + " FAILED.");
        }
    }
}
