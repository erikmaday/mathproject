package base;

/**
 * Linear Algebra Class that contains methods
 * for matrix * vector,
 * matrix + matrix,
 * vector . vector,
 * and vector + vector
 *
 * @author Erik Maday
 * @version 1.0
 */

public class LinearAlgebra {

    /**
     * Gets vector calculated by multiplying a matrix by a vector
     * Precondition: matrix width must match vector length
     * @param m Matrix
     * @param v Vector
     * @return Vector product
     */
    public static Vector matrixVectorMultiply(Vector v, Matrix m) {
        int mHeight = m.getHeight();
        int mWidth = m.getWidth();
        if (v.getSize() != mWidth) {
            //TODO
            //throw size mismatch error
        }
        double[] prod = new double[mHeight];
        for (int r = 0; r < mHeight; r++) {
            for (int c = 0; c < mWidth; c++) {
                prod[r] += (m.get(r, c) * v.get(c));
            }
        }
        return new Vector(prod);
    }

    /**
     * Calculates Matrix generated by adding two matrices.
     * @param m1 Matrix first to be added
     * @param m2 Matrix second to be added
     * checks to make sure the sizes match up
     * @return Matrix sum
     */
    public static Matrix matrixAddition(Matrix m1, Matrix m2) {
        int m1Height = m1.getHeight();
        int m1Width = m2.getWidth();
        if (m1Height != m2.getHeight() || m1Width != m2.getWidth()) {
            //TODO
            //throw size mismatch error
        }
        double[][] matrix = new double[m1Height][m1Width];
        for (int r = 0; r < m1Height; r++) {
            for (int c = 0; c < m1Width; c++) {
                matrix[r][c] = m1.get(r, c) + m2.get(r, c);
            }
        }
        return new Matrix(matrix);
    }

