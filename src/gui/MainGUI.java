package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.components.Components;

public class MainGUI {

	public void build() {
		Components components = new Components();
		
		
		JFrame main = new JFrame();
		main.setSize(900, 700);
		main.setLayout(null);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		main.add(components.LevelPanel());
		main.setVisible(true);
	}
	
}
