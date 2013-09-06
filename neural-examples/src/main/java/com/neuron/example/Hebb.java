package com.neuron.example;

public class Hebb {

	public static void main(final String args[]) {
		final Hebb delta = new Hebb();
		delta.run();
	}

	private double neuron1Weight;
	private double neuron2Weight;
	private double learningRate = 1.0;
	private int currentEpoch = 1;

	public Hebb() {
		this.neuron1Weight = 1;
		this.neuron2Weight = -1;
	}

	/**
	 * epoch. Here we learn from all three training samples and then update the
	 * weights based on error.
	 */
	protected void epoch() {
		System.out.println("***Beginning Epoch #" + this.currentEpoch + "***");
		presentPattern(-1, -1);
		presentPattern(-1, 1);
		presentPattern(1, -1);
		presentPattern(1, 1);
		this.currentEpoch++;
	}

	/**
	 * Present a pattern and learn from it.
	 * 
	 * @param i1
	 *            Input to neuron 1
	 * @param i2
	 *            Input to neuron 2
	 * @param i3
	 *            Input to neuron 3
	 * @param anticipated
	 *            The anticipated output
	 */
	protected void presentPattern(final double i1, final double i2) {
		double result;
		double delta;
		// run the net as is on training data
		// and get the error
		System.out.print("Presented [" + i1 + "," + i2 + "]");
		result = recognize(i1, i2);
		System.out.print(" Weigths [" + neuron1Weight + "," + neuron2Weight
				+ "]");
		System.out.print(" result=" + result);
		// adjust weight 1
		delta = trainingFunction(this.learningRate, i1, result);
		this.neuron1Weight += delta;
		System.out.print(",delta w1=" + delta);
		// adjust weight 2
		delta = trainingFunction(this.learningRate, i2, result);
		this.neuron2Weight += delta;
		System.out.println(",delta w2=" + delta);
	}

	/**
	 * @param i1
	 *            Input to neuron 1
	 * @param i2
	 *            Input to neuron 2
	 * @param i3
	 *            Input to neuron 3
	 * @return the output from the neural network
	 */
	protected double recognize(final double i1, final double i2) {
		final double a = (this.neuron1Weight * i1) + (this.neuron2Weight * i2);
		return (a * .5);
	}

	/**
	 * This method loops through 10 epochs.
	 */
	public void run() {
		for (int i = 0; i < 5; i++) {
			epoch();
		}
	}

	/**
	 * The learningFunction implements Hebb's rule. This method will return the
	 * weight adjustment for the specified input neuron.
	 * 
	 * @param rate
	 *            The learning rate
	 * @param input
	 *            The input neuron we're processing
	 * @param error
	 *            The error between the actual output and anticipated output.
	 * @return The amount to adjust the weight by.
	 */
	protected double trainingFunction(final double rate, final double input,
			final double output) {
		return rate * input * output;
	}
}