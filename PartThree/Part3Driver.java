package PartThree;

import PartOne.*;
import base.LinearAlgebraScanner;
import base.Matrix;
import base.Vector;

import java.util.Scanner;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 */
public class Part3Driver {

    public static void main(String[] args) {
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        System.out.println("Please enter your matrix.");
        System.out.println("Seperate entries by a space, rows by an enter, end with a blank line.");
        Matrix A = input.readMatrix();
        System.out.println("Please enter your vector for the initial guess.");
        Vector v = input.readVector();
        System.out.println("Please enter the tolerance of error.");
        double tolerance = input.nextDouble();
        System.out.println("Please enter the maximum number of iterations to allow.");
        int maxIts = input.nextInt();

        power_method p = new power_method(A.getArray(), v.toArray(), tolerance, maxIts);
        System.out.println(p.getValues().toString());
    }
}