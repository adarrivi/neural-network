package com.neuron.example;

import java.util.ArrayList;
import java.util.List;

public class ConsoleHopfield {

	public static String formatBoolean(final boolean b[]) {
		final StringBuilder result = new StringBuilder();
		result.append('[');
		for (int i = 0; i < b.length; i++) {
			if (b[i]) {
				result.append("T");
			} else {
				result.append("F");
			}
			if (i != b.length - 1) {
				result.append(",");
			}
		}
		result.append(']');
		return (result.toString());
	}

	public static void main(final String args[]) {
		final boolean[] pattern1 = { true, true, false, false };
		final boolean[] pattern2 = { true, false, false, false };
		List<boolean[]> trainingList = new ArrayList<boolean[]>();
		trainingList.add(pattern1);
		// trainAndPresent(trainingList, pattern1);
		// trainAndPresent(trainingList, pattern2);
		trainingList.add(pattern2);
		trainAndPresent(trainingList, pattern1);
		trainAndPresent(trainingList, pattern2);
	}

	private static void trainAndPresent(List<boolean[]> trainPatterns,
			boolean[] presentPattern) {
		HopfieldNetwork network = new HopfieldNetwork();
		network.createMatrix(trainPatterns.get(0).length);
		for (boolean[] trainPattern : trainPatterns) {
			network.trainWithPattern(trainPattern);
			System.out.println("Weight matrix");
			network.printWeighMatrix();
		}

		boolean[] result = network.present(presentPattern);

		StringBuilder sb = new StringBuilder();
		if (areSameArray(result, presentPattern)) {
			sb.append("Recognized pattern: ");
		} else {
			sb.append("Not recognized pattern: ");
		}
		sb.append(formatBoolean(presentPattern)).append(", and got ")
				.append(formatBoolean(network.present(presentPattern)));
		System.out.println(sb.toString());
	}

	private static boolean areSameArray(boolean[] array1, boolean[] array2) {
		for (int i = 0; i < array1.length; i++) {
			if (array1[i] != array2[i]) {
				return false;
			}
		}
		return true;
	}
}