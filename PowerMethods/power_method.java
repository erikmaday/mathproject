package PowerMethods;

import base.Matrix;
import base.LinearAlgebra;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class power_method {
    public static final int MAX_ITERATIONS = 2000;
    public static final int ACCURACY = 5;
    
    public static void main(String[] args) {
        ArrayList<MatrixGroup> matrices = new ArrayList<MatrixGroup>();
        
        for (int i = 0; i < 1000; i++) {
            MatrixGroup matrixGroup = new MatrixGroup(generateRandomMatrix(-2, 2));
            
            double powerMethod[] = powerMethod(matrixGroup.getMatrix(), ACCURACY);
            double inversePowerMethod[] = inversePowerMethod(matrixGroup.getMatrix(), ACCURACY);
            
            matrixGroup.setDominantEigenvalue(powerMethod[0]);
            matrixGroup.setPowerMethodIterations((int)powerMethod[1]);
            
            matrixGroup.setRecessiveEigenvalue(inversePowerMethod[0]);
            matrixGroup.setInversePowerMethodIterations((int)inversePowerMethod[1]);
            
            matrices.add(matrixGroup);
        }
    }
    
    /**
     * Generate a random 2x2 matrix with values between the lower and upper bound.
     * 
     * @param lowerBound lower bound for the values in the matrix
     * @param upperBound upper bound for the values in the matrix
     */
    public static Matrix generateRandomMatrix(int lowerBound, int upperBound) {
        //Generate a 2×2 matrix with random components evenly distributed in an interval [a,b]		
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Invalid bounds.");
        }
        
        Random rand = new Random();
        
        double[][] matrix = new double[2][2];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                matrix[r][c] = rand.nextDouble() * (upperBound - lowerBound + 1) + lowerBound;
            }
        }
        return new Matrix(matrix);
    }
    
    /**
     * Determines the largest eigenvalue via the power method.
     * @param matrix Matrix to find the eigenvalue
     * @param desiredAccuracy Number of digits accuracy requested
     * @return dominant eigenvalue and number of iterations
     */
    public static double[] powerMethod(Matrix matrix, int desiredAccuracy) {
        Matrix approximation = new Matrix(new double[][]{{1},{1}});
        int iterations = 0;
        
        double relativeError = Double.MAX_VALUE;
        double previousEigenvalue = 0;
        double eigenvalue = 0;
        
        while (relativeError * 100 > (0.5 * Math.pow(10, 2 - desiredAccuracy)) && iterations < MAX_ITERATIONS) {
            //Approximate
            approximation = new Matrix(LinearAlgebra.multiplyMatrix(matrix.toArray(), approximation.toArray()));
            //Use's the Rayleigh Equation to solve for the eigenvalue
            eigenvalue = rayleighEquation(matrix, approximation);
            //Calculate the accuracy
            relativeError = Math.abs((eigenvalue - previousEigenvalue) / eigenvalue);
            previousEigenvalue = eigenvalue;
            //increment iterations
            iterations++;
        }
        return new double[]{eigenvalue, iterations};
    }
    
    /**
     * Determines the smallest eigenvalue via the inverse power method.
     * @param matrix Matrix to find the eigenvalue
     * @param desiredAccuracy Number of digits accuracy requested
     * @return recessive eigenvalue and number of iterations
     */
    public static double[] inversePowerMethod(Matrix matrix, int desiredAccuracy) {
        Matrix approximation = new Matrix(new double[][]{{1},{1}});
        int iterations = 0;
        
        double relativeError = Double.MAX_VALUE;
        double previousEigenvalue = 0;
        double eigenvalue = 0;
        
        while (relativeError * 100 > (0.5 * Math.pow(10, 2 - desiredAccuracy)) && iterations < MAX_ITERATIONS) {
            //Approximate!
            approximation = LinearAlgebra.multiplyMatrix(matrix.inverse(), approximation);
            //Use's the Rayleigh Equation to solve for the eigenvalue
            eigenvalue = rayleighEquation(matrix, approximation);
            //Calculate the accuracy
            relativeError = Math.abs((eigenvalue - previousEigenvalue) / eigenvalue);
            previousEigenvalue = eigenvalue;
            //increment iterations
            iterations++;
        }
        return new double[]{eigenvalue, iterations};
    }
    
    /**
     * Solve for the eigenvalue given the matrix and its corresponding eigenvector.
     * 
     * @param matrix Matrix that goes with the eigenvalue and the eigenvector
     * @param eigenvector Eigenvector of the eigenvalue to find
     * @return Eigenvalue
     */
    public static double rayleighEquation(Matrix matrix, Matrix eigenvector) {
        Matrix rayleighNominator = (matrix.times(eigenvector)).transpose().times(eigenvector);
        Matrix rayleighDenominator = eigenvector.transpose().times(eigenvector);
        
        return rayleighNominator.getArray()[0][0] / rayleighDenominator.getArray()[0][0];
    }
}