    public static Matrix matrixSubtraction(Matrix A, Matrix B) {
        if (A == null || B == null) {
            throw new IllegalArgumentException("Illegal parameter.");
        }
        //TODO
        //Throw size mismatch error
        double[][] a = B.toArray();
        double[][] m = A.toArray();
        double[][] sub = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                sub[i][j] = m[i][j] - a[i][j];
            }
        }
        return new Matrix(sub);
    }

    /**
     * Multiplies the first matrix by the second.
     * @param A the first matrix
     * @param B the second matrix
     * @return A * B
     */
    public static double[][] multiplyMatrix(double[][] A, double[][] B) {
        //TODO 
        //check for size mismatch

        int aRows = A.length;
        int aCols = A[0].length;
        int bCols = B[0].length;

        double[][] ret = new double[aRows][bCols];
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 2; c++) {
                ret[r][c] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    ret[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return ret;
    }

    /**
     * Returns the dot product of two vectors
     * @param v1 Vector
     * @param v2 Vector
     * @return double dot product
     * check to make sure size of v1 and v2 match
     */
    public static double dotProduct(Vector v1, Vector v2) {
        double dotproduct = 0;
        if (v1.getSize() != v2.getSize()) {
            //TODO
            //Throw size mismatch error
        } else {
            for (int i = 0; i < v1.getSize(); i++) {
                dotproduct += v1.get(i) * v2.get(i);
            }
        }
        return dotproduct;
    }

    /**
     * Returns Vector generated by adding two vectors.
     * @param v1 Vector
     * @param v2 Vector
     * @return Vector calculated
     */
    public static Vector vectorAdd(Vector v1, Vector v2) {
        int v1Size = v1.getSize();
        if (v1Size != v2.getSize()) {
            //Throw size mismatch error
        }
        double[] arr = new double[v1Size];
        for (int i = 0; i < v1Size; i++) {
            arr[i] = v1.get(i) + v2.get(i);
        }
        return new Vector(arr);
    }

    /**
     * Transposes vector and returns it as a 2d array of one column
     * @param v the vector to transpose
     * @return vector passed in as a column vector
     */
    public static double[][] vectorTranspose(Vector v) {
        double[][] toReturn = new double[v.getSize()][1];
        for (int i = 0; i < v.getSize(); i++) {
            toReturn[i][0] = v.get(i);
        }
        return toReturn;
    }

    /**
     * Multiplies a vector by its transpose to create a matrix
     * Used in Householder reflections.
     * @param v the vector to transpose
     * @return Matrix
     */
    public static Matrix vectorVectorTransposeMultiply(Vector v) {
        double[][] matrix = new double[v.getSize()][v.getSize()];
        for (int i = 0; i < v.getSize(); i++) {
            for (int j = 0; j < v.getSize(); j++) {
                matrix[i][j] = v.get(i) * v.get(j);
            }
        }
        return new Matrix(matrix);
    }

    /**
     * Calculates the magnitude of a vector
     * @param v the vector to find the magnitude of
     * @return double magnitude of vector
     */
    public static double magnitude(Vector v) {
        double sum = 0;
        for (int i = 0; i < v.getSize(); i++) {
            sum += v.get(i) * v.get(i);
        }
        return Math.sqrt(sum);
    }

    /**
     * Multiplies an nx1 matrix and a 1xn vector
     * Used for U * U^T
     * @param matrix the given matrix
     * @param vector the given vector
     * @return U * U^T
     */
    public static Matrix transposeMultiply(Matrix matrix, Vector vector) {
        double[][] matrixArr = matrix.toArray();
        double[][] ret = new double[matrixArr[0].length][vector.getSize()];
        for (int i = 0; i < matrixArr.length; i++) {
            for (int j = 0; j < vector.getSize(); j++) {
                ret[i][j] = matrixArr[i][0] * vector.get(j);
            }
        }
        return new Matrix(ret);
    }

    /**
     * multiplies a vector by a scalar
     * @param v the given vector
     * @param s the given scalar
     * @return Vector
     */
    public static Vector scalarVectorMultiply(Vector v, int s) {
        double[] ret = v.toArray();
        for (int i = 0; i < ret.length; i++) {
            ret[i] *= s;
        }
        return new Vector(ret);
    }

    /**
     * multiplies a matrix by a scalar
     * @param m the given matrix
     * @param s the given scalar
     * @return Matrix
     */
    public static Matrix scalarMatrixMultiply(Matrix m, int s) {
        double[][] ret = m.toArray();
        for (int r = 0; r < ret.length; r++) {
            for (int c = 0; c < ret[0].length; c++) {
                ret[r][c] *= s;
            }
        }
        return new Matrix(ret);
    }

    /**
     * Takes in a matrix mxn and transposes it.
     * @param m the matrix to transpose
     * @return transpose Matrix nxm
     */
    public static double[][] transposeMatrix(double[][] m) {
        double[][] ret = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                ret[j][i] = m[i][j];
            }
        }
        return ret;
    }

    public static double norm(Matrix m) {
        double max = 0;
        double[][] matrix = m.toArray();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (Math.abs(matrix[i][j]) > max) {
                    max = Math.abs(matrix[i][j]);
                }
            }
        }
        return max;
    }

    /**
     * Find the determinant of the given matrix
     * @param matrix the given matrix
     * @param n the size
     * @return the determinant of the matrix
     */
    public static double determinant(Matrix matrix, int n) {
        double[][] m = matrix.toArray();
        if (n != m[0].length) {
            //throw size exception
        }

        double det = 0;
        int sign = 1;
        int p = 0;
        int q = 0;

        if (n == 1) {
            det = m[0][0];
        } else {
            double[][] m2 = new double[n - 1][n - 1];
            for (int x = 0; x < n; x++) {
                p = 0;
                q = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j != x) {
                            m2[p][q++] = m[i][j];
                            if (q % (n - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                det = det + m[0][x] * determinant(new Matrix(m2), n - 1) * sign;
                sign *= -1;
            }
        }
        return det;
    }

    /**
     * Finds the trace of a square matrix by finding the sum of the diagonal
     * @param m Matrix
     * @return tr is the trace of Matrix m
     */
    public static double trace(Matrix m) {
        double tr = 0;
        double val = 0;
        for (int x = m.getHeight(); x > 0; x--) {
            val = m.get(x, x);
            tr += val;
        }
        return tr;
    }
}
