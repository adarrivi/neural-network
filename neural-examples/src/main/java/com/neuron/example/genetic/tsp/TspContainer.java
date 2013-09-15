package com.neuron.example.genetic.tsp;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class TspContainer extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private CountryPanel countryPanel;
	private City[] cities;
	private TspProblemProperties problemProperties;

	public TspContainer(City[] cities, TspProblemProperties problemProperties) {
		this.cities = cities;
		this.problemProperties = problemProperties;
		setLayout(null);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object traveler) {
		showPath((Traveler) traveler);
	}

	private void showPath(Traveler traveler) {
		setLayout(null);
		if (countryPanel != null) {
			remove(countryPanel);
		}
		createNewCountryPanel(traveler);
		setVisible(true);
		repaint();
		validate();
	}

	private void createNewCountryPanel(Traveler traveler) {
		countryPanel = new CountryPanel(cities, traveler, problemProperties);
		add(countryPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (countryPanel != null) {
			countryPanel.repaint();
		}
	}
}
