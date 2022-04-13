package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import Controller.PartieController;

public class Partie extends JFrame implements MouseListener
{
	private Dessin dessin;
	public static Container contentPane;
	public Partie()
	{
		this.setSize(600,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.dessin = new Dessin(600,600);
		this.getContentPane().setLayout(new BorderLayout());
		this.dessin.tour=0;
		this.getContentPane().add(dessin, BorderLayout.CENTER);
		this.getContentPane().addMouseListener(this);
		contentPane = this.getContentPane();
		PartieController pc = new PartieController(this.getContentPane(),dessin,this);
		
		pc.start();
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		PartieController.is_joue = false;
		int x = e.getX();
		int y = e.getY();
		
		x = Dessin.getX(x);
		y = Dessin.getY(y);
		if(this.dessin.checkedCarre[x][y]==0)
		{
			PartieController.coordToGive=String.valueOf(x)+":"+String.valueOf(y);
			PartieController.is_joue = true;
		}
		
	}
	
	public void popup(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
