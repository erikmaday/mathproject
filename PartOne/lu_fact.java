package PartOne;

import base.LinearAlgebra;
import base.Matrix;
import java.util.LinkedList;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 */
public class lu_fact {

    private Matrix A;
    private Matrix U;
    private Matrix L;
    private double error;

    /**
     * Constructor, calculates the LU factorization for the given matrix
     * @param q the input matrix
     */
    public lu_fact(Matrix q) {
        this.A = new Matrix(q.getArrayCopy());
        int n;
        n = A.getRowDimension();

        LinkedList<Matrix> matrixList = new LinkedList<>();
        double [][]upper = q.getArray();
        double [][]identity = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    identity[i][j] = 1;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                if (j < i) {
                    double newValue = -1 * upper[i][j] / upper[j][j];
                    double[][] temporary = new double[n][n];
                    for (int m = 0; m < n; m++) {
                        for (int p = 0; p < n; p++) {
                            if (m == p) {
                                temporary[m][p] = 1;
                            }
                        }
                    }
                    temporary[i][j] = newValue;
                    matrixList.add(new Matrix(temporary));
                    for (int k = 0; k < n; k++) {
                        upper[i][k] = upper[i][k] + (newValue * upper[j][k]);
                    }
                }
            }
        }

        Matrix lMatrix = matrixList.remove();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    lMatrix.set(i, j, -1 * lMatrix.get(i, j));
                }
            }
        }

        // Calculate L and U
        this.L = lMatrix;
        while (matrixList.peek() != null) {
            lMatrix = matrixList.remove();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        lMatrix.set(i, j, -1 * lMatrix.get(i, j));
                    }
                }
            }
            this.L = new Matrix(LinearAlgebra.multiplyMatrix(L.getArray(),
                    lMatrix.getArray()));
        }
        this.U = new Matrix(upper);

        // Calculate Error
        Matrix luTimes = new
                Matrix(LinearAlgebra.multiplyMatrix(L.getArray(), U.getArray()));
        Matrix luMinusA = LinearAlgebra.matrixSubtraction(luTimes, A);
        double error = LinearAlgebra.norm(luMinusA);
        this.error = error;
    }

    /**
     * Returns the calculated lower matrix
     * @return the L matrix
     */
    public Matrix getL() {
        return L;
    }

    /**
     * Returns the calculated upper matrix
     * @return the U matrix
     */
    public Matrix getU() {
        return U;
    }

    /**
     * Returns the error
     * @return the error
     */
    public double getError() {
        return error;
    }

    public String toString() {
        String buf = "";
        buf += "L:\n" + Test.formatArray(L.getArray()).toString() + "\n";
        buf += "U:\n" + Test.formatArray(U.getArray()).toString() + "\n";
        buf += "Error:\n" + error + "\n";
        return buf;
    }
}