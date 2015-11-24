package PartOne;

import base.Vector;
import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class pascal {

    private Matrix pascalMatrix;
    private Vector pascalVector;

    /**
     *
     * @param size size of matrix
     */
    public pascal(int size) {
        double[][] tempMat = new double[size][size];
        for (int n = 1; n <= size; n++) {
            for (int m = 1; m <= size; m++) {
                tempMat[m - 1][n - 1] = pascalNumber(m, n);
            }
        }
        pascalMatrix = new Matrix(tempMat);
        double[] tempVec = new double[size];
        for (int n = 1; n <= size; n++) {
            tempVec[n - 1] = 1 / n;
        }
        pascalVector = new Vector(tempVec);
    }

    private int pascalNumber(int m, int n) {
        return factorial((m - 1) + (n - 1)) / (factorial(m - 1) * factorial(n - 1));
    }

    private int factorial(int n) {
        if (n == 0)
            return 1;
        else
            return n * factorial(n-1);
    }

    public Matrix getPascalMatrix() {
        return pascalMatrix;
    }

    public Vector getPascalVector() {
        return pascalVector;
    }


}
