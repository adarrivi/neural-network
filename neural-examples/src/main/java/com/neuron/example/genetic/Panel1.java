package com.neuron.example.genetic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel1 extends JPanel implements ActionListener {
	PracticePanel pp;
	JButton button1;
	JButton button2;
	Color color;
	Random random;

	public Panel1(PracticePanel pracPan, Color col) {
		random = new Random();
		color = col;
		pp = pracPan;
		button1 = new JButton("Change Colors");
		button2 = new JButton("New Game");
		button1.addActionListener(this);
		button2.addActionListener(this);
		add(button1);
		add(button2);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Color colors[] = { Color.RED, Color.BLACK, Color.BLUE, Color.GREEN };
		if (e.getSource() == button1) {
			pp.Reset1(colors[random.nextInt(4)], colors[random.nextInt(4)]);
		} else if (e.getSource() == button2) {
			pp.Reset2(colors[random.nextInt(4)], colors[random.nextInt(4)]);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setBackground(color);
	}
}