

// CodeCampTester.java - CS314 Assignment 1 - Tester class

/*
 * Student information for assignment:
 * Name: Hasher Hassan
 * email address: hmh3233@my.utexas.edu
 * UTEID: hmh3233
 * Section 5 digit ID: 55010
 * Number of slip days used on this assignment: 0
 */

/*
 * Experiment 1 average number of birthday pairs: 45.127769.
   Question 1: I predicted 20 people.
   Question 2: The percentage first exceeded 50% at 23 people.
   Question 3: It does not really surprise me.
   Question 4: It was actually very close to my predicted answer of 20 people.
   It requires 23 people in order to have at least a 50% chance of a shared birthday.
   Experiment 2 results are in the table below:
Num people:   2, number of experiments with one or more shared birthday:   156, percentage: 0.31
Num people:   3, number of experiments with one or more shared birthday:   431, percentage: 0.86
Num people:   4, number of experiments with one or more shared birthday:   804, percentage: 1.61
Num people:   5, number of experiments with one or more shared birthday:  1289, percentage: 2.58
Num people:   6, number of experiments with one or more shared birthday:  2023, percentage: 4.05
Num people:   7, number of experiments with one or more shared birthday:  2805, percentage: 5.61
Num people:   8, number of experiments with one or more shared birthday:  3728, percentage: 7.46
Num people:   9, number of experiments with one or more shared birthday:  4682, percentage: 9.36
Num people:  10, number of experiments with one or more shared birthday:  6002, percentage: 12.00
Num people:  11, number of experiments with one or more shared birthday:  6963, percentage: 13.93
Num people:  12, number of experiments with one or more shared birthday:  8306, percentage: 16.61
Num people:  13, number of experiments with one or more shared birthday:  9670, percentage: 19.34
Num people:  14, number of experiments with one or more shared birthday: 10969, percentage: 21.94
Num people:  15, number of experiments with one or more shared birthday: 12640, percentage: 25.28
Num people:  16, number of experiments with one or more shared birthday: 14206, percentage: 28.41
Num people:  17, number of experiments with one or more shared birthday: 15832, percentage: 31.66
Num people:  18, number of experiments with one or more shared birthday: 17510, percentage: 35.02
Num people:  19, number of experiments with one or more shared birthday: 18911, percentage: 37.82
Num people:  20, number of experiments with one or more shared birthday: 20576, percentage: 41.15
Num people:  21, number of experiments with one or more shared birthday: 22166, percentage: 44.33
Num people:  22, number of experiments with one or more shared birthday: 23725, percentage: 47.45
Num people:  23, number of experiments with one or more shared birthday: 25533, percentage: 51.07
Num people:  24, number of experiments with one or more shared birthday: 26927, percentage: 53.85
Num people:  25, number of experiments with one or more shared birthday: 28448, percentage: 56.90
Num people:  26, number of experiments with one or more shared birthday: 30040, percentage: 60.08
Num people:  27, number of experiments with one or more shared birthday: 31408, percentage: 62.82
Num people:  28, number of experiments with one or more shared birthday: 32909, percentage: 65.82
Num people:  29, number of experiments with one or more shared birthday: 33919, percentage: 67.84
Num people:  30, number of experiments with one or more shared birthday: 35445, percentage: 70.89
Num people:  31, number of experiments with one or more shared birthday: 36719, percentage: 73.44
Num people:  32, number of experiments with one or more shared birthday: 37727, percentage: 75.45
Num people:  33, number of experiments with one or more shared birthday: 38823, percentage: 77.65
Num people:  34, number of experiments with one or more shared birthday: 39649, percentage: 79.30
Num people:  35, number of experiments with one or more shared birthday: 40719, percentage: 81.44
Num people:  36, number of experiments with one or more shared birthday: 41720, percentage: 83.44
Num people:  37, number of experiments with one or more shared birthday: 42354, percentage: 84.71
Num people:  38, number of experiments with one or more shared birthday: 43227, percentage: 86.45
Num people:  39, number of experiments with one or more shared birthday: 43951, percentage: 87.90
Num people:  40, number of experiments with one or more shared birthday: 44558, percentage: 89.12
Num people:  41, number of experiments with one or more shared birthday: 45193, percentage: 90.39
Num people:  42, number of experiments with one or more shared birthday: 45715, percentage: 91.43
Num people:  43, number of experiments with one or more shared birthday: 46222, percentage: 92.44
Num people:  44, number of experiments with one or more shared birthday: 46647, percentage: 93.29
Num people:  45, number of experiments with one or more shared birthday: 47094, percentage: 94.19
Num people:  46, number of experiments with one or more shared birthday: 47421, percentage: 94.84
Num people:  47, number of experiments with one or more shared birthday: 47777, percentage: 95.55
Num people:  48, number of experiments with one or more shared birthday: 47981, percentage: 95.96
Num people:  49, number of experiments with one or more shared birthday: 48228, percentage: 96.46
Num people:  50, number of experiments with one or more shared birthday: 48522, percentage: 97.04
Num people:  51, number of experiments with one or more shared birthday: 48713, percentage: 97.43
Num people:  52, number of experiments with one or more shared birthday: 48856, percentage: 97.71
Num people:  53, number of experiments with one or more shared birthday: 49027, percentage: 98.05
Num people:  54, number of experiments with one or more shared birthday: 49249, percentage: 98.50
Num people:  55, number of experiments with one or more shared birthday: 49323, percentage: 98.65
Num people:  56, number of experiments with one or more shared birthday: 49442, percentage: 98.88
Num people:  57, number of experiments with one or more shared birthday: 49546, percentage: 99.09
Num people:  58, number of experiments with one or more shared birthday: 49554, percentage: 99.11
Num people:  59, number of experiments with one or more shared birthday: 49648, percentage: 99.30
Num people:  60, number of experiments with one or more shared birthday: 49689, percentage: 99.38
Num people:  61, number of experiments with one or more shared birthday: 49783, percentage: 99.57
Num people:  62, number of experiments with one or more shared birthday: 49778, percentage: 99.56
Num people:  63, number of experiments with one or more shared birthday: 49828, percentage: 99.66
Num people:  64, number of experiments with one or more shared birthday: 49865, percentage: 99.73
Num people:  65, number of experiments with one or more shared birthday: 49888, percentage: 99.78
Num people:  66, number of experiments with one or more shared birthday: 49908, percentage: 99.82
Num people:  67, number of experiments with one or more shared birthday: 49908, percentage: 99.82
Num people:  68, number of experiments with one or more shared birthday: 49929, percentage: 99.86
Num people:  69, number of experiments with one or more shared birthday: 49946, percentage: 99.89
Num people:  70, number of experiments with one or more shared birthday: 49955, percentage: 99.91
Num people:  71, number of experiments with one or more shared birthday: 49964, percentage: 99.93
Num people:  72, number of experiments with one or more shared birthday: 49966, percentage: 99.93
Num people:  73, number of experiments with one or more shared birthday: 49976, percentage: 99.95
Num people:  74, number of experiments with one or more shared birthday: 49982, percentage: 99.96
Num people:  75, number of experiments with one or more shared birthday: 49988, percentage: 99.98
Num people:  76, number of experiments with one or more shared birthday: 49985, percentage: 99.97
Num people:  77, number of experiments with one or more shared birthday: 49987, percentage: 99.97
Num people:  78, number of experiments with one or more shared birthday: 49995, percentage: 99.99
Num people:  79, number of experiments with one or more shared birthday: 49993, percentage: 99.99
Num people:  80, number of experiments with one or more shared birthday: 49994, percentage: 99.99
Num people:  81, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people:  82, number of experiments with one or more shared birthday: 49996, percentage: 99.99
Num people:  83, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people:  84, number of experiments with one or more shared birthday: 49997, percentage: 99.99
Num people:  85, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people:  86, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people:  87, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people:  88, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  89, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  90, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people:  91, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  92, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  93, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  94, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  95, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  96, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  97, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  98, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people:  99, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 100, number of experiments with one or more shared birthday: 50000, percentage: 100.00
 */

