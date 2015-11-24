package PartOne;

import base.LinearAlgebra;
import base.Vector;
import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class solve_px_b {

    private double LUError;
    private double PXBError;
    private double GivensError;
    private double HouseholderError;
    private Vector x;

    public solve_px_b(int n) {
        pascal p = new pascal(n);
        Matrix P = p.getPascalMatrix();
        Vector B = p.getPascalVector();
        lu_fact luFactor = new lu_fact(new Matrix(P.getArrayCopy()));
        Matrix LU = (luFactor.getL()).times(luFactor.getU());
        Matrix LUDiff = LinearAlgebra.matrixSubtraction(LU, P);
        LUError = LinearAlgebra.norm((LU.minus(P)));
        solve_ax_b pxb = new solve_ax_b(LinearAlgebra.augment(new Matrix(P.getArray()), new Vector(B.toArray())));
        x = pxb.getAnswer();
        Vector px = LinearAlgebra.matrixVectorMultiply(new Vector (x.toArray()), new Matrix(P.getArray()));
        PXBError = LinearAlgebra.magnitude(LinearAlgebra.vectorSubtract(px, B));
        GivensError = LinearAlgebra.norm(new Matrix(new qr_fact_givens(P.getArray()).getQR()).minus(P));
        HouseholderError = LinearAlgebra.norm(new qr_fact_househ(P).getQR().minus(P));

    }

    public double getLUError() {
        return LUError;
    }

    public double getPXBError() {
        return PXBError;
    }

    public double getGivensError() {
        return GivensError;
    }

    public double getHouseholderError() {
        return HouseholderError;
    }

    public Vector getX() {
        return x;
    }

    public static void main(String[] args) {
        for (int n = 2; n <= 12; n++) {
            solve_px_b temp = new solve_px_b(n);
            System.out.println("N = " + n + ":");
            System.out.println("\t|LU-P| = " + temp.getLUError());
            System.out.println("\t|PX-B| = " + temp.getPXBError());
            System.out.println("\t|QR-P| G = " + temp.getGivensError());
            System.out.println("\t|QR-P| H = " + temp.getHouseholderError());
        }
    }
}
