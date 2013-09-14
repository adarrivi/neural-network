package com.neuron.example.genetic;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.neuron.example.genetic.tsp.GeneticTspSolver;

public class Panel extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final Panel INSTANCE = new Panel();
	private static final Rectangle DIALOG_POS = new Rectangle(100, 100,
			GeneticTspSolver.MAP_SIZE, GeneticTspSolver.MAP_SIZE);

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

		// CountryPanel countryPanel = new CountryPanel();
		// countryPanel.setCountry(tspSolver.getCountry());
		//
		// getContentPane().add(countryPanel);
		// tspSolver.solve();
	}
}
