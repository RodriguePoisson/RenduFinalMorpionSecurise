package connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import utils.Requete;

public final class Connexion 
{
	private static Socket socket;
	public static Requete requete;
	public static byte[] cleDES;
	public static void initialiseSocket() throws UnknownHostException, IOException
	{
		try 
		{
			socket = new Socket("127.0.0.1",1234);
			requete = new Requete(socket);
			DataOutputStream out = requete.getOut();
			DataInputStream in = requete.getIn();
			
			//GENERATION DES CLEES PUBLIQUE ET PRIVE
			KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = kg.generateKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
			RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);
			PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
			PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
			System.out.println(publicKey.getEncoded().length);
			
			//ENVOIE DE LA CLE PUBLIQUE AU SERVEUR
			out.write(publicKey.getEncoded());
			
			
			byte[] b = new byte[256];
			
			//RECEPTIOND DE LA CLEE DES CHIFFRE AVEC NOTRE CLEE PUBLIQUE
			in.read(b,0,256);
			//DECHIFREMENT DE LA CLE DES
			Cipher encryptCipher = Cipher.getInstance("RSA");
			encryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
			cleDES = encryptCipher.doFinal(b);
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
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
}
