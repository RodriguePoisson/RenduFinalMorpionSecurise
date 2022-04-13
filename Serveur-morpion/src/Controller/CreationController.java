package Controller;

import Entity.Partie;
import server.Requete;

public class CreationController 
{
	private Requete requete;
	private int id_joueur;
	public CreationController(Requete requete,int id_joueur)
	{
		System.out.println("Creation Demandé");
		this.requete = requete;
		this.id_joueur=id_joueur;
		requete.Write("ok");
		String demande = requete.read();
		if(demande.equals("demandeIdJoueur"))
		{
			requete.Write(String.valueOf(id_joueur));
			String nomPartie = requete.read();
			Partie partie = new Partie(nomPartie,String.valueOf(this.id_joueur),requete);
			Partie.listPartie.add(partie);
			PartieController pc = new PartieController(this.requete,partie,this.id_joueur);
		}
	}
}
