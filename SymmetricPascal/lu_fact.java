package SymmetricPascal;

import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class lu_fact {

    private Matrix A;
    private double[][] LUMatrix;
    private int[] pivotVector;
    private int m, n;

    public lu_fact(Matrix A) {

        int m = A.getHeight();
        int n = A.getWidth();
        pivotVector = new int[m];

    }


    /**
     * Calculates and returns the L matrix for LU Decomposition.
     * @return the calculated L matrix
     */
    public double[][] LDecomposition() {
        return null;
    }

    /**
     * Calculates and returns the U matrix for LU Decomposition.
     * @return the calculated U matrix
     */
    public double[][] UDecomposition() {
        return null;
    }

    /**
     * Calculates and returns error
     * @return the error for LU Decomposition
     */
    public double LUerror() {
        return 2.0;
    }

    public static void main(String[] args) {
        double[][] newArray = {
                {1, 1, 1, 1},
                {1, 2, 3, 4},
                {1, 3, 6, 10},
                {1, 4, 10, 20}
        };
    }
}
