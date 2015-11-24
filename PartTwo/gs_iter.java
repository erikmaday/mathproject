package PartTwo;

import base.Matrix;

import java.util.Random;

public class gs_iter {
    Matrix a;
    Matrix x = null; //really a vector solution in the iteration
    private Matrix b; //stands for vector b
    private Matrix x0; //stands for the initial guess
    double tolerance;
    private int maxIterations;
    private int N;

    public gs_iter(Matrix a, Matrix b, Matrix x0, double tolerance, int M) {
        this.a = a;
        this.b = b;
        this.x0 = x0;
        this.tolerance = tolerance;
        this.maxIterations = M;
        iterate();
    }

    //if a is augmented
    public gs_iter(Matrix a, Matrix x0, double tolerance, int M) {
        double temp[][] = new double[a.getRowDimension()][a.getColumnDimension() - 1];
        double tempB[][] = new double[a.getRowDimension()][1];
        for (int i = 0; i < a.getRowDimension(); i++) {
            for (int j = 0; j < a.getColumnDimension(); j++) {
                if (j >= a.getColumnDimension() - 1) {
                    tempB[i][0] = a.get(i, j);
                } else {
                    temp[i][j] = a.get(i, j);
                }
            }
        }
        this.a = new Matrix(temp);
        this.b = new Matrix(tempB);
        this.x0 = x0;
        this.tolerance = tolerance;
        this.maxIterations = M;
        iterate();
    }

    private Matrix iterate() {
        int n = a.getColumnDimension();
        double[][] original = a.getArrayCopy();
        double[][] s = new double[n][n];
        double[][] t = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    s[i][j] = original[i][j];
                    t[i][j] = 0;
                } else {
                    s[i][j] = 0;
                    t[i][j] = -1 * original[i][j];
                }
            }
        }
        Matrix S = new Matrix(s);
        Matrix T = new Matrix(t);

        N = 0;
        Matrix previousX = x0;
        boolean stay = true;
        while (stay) {
            if (x != null) {
                previousX = new Matrix(x.getArrayCopy());
            }
            x = lowerTriangularForwardSubstitution(S, T.times(previousX).plus(b));

            N++;
            if (N >= maxIterations) {
                stay = false;
            }
            Matrix error = x.minus(previousX);
            if (vectorNorm(error) <= tolerance) {
                stay = false;
            }
        }
        return x;
    }

    private double vectorNorm(Matrix a) {
        if (a.getColumnDimension() == 1 || a.getRowDimension() == 1) {
            double sum = 0;
            for (int i = 0; i < a.getRowDimension(); i++) {
                for (int j = 0; j < a.getColumnDimension(); j++) {
                    sum = sum + Math.pow(a.get(i, j), 2);
                }
            }
            return Math.pow(sum, 0.5);
        }
        System.out.println("The entered object was not a vector.");
        return -1;
    }

    public Matrix getB() {
        return b;
    }

    public Matrix getX0() {
        return x0;
    }

    public int getN() {
        return N;
    }

    private static Matrix lowerTriangularForwardSubstitution(Matrix matrixA, Matrix matrixB) {
        double[][] a = matrixA.getArrayCopy();
        double[][] b = matrixB.getArrayCopy();
        double[][] x = new double[matrixA.getRowDimension()][1];
        for (int j = 0; j < matrixA.getRowDimension(); j++) {
            for (int k = 0; k < matrixA.getColumnDimension(); k++) {
                if (k == 0 && j == 0) {
                    x[j][0] = b[j][0] / a[j][k];
                } else {
                    if (k == j) {
                        double temp = 0;
                        for (int l = k - 1; l >= 0; l--) {
                            temp = temp + (x[l][0] * a[j][l]);
                        }
                        x[j][0] = (b[k][0] - temp) / a[j][k];
                    }
                }
            }
        }
        return new Matrix(x);
    }

    public String toString() {
        String buf = "";
        buf += "Gauss-Seidel\n";
        buf += "Original Matrix:\n";
        buf += a.toString() + "\n";
        if (N < maxIterations) {
            buf += "Final estimated x value: \n";
            buf += x.toString();
            buf += "The method completed after " + N + " iterations.\n";
        } else {
            buf += "The method failed after " + N + " iterations.\n";
            buf += "Final estimated x value: \n";
            buf += x.toString();
        }
        return buf;
    }

    public static void main(String[] args) {
        double [][]test = new double[2][3];
        test[0][0] = 4;
        test[0][1] = 2;
        test[1][0] = 3;
        test[1][1] = 5;
        test[0][2] = 1;
        test[1][2] = 2;
        double [][]testB = new double[2][1];
        testB[0][0] = 1;
        testB[1][0] = 2;
        double [][]testGuess = new double[2][1];
        testGuess[0][0] = 0;
        testGuess[1][0] = 0;
        Matrix a = new Matrix(test);
        Matrix b = new Matrix(testB);
        Matrix c = new Matrix(testGuess);
        gs_iter testing = new gs_iter(a, c, .00005, 100);
        System.out.println(testing.toString());
    }
}