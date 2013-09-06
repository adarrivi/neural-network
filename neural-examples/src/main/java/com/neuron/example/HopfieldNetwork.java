package com.neuron.example;

import org.encog.mathutil.matrices.BiPolarUtil;
import org.encog.mathutil.matrices.Matrix;
import org.encog.mathutil.matrices.MatrixMath;
import org.encog.neural.NeuralNetworkError;

public class HopfieldNetwork {

	private boolean[] currentPattern;
	private Matrix weightMatrix;

	public void createMatrix(int size) {
		weightMatrix = new Matrix(size, size);
	}

	public Matrix getWeightMatrix() {
		return weightMatrix;
	}

	public boolean[] present(final boolean[] pattern) {
		final boolean output[] = new boolean[pattern.length];
		// convert the input pattern into a matrix with a
		// single row.
		// also convert the boolean values to
		// bipolar(-1=false, 1=true)
		final Matrix inputMatrix = Matrix.createRowMatrix(BiPolarUtil
				.bipolar2double(pattern));
		// Process each value in the pattern
		for (int col = 0; col < pattern.length; col++) {
			Matrix columnMatrix = this.weightMatrix.getCol(col);
			columnMatrix = MatrixMath.transpose(columnMatrix);
			// The output for this input element is the dot product of the
			// input matrix and one column from the weight matrix.
			final double dotProduct = MatrixMath.dotProduct(inputMatrix,
					columnMatrix);
			// Convert the dot product to either true or false.
			if (dotProduct > 0) {
				output[col] = true;
			} else {
				output[col] = false;
			}
		}
		return output;
	}

	public void trainWithPattern(boolean[] pattern) {
		currentPattern = pattern;
		assertPatternLengthMatrixCreated();

		Matrix bipolarMatrix = getBipolarMatrix();
		Matrix transposedMultipliedMatrix = getTransposedMultipliedFromBipolarMatrix(bipolarMatrix);
		Matrix zeroNorthWestDiagonalMatrix = getZeroNorthWestDiagonalMatrixFromTransposedMatrix(transposedMultipliedMatrix);
		addToWeightMatrix(zeroNorthWestDiagonalMatrix);
	}

	private Matrix getBipolarMatrix() {
		return Matrix.createRowMatrix(BiPolarUtil
				.bipolar2double(currentPattern));
	}

	private Matrix getTransposedMultipliedFromBipolarMatrix(Matrix bipolarMatrix) {
		return MatrixMath.multiply(MatrixMath.transpose(bipolarMatrix),
				bipolarMatrix);
	}

	private Matrix getZeroNorthWestDiagonalMatrixFromTransposedMatrix(
			Matrix transposedMultipliedMatrix) {
		Matrix identity = MatrixMath.identity(transposedMultipliedMatrix
				.getRows());
		return MatrixMath.subtract(transposedMultipliedMatrix, identity);
	}

	private void addToWeightMatrix(Matrix matrix) {
		weightMatrix = MatrixMath.add(weightMatrix, matrix);

	}

	private void assertPatternLengthMatrixCreated() {
		if (currentPattern.length != this.weightMatrix.getRows()) {
			throw new NeuralNetworkError("Can't train a pattern of size "
					+ currentPattern.length + " on a hopfield network of size "
					+ this.weightMatrix.getRows());
		}
	}

	public void printWeighMatrix() {
		for (int row = 0; row < weightMatrix.getRows(); row++) {
			for (int column = 0; column < weightMatrix.getCols(); column++) {
				System.out.print(weightMatrix.get(row, column) + " ");
			}
			System.out.println("");
		}
	}
}
