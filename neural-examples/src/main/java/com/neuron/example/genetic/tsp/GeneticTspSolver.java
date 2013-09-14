package com.neuron.example.genetic.tsp;

import org.encog.ml.genetic.BasicGeneticAlgorithm;
import org.encog.ml.genetic.GeneticAlgorithm;
import org.encog.ml.genetic.crossover.SpliceNoRepeat;
import org.encog.ml.genetic.genes.Gene;
import org.encog.ml.genetic.genes.IntegerGene;
import org.encog.ml.genetic.genome.Chromosome;
import org.encog.ml.genetic.genome.Genome;
import org.encog.ml.genetic.mutate.MutateShuffle;
import org.encog.ml.genetic.population.BasicPopulation;
import org.encog.ml.genetic.population.Population;

public class GeneticTspSolver {
	public static final int CITIES = 50;
	public static final int POPULATION_SIZE = 1000;
	public static final double MUTATION_PERCENT = 0.1;
	public static final double PERCENT_TO_MATE = 0.24;
	public static final double MATING_POPULATION_PERCENT = 0.5;
	public static final int CUT_LENGTH = CITIES / 5;
	public static final int MAP_SIZE = 250;
	public static final int MAX_SAME_SOLUTION = 50;

	private GeneticAlgorithm genetic;
	private City cities[];
	private Country country;

	/**
	 * Place the cities in random locations.
	 */
	private void initCities() {
		cities = new City[CITIES];
		for (int i = 0; i < cities.length; i++) {
			int xPos = (int) (Math.random() * MAP_SIZE);
			int yPos = (int) (Math.random() * MAP_SIZE);

			cities[i] = new City(xPos, yPos);
		}
	}

	private void initPopulation(GeneticAlgorithm ga) {
		country = new Country(cities);
		ga.setCalculateScore(country);
		Population population = new BasicPopulation(POPULATION_SIZE);
		ga.setPopulation(population);

		for (int i = 0; i < POPULATION_SIZE; i++) {
			final TravelerGenome genome = new TravelerGenome(cities);
			genome.createGenome();
			ga.getPopulation().add(genome);
			ga.calculateScore(genome);
		}
		population.claim(ga);
		population.sort();
	}

	/**
	 * Setup and solve the TSP.
	 */

	public void init() {
		initCities();
		genetic = new BasicGeneticAlgorithm();

		initPopulation(genetic);
		genetic.setMutationPercent(MUTATION_PERCENT);
		genetic.setPercentToMate(PERCENT_TO_MATE);
		genetic.setMatingPopulation(MATING_POPULATION_PERCENT);
		genetic.setCrossover(new SpliceNoRepeat(CITIES / 3));
		genetic.setMutate(new MutateShuffle());

	}

	public void solve() {
		int sameSolutionCount = 0;
		int iteration = 1;
		double lastSolution = Double.MAX_VALUE;

		while (sameSolutionCount < MAX_SAME_SOLUTION) {
			genetic.iteration();

			Genome bestGenome = genetic.getPopulation().getBest();
			Traveler traveler = new Traveler((int[]) bestGenome.getOrganism(),
					bestGenome.getScore(), iteration++);
			country.addTraveler(traveler);
			traveler.logTravelerInfo();

			if (Math.abs(lastSolution - traveler.getDistance()) < 1.0) {
				sameSolutionCount++;
			} else {
				sameSolutionCount = 0;
			}

			lastSolution = traveler.getDistance();
		}

		System.out.println("Good solution found:");
		displaySolution();

	}

	/**
	 * Display the cities in the final path.
	 */
	public void displaySolution() {

		boolean first = true;
		Chromosome bestChromosome = genetic.getPopulation().getBest()
				.getChromosomes().get(0);
		for (Gene gene : bestChromosome.getGenes()) {
			if (!first)
				System.out.print(">");
			System.out.print("" + ((IntegerGene) gene).getValue());
			first = false;
		}
		System.out.println("");
	}

	public Country getCountry() {
		return country;
	}

}
