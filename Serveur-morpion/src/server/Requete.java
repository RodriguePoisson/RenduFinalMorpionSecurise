package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Requete
{
	private Socket socketcli;
	private DataInputStream in;
	private DataOutputStream out;
	private SecretKey desKey;
	public boolean waitResponse = false;
	public Requete(Socket socketcli)
	{
		this.socketcli = socketcli;
		
		try
		{
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			this.desKey = keyGen.generateKey();
			System.out.println(this.desKey.getEncoded().length);
			this.out = new DataOutputStream(socketcli.getOutputStream());
			this.in= new DataInputStream(socketcli.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Write(String query)
	{
		try 
		{
			waitResponse = true;
			//CHIFFREMENT DES DE NOTRE ENVOIE
			Cipher encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, this.desKey);
			byte[] to_send = encryptCipher.doFinal(query.getBytes());
			System.out.println(to_send.length+"azeazeaze");
			this.out.write(to_send);
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
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
		}
	}
	public String read()
	{
		byte[]b = new byte[80];
		try 
		{
			this.in.read(b,0,80);
			String regExp="\u0000*$";
			String reponse = new String(b, StandardCharsets.UTF_8);
			String[] reponse_a_trier= reponse.split(regExp);
			reponse = reponse_a_trier[0];
			return reponse;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Socket getSocket() {return this.socketcli;}
	public DataOutputStream getOut() {return this.out;}
	public DataInputStream getIn() {return this.in;}
	public byte[] chiffreDESkey(byte[]clePublique)
	{
		try 
		{
			//CHIFFREMENT DE LA CLEE DES AVEC LA CLE PUBLIQUE RSA DU CLIENT
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(clePublique));
			Cipher encryptCipher = Cipher.getInstance("RSA");
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return encryptCipher.doFinal(desKey.getEncoded());
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
