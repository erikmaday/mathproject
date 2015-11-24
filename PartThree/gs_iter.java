package PartThree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */

public class gs_iter

{
    private double[][] A = {{1.0, 1.0/2, 1.0/3},{1.0/2, 1.0/3, 1.0/4},{1.0/3, 1.0/4, 1.0/5}};
    private int maxIterations;
    private double tolerance;
    private double[] v;

    public gs_iter(double[] vector, double tolerance, int iterations) {
        v = vector;
        this.tolerance = tolerance;
        this.maxIterations = iterations;
    }

    























    ////////////////////////////////////////
    public void print()
    {

        int n = m.length;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n + 1; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
    }

    public boolean transformToDominant(int r, boolean[] V, int[] R)
    {

        int n = m.length;

        if (r == m.length)
        {
            double[][] T = new double[n][n+1];

            for (int i = 0; i < R.length; i++)
            {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = m[R[i]][j];
            }

            m = T;
            return true;
        }

        for (int i = 0; i < n; i++)
        {

            if (V[i]) continue;
            double sum = 0;

            for (int j = 0; j < n; j++)
                sum += Math.abs(m[i][j]);

            if (2 * Math.abs(m[i][r]) > sum)
            { // diagonally dominant?
                V[i] = true;
                R[r] = i;

                if (transformToDominant(r + 1, V, R))
                    return true;

                V[i] = false;
            }
        }

        return false;
    }

    public boolean makeDominant()
    {

        boolean[] visited = new boolean[m.length];
        int[] rows = new int[m.length];
        Arrays.fill(visited, false);

        return transformToDominant(0, visited, rows);
    }

    public void solve()
    {
        int iterations = 0;
        int n = m.length;
        double epsilon = 1e-15;
        double[] X = new double[n]; // Approximations
        double[] P = new double[n]; // Prev
        Arrays.fill(X, 0);

        while (true)
        {
            for (int i = 0; i < n; i++)
            {
                double sum = m[i][n]; // b_n

                for (int j = 0; j < n; j++)

                    if (j != i)
                        sum -= m[i][j] * X[j];

                X[i] = 1/m[i][i] * sum;
            }

            System.out.print("X_" + iterations + " = {");

            for (int i = 0; i < n; i++)
                System.out.print(X[i] + " ");
            System.out.println("}");

            iterations++;

            if (iterations == 1)
                continue;

            boolean stop = true;

            for (int i = 0; i < n && stop; i++)

                if (Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;

            if (stop || iterations == maxIterations) break;

            P = (double[])X.clone();
        }
    }

    public static void main(String[] args) throws IOException

    {

        int n;
        double[][] M;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true);
        System.out.println("Enter the number of variables in the equation:");

        n = Integer.parseInt(reader.readLine());
        M = new double[n][n+1];
        System.out.println("Enter the augmented matrix:");

        for (int i = 0; i < n; i++)

        {

            StringTokenizer strtk = new StringTokenizer(reader.readLine());
            while (strtk.hasMoreTokens())

                for (int j = 0; j < n + 1 && strtk.hasMoreTokens(); j++)
                    M[i][j] = Integer.parseInt(strtk.nextToken());
        }

        gs_iter gausSeidel = new gs_iter(M);

        if (!gausSeidel.makeDominant())
        {
            writer.println("The system isn't diagonally dominant: " +
                    "The method cannot guarantee convergence.");
        }

        writer.println();
        gausSeidel.print();
        gausSeidel.solve();
    }
}