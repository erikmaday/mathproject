package PartTwo;

import base.Matrix;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by erikmaday on 11/24/15.
 */
public class partb_gs {
    private ArrayList<gs_iter> g;
    private double[][] matrix = {{1, 1/2.0, 1/3.0}, {1/2.0, 1/3.0, 1/4.0}, {1/3.0, 1/4.0, 1/5.0}};
    private final Matrix A = new Matrix(matrix);
    private Matrix B;

    public partb_gs(int numMatrices) {
        g = new ArrayList<gs_iter>();
        for (int i = 0; i <= numMatrices; i++) {
            g.add(genRandom());
        }
    }

    public ArrayList<gs_iter> getList() {
        return g;
    }

    public gs_iter genRandom() {
        double[][] A = new double[3][3];
        A[0][0] = 1.0;
        A[0][1] = 1.0/2.0;
        A[0][2] = 1.0/3.0;
        A[1][0] = 1.0/2.0;
        A[1][1] = 1.0/3.0;
        A[1][2] = 1.0/4.0;
        A[2][0] = 1.0/3.0;
        A[2][1] = 1.0/4.0;
        A[2][2] = 1.0/5.0;
        double[][] randB = new double[3][1];
        randB[0][0] = .1;
        randB[1][0] = .1;
        randB[2][0] = .1;
        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();
        double[][] guess = new double[3][1];
        guess[0][0] = -1 + (10 - (-10)) * r1.nextDouble();
        guess[1][0] = -1 + (10 - (-10)) * r2.nextDouble();
        guess[2][0] = -1 + (10 - (-10)) * r3.nextDouble();
        return new gs_iter(new Matrix(A), new Matrix(randB), new Matrix(guess), .00005, 500);
    }



    public static void main(String[] args) {
        partb_gs test = new partb_gs(100);
        for (gs_iter e : test.getList()) {
            System.out.println(e.toString());
        }
    }
}
