import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/*
 * @author: Chhai Chivon on Jan 21, 2020
 * Senior Application Developer
 */

public class Client {
	
	private static OutputStreamWriter outputStreamWriter;
	private static Scanner scanner;

	public static void main(String[] agrs) {
		
		//SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			
			// Create a trust manager that does not validate certificate chains
						TrustManager[] trustAllCerts = new TrustManager[] {
								new X509TrustManager() {
									
									@Override
									public X509Certificate[] getAcceptedIssuers() {
										// TODO Auto-generated method stub
										return null;
									}
									
									@Override
									public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
										// TODO Auto-generated method stub
										
									}
								}
						};
						SSLContext sslContext = SSLContext.getInstance("SSL");
						sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
					    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
						
					    // Connect to Server
					    Socket socket = new Socket("localhost",1234);
					 					
						outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
						outputStreamWriter.write("Hello Server\n");
						outputStreamWriter.flush();
						
						System.out.println("Client request to server...");
						scanner  = new Scanner(socket.getInputStream());
						while (scanner.hasNext()) {
							String request = scanner.nextLine();
							System.out.println(" Request => " + request);
						}
						socket.close();
			
		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
