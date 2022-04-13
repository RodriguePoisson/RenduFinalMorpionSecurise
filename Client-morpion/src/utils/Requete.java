package utils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import connexion.Connexion;

public class Requete
{
	private Socket socketcli;
	private DataInputStream in;
	private DataOutputStream out;
	public boolean waitResponse = false;
	public Requete(Socket socketcli)
	{
		this.socketcli = socketcli;
		
		try
		{
			this.out = new DataOutputStream(socketcli.getOutputStream());
			this.in= new DataInputStream(socketcli.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Socket getSocket() {return this.socketcli;}
	public void Write(String query)
	{
		try 
		{
			waitResponse = true;
			this.out.write(query.getBytes());
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String read()
	{
		byte[]b2 = new byte[8];
		try 
		{
			//DECHIFREMENT DES DU MESSAGE DU SERVEUR
			this.in.read(b2,0,8);
			System.out.println(new String(b2, StandardCharsets.UTF_8));
			Cipher encryptCipher = Cipher.getInstance("DES");
			SecretKey secretDES = new SecretKeySpec(Connexion.cleDES,0,Connexion.cleDES.length,"DES");
			byte b[] = new byte[80];
			encryptCipher.init(Cipher.DECRYPT_MODE, secretDES);
			b = encryptCipher.doFinal(b2);
			String regExp="\u0000*$";
			String reponse = new String(b, StandardCharsets.UTF_8);
			String[] reponse_a_trier= reponse.split(regExp);
			reponse = reponse_a_trier[0];
			return reponse;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public DataOutputStream getOut() {return this.out;}
	public DataInputStream getIn() {return this.in;}
}
