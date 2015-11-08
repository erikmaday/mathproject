package base;

/**
 * @author Erik Maday
 * @version 1.0
 */
public class Matrix {

    private double[][] matrix;
    private int height;
    private int width;

    /**
     * Set a matrix to the given input
     * @param matrix the input matrix
     */
    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    /** Set a single element.
     * @param i row index.
     * @param j column index.
     * @param s matrix(i,j).
     * @exception ArrayIndexOutOfBoundsException
     */
    public void set(int i, int j, double s) {
        matrix[i][j] = s;
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

    /** Construct an m-by-n matrix of zeros.
     * @param height number of rows.
     * @param width number of columns.
     */
    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        matrix = new double[height][width];
    }


    /** Construct an m-by-n constant matrix.
     * @param height number of rows.
     * @param width number of columns.
     * @param s scalar with which to fill the matrix
     */
    public Matrix(int height, int width, double s) {
        this.height = height;
        this.width = width;
        matrix = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = s;
            }
        }
    }

    /**
     * Gets value located at specified location
     * @param r row
     * @param c column
     * @return double located at row r and column c in matrix
     */
    public double get(int r, int c) {
        if (r > height || c > width) {
            throw new IndexOutOfBoundsException(
                    "That is not a valid index in the matrix.");
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
     * Get a column vector from the matrix
     * @param heightIndex index of the column to start at
     * @param widthIndex index of the row to start at
     * @return Column vector
     */
    public double[] getColumnVector(int heightIndex, int widthIndex) {
        try {
            double[] colVector = new double[this.height - heightIndex];
            for (int i = heightIndex; i < this.height; i++) {
                colVector[i - heightIndex] = this.matrix[i][widthIndex];
            }
            return colVector;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Index is not valid");
        }
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