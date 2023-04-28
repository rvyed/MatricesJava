public class Matrix {
    private int numRows;
    private int numCols;
    private double[][] data;
    public Matrix(int r, int c) {
      numRows = r;
      numCols = c;
      data = new double[r][c];
    }
  
    public Matrix(int r, int c, double[] linArr) {
      numRows = r;
      numCols = c;
      data = new double[r][c];
      int k = 0;
      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          data[i][j] = linArr[k++];
        }
      }
    }
  
    public int getNumRows() {
      return numRows;
    }
  
    public int getNumCols() {
      return numCols;
    }
  
    public double[][] getData() {
      return data;
    }
  
    public double getElement(int r, int c) {
      return data[r][c];
    }
  
    public void setElement(int r, int c, double value) {
      data[r][c] = value;
    }
  
    public void transpose() {
      double[][] transposedData = new double[numCols][numRows];
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numCols; j++) {
          transposedData[j][i] = data[i][j];
        }
      }
      int temp = numRows;
      numRows = numCols;
      numCols = temp;
      data = transposedData;
    }
  
    public Matrix multiply(double scalar) {
      Matrix result = new Matrix(numRows, numCols);
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numCols; j++) {
          result.data[i][j] = data[i][j] * scalar;
        }
      }
      return result;
    }
  
    public Matrix multiply(Matrix other) {
      if (numCols != other.numRows) {
        return null;
      }
      int rows = numRows;
      int cols = other.numCols;
      int inner = numCols;
      Matrix result = new Matrix(rows, cols);
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          double sum = 0;
          for (int k = 0; k < inner; k++) {
            sum += data[i][k] * other.data[k][j];
          }
          result.data[i][j] = sum;
        }
      }
      return result;
    }


    public String toString() {
      if (data.length == 0) {
          return "Empty matrix";
      }
      String[] matrixRows = new String[numRows];
      for (int i = 0; i < numRows; i++) {
          StringBuilder row = new StringBuilder();
          for (int j = 0; j < numCols; j++) {
              row.append(String.format("%8.3f", data[i][j]));
          }
          matrixRows[i] = row.toString();
      }
      return String.join("\n", matrixRows).replaceAll("\\s+$", "");
  }
  
}    
  