public class CodeCampTester {

    /*
     * pre: none
     * post: runs two tests for each required method and prints
     *       whether each test passed
    */
    public static void main(String[] args) {
        // Tests for hammingDistance
        int[] listA = {1, 2, 3, 4, 5};
        int[] listB = {1, 8, 3, 4, 6};
        if (CodeCamp.hammingDistance(listA, listB) == 2) {
            System.out.println("hammingDistance test 1: passed");
        } else {
            System.out.println("hammingDistance test 1: **FAILED**");
        }

        listA = new int[] {2, 2};
        listB = new int[] {2, 2};
        if (CodeCamp.hammingDistance(listA, listB) == 0) {
            System.out.println("hammingDistance test 2: passed");
        } else {
            System.out.println("hammingDistance test 2: **FAILED**");
        }

        // Tests for isPermutation
        int[] permutationA = {1, 2, 2, 3};
        int[] permutationB = {2, 3, 2, 1};
        if (CodeCamp.isPermutation(permutationA, permutationB)) {
            System.out.println("isPermutation test 1: passed");
        } else {
            System.out.println("isPermutation test 1: **FAILED**");
        }

        permutationA = new int[] {1, 2, 3};
        permutationB = new int[] {1, 2, 3, 3};
        if (!CodeCamp.isPermutation(permutationA, permutationB)) {
            System.out.println("isPermutation test 2: passed");
        } else {
            System.out.println("isPermutation test 2: **FAILED**");
        }

        // Tests for mostVowels
        String[] words = {"ae", "io", "bbb"};
        if (CodeCamp.mostVowels(words) == 0) {
            System.out.println("mostVowels test 1: passed");
        } else {
            System.out.println("mostVowels test 1: **FAILED**");
        }

        words = new String[] {null, "sky", "AEIOU", "queue"};
        if (CodeCamp.mostVowels(words) == 2) {
            System.out.println("mostVowels test 2: passed");
        } else {
            System.out.println("mostVowels test 2: **FAILED**");
        }

        // Tests for sharedBirthdays
        if (CodeCamp.sharedBirthdays(1, 365) == 0) {
            System.out.println("sharedBirthdays test 1: passed");
        } else {
            System.out.println("sharedBirthdays test 1: **FAILED**");
        }

        if (CodeCamp.sharedBirthdays(4, 1) == 6) {
            System.out.println("sharedBirthdays test 2: passed");
        } else {
            System.out.println("sharedBirthdays test 2: **FAILED**");
        }

        // Tests for queensAreSafe
        char[][] safeBoard = {
            {'.', 'q', '.', '.'},
            {'.', '.', '.', 'q'},
            {'q', '.', '.', '.'},
            {'.', '.', 'q', '.'}
        };
        if (CodeCamp.queensAreSafe(safeBoard)) {
            System.out.println("queensAreSafe test 1: passed");
        } else {
            System.out.println("queensAreSafe test 1: **FAILED**");
        }

        char[][] unsafeBoard = {
            {'.', 'q', '.'},
            {'q', '.', '.'},
            {'.', '.', 'q'}
        };
        if (!CodeCamp.queensAreSafe(unsafeBoard)) {
            System.out.println("queensAreSafe test 2: passed");
        } else {
            System.out.println("queensAreSafe test 2: **FAILED**");
        }

        // Tests for getValueOfMostValuablePlot
        int[][] city = {
            {-17, -2},
            {-7, -9}
        };
        if (CodeCamp.getValueOfMostValuablePlot(city) == -2) {
            System.out.println("getValueOfMostValuablePlot test 1: passed");
        } else {
            System.out.println("getValueOfMostValuablePlot test 1: **FAILED**");
        }

        city = new int[][] {
            {1, -3, 10},
            {4, 8, -1}
        };
        if (CodeCamp.getValueOfMostValuablePlot(city) == 19) {
            System.out.println("getValueOfMostValuablePlot test 2: passed");
        } else {
            System.out.println("getValueOfMostValuablePlot test 2: **FAILED**");
        }
    } // end of main method

