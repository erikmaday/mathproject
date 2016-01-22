package PartThree;

import java.util.ArrayList;


/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 */
public class matrix_thousand {

    private int numMatrices;
    private ArrayList<PowerMatrix> matrices;

    public matrix_thousand(int numMatrices) {
        matrices = new ArrayList<PowerMatrix>();
        for (int i = 0; i <= numMatrices; i++) {
            PowerMatrix p = new PowerMatrix();
            matrices.add(p);
        }
    }

    public void print() {
        for (PowerMatrix e : matrices) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<PowerMatrix> getMatrices() {
        return matrices;
    }

    public static void main(String[] args) {
        matrix_thousand test = new matrix_thousand(1000);
        test.print();
    }
}
