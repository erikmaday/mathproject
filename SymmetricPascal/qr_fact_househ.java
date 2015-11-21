package SymmetricPascal;

import base.LinearAlgebra;
import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class qr_fact_househ {

    private double[][] QR;
    private int m;
    private int n;
    private double[] rightDiagonal;
    private double error;

    /**
     * Constructor, initializes matrices
     * @param A the input Matrix to calculate reflections for
     */
    public qr_fact_househ(Matrix A) {

        QR = A.toArray();
        m = A.getHeight();
        n = A.getWidth();
        rightDiagonal = new double[n];

        for (int k = 0; k < n; k++) {
            double norm = 0;
            for (int i = k; i < m; i++) {
                norm = Math.hypot(norm, QR[i][k]);
            }
            if (norm != 0.0) {
                if (QR[k][k] < 0) {
                    norm = -norm;
                }
                for (int i = k; i < m; i++) {
                    QR[i][k] /= norm;
                }
                QR[k][k] += 1.0;

                for (int j = k+1; j < n; j++) {
                    double s = 0.0;
                    for (int i = k; i < m; i++) {
                        s += QR[i][k]*QR[i][j];
                    }
                    s = -s/QR[k][k];
                    for (int i = k; i < m; i++) {
                        QR[i][j] += s*QR[i][k];
                    }
                }
            }
            rightDiagonal[k] = -norm;
        }

        //Calculate Error
        Matrix qrMinusA = LinearAlgebra.matrixSubtraction(new Matrix(QR), A);
        double error = LinearAlgebra.norm(qrMinusA);
        this.error = Math.abs(error);
    }

    /**
     * Calculates and returns the Q matrix for Householders
     * @return the Q matrix
     */
    public double[][] getQ () {
        Matrix X = new Matrix(m,n);
        double[][] Q = X.toArray();
        for (int k = n-1; k >= 0; k--) {
            for (int i = 0; i < m; i++) {
                Q[i][k] = 0.0;
            }
            Q[k][k] = 1.0;
            for (int j = k; j < n; j++) {
                if (QR[k][k] != 0) {
                    double s = 0.0;
                    for (int i = k; i < m; i++) {
                        s += QR[i][k]*Q[i][j];
                    }
                    s = -s/QR[k][k];
                    for (int i = k; i < m; i++) {
                        Q[i][j] += s*QR[i][k];
                    }
                }
            }
        }
        return Q;
    }

    /**
     * Calculates and returns the R matrix for Householders
     * @return the R matrix
     */
    public double[][] getR () {
        Matrix X = new Matrix(n,n);
        double[][] R = X.toArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    R[i][j] = QR[i][j];
                } else if (i == j) {
                    R[i][j] = rightDiagonal[i];
                } else {
                    R[i][j] = 0.0;
                }
            }
        }
        return R;
    }

    /**
     * Returns the error
     * @return the error
     */
    public double getError() {
        return error;
    }
}