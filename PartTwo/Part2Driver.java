package PartTwo;

import PartThree.power_method;
import base.LinearAlgebraScanner;
import base.Matrix;
import base.Vector;

/**
 * Created by erikmaday on 11/25/15.
 */
public class Part2Driver {
    public static void main(String[] args) {
        System.out.println("This solves Ax=b using the Jacobi and Gauss-Seidel Methods");
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        System.out.println("Please enter your matrix.");
        System.out.println("Seperate entries by a space, rows by an enter, end with a blank line.");
        Matrix A = input.readMatrix();
        System.out.println("Please enter your b vector.");
        Vector bVec = input.readVector();
        double[] tempB = bVec.toArray();
        double[][] bMat = new double[tempB.length][1];
        for (int i = 0; i < tempB.length; i++) {
            bMat[i][0] = tempB[i];
        }
        Matrix b = new Matrix(bMat);
        System.out.println("Please enter your vector for the initial guess.");
        Vector vVec = input.readVector();
        double[] tempV = vVec.toArray();
        double[][] vMat = new double[tempV.length][1];
        for (int i = 0; i < tempV.length; i++) {
            vMat[i][0] = tempV[i];
        }
        Matrix v = new Matrix(vMat);
        System.out.println("Please enter the tolerance of error.");
        double tolerance = input.nextDouble();
        System.out.println("Please enter the maximum number of iterations to allow.");
        int maxIts = input.nextInt();

        jacobi_iter j = new jacobi_iter(A, b, v, tolerance, maxIts);
        gs_iter g = new gs_iter(A, b, v, tolerance, maxIts);
        System.out.println(j.toString());
        System.out.println(g.toString());
        System.out.print("");
    }
}
