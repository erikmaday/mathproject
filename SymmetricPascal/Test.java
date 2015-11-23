package SymmetricPascal;

import base.Matrix;
import base.Vector;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 */
public class Test {

    public static void main(String[] args) {

        double[][] exampleMatrix = new double[][]{
                { 1, 1, 1, 1},
                { 1, 2, 3, 4},
                { 1, 3, 6, 10},
                { 1, 4, 10, 20}
        };

        // Test LU
        lu_fact luFact = new lu_fact(new Matrix(exampleMatrix));
        Matrix lMatrix = new Matrix(formatArray(luFact.getL().toArray()));
        Matrix uMatrix = new Matrix(formatArray(luFact.getU().toArray()));
        System.out.println("L for LU: \n" + lMatrix.toString());
        System.out.println("U for LU: \n" + uMatrix.toString());
        System.out.println("Error for LU: " + luFact.getError() + "\n");

        // Test Givens
        qr_fact_givens givensRotations = new qr_fact_givens(exampleMatrix);
        Matrix qMatrix = new Matrix(formatArray(givensRotations.getQ()));
        Matrix rMatrix = new Matrix(formatArray(givensRotations.getR()));
        System.out.println("Q for Givens: \n" + qMatrix.toString());
        System.out.println("R for Givens: \n" + rMatrix.toString() );
        System.out.println("Error for Givens: " +
                givensRotations.getError() + "\n");

        // Test Householder
        qr_fact_househ householderReflections =
                new qr_fact_househ(new Matrix(exampleMatrix));
        Matrix qhMatrix = new
                Matrix(formatArray(householderReflections.getQ()));
        Matrix rhMatrix = new
                Matrix(formatArray(householderReflections.getR()));
        System.out.println("Q for Householder: \n" + qhMatrix.toString());
        System.out.println("R for Householder: \n" + rhMatrix.toString());
        System.out.println("Error for Householder: " +
                householderReflections.getError() + "\n");

        
        double[][] m = {
                {1, -2, 3},
                {2, 1, 1},
                {-3, 2, -2}
        };
        double[] v = {7, 4, -10};
        solve_lu_b sol = new solve_lu_b(new Matrix(m), new Vector(v));
        System.out.println(sol.getX().toString());
    }

    public static double[][] formatArray(double[][] array) {
        double[][] newArray = new double[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int y = 0; y < array[0].length; y++) {
                newArray[i][y] = (double) Math.round(array[i][y] * 10000)
                        / 10000;
            }
        }
        return newArray;
    }

}
