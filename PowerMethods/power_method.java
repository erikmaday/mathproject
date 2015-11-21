package PowerMethods;

import base.*;
import java.util.ArrayList;
import java.util.Random;

public class power_method {

    private double[][] A;
    private double[] v;
    private double tolerance;
    private int N;

    /**
     * @param A an nxn matrix with real numbers as entries
     * @param v vector of size n that serves as initial guess for an eigenvector of A
     * @param tolerance the tolerance that determines when an approximation is close enough
     * @param N the maximum number of iterations before quitting
     */
    public power_method(double[][] A, double[] v, double tolerance, int N) {
        this.A = A;
        this.v = v;
        this.tolerance = tolerance;
        this.N = N;
    }

    public void approximateValues() {
        Vector guess = new Vector(v);
        Vector eigenvector = getEigenvector(guess, 0);
        double eigenvalue = getEigenvalue(eigenvector);
        System.out.println(eigenvector.toString());
        System.out.println(eigenvalue);
    }

    private Vector getEigenvector(Vector guess, int count) {
        Vector previous = LinearAlgebra.vectorNorm(guess);
        guess = LinearAlgebra.vectorNorm(LinearAlgebra.matrixVectorMultiply(guess, new Matrix(A)));
        //System.out.println(guess.toString());
        if (count < N) {
            count++;
            double difference = LinearAlgebra.magnitude(LinearAlgebra.vectorSubtract(guess, previous));
            if (difference <= tolerance) {
                return guess;
            } else {
                return getEigenvector(guess, count);
            }
        }
        return null;
    }

    private double getEigenvalue(Vector eigen) {
        double numerator = LinearAlgebra.dotProduct(LinearAlgebra.matrixVectorMultiply(eigen, new Matrix(A)), eigen);
        double denominator = LinearAlgebra.dotProduct(eigen, eigen);
        return numerator / denominator;
    }

    public static void main(String[] args) {
        double[][] m = {
                {2, 12, 10},
                {1, 5, 40},
                {5, 6, 7}
        };
        double[] vec = {1, 1, 0};
        power_method p = new power_method(m, vec, .001, 1000);
        p.approximateValues();
    }
}