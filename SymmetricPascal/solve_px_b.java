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
        System.out.println("P\n" + P.toString());
        lu_fact luFactor = new lu_fact(P.copy());
        System.out.println("P\n" + P.toString());
        System.out.println("L\n" + luFactor.getL().toString());
        System.out.println("U\n" + luFactor.getU().toString());

//        pascal p = new pascal(n);
//        Matrix P = p.getPascalMatrix();
//        Vector B = p.getPascalVector();
//        System.out.println("P\n" + P.toString());
//        solve_lu_b lup = new solve_lu_b(new Matrix(P.toArray()), B);
//        lu_fact luFactor = new lu_fact(P);
//        Matrix LU = luFactor.getL();
//        System.out.println("LU\n" + LU.toString());
//        System.out.println("P\n" + P.toString());
//        Matrix LUDiff = LinearAlgebra.matrixSubtraction(LU, P);
//        LUError = LinearAlgebra.norm(LinearAlgebra.matrixSubtraction(new Matrix(LU.toArray()), new Matrix(P.toArray())));
//        System.out.println("LU\n" + LU.toString());
//        System.out.println("P\n" + P.toString());
//        System.out.println(LUError);
//        solve_ax_b pxb = new solve_ax_b(LinearAlgebra.augment(new Matrix(P.toArray()), new Vector(B.toArray())));
//        x = pxb.getAnswer();
//        Vector px = LinearAlgebra.matrixVectorMultiply(new Vector (x.toArray()), new Matrix(P.toArray()));
//        PXBError = LinearAlgebra.magnitude(LinearAlgebra.vectorSubtract(px, B));
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

    public static void main(String[] args) {
        solve_px_b blah = new solve_px_b(5);
    }
}
