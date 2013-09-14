package com.neuron.example.genetic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;

public class Surface extends JPanel {

	private static final int NUMBER_OF_POINTS = 1000;
	private static final Color POINT_COLOR = Color.blue;
	private static final long serialVersionUID = 1L;

	private Graphics2D graphicsDrawer;
	private int surfaceWidth;
	private int surfaceHeight;

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		setGraphicsDrawer(graphics);
		setSurfaceDimensions();
		drawRandomPoints();
	}

	private void setGraphicsDrawer(Graphics graphics) {
		graphicsDrawer = (Graphics2D) graphics;
		graphicsDrawer.setColor(POINT_COLOR);
	}

	private void setSurfaceDimensions() {
		Dimension totalSize = getSize();
		Insets insets = getInsets();
		surfaceWidth = totalSize.width - insets.left - insets.right;
		surfaceHeight = totalSize.height - insets.top - insets.bottom;
	}

	private void drawRandomPoints() {
		Random random = new Random();
		for (int i = 0; i < NUMBER_OF_POINTS; i++) {
			int x = Math.abs(random.nextInt()) % surfaceWidth;
			int y = Math.abs(random.nextInt()) % surfaceHeight;
			drawPoint(x, y);
		}
	}

	private void drawPoint(int x, int y) {
		graphicsDrawer.drawLine(x, y, x, y);
	}

}