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

    private pmanswer getValues() {
        return getValuesHelper(new Vector(v), 0);
    }

    private pmanswer getValuesHelper(Vector guess, int count) {
        Vector previous = LinearAlgebra.vectorNorm(guess);
        guess = LinearAlgebra.vectorNorm(LinearAlgebra.matrixVectorMultiply(guess, new Matrix(A)));
        if (count < N) {
            count++;
            double difference = LinearAlgebra.magnitude(LinearAlgebra.vectorAdd(guess, previous));
            if (difference <= tolerance) {
                pmanswer answer = new pmanswer(guess, getEigenvalue(guess), count);
                return answer;
            } else {
                return getValuesHelper(guess, count);
            }
        }
        return null;
    }

    private double getEigenvalue(Vector eigen) {
        double numerator = LinearAlgebra.dotProduct(LinearAlgebra.matrixVectorMultiply(eigen, new Matrix(A)), eigen);
        double denominator = LinearAlgebra.dotProduct(eigen, eigen);
        return numerator / denominator;
    }
}