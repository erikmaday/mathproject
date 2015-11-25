package PartThree;

import PartOne.*;
import base.LinearAlgebraScanner;
import base.Matrix;
import base.Vector;

import java.util.Scanner;

/**
 * Created by erikmaday on 11/24/15.
 */
public class Part3Driver {

    public static void main(String[] args) {

        //get matrix A
        //get vector V (initial guess)
        //get tolerance parameter
        //get max number of iterations

        //construct power_method object
        //call .getValues() method
        //call .toString and print that shit out

        /**
         * @param A an nxn matrix with real numbers as entries
         * @param v vector of size n that serves as initial guess for an eigenvector of A
         * @param tolerance the tolerance that determines when an approximation is close enough
         * @param N the maximum number of iterations before quitting
         */
        public power_method(double[][] A, double[] v, double tolerance, int N) {



        //get Matrix A
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        double[][] hackM = {{0, 0}, {0, 0}};
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
    }
}