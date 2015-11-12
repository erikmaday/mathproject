package SymmetricPascal;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 */
public class Test {

    public static void main(String[] args) {
        double[][] exampleMatrix = new double[][]{
                { 1, 1, 1, 1, 1},
                { 1, 2, 3, 4, 5},
                { 1, 3, 6, 10, 15},
                { 1, 4, 10, 20, 35},
                { 1, 5, 15, 35, 70}
        };

        qr_fact_givens givensRotations = new qr_fact_givens(exampleMatrix);
        System.out.println("Q for Givens: " + givensRotations.getQ().toString());
        System.out.println("R for Givens: " + givensRotations.getR().toString());
    }
}
