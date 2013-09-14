package com.neuron.example.genetic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class PracticePanel extends JPanel {
	PracticeFrame pf;
	Panel1 pan1;
	Panel2 pan2;

	public PracticePanel(PracticeFrame pracFrame) {
		pf = pracFrame;
		LayoutManager gl = new GridLayout(2, 1);
		setLayout(gl);
		pan1 = new Panel1(this, Color.RED);
		pan2 = new Panel2(this, Color.BLUE);
		this.add(pan1);
		this.add(pan2);
		this.setVisible(true);
	}

	public void Reset1(Color color1, Color color2) {
		pan1.color = color1;
		pan2.color = color2;
		repaint();
	}

	public void Reset2(Color color1, Color color2) {
		LayoutManager gl = new GridLayout(2, 1);
		setLayout(gl);
		this.remove(pan1);
		this.remove(pan2);
		pan1 = new Panel1(this, color1);
		pan2 = new Panel2(this, color2);
		this.add(pan1);
		this.add(pan2);
		this.setVisible(true);
		repaint();
		validate();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		pan1.repaint();
		pan2.repaint();
	}
}
