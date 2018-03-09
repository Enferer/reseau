package exo4;

/**
 * M4102 : exemple de serveur TCP avec processus légers
 * @author <a href="mailto:jean.carle@univ-lille1.fr">Jean Carle</a>, IUT-A, Universite de Lille 1
 *
 * Utilisation d'une sous-classe dans cet exemple
 * d'autres solutions existent
 **/
import java.io.*;
import java.net.*;

public class SrvBegMT {
	
	public static final int PORT_SERVICE = 9876;
	private ServerSocket s_Srv;

	SrvBegMT() throws IOException {
		s_Srv = new ServerSocket(PORT_SERVICE);
	}

	// Réception clients et transfert vers un thread dédié
	private void attenteClient() throws IOException {
		Socket s_Clt;
		while(true) {
				s_Clt = s_Srv.accept();
				new ReponseTruc(s_Clt).start();
		}
	}

	// Test d'usage de la classe ... et rien d'autre
	public static void main(String [] args) throws IOException {
		SrvBegMT srvTruc = new SrvBegMT();
			srvTruc.attenteClient();
	}

	/**
	 * Gestion du protocole du service <<Truc>>
	 **/
	class ReponseTruc extends Thread {
		private Socket s_Client;
		private OutputStream out;
		private InputStream in;

		ReponseTruc(Socket sClient) {
			this.s_Client = sClient;
			
			try {
				out = sClient.getOutputStream();
				in = sClient.getInputStream();
			} catch (IOException e) {e.printStackTrace();}
			
		}

		void dialogue () {
			
			BufferedReader br;
			boolean connect = true;
			while(connect) {
				try{
					br = new BufferedReader(new InputStreamReader(in));
					String reponse = getReponse(br.readLine());
					out.write(reponse.getBytes());
				}catch (Exception e) {
					System.out.println("Déconnection");
					connect = false;
				}
			}
			
		}
		
		private String getReponse(String msg) {
			String reponse ="";
			int nbr;
			try {
				nbr = Integer.valueOf(msg.split(":")[0]);
				if(nbr == 0) return "0\n";
				msg = msg.split(":")[1];
				String[]tabMsg = msg.split(" ");
				tabMsg[tabMsg.length-1] = tabMsg[tabMsg.length-1].split("\0|\n")[0];
				
				for (int i = 0; i < tabMsg.length; i++) {
					
					for (int j = 0; j < nbr; j++) {
						reponse += tabMsg[i] + " ";
					}
				}	
				
				
			}catch (NumberFormatException e) {
				reponse = "1Erreur : multiplicateur manquant\n";
				return reponse;
			}
			return reponse+"\n";
		}
		

		public void run() {
			dialogue();

			try {
				s_Client.close();
			} catch(IOException e) {}
		}
	}
}


