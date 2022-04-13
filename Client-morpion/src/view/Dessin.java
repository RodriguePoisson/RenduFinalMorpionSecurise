package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Arrays;

import javax.swing.JPanel;

public class Dessin extends Component  
{
	private int longueur;
	private int largeur;
	private int rectangleLargeur;
	private int rectangleLongueur;
	public int [][]checkedCarre;
	public static int tour;
	public Dessin(int longueur,int largeur,int [][]checkedCarre)
	{
		this(longueur,largeur);
		this.checkedCarre=checkedCarre;
	}
	public Dessin(int longueur,int largeur)
	{
		int[][] fillCheckedFalse = new int[3][3];
		
		for(int[] i : fillCheckedFalse)
		{
			Arrays.fill(i, 0);
		}
		this.longueur=longueur;
		this.largeur=largeur;
		this.rectangleLargeur=this.largeur/3;
		this.rectangleLongueur=this.longueur/3;
		this.checkedCarre=fillCheckedFalse;
		
	}
	public void paint(Graphics g)
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				g.drawRect(i*this.rectangleLargeur,j*this.rectangleLongueur,this.rectangleLargeur, this.rectangleLongueur);
				if(this.checkedCarre[i][j]==1)
				{
					cocheJoueurCarre(g, i*this.rectangleLargeur, j*this.rectangleLongueur);
				}
				else if(this.checkedCarre[i][j]==2)
				{
					cocheJoueurCroix(g, i*this.rectangleLargeur, j*this.rectangleLongueur);
				}
			}
		}
	}
	private void cocheJoueurCarre(Graphics g,int x,int y)
	{
		g.drawOval(x, y, rectangleLargeur, rectangleLongueur);
	}
	private void cocheJoueurCroix(Graphics g,int x,int y)
	{
		g.drawLine(x, y, x+rectangleLargeur, y+rectangleLongueur);
		g.drawLine(x+rectangleLargeur, y, x, y+rectangleLongueur);
	}
	public static int getX(float pos)
	{
		return (int) (pos/(600/3));
	}
	public static int getY(float pos)
	{
		return (int) (pos/(600/3));
	}
	public static boolean validate(int posx,int posy)
	{
		return posy<600;
	}
}
