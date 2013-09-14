package com.neuron.example.genetic;

import java.awt.Graphics;

import javax.swing.JFrame;

public class PracticeFrame extends JFrame {
	PracticePanel pc;

	public PracticeFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pc = new PracticePanel(this);
		add(pc);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paint(g);
	}

	public static void main(String args[]) {
		new PracticeFrame();
	}
}