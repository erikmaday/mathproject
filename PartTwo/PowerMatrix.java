package PartTwo;

import base.LinearAlgebra;
import java.util.Random;
import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class PowerMatrix {
    private Matrix A;
    private Matrix inverse;
    private pmanswer largest;
    private pmanswer smallest;
    private double trace;
    private double determinant;
    private double inverseTrace;
    private double inverseDeterminant;

    /**
     * Creates a random 2x2 matrix
     */
    public PowerMatrix() {
        double[][] tempMatrix = new double[2][2];
        tempMatrix[0][0] = randomNumber();
        tempMatrix[0][1] = randomNumber();
        tempMatrix[1][0] = randomNumber();
        tempMatrix[1][1] = randomNumber();
        this.A = new Matrix(tempMatrix);
        this.inverse = inverse();
        this.determinant = LinearAlgebra.determinant(A, A.getColumnDimension());
        this.trace = LinearAlgebra.trace(A);
        this.inverseDeterminant = LinearAlgebra.determinant(this.inverse, this.inverse.getColumnDimension());
        this.inverseTrace = LinearAlgebra.trace(this.inverse);
        generateAnswers();
    }

    private static double randomNumber() {
        Random r = new Random();
        double randomValue = -2 + (2 - (-2)) * r.nextDouble();
        return randomValue;
    }

    private Matrix inverse() {
        double[][] arr = A.getArray();
        double denominator = arr[0][0] * arr[1][1] - arr[1][0] * arr[0][1];
        double temp = arr[0][0];
        arr[0][0] = arr[1][1] / denominator;
        arr[1][1] = temp / denominator;
        arr[0][1] *= -1 / denominator;
        arr[1][0] *= -1 / denominator;
        return new Matrix(arr);
    }

    private void generateAnswers() {
        double[] initial = {1.0, 0};
        power_method l = new power_method(A.getArray(), initial, .00005, 100);
        power_method s = new power_method(inverse.getArray(), initial, .00005, 100);
        largest = l.getValues();
        smallest = s.getValues();
    }

    public Matrix getA() {
        return A;
    }

    public Matrix getInverse() {
        return inverse;
    }

    public double getTrace() {
        return trace;
    }

    public double getInverseTrace() {
        return inverseTrace;
    }

    public double getDeterminant() {
        return determinant;
    }

    public double getInverseDeterminant() {
        return inverseDeterminant;
    }

    public double getLargestEigenvalue() {
        return largest.getEigenvalue();
    }

    public int getLargestIterations() {
        return largest.getIterations();
    }

    public double getSmallestEigenvalue() {
        return smallest.getEigenvalue();
    }

    public int getSmallestIterations() {
        return smallest.getIterations();
    }

    public String toString() {
        String buf = "";
        buf += "Matrix: \n" + getA().toString() + "\n";
        buf += "Inverse: \n" + getInverse().toString() + "\n";
        buf += "Largest Eigenvalue: " + getLargestEigenvalue() + "\n";
        buf += "Largest Iterations: " + getLargestIterations() + "\n";
        buf += "Smallest Eigenvalue: " + getSmallestEigenvalue() + "\n";
        buf += "Smallest Iterations: " + getSmallestIterations() + "\n";
        buf += "Trace: " + getTrace() + "\n";
        buf += "Determinant: " + getDeterminant() + "\n";
        return buf;
    }
}