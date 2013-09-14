package com.neuron.example;

import org.encog.engine.network.activation.ActivationFunction;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

public class SumBackPropagation {
	private static final double ACCEPTABLE_ERROR = 0.001;
	private static final int MAX_EPOCH = 5000;
	public static double XOR_INPUT[][] = { { 0.0, 0.0 }, { 1.0, 0.0 },
			{ 0.0, 1.0 }, { 1.0, 1.0 } };
	public static double XOR_IDEAL[][] = { { 0.0 }, { 1.0 }, { 1.0 }, { 2.0 } };

	private static final SumBackPropagation INSTANCE = new SumBackPropagation();

	private BasicNetwork network;
	private MLDataSet dataSet;
	private Train train;
	private int epochIteration = 0;

	public static void main(final String args[]) {
		INSTANCE.run();
	}

	private void run() {
		createFeedForwardNeuralNetwork();
		createDataSet();
		setBackpropagationTraining();
		trainNeuralNetwork();
		testNeuralNetwork();
	}

	private void createFeedForwardNeuralNetwork() {
		network = new BasicNetwork();
		ActivationFunction activationFunction = new ActivationTANH();
		network.addLayer(new BasicLayer(activationFunction, false, 2));
		network.addLayer(new BasicLayer(activationFunction, false, 3));
		network.addLayer(new BasicLayer(activationFunction, false, 1));
		network.getStructure().finalizeStructure();
		network.reset();
	}

	private void createDataSet() {
		dataSet = new BasicNeuralDataSet(XOR_INPUT, XOR_IDEAL);
	}

	private void setBackpropagationTraining() {
		train = new Backpropagation(network, dataSet);
	}

	private void trainNeuralNetwork() {
		while (continueTraining()) {
			train.iteration();
			System.out.println("Epoch #" + epochIteration + " Error:"
					+ train.getError());
			epochIteration++;
		}
	}

	private boolean continueTraining() {
		if (epochIteration >= MAX_EPOCH
				|| ((epochIteration != 0 && train.getError() < ACCEPTABLE_ERROR))) {
			return false;
		}
		return true;
	}

	private void testNeuralNetwork() {
		System.out.println("Neural Network Results:");
		for (int i = 0; i < XOR_IDEAL.length; i++) {
			MLData computed = network.compute(new BasicMLData(XOR_INPUT[i]));
			System.out.println(XOR_INPUT[i][0] + "," + XOR_INPUT[i][1]
					+ ", actual=" + computed.getData(0) + ",ideal="
					+ XOR_IDEAL[i][0]);
		}
	}
}