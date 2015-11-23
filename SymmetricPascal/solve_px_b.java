package SymmetricPascal;

import base.LinearAlgebra;
import base.Matrix;
import base.Vector;

/**
 * Created by erikmaday on 11/22/15.
 */
public class solve_px_b {

    private double LUError;
    private double PXBError;
    private Vector x;

    public solve_px_b (int n) {
        pascal p = new pascal(n);
        Matrix P = p.getPascalMatrix();
        Vector B = p.getPascalVector();
        solve_lu_b lup = new solve_lu_b(new Matrix(P.toArray()), new Vector(B.toArray()));
        Matrix LU = lup.getLU();
        LUError = LinearAlgebra.norm(LinearAlgebra.matrixSubtraction(new Matrix(LU.toArray()), new Matrix(P.toArray())));
        solve_ax_b pxb = new solve_ax_b(LinearAlgebra.augment(new Matrix(P.toArray()), new Vector(B.toArray())));
        x = pxb.getAnswer();
        Vector px = LinearAlgebra.matrixVectorMultiply(new Vector (x.toArray()), new Matrix(P.toArray()));
        PXBError = LinearAlgebra.magnitude(LinearAlgebra.vectorSubtract(px, B));
    }

    public static void main(String[] args) {
        solve_px_b pqr = new solve_px_b(4);
        System.out.println(pqr.getX().toString());
    }

    public double getLUError() {
        return LUError;
    }

    public double getPXBError() {
        return PXBError;
    }

    public Vector getX() {
        return x;
    }
}
