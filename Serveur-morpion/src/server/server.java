package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


 
public class server implements Runnable
{
	private int id_joueur=1;
	protected static ServerSocket sockserv;
	
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		sockserv = new ServerSocket(1234);
		server serv = new server();
		serv.run();
		
	}
	@Override
	public void run() 
	{
		while(true)
		{	
			Socket sockcli;
			try 
			{
				sockcli = sockserv.accept();
				
				Requete requete = new Requete(sockcli);
				DataOutputStream out = requete.getOut();
				DataInputStream in = requete.getIn();
				
				byte[] b = new byte[294];
				
				//RECEPTION DE LA CLEE PUBLIQUE DU CLIENT
				in.read(b,0,294);
				
				
				//chiffrement de la clée DES 
				byte[] result_to_send = requete.chiffreDESkey(b);
				
				out.write(result_to_send);
				
				Route route = new Route(requete,id_joueur);
				route.start();
				id_joueur++;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
