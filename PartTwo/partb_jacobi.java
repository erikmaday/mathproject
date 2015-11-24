package PartTwo;

import base.Matrix;
import base.Vector;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by erikmaday on 11/24/15.
 */
public class partb_jacobi {
    private ArrayList<jacobi_iter> j;
    private double[][] matrix = {{1, 1/2.0, 1/3.0}, {1/2.0, 1/3.0, 1/4.0}, {1/3.0, 1/4.0, 1/5.0}};
    private final Matrix A = new Matrix(matrix);
    private Matrix B;

    public partb_jacobi(int numMatrices) {
        j = new ArrayList<jacobi_iter>();
        for (int i = 0; i <= numMatrices; i++) {
            j.add(jacobi_iter.genRandom());
        }
    }

    public ArrayList<jacobi_iter> getList() {
        return j;
    }

    public static void main(String[] args) {
        partb_jacobi test = new partb_jacobi(100);
        for (jacobi_iter e : test.getList()) {
            System.out.println(e.toString());
        }
    }
}
