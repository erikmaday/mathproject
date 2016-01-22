package PartOne;

import base.Vector;

/**
 * @author Erik Maday
 */
public class gaussans {
    private Vector x;
    private Vector xInitial;
    private int N;

    public gaussans(Vector x, Vector xInitial, int N) {
        this.x = x;
        this.xInitial = xInitial;
        this.N = N;
    }

    public Vector getX() {
        return x;
    }

    public int getN() {
        return N;
    }

    public void setX(Vector x) {
        this.x = x;
    }

    public void setN(int n) {
        N = n;
    }

    public Vector getxInitial() {
        return xInitial;
    }

    public String toString() {
        String buf = "";
        buf += "Initial Guess: " + xInitial.toString() + "\n";
        buf += "X: " + x.toString() + "\n";
        buf += "Iterations: " + N + "\n";
        return buf;
    }
}
