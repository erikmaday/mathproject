package PartTwo;

import base.Vector;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class pmanswer {

    private Vector eigenvector;
    private double eigenvalue;
    private int iterations;

    public pmanswer(Vector eigenvector, double eigenvalue, int iterations) {
        this.eigenvector = eigenvector;
        this.eigenvalue = eigenvalue;
        this.iterations = iterations;
    }

    public Vector getEigenvector() {
        return eigenvector;
    }

    public double getEigenvalue() {
        return eigenvalue;
    }

    public int getIterations() {
        return iterations;
    }

    public String toString() {
        String buf = "";
        buf += "The eigenvector is: " + eigenvector + "\n";
        buf += "The eigenvalue is: "  + eigenvalue + "\n";
        buf += "This took " + iterations + " iterations to calculate.\n";
        return buf;
    }
}
