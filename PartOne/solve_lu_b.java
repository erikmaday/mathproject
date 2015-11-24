package PartOne;

import base.LinearAlgebra;
import base.Vector;
import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class solve_lu_b {

    private Matrix LU;
    private Vector x;

    /**
     * @param m is matrix to decompose to LU
     * @param B vector B in LU*x=B
     */
    public solve_lu_b(Matrix m, Vector B) {
        lu_fact fact = new lu_fact(m);
        Matrix L = fact.getL();
        Matrix U = fact.getU();
        this.LU = L.times(U);
        Matrix LB = LinearAlgebra.augment(L, B);

        solve_ax_b LYB = new solve_ax_b(LB);
        Vector Y = LYB.getAnswer();

        Matrix UY = LinearAlgebra.augment(U, Y);
        solve_ax_b UXY = new solve_ax_b(UY);
        this.x = UXY.getAnswer();
    }

    public Vector getX() {
        return x;
    }

    public Matrix getLU() {
        return LU;
    }
}
