package SymmetricPascal;

import base.Matrix;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Formatter;

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

        qr_fact_givens givensRotations = new qr_fact_givens(exampleMatrix);
        Matrix qMatrix = new Matrix(formatArray(givensRotations.getQ()));
        Matrix rMatrix = new Matrix(formatArray(givensRotations.getR()));
        Matrix qrMatrix = new Matrix(formatArray(givensRotations.getQR()));
        System.out.println("Q for Givens: \n" + qMatrix.toString());
        System.out.println("R for Givens: \n" + rMatrix.toString() );
        System.out.println("QR for Givens: \n" + rMatrix.toString());
        System.out.println("Error for Givens: \n" + givensRotations.getError());
    }

    public static double[][] formatArray(double[][] array) {

        double[][] newArray = new double[array.length][array[0].length];
        for(int i = 0; i < array.length; i++){
            for(int y = 0; y < array[0].length; y++){
                newArray[i][y] = (double)Math.round(array[i][y] * 10000)
                        / 10000;
            }
        }
        return newArray;
    }

}
