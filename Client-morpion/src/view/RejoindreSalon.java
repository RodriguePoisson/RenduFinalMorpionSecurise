package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;

import Controller.creerRejoinController;

public class RejoindreSalon extends Container
{
	private ArrayList<String> liste_partie;
	
	public RejoindreSalon(ArrayList<String>liste_partie)
	{
		int cpt=0;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.liste_partie = liste_partie;
		
		for(String nom_partie : liste_partie)
		{
			gbc.gridy=cpt;
			JButton partie = new JButton(nom_partie);
			partie.addActionListener(e->
			{
				creerRejoinController.attenteRejoindrePartie(partie.getText());
			});
			layout.setConstraints(partie, gbc);
			this.add(partie);
			cpt++;
		}
	}
}
