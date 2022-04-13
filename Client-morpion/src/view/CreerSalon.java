package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.creerPartieController;

public class CreerSalon extends Container
{
	private JLabel name_label;
	private JTextField name_field; 
	private JButton creer; 
	public CreerSalon() 
	{
		GridBagLayout layoutPrincipal = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(layoutPrincipal);
		
		name_label = new JLabel("Nom salon");
		gbc.gridx = 0;
		gbc.gridy = 0;
		layoutPrincipal.setConstraints(name_label, gbc);
		this.add(name_label);
		
		name_field = new JTextField(20);
		name_field.setVisible(true);
		gbc.gridx = 1;
		gbc.gridy = 0;
		layoutPrincipal.setConstraints(name_field, gbc);
		this.add(name_field);
		
		creer=new JButton("Créer");
		creer.addActionListener(e->
		{
			creerPartieController.creerPartie(name_field.getText());
			creerPartieController.attenteLancementPartie();
		});
		this.add(creer);
		creer.setVisible(true);
	}
}
