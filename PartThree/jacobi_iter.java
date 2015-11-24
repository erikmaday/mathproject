package PartThree;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class jacobi_iter {

    public static final int MAX_ITERATIONS = 100;
    private double[][] M;
  
    public jacobi_iter(double [][] matrix) { M = matrix; }

    public void print()
    {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
    }

    public boolean transformToDominant(int r, boolean[] V, int[] R)
    {
        int n = M.length;
        if (r == M.length) {
            double[][] T = new double[n][n+1];
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = M[R[i]][j];
            }

            M = T;
      
            return true;
        }

        for (int i = 0; i < n; i++) {
            if (V[i]) continue;

            double sum = 0;
      
            for (int j = 0; j < n; j++)
                sum += Math.abs(M[i][j]);

            if (2 * Math.abs(M[i][r]) > sum) { // diagonally dominant?
                V[i] = true;
                R[r] = i;

                if (transformToDominant(r + 1, V, R))
                    return true;

                V[i] = false;
            }
        }

        return false;
    }
  
  
  /**
   * Returns true if is possible to transform M(data member) to a diagonally
   * dominant matrix, false otherwise.
  */
    public boolean makeDominant()
    {
        boolean[] visited = new boolean[M.length];
        int[] rows = new int[M.length];

        Arrays.fill(visited, false);

        return transformToDominant(0, visited, rows);
    }


  /**
   * Applies Jacobi method to find the solution of the system
   * of linear equations represented in matrix M.
   * M is a matrix with the following form:
   * a_11 * x_1 + a_12 * x_2 + ... + a_1n * x_n = b_1
   * a_21 * x_1 + a_22 * x_2 + ... + a_2n * x_n = b_2
   * .                 .                  .        .
   * .                 .                  .        .
   * .                 .                  .        .
   * a_n1 * x_n + a_n2 * x_2 + ... + a_nn * x_n = b_n
  */
    public void solve()
    {
        int iterations = 0;
        int n = M.length;
        double epsilon = 1e-15;
        double[] X = new double[n]; // Approximations
        double[] P = new double[n]; // Prev
        Arrays.fill(X, 0);
        Arrays.fill(P, 0);

        while (true) {
            for (int i = 0; i < n; i++) {
                double sum = M[i][n]; // b_n

                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= M[i][j] * P[j];

                X[i] = 1/M[i][i] * sum;
            }

            System.out.print("X_" + iterations + " = {");
            for (int i = 0; i < n; i++)
                System.out.print(X[i] + " ");
            System.out.println("}");

            iterations++;
            if (iterations == 1) continue;

            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;

            if (stop || iterations == MAX_ITERATIONS) break;
            P = (double[])X.clone();
        }
    }

    public static void main(String[] args) throws IOException
    {
        int n;
        double[][] M;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true);

        n = Integer.parseInt(reader.readLine());
        M = new double[n][n+1];

        for (int i = 0; i < n; i++) {
            StringTokenizer strtk = new StringTokenizer(reader.readLine());

            while (strtk.hasMoreTokens())
            for (int j = 0; j < n + 1 && strtk.hasMoreTokens(); j++)
                M[i][j] = Integer.parseInt(strtk.nextToken());
        }
    
        jacobi_iter jacobi = new jacobi_iter(M);

        if (!jacobi.makeDominant()) {
        writer.println("The system isn't diagonally dominant: " + 
                     "The method cannot guarantee convergence.");
        }

        writer.println();
        jacobi.print();
        jacobi.solve();
    }
}