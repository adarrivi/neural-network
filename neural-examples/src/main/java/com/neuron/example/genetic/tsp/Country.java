package com.neuron.example.genetic.tsp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.encog.ml.genetic.genome.CalculateGenomeScore;
import org.encog.ml.genetic.genome.Genome;

public class Country implements CalculateGenomeScore {

	private static final Color CITY_COLOR = Color.red;
	private static final Color PATH_COLOR = Color.blue;
	private City[] cities;
	private Graphics2D drawer;
	private List<Traveler> bestTravelers = new ArrayList<Traveler>();
	private int currentTravelerIndex;

	public Country(City[] cities) {
		this.cities = cities;
	}

	public void addTraveler(Traveler traveler) {
		bestTravelers.add(traveler);
	}

	@Override
	public double calculateScore(Genome genome) {
		double totalDistance = 0.0;
		int[] path = (int[]) genome.getOrganism();
		for (int i = 0; i < cities.length - 1; i++) {
			City city1 = cities[path[i]];
			City city2 = cities[path[i + 1]];
			totalDistance += city1.getDistanceTo(city2);
		}
		return totalDistance;
	}

	@Override
	public boolean shouldMinimize() {
		return true;
	}

	public void drawComponent(Graphics graphics) {
		this.drawer = (Graphics2D) graphics;
		drawCities();
		drawTravelerPath();
	}

	private void drawCities() {
		drawer.setColor(CITY_COLOR);
		for (int i = 0; i < cities.length; i++) {
			drawCity(i);
		}
	}

	private void drawCity(int index) {
		City city = cities[index];
		char[] chars = fixedLenthString(Integer.toString(index), 2)
				.toCharArray();
		drawer.drawChars(chars, 0, 2, city.getXPos(), city.getYPos());
	}

	private String fixedLenthString(String string, int length) {
		return String.format("%1$" + length + "s", string);
	}

	private void drawTravelerPath() {
		drawer.setColor(PATH_COLOR);
		Traveler traveler = bestTravelers.get(currentTravelerIndex);
		traveler.logTravelerInfo();
		for (int i = 0; i < traveler.getPath().length - 1; i++) {
			int city1Index = traveler.getPath()[i];
			int city2Index = traveler.getPath()[i + 1];
			City city1 = cities[city1Index];
			City city2 = cities[city2Index];
			drawer.drawLine(city1.getXPos(), city1.getYPos(), city2.getXPos(),
					city2.getYPos());
		}
	}

	public void incrementCurrentTravelerIndex() {
		if (currentTravelerIndex < bestTravelers.size() - 1) {
			currentTravelerIndex++;
		} else {
			System.out.println("best reached");
		}
	}
}
