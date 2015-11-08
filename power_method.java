public class power_method {
    private Matrix approx;
    private Matrix initial;
    private double TOLERANCE = .00000001;
    
    /**
     * Performs the PowerMethod on the matrix for the specified number of iterations
     * prints out the amount
     * @param A matrix to perform power method on
     * @param iterations amount of times to perform power method
     */
    public power_method(Matrix matrix, int iterations) {
        initial = matrix;
        this.approx = new Matrix(matrix.getHeight(), 1, 1);
        powerMethod(iterations);
        printApprox();
        printEigenvalue();
    }
    
    /**
     * Performs the PowerMethod on a given matrix A until a given
     * tolerance is reached
     * @param A matrix to perform power method on
     */
    public power_method(Matrix matrix) {
        initial = matrix;
        this.approx = new Matrix(matrix.getHeight(), 1, 1);
        System.out.println("Iterations: " + powerMethod());
        printApprox();
        printEigenvalue();
    }
    
    /**
     * Performs the PowerMethod on a given matrix A until a given
     * tolerance is reached
     * @param A matrix to perform power method on
     * @param approx the initial approximation for the power method
     * @param tol minimum tolerance of error for the method
     */
     public power_method(Matrix matrix, Matrix approx, double tol) {
         TOLERANCE = tol;
         initial = matrix;
         this.approx = approx;
         System.out.println("Iterations: " + powerMethod());
         printApprox();
         printEigenvalue();
     }
    
    /**
     * Performs the PowerMethod on a given matrix A until a given
     * tolerance is reached
     * @param A matrix to perform power method on
     * @param approx the initial approximation for the power method
     * @param tol minimum tolerance of error for the method
     */
    public power_method(Matrix matrix, Matrix approx, int iterations) {
        initial = matrix;
        this.approx = approx;
        powerMethod(iterations);
        printApprox();
        printEigenvalue();
    }
    
    /**
     * Performs one iteration of power method
     * @return new approximation 
     */
    private Matrix powerMethodIteration() {
        Matrix tempApprox = new Matrix(initial.getHeight(), 1);
        double rowSum = 0;
        for (int row = 0; row < initial.getHeight(); row++) {
            for (int col = 0; col < initial.getWidth(); col++) {
                rowSum += initial.get(row, col) * approx.get(col, 0);
            }
            tempApprox.set(row, 0, rowSum);
            rowSum = 0;
        }
        return tempApprox;
    }
    
    /**
     * runs powerMethodIteration for given amount of iterations
     * @param iterations to be performed
     */
    private void powerMethod(int iterations) {
        for (int i = 0; i < iterations; i++) {
             approx = powerMethodIteration();
        }
        //Normalizing

        double normalizer = approx.get(approx.getHeight() - 1, 0);
        for (int i = 0; i < approx.getHeight(); i++) {
            approx.set(i, 0, approx.get(i, 0) / normalizer);
        }
    }
    
    /**
     * Runs the power method with enough iterations to get a tol < TOLERANCE
     * @return iterations of powerMethod
     */
    private int powerMethod() {
        double tol = 100;
        int iterations = 0;
        double eigenvalue = 0;
        while (tol > (TOLERANCE)) {
                
            if (iterations < 6) {
                System.out.print("Age distribution in " + (int) (2000 + ((iterations) * 10)) + ": ");
                printApprox();
                System.out.println("Total population: " + matrixTotal(approx));
                printEigenvalue();
            }
                
            approx = powerMethodIteration();
            double tempEigen = getEigenvalue();
            tol = Math.abs(tempEigen - eigenvalue);
            eigenvalue = tempEigen;
            iterations++;
            if (iterations >= 150) {
                throw new Error("Did not converge within 150 iterations.");
            }
        }
        //Normalizing
        double normalizer = approx.get(approx.getHeight() - 1, 0);
        for (int i = 0; i < approx.getHeight(); i++) {
            approx.set(i, 0, approx.get(i, 0) / normalizer);
        }
         
        return iterations;
    }
    
    /**
     * Calculates and prints an Eigenvalue approximation as an int
     */
    private void printEigenvalue() {
        Matrix tempApprox = new Matrix(initial.getHeight(), 1);
        double rowSum = 0;
        for (int row = 0; row < initial.getHeight(); row++) {
            for (int col = 0; col < initial.getWidth(); col++) {
               rowSum += initial.get(row, col) * approx.get(col, 0);
            }
            tempApprox.set(row, 0, rowSum);
            rowSum = 0;
        }
        double eigenvalue = 0;
        for (int i = 0; i < tempApprox.getHeight(); i++) {
            eigenvalue += tempApprox.get(i, 0) * approx.get(i, 0);
        }
           
        eigenvalue = eigenvalue / (LinearAlgebra.dotProduct(new Vector(approx.getColumnVector(0,0)), new Vector (approx.getColumnVector(0,0))));
        System.out.println("Eigenvalue: " + (eigenvalue));
    }
    
    /**
     * Calculates and returns an approximate eigenvalue as a double
     * @return the eigenvalue
     **/
    private double getEigenvalue() {
        Matrix tempApprox = new Matrix(initial.getHeight(), 1);
        double rowSum = 0;
        for (int row = 0; row < initial.getHeight(); row++) {
            for (int col = 0; col < initial.getWidth(); col++) {
                rowSum += initial.get(row, col) * approx.get(col, 0);
            }
            tempApprox.set(row, 0, rowSum);
            rowSum = 0;
        }
        double eigenvalue = 0;
        for (int i = 0; i < tempApprox.getHeight(); i++) {
            eigenvalue += tempApprox.get(i, 0) * approx.get(i, 0);
        }
        eigenvalue = eigenvalue / (LinearAlgebra.dotProduct(new Vector(approx.getColumnVector(0,0)), new Vector (approx.getColumnVector(0,0))));
        return eigenvalue;
    }
        
    /**
     * Returns the value of approx
     * @return approx, the n by 1 matrix used as an approximation
     */
    public Matrix getApprox() {
        return approx;
    }
        
    /**
     * Prints the current value of approx
     */
    public void printApprox() {
       System.out.print("Eigenvector Approximation: {");
       for (int i = 0; i < approx.getHeight(); i++) {
           System.out.print("{" + (approx.get(i, 0)) + "}");
           if (i != approx.getHeight() - 1) {
                System.out.print(", ");
           }
       }
       System.out.println("}");
    }
        
    public double matrixTotal(Matrix approx) {
        double total = 0;
        for (int row = 0; row < approx.getHeight(); row++) {
            total += approx.get(row, 0);
        }
        return total;
    }
}