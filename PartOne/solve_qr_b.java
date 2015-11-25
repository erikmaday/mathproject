package PartOne;

import base.LinearAlgebra;
import base.Vector;
import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class solve_qr_b {

    private Vector x;
    private Vector b;
    /**
     * @param m is matrix to decompose to QR
     * @param B vector B in QR*x=B
     */
    public solve_qr_b(Matrix m, Vector B) {
        //R*x=Qt*b
        qr_fact_givens fact = new qr_fact_givens(m.getArray());
        b = new Vector(B.toArray());

        Matrix Q = new Matrix(fact.getQ());
        Matrix R = new Matrix(fact.getR());

        Matrix Qt = new Matrix(LinearAlgebra.transposeMatrix(Q.getArray()));
        Vector QtB = LinearAlgebra.matrixVectorMultiply(B, Qt);

        Matrix RQtB = LinearAlgebra.augment(R, QtB);

        solve_ax_b RxQtB = new solve_ax_b(RQtB);
        this.x = RxQtB.getAnswer();
    }

    public Vector getX() {
        return x;
    }

    public String toString() {
        String buf = "";
        buf += "B:\n" + b.toString() + "\n\n";
        buf += "Solution X:\n" + x.toString() + "\n";
        return buf;
    }
}
