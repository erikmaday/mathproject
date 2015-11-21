package SymmetricPascal;

import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 * got ideas from code seen here
 * http://www.iaa.ncku.edu.tw/~dychiang/
 * lab/program/mohr3d/source/Jama%5CLUDecomposition.html
 */
public class lu_fact {

    private double[][] A;
    private double[][] LU;
    private double[][] L;
    private double[][] U;
    private int n, pivsign;
    private int[] piv;

    public lu_fact (double[][] A) {
        this.A = A;
        LU = this.A;
        n = this.A.length;
        piv = new int[n];
        for (int i = 0; i < n; i++) {
            piv[i] = i;
        }
        pivsign = 1;
        double[] LUrowi;
        double[] LUcolj = new double[n];

        for (int j = 0; j < n; j++) {

            for (int i = 0; i < n; i++) {
                LUcolj[i] = LU[i][j];
            }

            for (int i = 0; i < n; i++) {
                LUrowi = LU[i];
                int kmax = Math.min(i,j);
                double s = 0.0;
                for (int k = 0; k < kmax; k++) {
                    s += LUrowi[k]*LUcolj[k];
                }

                LUrowi[j] = LUcolj[i] -= s;
            }

            int p = j;
            for (int i = j+1; i < n; i++) {
                if (Math.abs(LUcolj[i]) > Math.abs(LUcolj[p])) {
                    p = i;
                }
            }
            if (p != j) {
                for (int k = 0; k < n; k++) {
                    double t = LU[p][k]; LU[p][k] = LU[j][k]; LU[j][k] = t;
                }
                int k = piv[p]; piv[p] = piv[j]; piv[j] = k;
                pivsign = -pivsign;
            }

            if (j < n & LU[j][j] != 0.0) {
                for (int i = j+1; i < n; i++) {
                    LU[i][j] /= LU[j][j];
                }
            }
        }
    }

    /**
     * creates square identity matrix with dimensions
     * of the inputed size
     * @param size of identity matrix to create
     * @return 2-D double array that is identity matrix
     */
    private double[][] createIdentity(int size) {
        double[][] identity = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                identity[i][j] = 0;
            }
        }
        for (int i = 0; i < size; i++) {
            identity[i][i] = 1;
        }
        return identity;
    }


    public double[][] getL() {
        Matrix matrix = new Matrix(n, n, 0.0);
        double[][] L = matrix.toArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    L[i][j] = LU[i][j];
                } else if (i == j) {
                    L[i][j] = 1.0;
                } else {
                    L[i][j] = 0.0;
                }
            }
        }
        return L;
    }

    public double[][] getU() {
        Matrix matrix = new Matrix(n, n, 0.0);
        double[][] U = matrix.toArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i <= j) {
                    U[i][j] = LU[i][j];
                } else {
                    U[i][j] = 0.0;
                }
            }
        }
        return U;
    }

    private static void printMatrix(double[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
