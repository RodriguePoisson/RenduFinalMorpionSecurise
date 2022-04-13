package Controller;

import Entity.Partie;
import server.Requete;

public class ThreadDeroulement extends Thread
{
	private Partie partie;
	private Requete requete_joueur1;
	private int i;
	private Requete requete_joueur2;
	public ThreadDeroulement(Partie partie,int i)
	{
		this.partie=partie;
		this.i = i;
		this.requete_joueur1 = this.partie.getRequeteJoueur1();
		this.requete_joueur2 = this.partie.getRequeteJoueur2();
	}
	
	@Override
	public void run()
	{
		if(this.i==1)
		{
			this.requete_joueur1.Write("oui");
			String state = this.requete_joueur1.read();
			while(true)
			{
				this.requete_joueur1.Write("joue");
				state = this.requete_joueur1.read();
			}
		}
		else
		{
			this.requete_joueur2.Write("oui");
			String state2 = this.requete_joueur2.read();
			while(true)
			{
				this.requete_joueur2.Write("attend");
				state2 = this.requete_joueur2.read();
			}
		}
	}
}
