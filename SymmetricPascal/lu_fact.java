package SymmetricPascal;

import base.Matrix;
import base.LinearAlgebra;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class lu_fact {
    /**
     * int m, n: row dimension and column dimension of matrix A
     * Matrix A: original matrix to be decomposed into LU
     * Matrix L, U: L and U matrices found from LU decomposition of A
     * Matrix Lstep: matrix to use for each step in getLU()
     * Matrix e: initial diagonal matrix with 1's on the diagonal
     * double[][] Larr, Uarr, LstepArr, eArr: 2D arrays of matrices
     */
    private int m, n;
    //private double[][] Larr, Uarr, LstepArr, eArr;
    private Matrix A, L, U; //Lstep, e;
    private LinkedList<Matrix> matrices;

    /**
     * Does LU decomposition of matrix A into a lower-triangular matrix L and
     * the upper-triangular matrix U
     * @param A (original matrix)
     */
    public lu_fact(Matrix A) {
        this.m = A.getHeight();
        this.n = A.getWidth();
        this.A = A.copy();
        this.U = A.copy();
        this.L = A.copy();
        LUfactor();
    }

    /**
     * Does LU decomposition
     */
    private void LUfactor() {
        matrices = new LinkedList<Matrix>();
        double[][] Aarr = A.toArray();
        double[][] Uarr = A.toArray();

        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                if (j < i) {
                    double value = -1 * Uarr[i][j] / Uarr[j][j];
                    double[][] values = new double[n][n];
                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if (k == l) {
                                values[k][l] = 1;
                            }
                        }
                    }
                    values[i][j] = value;
                    Matrix m = new Matrix(values);
                    matrices.add(m);

                    for (int x = 0; x < n; x++) {
                        Uarr[i][x] = Uarr[i][x] + (value * Uarr[j][x]);
                    }
                }
            }
        }

        Matrix M = matrices.remove();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double[][] mArr = M.toArray();
                    mArr[i][j] = -1 * M.get(i, j);
                }
            }
        }

        L = M;
        while (matrices.peek() != null) {
            M = matrices.remove();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double[][] mArr = M.toArray();
                        mArr[i][j] = -1 * M.get(i, j);
                    }
                }
            }
            L = new Matrix(LinearAlgebra.multiplyMatrix2(L, M));
        }
        U = new Matrix(Uarr);
    }

    /**
     * Returns lower-triangular matrix L
     * @return lower-triangular matrix L
     */
    public Matrix getL() {
        return L;
    }

    /**
     * Returns upper-triangular matrix U
     * @return upper-triangular matrix U
     */
    public Matrix getU() {
        return U;
    }

}
