package PartOne;

import base.LinearAlgebra;
import base.Vector;
import base.Matrix;
import java.util.Arrays;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class solve_px_b {

    private double LUError;
    private double PXBError;
    private Vector x;

    public solve_px_b(int n) {
        pascal p = new pascal(n);
        Matrix P = p.getPascalMatrix();
        Vector B = p.getPascalVector();
        System.out.print("P: \n" + Arrays.deepToString(P.getArrayCopy())
                + "\n\n");
        lu_fact luFactor = new lu_fact(new Matrix(P.getArrayCopy()));
        Matrix LU = luFactor.getL();
        System.out.println("LU\n" + Arrays.deepToString(LU.getArrayCopy())
                + "\n");
        Matrix LUDiff = LinearAlgebra.matrixSubtraction(LU, P);
        LUError = LinearAlgebra.norm((LU.minus(P)));
        System.out.println(LUError);
        solve_ax_b pxb = new solve_ax_b(LinearAlgebra.augment(new
                Matrix(P.getArray()), new Vector(B.toArray())));
        x = pxb.getAnswer();
        Vector px = LinearAlgebra.matrixVectorMultiply(new
                Vector (x.toArray()), new Matrix(P.getArray()));
        PXBError = LinearAlgebra.magnitude(LinearAlgebra.vectorSubtract(px, B));
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
