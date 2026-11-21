import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
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

public class AnagramFinderTester {


    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     * @param args Not used.
     */
    public static void main(String[] args) {

        cs314StudentTestsForLetterInventory();

        // tests on the anagram solver itself
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver
                = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);

    }

    private static void cs314StudentTestsForLetterInventory() {
        // Test 1: Constructor
        LetterInventory first = new LetterInventory("AaB! 12");
        if (first.size() == 3 && first.get('a') == 2 && first.get('b') == 1) {
            System.out.println("Passed Test 1");
        } else {
            System.out.println("Failed Test 1");
        }

        // Test 2: Constructor
        first = new LetterInventory("283 !(#");
        if (first.size() == 0 && first.isEmpty()) {
            System.out.println("Passed Test 2");
        } else {
            System.out.println("Failed Test 2");
        }

        // Test 3: Get
        first = new LetterInventory("apple");
        if (first.get('p') == 2) {
            System.out.println("Passed Test 3");
        } else {
            System.out.println("Failed Test 3");
        }

        // Test 4: Get
        if (first.get('A') == 1) {
            System.out.println("Passed Test 4");
        } else {
            System.out.println("Failed Test 4");
        }

        // Test 5: Size
        LetterInventory empty = new LetterInventory("");
        if (empty.size() == 0) {
            System.out.println("Passed Test 5");
        } else {
            System.out.println("Failed Test 5");
        }

        // Test 6: Size
        first = new LetterInventory("Hello World!");
        if (first.size() == 10) {
            System.out.println("Passed Test 6");
        } else {
            System.out.println("Failed Test 6");
        }

        // Test 7: isEmpty
        if (empty.isEmpty()) {
            System.out.println("Passed Test 7");
        } else {
            System.out.println("Failed Test 7");
        }

        // Test 8: isEmpty
        if (!first.isEmpty()) {
            System.out.println("Passed Test 8");
        } else {
            System.out.println("Failed Test 8");
        }

        // Test 9: toString
        first = new LetterInventory("Cab!*#*# B_9");
        if (first.toString().equals("abbc")) {
            System.out.println("Passed Test 9");
        } else {
            System.out.println("Failed Test 9");
        }

        // Test 10: toString
        if (empty.toString().equals("")) {
            System.out.println("Passed Test 10");
        } else {
            System.out.println("Failed Test 10");
        }

        // Test 11: add
        LetterInventory left = new LetterInventory("cdd");
        LetterInventory right = new LetterInventory("ed");
        LetterInventory sum = left.add(right);
        if (sum.toString().equals("cddde") && sum.size() == 5 &&
            left.toString().equals("cdd") && right.toString().equals("de")) {
            System.out.println("Passed Test 11");
        } else {
            System.out.println("Failed Test 11");
        }

        // Test 12: add
        first = new LetterInventory("apple");
        sum = first.add(empty);
        if (sum.toString().equals("aelpp") && sum.size() == 5 &&
            sum != first && sum != empty) {
            System.out.println("Passed Test 12");
        } else {
            System.out.println("Failed Test 12");
        }

        // Test 13: subtract
        left = new LetterInventory("apple");
        right = new LetterInventory("app");
        LetterInventory diff = left.subtract(right);
        if (diff != null && diff.toString().equals("el") &&
            left.toString().equals("aelpp") && right.toString().equals("app")) {
            System.out.println("Passed Test 13");
        } else {
            System.out.println("Failed Test 13");
        }

        // Test 14: subtract
        left = new LetterInventory("app");
        right = new LetterInventory("apple");
        diff = left.subtract(right);
        if (diff == null) {
            System.out.println("Passed Test 14");
        } else {
            System.out.println("Failed Test 14");
        }

        // Test 15: equals
        left = new LetterInventory("earth");
        right = new LetterInventory("HEART9432");
        if (left.equals(right)) {
            System.out.println("Passed Test 15");
        } else {
            System.out.println("Failed Test 15");
        }

        // Test 16: equals
        left = new LetterInventory("asdf");
        right = new LetterInventory("asdfs");
        if (!left.equals(right)) {
            System.out.println("Passed Test 16");
        } else {
            System.out.println("Failed Test 16");
        }
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0
                && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual,
            int testNum, String featureTested) {

        System.out.println("Test Number " + testNum + " testing "
                + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null)
                || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     *
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver,
            boolean displayAnagrams) {

        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams
                    = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if(displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }


                if(checkPassOrFailTest(currentTest, actualAnagrams))
                    solverTestCasesPassed++;
                System.out.println("Time to find anagrams: " + st.time());
                /* System.out.println("Number of calls to recursive helper method: "
                        + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));*/
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                            " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: "
                        + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                    List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for (List<String> singleAnagram : anagrams) {
            System.out.println(singleAnagram);
        }
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                    List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: "
                    + currentTest.anagrams.size());
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: "
                        + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   "
                        + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, "
                        + "but either a difference in anagrams or"
                        + " anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }
        return passed;
    }

    // class to handle the parameters for an anagram test
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams
                    : "Wrong number of angrams read or expected";
        }
    }
}
