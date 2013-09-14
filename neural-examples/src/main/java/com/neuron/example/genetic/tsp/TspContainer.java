package com.neuron.example.genetic.tsp;

import java.awt.Graphics;

import javax.swing.JPanel;

public class TspContainer extends JPanel {

	private static final long serialVersionUID = 1L;
	private CountryPanel countryPanel;
	private Country country;

	public TspContainer(Country country) {
		this.country = country;
		setLayout(null);

		createNewCountryPanel();

		setVisible(true);
	}

	private void createNewCountryPanel() {
		countryPanel = new CountryPanel(country);
		countryPanel.setBounds(10, 11, 250, 250);
		add(countryPanel);
	}

	public void showNextPath() {
		setLayout(null);
		remove(countryPanel);
		country.incrementCurrentTravelerIndex();
		createNewCountryPanel();
		setVisible(true);
		repaint();
		validate();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		countryPanel.repaint();
	}
}
