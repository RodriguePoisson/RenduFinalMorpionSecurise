package Controller;

import Entity.Partie;
import server.Requete;

public class RejoindreController
{
	private Requete requete;
	private int id_joueur;
	
	public RejoindreController(Requete requete,int id_joueur)
	{
		this.requete=requete;
		this.id_joueur = id_joueur;
		System.out.println("Rejoindre Demandé");
		requete.Write("ok");
		
		for(Partie partie:Partie.listPartie)
		{
			String demande = requete.read();
			
			if(demande.equals("suivant"))
			{
				requete.Write(partie.getName());
			}
		}
		String demande = requete.read();
		if(demande.equals("suivant"))
		{
			requete.Write("stop");
			String nom_partie = requete.read();
			Partie partieRejoin = Partie.recherchePartie(nom_partie);
			partieRejoin.setId_joueur2(String.valueOf(id_joueur));
			partieRejoin.setRequeteJoueur2(requete);
			partieRejoin.is_launch=true;
			PartieController pc = new PartieController(this.requete,partieRejoin,this.id_joueur);
		}
	}
}
