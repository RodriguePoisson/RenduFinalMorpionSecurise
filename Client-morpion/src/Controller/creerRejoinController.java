package Controller;

import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;
import view.Acceuil;
import view.Partie;
import view.RejoindreSalon;

public class creerRejoinController 
{
	private ArrayList<String> liste_partie;
	private boolean finRequete=false;
	
	public creerRejoinController()
	{
		Acceuil.requete.Write("Rejoindre Partie");
		liste_partie = new ArrayList<String>();
		String state = Acceuil.requete.read();
		if(state.equals("ok"))
		{
			while(!finRequete)
			{
				Acceuil.requete.Write("suivant");
				String reponse = Acceuil.requete.read();
				if(reponse.equals("stop"))
				{
					finRequete=true;
				}
				else
				{
					liste_partie.add(reponse);
				}
			}
			
		}
	}
	public Container getContainer()
	{
		return new RejoindreSalon(this.liste_partie);
	}
	
	public static void attenteRejoindrePartie(String nom_partie)
	{
		Acceuil.requete.Write(nom_partie);
		String state = Acceuil.requete.read();
		if(state.equals("oui"))
		{
			Partie p = new Partie();
			
		}
		
	}
}
