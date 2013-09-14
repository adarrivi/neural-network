package com.neuron.example.genetic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel2 extends JPanel {
	PracticePanel pp;
	Color color;

	public Panel2(PracticePanel pracPan, Color col) {
		color = col;
		pp = pracPan;
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setBackground(color);
	}
}