    /*
     * pre: none
     * post: returns average number of pairs of people who share
     *      a birthday over all of the trials
     */
    private static double experiment1() {
        final int NUM_TRIALS = 1000000;
        final int NUM_PEOPLE = 182;
        final int NUM_DAYS = 365;
        int sum = 0;
        for (int trial = 0; trial < NUM_TRIALS; trial++) {
            sum += CodeCamp.sharedBirthdays(NUM_PEOPLE, NUM_DAYS);
        }
        return (double) sum / NUM_TRIALS;
    }

    /*
     * pre: none
     * post: prints each group size and the percentage
     *   of trials for that size that contain at least one shared birthday
     */
    private static void experiment2() {
        final int MAX_PEOPLE = 100;
        final int NUM_TRIALS = 50000;
        final int NUM_DAYS = 365;
        final double PERCENT_SCALE = 100.0;
        for (int numPeople = 2; numPeople <= MAX_PEOPLE; numPeople++) {
            int sharedBirthdayTrials = 0;
            for (int trial = 0; trial < NUM_TRIALS; trial++) {
                if (CodeCamp.sharedBirthdays(numPeople, NUM_DAYS) > 0) {
                    sharedBirthdayTrials++;
                }
            }
            double percentage = (sharedBirthdayTrials * PERCENT_SCALE) / NUM_TRIALS;
            System.out.printf(
                   "Num people: %3d, number of experiments with one or more "
                            + "shared birthday: %5d, percentage: %.2f%n",
                   numPeople, sharedBirthdayTrials, percentage);
        }
    }
}
