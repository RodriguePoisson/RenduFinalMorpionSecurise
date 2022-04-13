package Controller;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utils.Requete;
import view.Acceuil;
import view.CreerSalon;
import view.Partie;

public class creerPartieController
{
	public String id_joueur;
	
	
	public creerPartieController()
	{
		
		
		Acceuil.requete.Write("creation");
		String state = Acceuil.requete.read();
		if(state.equals("ok"))
		{
			Acceuil.requete.Write("demandeIdJoueur");
			id_joueur = Acceuil.requete.read();
		}
	}
	
	public Container getContainer()
	{
		return new CreerSalon();
	}
	
	public static void creerPartie(String nom_partie)
	{
		try {
			Acceuil.requete.Write(nom_partie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void attenteLancementPartie()
	{
		String lancement = Acceuil.requete.read();
		if(lancement.equals("oui"))
		{
			Partie p = new Partie();			
		}
	}

}
