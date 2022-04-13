package Controller;

import Entity.Partie;
import server.Requete;

public class PartieController 
{
	private Partie partie;
	private int id_joueur;
	private int real_id;
	
	public PartieController(Requete requete,Partie partie,int id_joueur)
	{
		this.partie=partie;
		this.real_id = id_joueur;
		this.id_joueur=id_joueur;
		int signe;
		if(this.id_joueur==this.partie.getId1())
		{
			signe = 1;
		}
		else
		{
			signe = 2;
		}
			
		while(!this.partie.is_launch)
		{
			System.out.println("ok");
		}
		
		requete.Write("oui");
		String state = requete.read();

		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.partie.gagne==0)
			{
				
				this.partie.is_joue = false;
				if(this.id_joueur==this.partie.getId1())
				{
					requete.Write("joue");
					String reponse = requete.read();
					String[] tab_reponse = reponse.split(":");
					this.partie.xjoue = Integer.parseInt(tab_reponse[0]);
					this.partie.yjoue = Integer.parseInt(tab_reponse[1]);
					this.partie.listchecked[this.partie.xjoue][this.partie.yjoue]=signe;
					if(this.partie.isFinish()==1)
					{
						this.partie.gagne=1;
					}
					else if(this.partie.isFinish()==2)
					{
						this.partie.gagne=2;
					}
					this.partie.is_joue=true;
				}
				if(this.id_joueur==this.partie.getId2())
				{
					requete.Write("attend");
					String reponse = requete.read();
					String z ="";
					while(!this.partie.is_joue)
					{
						z+="z";
					}
	
					requete.Write(String.valueOf(this.partie.xjoue)+":"+String.valueOf(this.partie.yjoue));
					reponse=requete.read();
				}
				if(this.id_joueur == this.partie.getId1())
				{
					this.id_joueur = this.partie.getId2();
				}
				else
				{
					this.id_joueur = this.partie.getId1();
				}
			}
			else if(this.partie.gagne==1)
			{
				if(this.real_id == this.partie.getId1())
				{
					requete.Write("gagne");
				}
				else
				{
					requete.Write("perdu");
				}
			}
			else if(this.partie.gagne==2)
			{
				if(this.real_id == this.partie.getId2())
				{
					requete.Write("gagne");
				}
				else
				{
					requete.Write("perdu");
				}
			}
		}
		
		
	}
		
	
	
}