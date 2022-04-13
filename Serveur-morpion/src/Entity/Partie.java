package Entity;

import java.util.ArrayList;
import java.util.Arrays;

import server.Requete;

public class Partie
{
	public boolean is_launch = false;
	public boolean is_joue = false;
	private String id_joueur1;
	private String id_joueur2;
	private String nom_partie;
	private Requete requeteJoueur1;
	private Requete requeteJoueur2;
	public int gagne = 0;
	public int xjoue;
	public int yjoue;
	public static ArrayList<Partie> listPartie = new ArrayList<Partie>();
	public int[][]listchecked;
	public Partie(String nom_partie,String id_joueur1,Requete requeteJoueur1)
	{
		this.id_joueur1 = id_joueur1;
		this.nom_partie = nom_partie;
		this.requeteJoueur1=requeteJoueur1;
		listchecked = new int[3][3];
		
		for(int[] i : listchecked)
		{
			Arrays.fill(i, 0);
		}
	}
	
	public void setId_joueur2(String id_joueur2)
	{
		this.id_joueur2=id_joueur2;
	}
	public void setId_joueur1(String id_joueur1)
	{
		this.id_joueur1=id_joueur1;
	}
	public void setRequeteJoueur2(Requete requete)
	{
		this.requeteJoueur2=requete;
	}
	public String getName()
	{
		return this.nom_partie;
	}
	
	public void check(int signe,int posx,int posy)
	{
		this.listchecked[posx][posy]=signe;
	}
	public int isFinish()
	{
		int cpt=0;
		for(int[]i:this.listchecked)
		{
			if (i[0]==1)
			{
				cpt++;
			}
		}
		if(cpt==3) {return 1;}
		cpt=0;
		for(int[]i:this.listchecked)
		{
			if (i[0]==2)
			{
				cpt++;
			}
		}
		if(cpt==3) {return 2;}
		
		for(int i =0;i<this.listchecked.length;i++)
		{
			cpt=0;
			for(int j =0;j<this.listchecked[0].length;j++)
			{
				if(this.listchecked[i][j]==1)
				{
					cpt++;
				}
			}
			if(cpt==3) {return 1;}
		}
		for(int i =0;i<this.listchecked.length;i++)
		{
			cpt=0;
			for(int j =0;j<this.listchecked[0].length;j++)
			{
				if(this.listchecked[i][j]==2)
				{
					cpt++;
				}
			}
			if(cpt==3) {return 2;}
		}
		
		if(this.listchecked[0][0]==1 &&this.listchecked[1][1]==1 &&this.listchecked[2][2]==1)
		{
			return 1;
		}
		if(this.listchecked[0][0]==2 &&this.listchecked[1][1]==2 &&this.listchecked[2][2]==2)
		{
			return 2;
		}
		if(this.listchecked[2][0]==1 &&this.listchecked[1][1]==1 &&this.listchecked[0][2]==1)
		{
			return 1;
		}
		if(this.listchecked[2][0]==2 &&this.listchecked[1][1]==2 &&this.listchecked[0][2]==2)
		{
			return 2;
		}
		return 0;
	}
	
	public Requete getRequeteJoueur1() {return this.requeteJoueur1;}
	public Requete getRequeteJoueur2() {return this.requeteJoueur2;}
	public int getId1() {return Integer.parseInt(this.id_joueur1);}
	public int getId2() {return Integer.parseInt(this.id_joueur2);}
	public static Partie recherchePartie(String name)
	{
		for(Partie p:listPartie)
		{
			if(p.getName().equals(name))
			{
				return p;
			}
		}
		return null;
	}
}
