package com.neuron.example.genetic.tsp;

import java.awt.Rectangle;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TspPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final TspPanel INSTANCE = new TspPanel();
	private static final int SIZE_OFFSET = 40;
	private static final int BUTTON_OFFSET = 40;
	private static final Rectangle DIALOG_POS = new Rectangle(100, 100,
			GeneticTspSolver.MAP_SIZE + SIZE_OFFSET, GeneticTspSolver.MAP_SIZE
					+ SIZE_OFFSET + BUTTON_OFFSET);

	public static void main(String[] args) {
		INSTANCE.run();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				INSTANCE.setVisible(true);
			}
		});
	}

	private void run() {
		setTitle("Genetic Tsp");
		setBounds(DIALOG_POS);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GeneticTspSolver tspSolver = new GeneticTspSolver();
		tspSolver.init();
		tspSolver.solve();
		TspContainer tspContainer = new TspContainer(tspSolver.getCountry());
		getContentPane().add(tspContainer);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new ShowNextBestTask(tspContainer), 0, 50);
	}
}
