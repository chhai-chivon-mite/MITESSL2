import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.security.KeyStore;
import java.util.Scanner;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/*
 * @author: Chhai Chivon on Jan 21, 2020
 * Senior Application Developer
 */

public class Server {

	public static void main(String[] args) {
		try {
			
			// Create Context
			SSLContext sslContext = SSLContext.getInstance("SSL");
			
			// Create KeyManagerFactory
			KeyManagerFactory  keyManagerFactory  = KeyManagerFactory.getInstance("SunX509");
		
			// Create KeyStore
			KeyStore keyStore = KeyStore.getInstance("JKS");
			
			// Filling the KeyStore
			FileInputStream fileInputStream = new FileInputStream("/Users/chhaichivon/Desktop/SSL/SSL2/MITESSL2.jks");
			char[] password =  "123456".toCharArray();
			keyStore.load(fileInputStream,password);
			
			// Initializing the KeyManagerFactory 
			keyManagerFactory.init(keyStore, password);
			
			// Initializing the Context
			sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
			
			// Creating Server Socket
			SSLServerSocketFactory sslServerSocketFactory  = sslContext.getServerSocketFactory();
			SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(1234);
			
			while(true) {
				
				// Listen from Client
				System.out.println("Waiting client...");
				SSLSocket connection = (SSLSocket) sslServerSocket.accept();
				
				System.out.println("Already Accept");
				
				// Read from Client 
				Scanner scanner  = new Scanner(connection.getInputStream());
				while(scanner.hasNext()) {
					String request = scanner.nextLine();
					System.out.println("request => " +  request);
				}
				
				// Send to Client
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
				outputStreamWriter.write("Hello Client\n");
				outputStreamWriter.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
