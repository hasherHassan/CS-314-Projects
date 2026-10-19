import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: hmh3233
 *  email address: hmh3233@my.utexas.edu
 *  Number of slip days I am using: 0
 */

/*
 Experiment 1: Addition
 Dimension:             Total Time:
 1000 x 1000            1.294 seconds
 2000 x 2000            4.769 seconds
 4000 x 4000            20.227 seconds

 1. Based on the results of the experiment, if I double dimension
    size again from  4000 x 4000 to 8000 x 8000, I would expect the
     add method to take around 80.908 seconds because doubling N
     should multiply the running time by 4 because the method is O(N^2).

 2. The Big O of the add operation given two N by N matrices is
    O(N^2) because every element in the matrices has to be visited
    at least once. The timing data also supports this since when N
    was doubled, the timing data went from 1.294 seconds to 4.769
    seconds, which is 4.769/1.294 = 3.685 or approximately 4 times
    as long. It then increased from 4.769 to 20.227 seconds, which is
    20.227/4.769 = 4.241 times as long, again roughly 4 times. Since
    O((2N)^2) = O(4N^2), and the timing data shows around a general
    trend of a 4 times longer run time every time we double the data,
    this is supported.

 Experiment 2: Multiplication
 Dimension:             Total Time:
 300 x 300              3.061 seconds
 600 x 600              25.441 seconds
 1200 x 1200            284.789 seconds

 3. Based on the result of experiment 2, if I double the dimension size
    again from 1200 x 1200 to 2400 x 2400, I would expect the multiply
    method to take around 2,278.312 seconds because doubling N should
    multiply the running time by 8 since the method is O(N^3).

 4. The Big O of the multiply operation given two N by N matrices is
    O(N^3) because the code uses three nested for loops. The timing
    data supports this since when N is doubled, the timing data went
    from 3.061 seconds to 25.441 seconds, which is 25.441/3.061 = 8.311
    times or approximately 8 times. However, it increased from 25.441
    seconds to 284.789 seconds, which is a 284.789/25.441 = 11.19
    or approximately 11 times as long, but this can be accounted for by
    memory use or other system activity outside of the norm. Since O((2N)^3)
    = O(8N^3), and the timing data shows a general trend of around an 8
    times longer run time every time we double the data, this is supported.

 5. The largest MathMatrix that I was able to successfully create was
    45586 x 45586. Its int[][] contained 2,078,083,396 integers. 
    Since each integer requires 4 bytes, the matrix required 8,312,333,584
    bytes, or approximately 8.312 GB. A 45587 x 45587 matrix first caused
    an OutOfMemoryError. My computer has 31.8 GB of usable RAM, so the
    program used approximately (8.312/31.8) x 100 or around 26.14% of
    my computer's usable RAM before crashing.

*/

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[][] data1 = { {3, 6, 9},
                {2, 4, 6},
                {4, 8, 12}};
        int[][] data2 = { {6, 3, 3},
                {2, 4, 1},
                {7, 6, 9}};
        int[][] expected;

        // test 1, size/value constructor, getNumRows(), getNumColumns(),
        // and getVal() with positive initial value
        MathMatrix mat1 = new MathMatrix(2, 3, 1);
        expected = new int[][] {{1, 1, 1}, {1, 1, 1}};
        printTestResult(get2DArray(mat1), expected, 1,
    "Constructor with size and positive initial val specified");

        // test 2, size/value constructor, getNumRows(), getNumColumns(),
        // and getVal() with negative initial value
        mat1 = new MathMatrix(4, 2, -1);
        expected = new int[][] {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}};
        printTestResult(get2DArray(mat1), expected, 2,
    "Constructor with size and negative initial val specified");

        // test 3 , are all the values copied correctly from an int[][]
        mat1 = new MathMatrix(data1);
        expected = new int[][] {{3, 6, 9}, {2, 4, 6}, {4, 8, 12}};
        printTestResult(get2DArray(mat1), expected, 3,
                "constructor with one parameter of type int[][] and " +
                "seeing if the values were correctly copied");

        // test 4, altered data1, test to see if mat1 is unchanged if deep copy is made
        mat1 = new MathMatrix(data1);
        data1[0][0] = 5;
        expected = new int[][] {{3, 6, 9}, {2, 4, 6}, {4, 8, 12}};
        printTestResult(get2DArray(mat1), expected, 4,
                "constructor with one parameter of type int[][] and " +
                "seeing if a deep copy is correctly made");

        data1[0][0] = 3;
        // test 5, add two matrices with positive values
        mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);
        MathMatrix result = mat1.add(mat2);
        expected = new int[][] {{9, 9, 12}, {4, 8, 7}, {11, 14, 21}};
        printTestResult(get2DArray(result), expected, 5,
    "add method with positive values");

        // test 6, add two matrices with negative and zero values
        int[][] left = {{-3, 0, 4}, {4, -2, 9}};
        int[][] right = {{1, -7, 2}, {0, 8, 5}};
        mat1 = new MathMatrix(left);
        mat2 = new MathMatrix(right);
        result = mat1.add(mat2);
        expected = new int[][] {{-2, -7, 6}, {4, 6, 14}};
        printTestResult(get2DArray(result), expected, 6,
    "add method with negative and zero values");

        // test 7, subtract with only positive resulting values
        mat1 = new MathMatrix(new int[][] {{7, 5}, {6, 10}});
        mat2 = new MathMatrix(new int[][] {{2, 1}, {3, 5}});
        result = mat1.subtract(mat2);
        expected = new int[][] {{5, 4}, {3, 5}};
        printTestResult(get2DArray(result), expected, 7,
    "subtraction method with only positive resulting values");

        // test 8, subtract that results in some negative values
        mat1 = new MathMatrix(new int[][] {{6, 5}, {2, 8}});
        mat2 = new MathMatrix(new int[][] {{10, 7}, {3, 5}});
        result = mat1.subtract(mat2);
        expected = new int[][] {{-4, -2}, {-1, 3}};
        printTestResult(get2DArray(result), expected, 8,
    "subtraction method with some negative resulting values");

        // test 9, multiply two square matrices
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data2);
        result = mat1.multiply(mat2);
        expected = new int[][] {{93, 87, 96}, {62, 58, 64}, {124, 116, 128}};
        printTestResult(get2DArray(result), expected, 9,
    "multiply method with square matrices");

        // test 10, multiply with two compatible rectangular matrices
        mat1 = new MathMatrix(new int[][] {{1, 2, 3}, {4, 5, 6}});
        mat2 = new MathMatrix(new int[][] {{7, 8}, {9, 10}, {11, 12}});
        result = mat1.multiply(mat2);
        expected = new int[][] {{58, 64}, {139, 154}};
        printTestResult(get2DArray(result), expected, 10,
    "multiply method with compatible rectangular matrices");

        // test 11, scale by a positive value
        mat1 = new MathMatrix(data1);
        result = mat1.getScaledMatrix(3);
        expected = new int[][] {{9, 18, 27}, {6, 12, 18}, {12, 24, 36}};
        printTestResult(get2DArray(result), expected, 11,
    "scale method by scaling with a positive value");

        // test 12, scale by a negative value
        mat1 = new MathMatrix(data1);
        result = mat1.getScaledMatrix(-2);
        expected = new int[][] {{-6, -12, -18}, {-4, -8, -12}, {-8, -16, -24}};
        printTestResult(get2DArray(result), expected, 12,
    "scale method by scaling with a negative value");

        // test 13, transpose a square matrix
        mat1 = new MathMatrix(new int[][] {{1, 2}, {3, 4}});
        result = mat1.getTranspose();
        expected = new int[][] {{1, 3}, {2, 4}};
        printTestResult(get2DArray(result), expected, 13,
    "transpose method with a square matrix");

        // test 14, transpose a rectangular matrix
        mat1 = new MathMatrix(new int[][] {{1, 2, 3}, {4, 5, 6}});
        result = mat1.getTranspose();
        expected = new int[][] {{1, 4}, {2, 5}, {3, 6}};
        printTestResult(get2DArray(result), expected, 14,
    "transpose method with a rectangular matrix");

        // test 15, tests if two matrices with same values and dimensions are equal
        mat1 = new MathMatrix(new int[][] {{1, 2}, {3, 4}, {5, 6}});
        mat2 = new MathMatrix(new int[][] {{1, 2}, {3, 4}, {5, 6}});
        if (mat1.equals(mat2)) {
            System.out.println("Test number 15 tests the equals method with 2 equal matrices. " +
                        "The test passed.");
        } else {
            System.out.println("Test number 15 tests the equals method with 2 equal matrices. " +
                        "The test failed.");
        }

        // test 16, tests if two matrices with different values are equal
        mat1 = new MathMatrix(new int[][] {{1, 2}, {3, 4}, {5, 6}});
        mat2 = new MathMatrix(new int[][] {{1, 2}, {4, 4}, {5, 6}});
        if (!mat1.equals(mat2)) {
            System.out.println("Test number 16 tests the equals method with 2 unequal matrices. " +
                        "The test passed.");
        } else {
            System.out.println("Test number 16 tests the equals method with 2 unequal matrices. " +
                        "The test failed.");
        }

        // test 17, toString with positive values of different lengths
        data1 = new int[][] {{10, 1000}, {89, 3}};
        mat1 = new MathMatrix(data1);
        String expectedString = "|   10 1000|\n|   89    3|\n";
        if (mat1.toString().equals(expectedString)) {
            System.out.println("Test number 17 tests the toString method with only positive values. " +
                        "The test passed.");
        } else {
            System.out.println("Test number 17 tests the toString method with only positive values. " +
                        "The test failed.");
        }

        // test 18, toString with negative values and zeros
        data1 = new int[][] {{-1, 0}, {67, -100}};
        mat1 = new MathMatrix(data1);
        expectedString = "|   -1    0|\n|   67 -100|\n";
        if (mat1.toString().equals(expectedString)) {
            System.out.println("Test number 18 tests the toString method with negative and zero values. " +
                        "The test passed.");
        } else {
            System.out.println("Test number 18 tests the toString method with negative and zero values. " +
                        "The test failed.");
        }

        // test 19, valid upper-triangular matrix
        mat1 = new MathMatrix(new int[][] {
                {1, 2, 3},
                {0, 4, 5},
                {0, 0, 6}});
        if (mat1.isUpperTriangular()) {
            System.out.println("Test number 19 tests the isUpperTriangular method with a valid matrix. " +
                        "The test passed.");
        } else {
            System.out.println("Test number 19 tests the isUpperTriangular method with a valid matrix. " +
                        "The test failed.");
        }

        // test 20, invalid upper-triangular matrix
        mat1 = new MathMatrix(new int[][] {
                {1, 2, 3},
                {0, 4, 5},
                {6, 0, 7}});
        if (!mat1.isUpperTriangular()) {
            System.out.println("Test number 20 tests the isUpperTriangular method with an invalid matrix. " +
                        "The test passed.");
        } else {
            System.out.println("Test number 20 tests the isUpperTriangular method with an invalid matrix. " +
                        "The test failed.");
        }

        // test 21, tests correct number of rows
        mat1 = new MathMatrix(new int[][] {{1, 2}, {3,4}, {5, 6}});
        if (mat1.getNumRows() == 3) {
            System.out.println("Test number 21 tests the getNumRows method of a MathMatrix. " +
                        "The test passed.");
        } else {
            System.out.println("Test number 21 tests the getNumRows method of a MathMatrix. " +
                        "The test failed.");
        }

        // test 22, tests correct number of columns
        if (mat1.getNumColumns() == 2) {
            System.out.println("Test number 22 tests the getNumColumns method of a MathMatrix. " +
                        "The test passed.");
        } else {
            System.out.println("Test number 22 tests the getNumColumns method of a MathMatrix. " +
                        "The test failed.");
        }
        // Stopwatch s = new Stopwatch();
        // int[][] data1 = new int[1000][1000];
        // int[][] data2 = new int[1000][1000];
        // final int VAL_COUNT = 101;
        // final int OFFSET = 50;
        // for (int i = 0; i < 1000; i++) {
        //     for (int j = 0; j < 1000; j++) {
        //         data1[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //         data2[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //     }
        // }
        // MathMatrix mat1 = new MathMatrix(data1);
        // MathMatrix mat2 = new MathMatrix(data2);
        // s.start();
        // for (int count = 0; count < 1000; count++) {
        //     mat1.add(mat2);
        // }
        // s.stop();
        // System.out.println(s.time());
        // s = new Stopwatch();
        // data1 = new int[2000][2000];
        // data2 = new int[2000][2000];
        // for (int i = 0; i < 2000; i++) {
        //     for (int j = 0; j < 2000; j++) {
        //         data1[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //         data2[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //     }
        // }
        // mat1 = new MathMatrix(data1);
        // mat2 = new MathMatrix(data2);
        // s.start();
        // for (int count = 0; count < 1000; count++) {
        //     mat1.add(mat2);
        // }
        // s.stop();
        // System.out.println(s.time());
        // s = new Stopwatch();
        // data1 = new int[4000][4000];
        // data2 = new int[4000][4000];
        // for (int i = 0; i < 4000; i++) {
        //     for (int j = 0; j < 4000; j++) {
        //         data1[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //         data2[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //     }
        // }
        // mat1 = new MathMatrix(data1);
        // mat2 = new MathMatrix(data2);
        // s.start();
        // for (int count = 0; count < 1000; count++) {
        //     mat1.add(mat2);
        // }
        // s.stop();
        // System.out.println(s.time());
        // s = new Stopwatch();
        // data1 = new int[300][300];
        // data2 = new int[300][300];
        // for (int i = 0; i < 300; i++) {
        //     for (int j = 0; j < 300; j++) {
        //         data1[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //         data2[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //     }
        // }
        // mat1 = new MathMatrix(data1);
        // mat2 = new MathMatrix(data2);
        // s.start();
        // for (int count = 0; count < 100; count++) {
        //     mat1.multiply(mat2);
        // }
        // s.stop();
        // System.out.println(s.time());
        // s = new Stopwatch();
        // data1 = new int[600][600];
        // data2 = new int[600][600];
        // for (int i = 0; i < 600; i++) {
        //     for (int j = 0; j < 600; j++) {
        //         data1[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //         data2[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //     }
        // }
        // mat1 = new MathMatrix(data1);
        // mat2 = new MathMatrix(data2);
        // s.start();
        // for (int count = 0; count < 100; count++) {
        //     mat1.multiply(mat2);
        // }
        // s.stop();
        // System.out.println(s.time());
        // s = new Stopwatch();
        // data1 = new int[1200][1200];
        // data2 = new int[1200][1200];
        // for (int i = 0; i < 1200; i++) {
        //     for (int j = 0; j < 1200; j++) {
        //         data1[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //         data2[i][j] = (int) (Math.random() * VAL_COUNT) - OFFSET;
        //     }
        // }
        // mat1 = new MathMatrix(data1);
        // mat2 = new MathMatrix(data2);
        // s.start();
        // for (int count = 0; count < 100; count++) {
        //     mat1.multiply(mat2);
        // }
        // s.stop();
        // System.out.println(s.time());
        // int dimension = 45586;
        // MathMatrix test = new MathMatrix(dimension, dimension, 1);
        // System.out.println("matrix is created");
    }

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("mat may not be null");
        }
        int result = 0;
        final int ROWS =  mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
                    "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    // Compare two arrays and print whether the test case passed
    private static void printTestResult(int[][] data1, int[][] data2, int testNum,
            String testingWhat) {
        System.out.print("Test number " + testNum + " tests the " + testingWhat + ". The test ");
        String result = equals(data1, data2) ? "passed." : "failed.";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        if ((m == null) || (m.getNumRows() == 0)
                || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = m.getVal(r, c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
    private static boolean equals(int[][] data1, int[][] data2) {
        if ((data1 == null) || (data1.length == 0)
                || (data1[0].length == 0) || !rectangularMatrix(data1)
                || (data2 == null) || (data2.length == 0)
                || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null"
                    + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }
}

