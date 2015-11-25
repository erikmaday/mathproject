package PartOne;

import base.LinearAlgebraScanner;
import base.Matrix;
import base.Vector;

import java.util.Scanner;

/**
 * Created by erikmaday on 11/24/15.
 */
public class Part1Driver {

    public static void main(String[] args) {
        int function = 0;
        function = getFunctionOption();
        //get Matrix A
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        double[][] hackM = {{0,0},{0,0}};
        Matrix A = new Matrix(hackM);
        double[] hackV = {1, 2};
        Vector B = new Vector(hackV);
        int n = 0;
        if (function < 6) {
            System.out.println("Please enter your matrix.");
            System.out.println("Seperate entries by a space, rows by an enter, end with a blank line.");
            A = input.readMatrix();
            if (function >= 4) {
                System.out.println("Please enter your vector4.");
                B = input.readVector();
            }
        } else {
            System.out.println("Enter the desired size of the pascal matrix:");
            Scanner in = new Scanner(System.in);
            n = in.nextInt();
        }

        switch (function) {
            case 1: lu_fact o1 = new lu_fact(A);
                    System.out.println(o1.toString());
                    break;
            case 2: qr_fact_givens o2 = new qr_fact_givens(A.getArray());
                    System.out.println(o2.toString());
                    break;
            case 3: qr_fact_househ o3 = new qr_fact_househ(A);
                    System.out.println(o3.toString());
                    break;
            case 4: solve_lu_b o4 = new solve_lu_b(A, B);
                    System.out.println(o4.toString());
                    break;
            case 5: solve_qr_b o5 = new solve_qr_b(A, B);
                    System.out.println(o5.toString());
                    break;
            case 6: solve_px_b o6 = new solve_px_b(n);
                    System.out.println(o6.toString());
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
        System.out.println("6. Solve Px=B");
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
