package com.neuron.example.genetic.tsp;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CountryPanel extends JPanel {

	private Country country;

	private static final long serialVersionUID = 1L;

	public CountryPanel(Country country) {
		this.country = country;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		country.drawComponent(graphics);
	}

}
