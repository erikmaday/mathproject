import java.util.Stack;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class qr_fact_givens {

    private double[][] Q;
    private double[][] R;
    private double[][] QR;

    public qr_fact_givens() {}

    public double[][] getQ() {
        return Q;
    }

    public double[][] getR() {
        return R;
    }

    public double[][] getQR() {
        return QR;
    }

    public qr_fact_givens(double[][] matrix) {
        int height = matrix.length;
        double[][] diagonalMatrix = createDiagonal(height);
        Stack<double[][]> givensMatrices = new Stack<double[][]>();
        double[][] matrixR = matrix;
        int i = 0;
        int j = 0;
        while (i < matrixR[0].length) {
            int level = height - 1;
            while (j < level) {
                double x = matrixR[level-1][i];
                double y = matrixR[level][i];
                double c = changeToC(x, y);
                double s = changeToS(x, y);
                diagonalMatrix[level][level - 1] = s;
                diagonalMatrix[level][level] = c;
                diagonalMatrix[level - 1][level - 1] = c;
                diagonalMatrix[level - 1][level] = -s;
                givensMatrices.push(diagonalMatrix);
                matrixR = LinearAlgebra.multiplyMatrix(diagonalMatrix, matrixR);
                diagonalMatrix = createDiagonal(height);
                level--;
            }
            i++;
            j++;
        }
        this.R = matrixR;
        double[][] first = LinearAlgebra.transposeMatrix(givensMatrices.pop());
        while (!givensMatrices.empty()) {
            first = LinearAlgebra.multiplyMatrix(LinearAlgebra.transposeMatrix(givensMatrices.pop()), first);
        }
        this.Q = first;
        this.QR = LinearAlgebra.multiplyMatrix(first, matrixR);
    }

    public double changeToC(double x, double y) {
        double bottom = Math.sqrt((x * x) + (y * y));
        return x / bottom;
    }

    public double changeToS(double x, double y) {
        double bottom = Math.sqrt((x * x) + (y * y));
        y = y * (-1);
        return y / bottom;
    }

    /**
     * helper method to create diagonal
     * matrix with 1's for diagonal
     * @param h number of rows in matrix A
     * @return matrix
     */
    public double[][] createDiagonal(int height) {
        double[][] diagonalMatrix = new double[height][height];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < height; y++) {
                if (x == y)
                    diagonalMatrix[x][y] = 1;
                else
                    diagonalMatrix[x][y] = 0;
            }
        }
        return diagonalMatrix;
    }
}
