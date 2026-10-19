import java.util.Arrays;
// MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, Hasher, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: hmh3233
*  email address: hmh3233@my.utexas.edu
*  Unique section number: 55010
*  Number of slip days I am using: 0
 */
/**
 * A class that models systems of linear equations (Math Matrices) as used in
 * linear algebra.
 */
public class MathMatrix {

    // integer entries are stored in this MathMatrix
    private int[][] values;

    /**
     * Create a MathMatrix with cells equal to the values in mat. A "deep" copy
     * of mat is made. Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     *
     * @param mat mat !=null, mat.length > 0, mat[0].length > 0, mat is a
     * rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        if (!rectangularMatrix(mat)) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "mat must not be null, the number of rows and columns "
                    + "must both be greater than 0, and the matrix must "
                    + "be a rectangular matrix");
        }
        values = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                values[i][j] = mat[i][j];
            }
        }
    }

    /**
     * Create a MathMatrix of the specified size with all cells set to the
     * initial value.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. All
     * elements of this matrix equal initialVal. In other words after this
     * method has been called getVal(r, c) = initialVal for all valid r and c.
     *
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        if ((numRows <= 0) || (numCols <= 0)) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "the number of rows and columns must both be greater than 0.");
        }
        values = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                values[i][j] = initialVal;
            }
        }
    }

    /**
     * Get the number of rows.
     * <br>pre: none
     *
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return values.length;
    }

    /**
     * Get the number of columns.
     * <br>pre: none
     *
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns() {
        return values[0].length;
    }

    /**
     * Get the value of a cell in this MathMatrix.
     * <br>pre: row 0 <= row < getNumRows(), col 0 <= col < getNumColumns()
     * @param  row 0 <= row < getNumRows() 
     * @param col 0 <= col < getNumColumns() 
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
        if ((row < 0) || (row >= getNumRows()) || (col < 0) || (col >= getNumColumns())) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "the row and column indices must both be between 0 and "
                    + "their respective getNumRows()/getNumColumns()");
        }
        return values[row][col];
    }

    /**
     * Implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() =
     * getNumRows(), rightHandSide.getNumColumns() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     *
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.getNumColumns() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to
     * rightHandSide. The number of rows in the returned Matrix is equal to the
     * number of rows in this MathMatrix. The number of columns in the returned
     * Matrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide) {
        if ((rightHandSide == null) || (rightHandSide.getNumRows() != getNumRows())
                || (rightHandSide.getNumColumns() != getNumColumns())) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "rightHandSide must not be null and must have the same number "
                    + "of rows and columns as in this MathMatrix");
        }
        MathMatrix result = new MathMatrix(getNumRows(), getNumColumns(), 0);
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                result.values[i][j] = values[i][j] + rightHandSide.getVal(i, j);
            }
        }
        return result;
    }

    /**
     * Implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() =
     * getNumRows(), rightHandSide.getNumColumns() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     *
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.getNumColumns() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide
     * from this MathMatrix. The number of rows in the returned MathMatrix is
     * equal to the number of rows in this MathMatrix.The number of columns in
     * the returned MathMatrix is equal to the number of columns in this
     * MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide) {
        if ((rightHandSide == null) || (rightHandSide.getNumRows() != getNumRows())
                || (rightHandSide.getNumColumns() != getNumColumns())) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "rightHandSide must not be null and the number of columns "
                    + "and rows in rightHandSide must equal the number of rows "
                    + "and columns in values");
        }
        MathMatrix result = new MathMatrix(getNumRows(), getNumColumns(), 0);
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                result.values[i][j] = values[i][j] - rightHandSide.getVal(i, j);
            }
        }
        return result;
    }

    /**
     * Implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() =
     * getNumColumns()
     * <br>post: This method does not alter the calling object or
     * rightHandSide and returns the multiplication result MathMatrix 
     * of the two inputted MathMatrix's
     *
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this
     * MathMatrix and rightHandSide. The number of rows in the returned
     * MathMatrix is equal to the number of rows in this MathMatrix. The number
     * of columns in the returned MathMatrix is equal to the number of columns
     * in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide) {
        if ((rightHandSide == null) || (rightHandSide.getNumRows() != getNumColumns())) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "the rightHandSide array must not be null and the number "
                    + "of rows in rightHandSide must equal the number of columns "
                    + "in values");
        }
        MathMatrix result = new MathMatrix(values.length, rightHandSide.getNumColumns(), 0);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < rightHandSide.getNumColumns(); j++) {
                int sum = 0;
                for (int k = 0; k < values[0].length; k++) { 
                    sum += values[i][k] * rightHandSide.getVal(k, j); 
                    // multiplies the correct combination and adds to total sum value
                }
                result.values[i][j] = sum;
            }
        }
        return result;
    }

    /**
     * Create and return a new Matrix that is a copy of this matrix, but with
     * all values multiplied by a scale value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix
     * multiplied by factor. In other words after this method has been called
     * returned_matrix.getVal(r, c) = original_matrix.getVal(r, c) * factor for
     * all valid r and c.
     *
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
        MathMatrix result = new MathMatrix(values.length, values[0].length, 0);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                result.values[i][j] = values[i][j] * factor;
            }
        }
        return result;
    }

    /**
     * Accessor: get a transpose of this MathMatrix. This Matrix is not changed.
     * <br>pre: none
     *
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        MathMatrix result = new MathMatrix(values[0].length, values.length, 0);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                result.values[j][i] = values[i][j];
            }
        }
        return result;
    }

    /**
     * <br>pre: none
     *
     * @param rightHandSide is the object to compare to this MathMatrix
     * @return true if rightHandSide is the same size as this MathMatrix and all
     * values in the two MathMatrix objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object rightHandSide) {
        if (rightHandSide == null || this.getClass() != rightHandSide.getClass()) {
            return false;
        }
        MathMatrix otherMathMatrix = (MathMatrix) rightHandSide;
        if ((getNumRows() != otherMathMatrix.getNumRows())
                || (getNumColumns() != otherMathMatrix.getNumColumns())) {
            return false;
        }

        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                if (values[row][col] != otherMathMatrix.getVal(row, col)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * overrides Java toString.
     * <br>pre: none
     *
     * @return a String with all elements of this MathMatrix. Each row is on a
     * separate line. Spacing based on longest element in this Matrix.
     */
    @Override
    public String toString() {
        int maxLength = findMaxLength();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            result.append("|");
            for (int j = 0; j < values[0].length; j++) {
                String value = "" + values[i][j];
                int lengthOfInt = value.length();
                for (int space = 0; space < ((maxLength + 1) - lengthOfInt); space++) {
                    result.append(" "); 
                }
                result.append(value);
            }
            result.append("|\n");
        }
        return result.toString();
    }

    /**
     * Finds the length of the longest string representation of any integer
     * in the MathMatrix()
     * <br>pre: none
     * <br>post: returns the number of characters in the longest integer
     * values, including its negative sign if applicable
     */
    private int findMaxLength() {
        int maxLength = 0;
        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[row].length; col++) {
                int length = ("" + values[row][col]).length();
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }
        return maxLength;
    }

    /**
     * Return true if this MathMatrix is upper triangular. To be upper
     * triangular all elements below the main diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()
     *
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise.
     */
    public boolean isUpperTriangular() {
        if (getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "the number of rows has to be equal to the number of columns ");
        }
        for (int i = getNumRows() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (values[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Ensure mat is a rectangular 2d array of ints. Method is public so client
     * classes can also use it.
     *
     * @param mat mat != null, mat must have at least one row, there must be at
     * least one column in the first row, and all rows in mat must have the same
     * number of columns.
     * @return true if mat is rectangular, false otherwise.
     */
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        } else if (mat[0] == null) {
            throw new IllegalArgumentException("argument may not have null rows. "
                    + "mat = " + Arrays.toString(mat));
        } else if (mat[0].length == 0) {
            throw new IllegalArgumentException("argument mat must have at least "
                    + "one column per row.");
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            if (mat[row] == null) {
                throw new IllegalArgumentException("argument may not have null rows. "
                        + "mat = " + Arrays.toString(mat));
            }
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}
