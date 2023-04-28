public class Vector extends Matrix {
    // Constructor with number of columns
    public Vector(int c) {
        super(1, c);
    }

    // Constructor with number of columns and 1-dimensional array
    public Vector(int c, double[] linArr) {
        super(1, c, linArr);
    }

    // Return the value at row 0 and column c
    public double getElement(int c) {
        return super.getElement(0, c);
    }
}
