package PartOne;

import base.Matrix;

import java.util.Scanner;

/**
 * Created by erikmaday on 11/24/15.
 */
public class Part1Driver {

    public static void main(String[] args) {
        int function = 0;
        function = getFunctionOption();
        double[][] exampleMatrix = new double[][]{
                { 1, 1, 1, 1},
                { 1, 2, 3, 4},
                { 1, 3, 6, 10},
                { 1, 4, 10, 20}
        };
        //get Matrix A
        Matrix A = new Matrix(exampleMatrix);
        if (function >= 4) {
            //get vector b
        }
        switch (function) {
            case 1: lu_fact o1 = new lu_fact(A);
                    System.out.println(o1.toString());
                    break;
            case 2: qr_fact_givens o2 = new qr_fact_givens(A.getArray());
                    break;
        }
    }

    private static int getFunctionOption() {
        System.out.println("Select what you would like to do.");
        System.out.println("1. Get LU Factorization");
        System.out.println("2. Get QR Factorization (Givens)");
        System.out.println("3. Get LU Factorization (Householder)");
        System.out.println("4. Solve Ax=B using LU");
        System.out.println("5. Solve Ax=B using Givens");
        System.out.println("6. Solve Ax=B using Householder");
        Scanner in = new Scanner(System.in);
        int a = 0;
        while (a < 1 || a > 6) {
            System.out.println("Enter the number of your choice.");
            a = in.nextInt();
            if (a < 1 || a > 6) {
                System.out.println("That is not a valid option.");
            }
        }
        return a;
    }
}
