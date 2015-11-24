package PartOne;

import base.LinearAlgebra;
import java.util.Stack;
import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class qr_fact_givens {

    private double[][] Q;
    private double[][] R;
    private double[][] QR;
    private double error;

    /**
     * A method to get Q for QR factorization
     *
     * @return the Q matrix
     */
    public double[][] getQ() {
        return Q;
    }

    /**
     * A method to get R for QR factorization
     *
     * @return the R matrix
     */
    public double[][] getR() {
        return R;
    }

    /**
     * A method to get Q and R for QR factorization
     *
     * @return the QR matrix
     */
    public double[][] getQR() {
        return QR;
    }

    /**
     * Finds the QR factorization of a given matrix using Givens Rotations
     *
     * @param matrix the given matrix
     */
    public qr_fact_givens(double[][] matrix) {
        int height = matrix.length;
        double[][] diagonalMatrix = createDiagonal(height);
        Stack<double[][]> givensMatrices = new Stack<double[][]>();
        double[][] matrixR = matrix;
        int i = 0;
        int j = 0;
        // Calculating Matrix
        while (i < matrixR[0].length) {
            int level = height - 1;
            while (j < level) {
                double x = matrixR[level - 1][i];
                double y = matrixR[level][i];
                double cos = calculateCos(x, y);
                double sin = calculateSin(x, y);
                diagonalMatrix[level][level - 1] = sin;
                diagonalMatrix[level][level] = cos;
                diagonalMatrix[level - 1][level - 1] = cos;
                diagonalMatrix[level - 1][level] = -sin;
                givensMatrices.push(diagonalMatrix);
                matrixR = LinearAlgebra.multiplyMatrix(diagonalMatrix, matrixR);
                diagonalMatrix = createDiagonal(height);
                level--;
            }
            i++;
            j++;
        }

        // Setting R
        this.R = matrixR;
        double[][] first = LinearAlgebra.transposeMatrix(givensMatrices.pop());
        while (!givensMatrices.empty()) {
            first = LinearAlgebra.multiplyMatrix(LinearAlgebra.
                    transposeMatrix(givensMatrices.pop()), first);
        }
        // Setting Q
        this.Q = first;

        // Setting QR
        this.QR = LinearAlgebra.multiplyMatrix(first, matrixR);

        //Calculate Error
        Matrix qTimesR = new Matrix(LinearAlgebra.multiplyMatrix(Q, R));
        Matrix qrMinusA = LinearAlgebra.matrixSubtraction(qTimesR,
                new Matrix(matrix));
        double error = LinearAlgebra.norm(qrMinusA);
        this.error = Math.abs(error);

    }

    /**
     * Changes elements of a matrix
     *
     * @param x a given element
     * @param y a given element
     * @return the calculated change
     */
    public double calculateCos(double x, double y) {
        double bottom = Math.sqrt((x * x) + (y * y));
        return x / bottom;
    }

    /**
     * Changes elements of a matrix
     *
     * @param x a given element
     * @param y a given element
     * @return the calculated change
     */
    public double calculateSin(double x, double y) {
        double bottom = Math.sqrt((x * x) + (y * y));
        y = y * (-1);
        return y / bottom;
    }

    /**
     * A method to create the diagonal for a matrix
     *
     * @param height the height of the matrix
     * @return the diagonal in a 2D array
     */
    public double[][] createDiagonal(int height) {
        double[][] diagonalMatrix = new double[height][height];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < height; y++) {
                if (x == y) {
                    diagonalMatrix[x][y] = 1;
                } else {
                    diagonalMatrix[x][y] = 0;
                }
            }
        }
        return diagonalMatrix;
    }

    /**
     * Returns the error
     * @return the error
     */
    public double getError() {
        return error;
    }
}
