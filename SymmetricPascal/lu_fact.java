package SymmetricPascal;

import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class lu_fact {

    //private Matrix A;
    private double[][] LUMatrix;
    private int[] pivotVector;
    private int m, n, pivsign;
/*
    public lu_fact(Matrix A) {

        int m = A.getHeight();
        int n = A.getWidth();
        pivotVector = new int[m];

    }*/

    public lu_fact (Matrix A) {
        LUMatrix = A.toArray();
        m = A.getHeight();
        n = A.getWidth();
        pivotVector = new int[m];
        for (int i = 0; i < m; i++) {
            pivotVector[i] = i;
        }
        pivsign = 1;
        double[] LUrowi;
        double[] LUcolj = new double[m];

        //OUTER LOOP

        for (int j = 0; j < n; j++) {
            //copy the jth column to localize references
            for (int i = 0; i < m; i++) {
                LUcolj[i] = LUMatrix[i][j];
            }

            //apply transformations
            for (int i = 0; i < m; i++) {
                LUrowi = LUMatrix[i];

                int kmax = Math.min(i,j);
                double s = 0.0;
                for (int k = 0; k < kmax; k++) {
                    s += LUrowi[k]*LUcolj[k];
                }

                LUrowi[j] = LUcolj[i] -= s;
            }

            //find pibot and exchange if needed
            int p = j;
            for (int i = j+1; i < m; i++) {
                if (Math.abs(LUcolj[i]) > Math.abs(LUcolj[p])) {
                    p = i;
                }
            }
            if (p != j) {
                for (int k = 0; k < n; k++) {
                    double t = LUMatrix[p][k];
                    LUMatrix[p][k] = LUMatrix[j][k];
                    LUMatrix[j][k] = t;
                }
                int k = pivotVector[p];
                pivotVector[p] = pivotVector[j];
                pivotVector[j] = k;
                pivsign = -pivsign;
            }

            // Compute multipliers.
            if (j < m & LUMatrix[j][j] != 0.0) {
                for (int i = j+1; i < m; i++) {
                    LUMatrix[i][j] /= LUMatrix[j][j];
                }
            }
        }
    }

    /** Is the matrix nonsingular?
     @return true if U, therefore A, is nonsingular.
     */
    public boolean isNonsingular() {
        for (int j = 0; j < n; j++) {
            if (LUMatrix[j][j] == 0) {
                return false;
            }
        }
        return true;
    }

    /** return lower triangular factor
     * @return L
     */
    public Matrix getL() {
        Matrix X = new Matrix(m,n);
        double[][] L = X.toArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    L[i][j] = LUMatrix[i][j];
                } else if (i == j) {
                    L[i][j] = 1.0;
                } else {
                    L[i][j] = 0.0;
                }
            }
        }
        return X;
    }

    /** Return upper triangular factor
     @return U
     */
    public Matrix getU() {
        Matrix X = new Matrix(n,n);
        double[][] U = X.toArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i <= j) {
                    U[i][j] = LUMatrix[i][j];
                } else {
                    U[i][j] = 0.0;
                }
            }
        }
        return X;
    }

    /** return pivot permutation vector
     @return piv
     */
    public int[] getPivot() {
        int[] p = new int[m];
        for (int i = 0; i < m; i++) {
            p[i] = pivotVector[i];
        }
        return p;
    }

    /** Return pivot permutation vector as a one-dimensional double array
     @return     (double) piv
     */
    public double[] getDoublePivot() {
        double[] vals = new double[m];
        for (int i = 0; i < m; i++) {
            vals[i] = (double) pivotVector[i];
        }
        return vals;
    }

    /** Determinant
     @return det(A)
     @exception IllegalArgumentException Matrix must be square
     */
    public double det() {
        if (m != n) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        double d = (double) pivsign;
        for (int j = 0; j < n; j++) {
            d *= LUMatrix[j][j];
        }
        return d;
    }

    /** Solve A*X = B
     @param  B   A Matrix with as many rows as A and any number of columns.
     @return     X so that L*U*X = B(piv,:)
     @exception  IllegalArgumentException Matrix row dimensions must agree.
     @exception  RuntimeException  Matrix is singular.
     */
    public Matrix solve (Matrix B) {
        if (B.getHeight() != m) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        }
        if (!this.isNonsingular()) {
            throw new RuntimeException("Matrix is singular.");
        }

        // Copy right hand side with pivoting
        int nx = B.getWidth();
        Matrix Xmat = B.getMatrix(pivotVector,0,nx-1);
        double[][] X = Xmat.toArray();

        // Solve L*Y = B(piv,:)
        for (int k = 0; k < n; k++) {
            for (int i = k+1; i < n; i++) {
                for (int j = 0; j < nx; j++) {
                    X[i][j] -= X[k][j]*LUMatrix[i][k];
                }
            }
        }
        // Solve U*X = Y;
        for (int k = n-1; k >= 0; k--) {
            for (int j = 0; j < nx; j++) {
                X[k][j] /= LUMatrix[k][k];
            }
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < nx; j++) {
                    X[i][j] -= X[k][j]*LUMatrix[i][k];
                }
            }
        }
        return Xmat;
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
}
