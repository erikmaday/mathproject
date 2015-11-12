package SymmetricPascal;

import base.*;

/**
* @author Erik Maday, CG Carson, Quinton Johnson
* @version 1.0
*/
public class qr_fact_househ {

  private double[][] Q;
  private double[][] QTranspose;
  private double[][] QTransposeMult;
  private double[][] R;
  private double[][] QR;

  public qr_fact_househ() {}

  public double[][] getQ() {
    return Q;
  }

  public double[][] getR() {
    return R;
  }

  public double[][] getQR() {
    return QR;
  }

  public double[][] getQTranspose() {
    return QTranspose;
  }

  public double[][] getQTransposeMult() {
    return QTransposeMult;
  }

  public void findQR(double[][] mArray) {

    double[] x1 = new double[mArray.length]; //Horizontal Vector V V
    double[] x2 = new double[mArray.length - 1];
    double[] x3 = new double[mArray.length - 2];
    for (int i = 0; i < x1.length; i++) {
      x1[i] = mArray[i][0];
    }

    x1[0] += LinearAlgebra.magnitude(x1); //v = x + ||x||e1

    double magV = LinearAlgebra.magnitude(x1);
    for (int i = 0; i < x1.length; i++) {// u = v / ||v||
      x1[i] = x1[i] / magV;
    }

    double[][] i1 = getIdentity(x1.length, x1.length);
    double[][] i2 = getIdentity(x2.length, x2.length);
    double[][] i3 = getIdentity(x3.length, x3.length);

    double[][] x1Mult = LinearAlgebra.vectorVectorTransposeMultiply(x1, x1); //u * u^T

    x1Mult = LinearAlgebra.scalarMultiply(x1Mult, -2); //-2 uu^T

    double[][] h1 = LinearAlgebra.matrixAdd(new Matrix(i1), new Matrix(x1Mult)); //II - 2uu^T

    double[][] h1A = LinearAlgebra.multiplyMatrix(h1, mArray);

    for (int i = 0; i < x2.length; i++) {
      x2[i] = h1A[i + 1][1];
    }

    x2[0] += LinearAlgebra.magnitude(x2);

    double magV2 = LinearAlgebra.magnitude(x2);
    for (int i = 0; i < x2.length; i++) {// u = v / ||v||
      x2[i] = x2[i] / magV2;
    }

    double[][] x2Mult = LinearAlgebra.vectorVectorTransposeMultiply(x2, x2);

    x2Mult = LinearAlgebra.scalarVectorMultiply(x2Mult, -2);
    double[][] h2 = LinearAlgebra.matrixAddition(new Matrix(i2), new Matrix(x2Mult));
    double[][] q1 = LinearAlgebra.multiplyMatrix(h2Create(h2), h1);
    double[][] q1A = LinearAlgebra.multiplyMatrix(q1, mArray);

    for (int i = 0; i < x3.length; i++) {
      x3[i] = q1A[i + 2][2];
    }

    x3[0] += LinearAlgebra.magnitude(x3);

    double magV3 = LinearAlgebra.magnitude(x3);
    for (int i = 0; i < x3.length; i++) {// u = v / ||v||
      x3[i] = x3[i] / magV3;
    }

    double[][] x3Mult = LinearAlgebra.vectorVectorTransposeMultiply(x3, x3);
    x3Mult = LinearAlgebra.scalarVectorMultiply(x3Mult, -2);

    double[][] h3 = LinearAlgebra.matrixAddition(new Matrix(i3), new Matrix(x3Mult));
    double[][] qArr = LinearAlgebra.multiplyMatrix(h3Create(h3), q1);

    double[][] rArr = LinearAlgebra.multiplyMatrix(qArr, mArray);
    this.Q = resizeQ(qArr);
    qArr = LinearAlgebra.transposeMatrix(qArr);
    this.QTranspose = qArr;
    this.QTransposeMult = LinearAlgebra.transposeMatrix(resizeQ(qArr));
    this.R = resizeR(rArr);
    this.QR = LinearAlgebra.multiplyMatrix(QTranspose, rArr);
    System.out.println("Q");
    System.out.println(new Matrix(this.Q));
    System.out.println("Q Transpose");
    System.out.println(new Matrix(this.QTranspose));
    System.out.println("R");
    System.out.println(new Matrix(this.R));
    System.out.println("QR");
    System.out.println(new Matrix(this.QR));
  }

  public double[][] resizeQ(double[][] qArr) {
    double[][] copy = new double[qArr.length][3];
    for (int i = 0; i < qArr.length; i++) {
      for (int j = 0; j <= 2; j++) {
        copy[i][j] = qArr[i][j];
      }
    }
    return copy;
  }

  public double[][] resizeR(double[][] rArr) {
    double[][] copy = new double[3][3];
    for (int i = 0; i <= 2; i++) {
      for (int j = 0; j <= 2; j++) {
        copy[i][j] = rArr[i][j];
      }
    }
    return copy;
  }

  public double[][] h2Create(double[][] matrix) {
    double[][] resized = getIdentity(matrix.length + 1,matrix[0].length + 1);
    for (int i = 1; i < resized.length; i++) {
      for (int j = 1; j < resized[0].length; j++) {
        resized[i][j] = matrix[i - 1][j - 1];
      }
    }
    return resized;
  }

  public double[][] h3Create(double[][] matrix) {
    double[][] resized = getIdentity(matrix.length + 2,matrix[0].length + 2);
    for (int i = 2; i < resized.length; i++) {
      for (int j = 2; j < resized[0].length; j++) {
        resized[i][j] = matrix[i - 2][j - 2];
      }
    }
    return resized;
  }

  public double[][] getIdentity(int height, int width) {
    double[][] matrix = new double[height][width];
    for(int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        matrix[i][j] = (i == j) ? 1 : 0;
      }
    }
    return matrix;
  }
}