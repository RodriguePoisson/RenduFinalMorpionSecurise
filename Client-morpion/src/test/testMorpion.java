package test;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import view.Acceuil;

public class testMorpion {

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Morpion");
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Acceuil ac = new Acceuil();
		frame.add(ac);
		
		frame.setVisible(true);
	}
}
