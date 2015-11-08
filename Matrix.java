import java.lang.IndexOutOfBoundsException;

/**
 * @author Erik Maday
 * @version 1.0
 */
public class Matrix {

    private double[][] matrix;
    private int height;
    private int width;

    public Matrix() {}

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Initialize instance variables
     * @param matrix 2D array representation of Matrix
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.height = matrix.length;
        this.width = matrix[0].length;
    }

    /**
     * Gets value located at specified location
     * @param r row
     * @param c column
     * @return double located at row r and column c in matrix
     */
    public double get(int r, int c) {
        if (r > height || c > width) {
            throw new IndexOutOfBoundsException("That is not a valid index in the matrix.");
        }
        return matrix[r][c];
    }

    /**
     * Returns the matrix as an array of doubles
     * @return matrix
     */
    public double[][] toArray() {
        return matrix;
    }

    /**
     * Returns height of the matrix.
     * @return number of rows in matrix
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the matrix.
     * @return number of columns in matrix
     */
    public int getWidth() {
        return width;
    }

    /**
     * String representation of matrix
     * Columns seperated by tabs, rows seperated by new lines
     * @return String representation of matix
     */
    public String toString() {
        String buf = "";
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                buf = buf + "    " + matrix[r][c];
            }
            buf = buf + "\n";
        }
        return buf;
    }
}