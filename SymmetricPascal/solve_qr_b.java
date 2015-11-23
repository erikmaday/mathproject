package SymmetricPascal;

import base.LinearAlgebra;
import base.Matrix;
import base.Vector;

/**
 * Created by erikmaday on 11/22/15.
 */
public class solve_qr_b {

    private Vector x;
    /**
     * @param m is matrix to decompose to QR
     * @param B vector B in QR*x=B
     */
    public solve_qr_b(Matrix m, Vector B) {
        //R*x=Qt*b
        qr_fact_givens fact = new qr_fact_givens(m.toArray());

        Matrix Q = new Matrix(fact.getQ());
        Matrix R = new Matrix(fact.getR());

        Matrix Qt = new Matrix(LinearAlgebra.transposeMatrix(Q.toArray()));
        Vector QtB = LinearAlgebra.matrixVectorMultiply(B, Qt);

        Matrix RQtB = LinearAlgebra.augment(R, QtB);

        solve_ax_b RxQtB = new solve_ax_b(RQtB);
        this.x = RxQtB.getAnswer();
    }

    public Vector getX() {
        return x;
    }
}
