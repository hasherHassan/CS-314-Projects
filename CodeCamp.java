//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 *
 *
 *  On my honor, Hasher Hassan, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Hasher Hassan
 *  email address: hmh3233@my.utexas.edu
 *  UTEID: hmh3233
 *  Section 5 digit ID: 55010
 *  Number of slip days used on this assignment: 0
 */

public class CodeCamp {

    /**
     * Determine the Hamming distance between two arrays of ints.
     * Neither the parameter <tt>aData</tt> or
     * <tt>bData</tt> are altered as a result of this method.<br>
     * @param aData != null, aData.length == bData.length
     * @param bData != null
     * @return the Hamming Distance between the two arrays of ints.
     */
    public static int hammingDistance(int[] aData, int[] bData) {
        // check preconditions
        if (aData == null || bData == null || aData.length != bData.length) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "hammingDistance. neither parameter may equal null, arrays" +
                    " must be equal length.");
        }
        int distance = 0;
        for (int i = 0; i < aData.length; i++) {
            if (aData[i] != bData[i]) {
                distance++;
            }
        }
        return distance;
    }


    /**
     * Determine if one array of ints is a permutation of another.
     * Neither the parameter <tt>aData</tt> or
     * the parameter <tt>bData</tt> are altered as a result of this method.<br>
     * @param aData != null
     * @param bData != null
     * @return <tt>true</tt> if aData is a permutation of bData,
     * <tt>false</tt> otherwise
     *
     */
    public static boolean isPermutation(int[] aData, int[] bData) {
        // check preconditions
        if (aData == null || bData == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isPermutation. neither parameter may equal null.");
        }
        if (aData.length != bData.length) {
            return false;
        }
        for (int i = 0; i < aData.length; i++) { 
            int countA = 0;
            int countB = 0;
            for (int j = 0; j < aData.length; j++) {
                if (aData[j] == aData[i]) {
                    countA++;
                }
            }
            for (int j = 0; j < bData.length; j++) {
                if (bData[j] == aData[i]) {
                    countB++;
                }
            }
            if (countA != countB) {
                return false;
            }
        }
        return true;
    }


    /**
     * Determine the index of the String that
     * has the largest number of vowels.
     * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o',
     * 'U', and 'u'</tt>.
     * The parameter <tt>arrayOfStrings</tt> is not altered as a result of this method.
     * <p>pre: <tt>arrayOfStrings != null</tt>, <tt>arrayOfStrings.length > 0</tt>,
     * there is an least 1 non null element in arrayOfStrings.
     * <p>post: return the index of the non-null element in arrayOfStrings that has the
     * largest number of characters that are vowels.
     * If there is a tie return the index closest to zero.
     * The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.<br>
     * @param arrayOfStrings the array to check
     * @return the index of the non-null element in arrayOfStrings that has
     * the largest number of vowels.
     */
    public static int mostVowels(String[] arrayOfStrings) {
        // check preconditions
        if (arrayOfStrings == null || arrayOfStrings.length == 0
                || !atLeastOneNonNull(arrayOfStrings)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "mostVowels. parameter may not equal null and must contain " +
                    "at least one none null value.");
        }
        int maxVowels = -1;
        int maxVowelsIndex = 0;
        final String VOWELS = "AEIOUaeiou";
        for (int i = 0; i < arrayOfStrings.length; i++) {
            int vowelCount = 0;
            String word = arrayOfStrings[i];
            if (word != null) {
                for (int letterIndex = 0; letterIndex < word.length(); letterIndex++) {
                    if (VOWELS.indexOf(word.charAt(letterIndex)) >= 0) {
                        vowelCount++;
                    }
                }
                if (vowelCount > maxVowels) {
                    maxVowels = vowelCount;
                    maxVowelsIndex = i;
                }
            }
        }
        return maxVowelsIndex;
    }



    /**
     * Perform an experiment simulating the birthday problem.
     * Pick random birthdays for the given number of people.
     * Return the number of pairs of people that share the
     * same birthday.<br>
     * @param numPeople The number of people in the experiment.
     * This value must be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     * This value must be > 0
     * @return The number of pairs of people that share a birthday
     * after running the simulation.
     */
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople +
                    ", numDaysInYear: " + numDaysInYear);
        }
        int[] birthdays = new int[numPeople];
        int sharedPairs = 0;
        for (int i = 0; i < numPeople; i++) {
            int day = (int) (Math.random() * numDaysInYear);
            birthdays[i] = day;
        }
        for (int i = 0; i < birthdays.length; i++) {
            for (int j = i + 1;
                 j < birthdays.length; j++) {
                if (birthdays[i] == birthdays[j]) {
                    sharedPairs++;
                }
            }
        }
        return sharedPairs;
    }

    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>pre: board != null, board.length > 0, board is a square matrix.
     * (In other words all rows in board have board.length columns.),
     * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
     * represent open spaces.<br>
     * <p>post: return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     * the parameter <tt>board</tt> is not altered as a result of
     * this method.<br>
     * @param board the chessboard
     * @return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     */
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board)
                || !onlyContains(board, validChars)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "queensAreSafe. The board may not be null, must be square, " +
                    "and may only contain 'q's and '.'s");
        }
        // Use row and column directions to represent all 8 queen directions
        int[] rowDirections = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colDirections = {-1, 0, 1, 1, -1, -1, 0, 1};
        // Check every queen for another queen on any attack direction
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 'q') {
                    if (!checkDirections(board, row, col, rowDirections, colDirections)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*
     * pre: board is not null, col and row are valid inputs for the given board
     *      rowDirections and colDirections have the proper directional values
     * post: returns false if another queen can be attacked from the given
     *      position, otherwise it returns true
     */
    private static boolean checkDirections(char[][] board, int row, int col,
            int[] rowDirections, int[] colDirections) {
        // Follow each direction until reaching the bound of board or another queen
        for (int direction = 0; direction < rowDirections.length; direction++) {
            int nextRow = row + rowDirections[direction];
            int nextCol = col + colDirections[direction];
            while (nextRow >= 0 && nextRow < board.length
                    && nextCol >= 0 && nextCol < board.length) {
                if (board[nextRow][nextCol] == 'q') {
                    return false;
                }
                nextRow += rowDirections[direction];
                nextCol += colDirections[direction];
            }
        }
        return true;
    }

    /**
     * Given a 2D array of ints return the value of the
     * most valuable contiguous sub rectangle in the 2D array.
     * The sub rectangle must be at least 1 by 1.
     * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>post: return the value of the most valuable contiguous sub rectangle
     * in <tt>city</tt>.<br>
     * @param city The 2D array of ints representing the value of
     * each block in a portion of a city.
     * @return return the value of the most valuable contiguous sub rectangle
     * in <tt>city</tt>.
     */
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0
                || !isRectangular(city)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "getValueOfMostValuablePlot. The parameter may not be null," +
                    " must value at least one row and at least" +
                    " one column, and must be rectangular.");
        }
        int max = city[0][0];
        // Try every possible rectnagle by choosing its top left corner and its bottom right corner points
        for (int topRow = 0; topRow < city.length; topRow++) {
            for (int leftColumn = 0; leftColumn < city[topRow].length; leftColumn++) {
                for (int bottomRow = topRow; bottomRow < city.length; bottomRow++) {
                    for (int rightColumn = leftColumn;
                         rightColumn < city[bottomRow].length; rightColumn++) {
                        int sum = getPlotSum(city, topRow, leftColumn,
                                bottomRow, rightColumn);
                        if (sum > max) {
                            max = sum;
                        }
                    }
                }
            }
        }
        return max;
    }

    /*
     * pre: city is rectangular, and topRow, leftColumn, bottomRow,
     *      and rightColumn create a valid rectangle
     * post: returns the sum of all the values in the specified rectangle
     */
    private static int getPlotSum(int[][] city, int topRow, int leftColumn,
            int bottomRow, int rightColumn) {
        int sum = 0;
        for (int row = topRow; row <= bottomRow; row++) {
            for (int col = leftColumn; col <= rightColumn; col++) {
                sum += city[row][col];
            }
        }
        return sum;
    }

    /*
     * pre: arrayOfStrings != null
     * post: return true if at least one element in arrayOfStrings is
     * not null, otherwise return false.
     */
    private static boolean atLeastOneNonNull(String[] arrayOfStrings) {
        // check precondition
        if (arrayOfStrings == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "atLeastOneNonNull. parameter may not equal null.");
        }
        boolean foundNonNull = false;
        int i = 0;
        while (!foundNonNull && i < arrayOfStrings.length) {
            foundNonNull = arrayOfStrings[i] != null;
            i++;
        }
        return foundNonNull;
    }


    /*
     * pre: mat != null
     * post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isSquare. Parameter may not be null.");
        }
        final int NUM_ROWS = mat.length;
        int row = 0;
        boolean isSquare = true;
        while (isSquare && row < NUM_ROWS) {
            isSquare = (mat[row] != null) && (mat[row].length == NUM_ROWS);
            row++;
        }
        return isSquare;
    }


    /*
     * pre: mat != null, valid != null
     * post: return true if all elements in mat are one of the
     * characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if (mat == null || valid == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "onlyContains. Parameters may not be null.");
        }
        String validChars = new String(valid);
        int row = 0;
        boolean onlyContainsValidChars = true;
        while (onlyContainsValidChars && row < mat.length) {
            int col = 0;
            while (onlyContainsValidChars && col < mat[row].length) {
                int indexOfChar = validChars.indexOf(mat[row][col]);
                onlyContainsValidChars = indexOfChar != -1;
                col++;
            }
            row++;
        }
        return onlyContainsValidChars;
    }


    /*
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isRectangular. Parameter may not be null and must contain" +
                    " at least one row.");
        }
        boolean correct = mat[0] != null;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null)
                    && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }

    // make constructor private so no instances of CodeCamp can be created
    private CodeCamp() {

    }
}
