/*  Student information for assignment:
 *
 *  On MY honor, Hasher, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used) Hasher Hassan
 *  UTEID: hmh3233
 *  email address: hmh3233@my.utexas.edu
 *
 *  Student 2
 *  UTEID:
 *  email address:
 *
 */


import java.util.ArrayList;

/**
 * Tester class for the methods in Recursive.java
 */
public class RecursiveTester {

    public static void main(String[] args) {
        studentTests();
    }

    private static void studentTests() {
        // Test 1: Binary Test
        final int BINARY_TEST = 50;
        if (Recursive.getBinary(BINARY_TEST).equals("110010")) {
            System.out.println("Test 1 Passed");
        } else {
            System.out.println("Test 1 Failed");
        }

        // Test 2: Binary Test
        final int BINARY_TEST_2 = -1038;
        if (Recursive.getBinary(BINARY_TEST_2).equals("-10000001110")) {
            System.out.println("Test 2 Passed");
        } else {
            System.out.println("Test 2 Failed");
        }

        // Test 3: Reverse String Test
        if (Recursive.revString("Hello Bro").equals("orB olleH")) {
            System.out.println("Test 3 Passed");
        } else {
            System.out.println("Test 3 Failed");
        }

        // Test 4: Reverse String Test
        if (Recursive.revString("what IS going ON").equals("NO gniog SI tahw")) {
            System.out.println("Test 4 Passed");
        } else {
            System.out.println("Test 4 Failed");
        }

        // Test 5: nextIsDouble Test
        final int EXPECTED_DOUBLE_COUNT = 3;
        int[] data = {10, 20, 30, 60, 70, 80, 160};
        if (Recursive.nextIsDouble(data) == EXPECTED_DOUBLE_COUNT) {
            System.out.println("Test 5 Passed");
        } else {
            System.out.println("Test 5 Failed");
        }

        // Test 6: nextIsDouble Test
        int[] data2 = {-10, -20, -40, 80, 160, 3, 5};
        if (Recursive.nextIsDouble(data2) == EXPECTED_DOUBLE_COUNT) {
            System.out.println("Test 6 Passed");
        } else {
            System.out.println("Test 6 Failed");
        }

        // Test 7: listMnemonics Test
        ArrayList<String> answers = new ArrayList<>();
        answers.add("AD");
        answers.add("AE");
        answers.add("AF");
        answers.add("BD");
        answers.add("BE");
        answers.add("BF");
        answers.add("CD");
        answers.add("CE");
        answers.add("CF");
        if (Recursive.listMnemonics("23").equals(answers)) {
            System.out.println("Test 7 Passed");
        } else {
            System.out.println("Test 7 Failed");
        }

        // Test 8: listMnemonics Test
        answers = new ArrayList<>();
        answers.add("JW");
        answers.add("JX");
        answers.add("JY");
        answers.add("JZ");
        answers.add("KW");
        answers.add("KX");
        answers.add("KY");
        answers.add("KZ");
        answers.add("LW");
        answers.add("LX");
        answers.add("LY");
        answers.add("LZ");
        if (Recursive.listMnemonics("59").equals(answers)) {
            System.out.println("Test 8 Passed");
        } else {
            System.out.println("Test 8 Failed");
        }

        // Test 9: canFlow Test
        int[][] map = {{10, 10, 10, 10},
                         {10, 10, 10, 10},
                         {10, 10, 10, 10},
                         {10, 9, 9, 10},
                         {10, 8, 8, 10},
                         {10, 8, 7, 10},
                         {10, 8, 6, 10},
                         {10, 10, 4, 10}};
        if (Recursive.canFlowOffMap(map, 2, 2)) {
            System.out.println("Test 9 Passed");
        } else {
            System.out.println("Test 9 Failed");
        }

        // Test 10: canFlow Test
        int[][] map2 = {{30, 30, 30, 30},
                         {30, 30, 30, 30},
                         {30, 20, 30, 30},
                         {30, 15, 9, 30},
                         {30, 5, 8, 30},
                         {30, 30, 30, 10}};
        if (!Recursive.canFlowOffMap(map2, 2, 1)) {
            System.out.println("Test 10 Passed");
        } else {
            System.out.println("Test 10 Failed");
        }

        // Test 11: minDifference Test
        final int NUM_TEAMS = 3;
        int[] abilities = {1, 2, 3, 4, 5, 6, 10, 14, 18};
        if (Recursive.minDifference(NUM_TEAMS, abilities) == 0) {
            System.out.println("Test 11 Passed");
        } else {
            System.out.println("Test 11 Failed");
        }

        // Test 12: minDifference Test
        final int NUM_TEAMS_2 = 4;
        int[] abilities2 = {2, 2, 2, 2, 4, 4, 4, 4, 6, 6, 6, 8};
        if (Recursive.minDifference(NUM_TEAMS_2, abilities2) == 2) {
            System.out.println("Test 12 Passed");
        } else {
            System.out.println("Test 12 Failed");
        }

        // Test 13: canEscapeMaze Test
        char[][] maze = {{'*', '*', '*', '*', '*'},
                         {'*', 'S', '$', '$', '*'},
                         {'*', 'G', '$', 'Y', '*'},
                         {'*', '$', '$', 'E', '*'},
                         {'*', '*', '*', '*', '*'}};
        if (Recursive.canEscapeMaze(maze) == 2) {
            System.out.println("Test 13 Passed");
        } else {
            System.out.println("Test 13 Failed");
        }

        // Test 14: canEscapeMaze Test
        char[][] maze2 = {{'$', '*', '*', '*',},
                          {'*', 'S', 'G', '*'},
                          {'*', 'Y', '$', '*'},
                          {'*', '$', 'E', '*'},
                          {'*', '*', '*', '*'}};
        if (Recursive.canEscapeMaze(maze2) == 1) {
            System.out.println("Test 14 Passed");
        } else {
            System.out.println("Test 14 Failed");
        }
    }
}
