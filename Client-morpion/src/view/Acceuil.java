package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;

import Controller.creerPartieController;
import Controller.creerRejoinController;
import connexion.Connexion;
import utils.Requete;

public class Acceuil extends Container
{
	private JButton creerSalon;
	private JButton rejoindreSalon;
	public static Requete requete;
	public Acceuil()
	{
		try 
		{
			Connexion.initialiseSocket();
			requete= Connexion.requete;
			this.creerSalon = new JButton("Creer un salon");
			this.rejoindreSalon = new JButton("Rejoindre un salon");
			this.setLayout(new FlowLayout());
			this.add(creerSalon);
			this.add(rejoindreSalon);
			
			
			this.creerSalon.addActionListener(e->
			{
				this.removeAll();
				
				creerPartieController cpc = new creerPartieController();
				this.add(cpc.getContainer());
				this.revalidate();
				this.repaint();
			});
			
			this.rejoindreSalon.addActionListener(e->
			{
				this.removeAll();
				
				creerRejoinController crc = new creerRejoinController();
				this.add(crc.getContainer());
				this.revalidate();
				this.repaint();
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
