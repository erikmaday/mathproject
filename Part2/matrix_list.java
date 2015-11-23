package Part2;

import java.util.ArrayList;

/**
 * Created by CG Carson
 */
public class matrix_list {

    private int num;
    private ArrayList<IterativeMatrix> matrices;

    public matrix_list(int num) {
        matrices = new ArrayList<IterativeMatrix>();
        for (int i = 0; i <= num; i++) {
            IterativeMatrix p = new IterativeMatrix();
            matrices.add(p);
        }
    }

    public void print() {
        for (IterativeMatrix e : matrices) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<IterativeMatrix> getMatrices() {
        return matrices;
    }

    public static void main(String[] args) {
        matrix_list test = new matrix_list(100);
        test.print();
    }
}