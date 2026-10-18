/*  Student information for assignment:
 *
 *  On MY honor, Hasher,
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used): Hasher Hassan
 *  UTEID: hmh3233
 *  email address: hmh3233@my.utexas.edu
 *  Grader name: Jason Oh
 *  Section number: 55010
 *
 *  Student 2
 *  UTEID:
 *  email address:
 *
 */


//imports

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive {
    /**
     * Problem 1: convert a base 10 int to binary recursively.
     *   <br>pre: n != Integer.MIN_VALUE<br>
     *   <br>post: Returns a String that represents N in binary.
     *   If n is negative the returned String starts with a '-' char.
     *   All subsequent chars in returned String are '1's or '0's.
     *   Most significant digit is at position 0
     *   @param n the base 10 int to convert to base 2
     *   @return a String that is a binary representation of the parameter n
     *   The base case is if the number is 0 or 1, return 0 or 1
     *   The Recursive Step is if it isn't 0 or 1, recursively convert n / 2
     *   and then add n % 2 to the string. Dividing it finds the earlier digits,
     *   and the remainder gives the last digit. If the number is negative, compute
     *   as if it has no - and add a negative at the beginning
     *   @ Throws IllegalArgumentException if the preconiditons are not met.
     */
    public static String getBinary(int n) {
        if (n == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "getBinary. n cannot equal "
                    + "Integer.MIN_VALUE. n: " + n);
        }
        if (n < 0) {
            return "-" + getBinary(-n);
        }
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }
        return getBinary(n / 2) + (n % 2);
    }


    /**
     * Problem 2: reverse a String recursively.<br>
     *   pre: stringToRev != null<br>
     *   post: returns a String that is the reverse of stringToRev
     *   @param stringToRev the String to reverse.
     *   @return a String with the characters in stringToRev
     *   in reverse order.
     *   Base Case: String length is <= 1, return the string
     *   Recursive Case: Place the last character first, then add
     *   the recursive reversal of the string without its last
     *   character.
     *   @ Throws IllegalArgumentException if the preconiditons are not met.
     */
    public static String revString(String stringToRev) {
        if (stringToRev == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }
        if (stringToRev.length() <= 1) {
            return stringToRev;
        }
        // Adds the last letter, then continue to recursively call method to
        // on shortened word until one letter remains
        return stringToRev.substring(stringToRev.length() - 1, stringToRev.length())
                + revString(stringToRev.substring(0, stringToRev.length() - 1));
    }


    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data
     * that are followed immediately by double the value
     * @param data The array to search.
     * @return The number of elements in data that
     * are followed immediately by a value that is double the element.
     * Base Case: If the position is at the end of the array, return 0
     * Recursive Case: If the next element is double of the current element,
     * then add 1 and keep on going through the list, otherwise just keep
     * on going through the list. Recursion stops at or beyond the last index.
     * @ Throws IllegalArgumentException if the preconiditons are not met.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "nextIsDouble. parameter may not be null.");
        }
        return nextIsDoubleHelper(data, 0);
    }

    /**
     * pre: data != null, 0 <= pos <= data.length
     * post: Returns the number of adjacent pairs starting at pos or later
     * where the second value is double the first. Does not modify data
     */
    private static int nextIsDoubleHelper(int[] data, int pos) {
        if (pos >= data.length - 1) {
            return 0;
        }
        // if the next elment is 2 times the current one, then add one
        // and continue looping
        if (data[pos] * 2 == data[pos + 1]) {
            return 1 + nextIsDoubleHelper(data, pos + 1);
        }
        // otherwise, continue "looping" through
        return nextIsDoubleHelper(data, pos + 1);
    }

    /**  Problem 4: Find all combinations of mnemonics
     * for the given number.<br>
     *   pre: number != null, number.length() > 0,
     *   all characters in number are digits<br>
     *   post: see tips section of assignment handout
     *   @param number The number to find mnemonics for
     *   @return An ArrayList<String> with all possible mnemonics
     *   for the given number
     *   Base Case: No digits remain, so add the mnemonic to the results list
     *   Recursive Case: Loop through each letter mapped to the current number,
     *   add it to the partial mnemonic, and recurse with the remaining numbers
     *   @ Throws IllegalArgumentException if the preconiditons are not met.
     */
    public static ArrayList<String> listMnemonics(String number) {
        if (number == null ||  number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "listMnemonics");
        }

        ArrayList<String> results = new ArrayList<>(); // to store the mnemonics
        recursiveMnemonics(results, "", number);
        return results;
    }


    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mnemonicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used
     * from the original number.
     * pre: mnemonics, mnemonicSoFar, and digitsLeft are non-null
     * digitsLeft contains only digits, and mnemonicSoFar is a valid
     * mnemonic for the digits already used
     * post: adds all possible mnemonics using digitsLeft to mnemonics,
     * keeping existing mnemonics
     */
    private static void recursiveMnemonics(ArrayList<String> mnemonics,
                    String mnemonicSoFar, String digitsLeft) {
        if (digitsLeft.length() == 0) {
            mnemonics.add(mnemonicSoFar);
        } else {
            char currentDigit = digitsLeft.charAt(0);
            String letters = digitLetters(currentDigit);
            for (int i = 0; i < letters.length(); i++) {
                char nextLetter = letters.charAt(i);
                recursiveMnemonics(mnemonics, mnemonicSoFar + nextLetter, digitsLeft.substring(1));
            }
        }
    }

    /* Static code blocks are run once when this class is loaded.
     * Here we create an unmoddifiable list to use with the phone
     * mnemonics method.
     */
    private static final List<String> LETTERS_FOR_NUMBER;
    static {
        String[] letters = {"0", "1", "ABC",
                "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        ArrayList<String> lettersAsList = new ArrayList<>();
        for (String s : letters) {
            lettersAsList.add(s);
        }
        LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);

    }
    // used by method digitLetters



    /* helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with
     * this digit on a phone keypad
     */
    private static String digitLetters(char ch) {
        if (ch < '0' || ch > '9') {
            throw new IllegalArgumentException("parameter "
                    + "ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return LETTERS_FOR_NUMBER.get(index);
    }


    /* helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is
     * a digit ('0' through '9')
     */
    private static boolean allDigits(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }


    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     * Base Case: If the length of the square is less than / equal to limit,
     * stop recursing
     * Recursive Case: Divide the square into 9 equal squares, fill the center
     * square white, and recurse on the other 8 squares
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }


    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     * pre: g != null, size > 0; limit > 0; x >= 0, y >= 0
     * post: Draws white center square at the center of current position, and
     * recurses to draw more squares in each of the other locations as long as
     * size > limit and size / 3 != 0
     */
    private static void drawSquares(Graphics g, int size, int limit,
            double x, double y) {
        if (size > limit) {
            final int DIVIDER = 3;
            int subSize = size / DIVIDER;
            if (subSize > 0) {
                for (int i = 0; i < DIVIDER; i++) {
                    for (int j = 0; j < DIVIDER; j++) {
                        int newX = (int) (x + (i * subSize));
                        int newY = (int) (y + (j * subSize));
                        if (i == 1 && j == 1) {
                            g.fillRect(newX, newY, subSize, subSize);
                        } else {
                            drawSquares(g, subSize, limit, newX, newY);
                        }
                    }
                }
            }
        }
    }


    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length,
     * 0 <= col < map[0].length
     * <br>post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map,
     * false otherwise.
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map, false otherwise.
     * Base Case: If the current cell is at the edge of the map, return true
     * Recursive Case: Check each adjacent cell with a lower elevation. Then,
     * recurse on those cells and return true if any path reaches the edge.
     * Otherwise, if no path reaches the edge, return false.
     * @ Throws IllegalArgumentException if the preconiditons are not met.
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {
        if (map == null || map.length == 0 || !isRectangular(map)
                || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "canFlowOffMap");
        }
        if (row == 0 || row == map.length - 1 || col == 0 || col == map[0].length - 1) {
            return true;
        }
        int current = map[row][col];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < directions.length; i++) {
            int newR = row + directions[i][0];
            int newC = col + directions[i][1];
            if (map[newR][newC] < current) {
                if (canFlowOffMap(map, newR, newC)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* helper method for canFlowOffMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null
                && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOffMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null "
                    + "and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }


    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br> post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * @param numTeams the number of teams to form.
     * Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability. The return value will be greater than or equal to 0.
     * Base Case: If every member has been assigned, return Integer.MAX_VALUE if any
     * team is empty, otherwise, return the max team total - min
     * Recursive Case: Assign the current person to each team, recurse on the next person,
     * and undo the assignment after each call. Return the smallest diff found
     * @ Throws IllegalArgumentException if the preconiditons are not met.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        if (numTeams < 2 || abilities == null || abilities.length < numTeams) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "numTeams has to be >= 2, abilities cannot be null "
                    + "and the length of abilities must be>= than the numTeams");
        }
        int[] teamTotals = new int[numTeams];
        int[] teamCounts = new int[numTeams];
        return minDifferenceHelper(abilities, 0, teamTotals, teamCounts);
    }

    /**
     * pre: abilities != null, teamTotals != null, teamCounts != null
     * 0 <= index <= abilities.length, teamTotals.length == teamCounts.length, atleast 2
     * post: Returns the smallest difference of the max and min teams
     * @param abilities the ability score of each person
     * @param index the index that tracks the person
     * @param teamTotals the total score for each team
     * @param teamCounts the total number of people for each team
     * @return the smallest difference of the max and min teams or Integer.MAX_VALUE if no
     * valid order is possible
     */
    private static int minDifferenceHelper(int[] abilities, int index,
                int[] teamTotals, int[] teamCounts) {
        if (index == abilities.length) {
            for (int i = 0; i < teamCounts.length; i++) {
                if (teamCounts[i] == 0) {
                    return Integer.MAX_VALUE;
                }
            }
            return findDifference(teamTotals);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < teamCounts.length && min > 0; i++) {
            teamTotals[i] += abilities[index];
            teamCounts[i] += 1;
            int diff = minDifferenceHelper(abilities, index + 1, teamTotals, teamCounts);
            if (diff < min) {
                min = diff;
            }
            teamTotals[i] -= abilities[index];
            teamCounts[i] -= 1;
        }
        return min;
    }

    /**
     * pre: teamTotals != null
     * post: returns the difference between the maximum
     * and minimum teamTotals
     */
    private static int findDifference(int[] teamTotals) {
        int max = teamTotals[0];
            int min = teamTotals[0];
            for (int i = 1; i < teamTotals.length; i++) {
                max = Math.max(max, teamTotals[i]);
                min = Math.min(min, teamTotals[i]);
            }
        return max - min;
    }

    /**
     * Problem 8: Maze solver.
     * <br>pre: board != null
     * <br>pre: board is a rectangular matrix
     * <br>pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>pre: there is a single 'S' character present
     * <br>post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins.
     * Return 1 if it is possible to escape the maze
     * but without collecting all the coins.
     * Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * @param rawMaze represents the maze we want to escape.
     * rawMaze is not altered as a result of this method.
     * @return per the post condition
     * Base Case: Return 0 if outside the maze or on an impassable cell
     * At an exit, return 2 if there are no more coins, otherwise return 1
     * Recursive Case: Collect a coin if present, update the current cell to
     * its new state after leaving, and recurse to all four directions. Restore
     * the original cell by backtracking and return the highest result found
     * @ Throws IllegalArgumentException if the preconiditons are not met.
     */
    public static int canEscapeMaze(char[][] rawMaze) {
        if (rawMaze == null || !isRectangular(rawMaze)) {
            throw new IllegalArgumentException("Board cannot be null and has "
                     + "to be rectangular.");
        }
        if (!hasValidCells(rawMaze) || !hasSingleStart(rawMaze)) {
            throw new IllegalArgumentException("Board can only contain valid "
                     + "characters and can only have one start cell");
        }
        if (!isExit(rawMaze)) {
            return 0;
        }
        int[] start = findStart(rawMaze);
        int totalCoins = totalCoins(rawMaze);
        return canEscapeMazeHelper(rawMaze, start[0], start[1], totalCoins);
    }

    /**
     * pre: maze != null
     * post: returns true if maze is rectangular, otherwise returns false
     */
    private static boolean isRectangular(char[][] maze) {
        if (maze.length == 0 || maze[0] == null) {
            return false;
        }
        int columns = maze[0].length;
        for (int row = 1; row < maze.length; row++) {
            if (maze[row] == null || maze[row].length != columns) {
                return false;
            }
        }
        return true;
    }

    /**
     * pre: maze != null
     * post: returns true if there are only valid characters, otherwise returns false
     */
    private static boolean hasValidCells(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if ("SE$GY*".indexOf(maze[i][j]) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * pre: maze != null
     * post: returns true if there is only one start, otherwise returns false
     */
    private static boolean hasSingleStart(char[][] maze) {
        int count = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    /**
     * pre: maze != null, coinsRemaining is number of uncollected coins,
     * post: returns best escape result from the current position and leaves
     * maze unchanged after the call
     */
    private static int canEscapeMazeHelper(char[][] maze, int row,
            int col, int coinsRemaining) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return 0;
        }
        char val = maze[row][col];
        if (val == '*' || val == 'E') {
            return earlyExit(val, coinsRemaining);
        }
        if (val == '$') {
            coinsRemaining -= 1;
        }
        maze[row][col] = newValue(val);
        int best = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < directions.length && best < 2; i++) {
            int newR = row + directions[i][0];
            int newC = col + directions[i][1];
            int newVal = canEscapeMazeHelper(maze, newR, newC, coinsRemaining);
            if (newVal > best) {
                best = newVal;
            }
        }
        maze[row][col] = val;
        return best;
    }

    /**
     * pre: val == '*' || val == 'E', coinsRemaining >= 0
     * post: returns the specific value if an early exit is found
     */
    private static int earlyExit(char val, int coinsRemaining) {
        if (val == '*') {
            return 0;
        } else if (coinsRemaining == 0) {
            return 2;
        }
        return 1;
    }

    /**
     * pre: val equals 'S', 'G', 'Y', or '$'
     * post: returns the new value of the element after leaving
     */
    private static char newValue(char val) {
        if (val == 'S') {
            return 'G';
        } else if (val == 'G') {
            return 'Y';
        } else if (val == 'Y') {
            return '*';
        }
        return 'Y';
    }

    /**
     * pre: rawMaze != null
     * post: returns the position of the starting element
     */
    private static int[] findStart(char[][] rawMaze) {
        int[] start = new int[2];
        for (int i = 0; i < rawMaze.length; i++) {
            for (int j = 0; j < rawMaze[0].length; j++) {
                if (rawMaze[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                    return start;
                }
            }
        }
        return null;
    }

    /**
     * pre: rawMaze != null
     * post: returns the total number of coins in the maze
     */
    private static int totalCoins(char[][] rawMaze) {
        int count = 0;
        for (int i = 0; i < rawMaze.length; i++) {
            for (int j = 0; j < rawMaze[0].length; j++) {
                if (rawMaze[i][j] == '$') {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * pre: rawMaze != null
     * post: returns if there is an exit
     */
    private static boolean isExit(char[][] rawMaze) {
        for (int i = 0; i < rawMaze.length; i++) {
            for (int j = 0; j < rawMaze[0].length; j++) {
                if (rawMaze[i][j] == 'E') {
                    return true;
                }
            }
        }
        return false;
    }
}
