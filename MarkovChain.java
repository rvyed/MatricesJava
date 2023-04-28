//Author: Raed LNU
//Date: Wed, Feb 8th 2023

public class MarkovChain {

    // field to store the state vector
    private Vector stateVector;
    // field to store the transition matrix
    private Matrix transitionMatrix;

    // constructor to initialize the state vector and transition matrix
    public MarkovChain(Vector sVector, Matrix tMatrix) {
        this.stateVector = sVector;
        this.transitionMatrix = tMatrix;
    }

    // method to validate the markov chain
    public boolean isValid() {
        // check if the number of rows of the transition matrix is equal to the number of columns
        if (transitionMatrix.getNumRows() != transitionMatrix.getNumCols()) {
            return false;
        }

        // check if the number of columns of the state vector is equal to the number of rows of the transition matrix
        if (stateVector.getNumCols() != transitionMatrix.getNumRows()) {
            return false;
        }

        // check if the total of elements in the state vector is between 0.99 and 1.01
        double total = 0;
        for (int x = 0; x < stateVector.getNumCols(); x++) {
            total += stateVector.getElement(x);
        }
        if (total < 0.99 || total > 1.01) {
            return false;
        }

        // check if the total of elements in each row of the transition matrix is between 0.99 and 1.01
        for (int y = 0; y < transitionMatrix.getNumRows(); y++) {
            total = 0;
            for (int z = 0; z < transitionMatrix.getNumCols(); z++) {
                total += transitionMatrix.getElement(y, z);
            }
            if (total < 0.99 || total > 1.01) {
                return false;
            }
        }

        // if all checks pass, return true
        return true;
    }

    // method to compute the probability matrix
    public Matrix computeProbabilityMatrix(int numSteps) {
        // check if the markov chain is valid
        if (!isValid()) {
            return null;
        }
        // initialize the result matrix with the transition matrix
        Matrix result = transitionMatrix;
        // multiply the transition matrix with itself "numSteps" times
        for (int n = 1; n < numSteps; n++) {
            result = result.multiply(transitionMatrix);
        }
        // return the state vector multiplied with the result matrix
        return stateVector.multiply(result);
    }
}
