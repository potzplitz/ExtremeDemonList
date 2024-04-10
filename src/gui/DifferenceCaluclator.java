package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import database.Sqlite;

public class DifferenceCaluclator {
	
	int number1;
	int number2;
	
	public void calculate() {
		
		Sqlite sql = new Sqlite("levels");
		sql.queryData("levels");
		
		JFrame main = new JFrame("Rechner");
		main.setSize(500, 360);
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main.setResizable(false);
		main.setLayout(null);
		
		JTextField search = new JTextField("nach Level suchen");
		search.setBounds(0, 0, 500, 50);
		
		JLabel calc = new JLabel("Unterschied: ");
		calc.setBounds(20, 197, 440, 30);
		calc.setFont(calc.getFont().deriveFont(20f));
		
		JPanel showresults = new JPanel();
		showresults.setLayout(null);
		showresults.setBounds(1, 49, 483, 200);
		showresults.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		showresults.setVisible(false);

		JPanel lvl1 = new JPanel();
		lvl1.setBounds(20, 100, 170, 50);
		lvl1.setBackground(Color.LIGHT_GRAY);
		lvl1.setLayout(null);
		lvl1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		JLabel name1 = new JLabel();
		name1.setBounds(7, 10, 100, 30);
		name1.setVisible(false);
		lvl1.add(name1);
		
		JLabel rank1 = new JLabel();
		rank1.setBounds(130, 10, 50, 30);
		rank1.setVisible(false);
		lvl1.add(rank1);
		
		JPanel lvl2 = new JPanel();
		lvl2.setBounds(290, 100, 170, 50);
		lvl2.setBackground(Color.LIGHT_GRAY);
		lvl2.setLayout(null);
		lvl2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		JLabel name2 = new JLabel();
		name2.setBounds(7, 10, 100, 30);
		name2.setVisible(false);
		lvl2.add(name2);
		
		JLabel rank2 = new JLabel();
		rank2.setBounds(130, 10, 50, 30);
		rank2.setVisible(false);
		lvl2.add(rank2);
		
		JPanel results = new JPanel();
		results.setLayout(new GridLayout(sql.getLevelname().size(), 1));
		
		JScrollPane scroll = new JScrollPane(results);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		
		
		for(int i = 0; i < sql.getLevelname().size(); i++) {
			
			 int index = i;
			
			JPanel contents = new JPanel();
			contents.setPreferredSize(new Dimension(500, 40));
			contents.setLayout(null);
			contents.setBackground(Color.LIGHT_GRAY);
			contents.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			
			JLabel name = new JLabel(sql.getLevelname().get(i));
			name.setBounds(5, 5, 150, 30);
			
			JLabel rank = new JLabel("#" + (i + 1));
			rank.setBounds(200, 5, 60, 30);
			
			JButton b1 = new JButton("1");
			b1.setBounds(400, 10, 18, 18);	
			b1.setMargin(new Insets(0,0,0,0));
			b1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					name1.setText(sql.getLevelname().get(index));
					rank1.setText("#" + (index + 1));
					
					contents.setBackground(Color.decode("#cbffbf"));
					
					number1 = index;
					
				}
				
			});
			
			JButton b2 = new JButton("2");
			b2.setBounds(440, 10, 18, 18);	
			b2.setMargin(new Insets(0,0,0,0));
			b2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					name2.setText(sql.getLevelname().get(index));
					rank2.setText("#" + (index + 1));
					
					contents.setBackground(Color.decode("#cbffbf"));
					
					number2 = index;
					
				}
				
			});
			
			search.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					
					showresults.setVisible(true);

					if(!sql.getLevelname().get(index).toLowerCase().contains(search.getText())) {
						results.remove(contents);
						results.repaint();
						results.revalidate();
					} else if(sql.getLevelname().get(index).toLowerCase().contains(search.getText())) {
						results.add(contents);
						results.repaint();
						results.revalidate();
					}
					results.revalidate();
					results.repaint();
					scroll.revalidate();
					scroll.repaint();
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			contents.add(b1);
			contents.add(b2);
			contents.add(name);
			contents.add(rank);
			results.add(contents);
		}
		
		scroll.setBounds(0, 0, 484, 200);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		
		showresults.add(scroll);
		
		
		
		search.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				search.setText("");
				name1.setVisible(false);
				rank1.setVisible(false);
				rank2.setVisible(false);
				name2.setVisible(false);
				
			}
		});
		
		main.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				showresults.setVisible(false);
				name1.setVisible(true);
				rank1.setVisible(true);
				rank2.setVisible(true);
				name2.setVisible(true);
				
				int calculate = number1 - number2;
				
				if(calculate <= 0) {
					calculate *= (-1);
				}
				
				calc.setText("Unterschied: " + calculate);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		main.add(showresults);
		main.add(calc);
		main.add(lvl1);
		main.add(lvl2);
		main.add(search);
		main.setVisible(true);
		
		
	}

}
