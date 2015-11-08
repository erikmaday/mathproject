package base;

/**
 * @author Erik Maday
 * @version 1.0
 */
public class Vector {

    private final double[] vector;
    private final int size;

    /**
     * Initialize vector
     * @param vector that represents the vector
     */
    public Vector(double[] vector) {
        this.vector = vector;
        this.size = vector.length;
    }

    /**
     * Gets value at index
     * @param i index in vector
     * @return double value located at given
     */
    public double get(int i) {
        if (i > size) {
            throw new IndexOutOfBoundsException(i + " is not a valid "
                    + "index in the vector of size " + size + ".");
        }
        return vector[i];
    }

    /**
     * Returns the size of the vector
     * @return int number of entries in vector
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns array of the vector
     * @return array of doubles of the vector
     */
    public double[] toArray() {
        return vector;
    }

    /**
     * String representation of vector with tab spacing
     * @return String representation of vector
     */
    public String toString() {
        String buf = "";
        for (int i = 0; i < size; i++) {
            buf = buf + "    " + vector[i];
        }
        return buf;
    }
}