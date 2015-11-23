package SymmetricPascal;

        import base.LinearAlgebra;
        import base.Matrix;
        import base.Vector;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class solve_lu_b {

    private Vector x;

    public solve_lu_b(Matrix m, Vector B) {
        lu_fact fact = new lu_fact(m);
        Matrix L = fact.getL();
        Matrix U = fact.getU();
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
}
