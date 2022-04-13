package server;

import Controller.CreationController;
import Controller.RejoindreController;

public class Route extends Thread
{
	private Requete requete;
	private int id_joueur;
	public Route(Requete requete,int id_joueur)
	{
		this.requete = requete;
		this.id_joueur = id_joueur;
	}
	@Override
	public void run() 
	{
		while (true)
		{
			String path_demande = requete.read();
			
			if(path_demande.equals("creation"))
			{
				CreationController cc = new CreationController(requete,id_joueur);
			}
			else if(path_demande.equals("Rejoindre Partie"))
			{
				RejoindreController rc = new RejoindreController(requete,id_joueur);
			}
		}
	}
}
