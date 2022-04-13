package Controller;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;

import view.Acceuil;
import view.Dessin;
import view.Partie;

public class PartieController extends Thread
{
	private Container contentPane;
	public static boolean is_joue = false;
	public static String coordToGive;
	private Partie p;
	private Dessin dessin;
	
	public PartieController(Container contentPane, Dessin dessin,Partie p)
	{
		this.contentPane = contentPane;
		this.dessin=dessin;
		this.p=p;
	}

	@Override
	public void run() 
	{
		int cpt=0;
		int numeroJoueur = 0;
		int numeroAdversaire = 0;
		Acceuil.requete.Write("pret");
		while(true)
		{
			is_joue = false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String instruction = Acceuil.requete.read();
			if(instruction.equals("attend"))
			{
				if(cpt==0) 
				{
					numeroJoueur=2;
					numeroAdversaire=1;
				}
				Acceuil.requete.Write("yes");
				String coord = Acceuil.requete.read();
				String[]coordTab = coord.split(":");
				String x = coordTab[0];
				String y = coordTab[1];
				int[][]listchecked= this.dessin.checkedCarre;
				listchecked[Integer.parseInt(x)][Integer.parseInt(y)]=numeroAdversaire;
				Partie.contentPane.invalidate();
				Partie.contentPane.removeAll();
				Partie.contentPane.add(new Dessin(600,600,listchecked));
				Partie.contentPane.revalidate();
				Partie.contentPane.repaint();
				Acceuil.requete.Write("ok");
				
			}
			else if(instruction.equals("joue"))
			{
				System.out.println("id"+numeroJoueur);
				if(cpt==0) 
				{
					numeroJoueur=1;
					numeroAdversaire=2;
				}
				int[][]listchecked= this.dessin.checkedCarre;
				String z="";
				while(!is_joue)
				{
					z+=" ";
				}
				String[]coordTab = coordToGive.split(":");
				String x = coordTab[0];
				String y = coordTab[1];
				
				listchecked[Integer.parseInt(x)][Integer.parseInt(y)]=numeroJoueur;
				Partie.contentPane.invalidate();
				Partie.contentPane.removeAll();
				Partie.contentPane.setLayout(new BorderLayout());
				Partie.contentPane.add(new Dessin(600,600,listchecked));
				
				Partie.contentPane.revalidate();
				Partie.contentPane.repaint();
				Acceuil.requete.Write(coordToGive);
				
			}
			else if(instruction.equals("gagne"))
			{
				Partie.contentPane.invalidate();
				Partie.contentPane.removeAll();
				Partie.contentPane.add(new JLabel("BRAVO VOUS AVEZ GAGNE"));
				Partie.contentPane.revalidate();
				Partie.contentPane.repaint();
			}
			else if(instruction.equals("perdu"))
			{
				Partie.contentPane.invalidate();
				Partie.contentPane.removeAll();
				Partie.contentPane.add(new JLabel("DOMMAGE VOUS AVEZ PERDU"));
				Partie.contentPane.revalidate();
				Partie.contentPane.repaint();
			}
			
			cpt++;
		}
		
	}
}
