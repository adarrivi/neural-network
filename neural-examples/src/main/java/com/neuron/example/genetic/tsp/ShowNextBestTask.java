package com.neuron.example.genetic.tsp;

import java.util.TimerTask;

public class ShowNextBestTask extends TimerTask {

	private TspContainer tspContainer;

	public ShowNextBestTask(TspContainer tspContainer) {
		this.tspContainer = tspContainer;
	}

	@Override
	public void run() {
		tspContainer.showNextPath();
	}

}
