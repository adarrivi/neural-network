package com.neuron.example.genetic.tsp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class CountryPanel extends JPanel {
	private static final Color CITY_COLOR = Color.red;
	private static final Color PATH_COLOR = Color.blue;
	private static final int BORDER_OFFSET = 40;

	private City[] cities;
	private Traveler traveler;
	private Graphics2D drawer;

	private static final long serialVersionUID = 1L;

	public CountryPanel(City[] cities, Traveler traveler,
			TspProblemProperties problemProperties) {
		this.cities = cities;
		this.traveler = traveler;
		int realPanelSize = (BORDER_OFFSET * 2)
				+ problemProperties.getMapSize();
		setBounds(10, 11, realPanelSize, realPanelSize);
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		drawer = (Graphics2D) graphics;
		drawCities();
		drawTravelerPath();
	}

	private void drawCities() {
		for (int i = 0; i < cities.length; i++) {
			drawCity(i);
		}
	}

	private void drawCity(int index) {
		City city = cities[index];
		drawCityAsDot(city);
		char[] chars = fixedLenthString(Integer.toString(index), 2)
				.toCharArray();
		int xPosition = city.getXPos() + BORDER_OFFSET;
		int yPosition = city.getYPos() + BORDER_OFFSET;
		drawer.setColor(CITY_COLOR);
		drawer.drawChars(chars, 0, 2, xPosition, yPosition);
	}

	private void drawCityAsDot(City city) {
		int xPosition = city.getXPos() + BORDER_OFFSET;
		int yPosition = city.getYPos() + BORDER_OFFSET;
		drawer.setColor(PATH_COLOR);
		int radius = 5;
		Ellipse2D.Double circle = new Ellipse2D.Double(xPosition - radius,
				yPosition - radius, radius * 2, radius * 2);
		drawer.fill(circle);
	}

	private String fixedLenthString(String string, int length) {
		return String.format("%1$" + length + "s", string);
	}

	private void drawTravelerPath() {
		drawer.setColor(PATH_COLOR);
		traveler.logTravelerInfo();
		for (int i = 0; i < traveler.getPath().length - 1; i++) {
			int city1Index = traveler.getPath()[i];
			int city2Index = traveler.getPath()[i + 1];
			City city1 = cities[city1Index];
			City city2 = cities[city2Index];
			drawer.drawLine(city1.getXPos() + BORDER_OFFSET, city1.getYPos()
					+ BORDER_OFFSET, city2.getXPos() + BORDER_OFFSET,
					city2.getYPos() + BORDER_OFFSET);
		}
	}
}
