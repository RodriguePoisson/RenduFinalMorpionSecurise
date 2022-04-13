package view;

import java.awt.Container;

import javax.swing.JLabel;

public class AttentePartie extends Container
{
	public AttentePartie()
	{
		this.add(new JLabel("En attente du lancement de la partie ..."));
	}
}
