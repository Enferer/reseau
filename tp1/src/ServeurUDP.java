

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurUDP {
	private DatagramSocket dgSocket;

	ServeurUDP(int pSrv) throws IOException {
		dgSocket = new DatagramSocket(pSrv);
	}

	void go() throws IOException {
		DatagramPacket dgPacket;
		
		int nbr;
		
		while (true) {
			nbr=0;
			String reponse = "";
			dgPacket = new DatagramPacket(new byte[5000], 5000);
			dgSocket.receive(dgPacket);
			byte[] tab = dgPacket.getData();
			
			System.out.println("Address : " + dgPacket.getSocketAddress().toString().substring(1));
			System.out.println("Numero reçu : "+new String(tab).charAt(0));
			System.out.println("Message reçu : "+new String(tab).substring(2));
			
			dgPacket.setSocketAddress(dgPacket.getSocketAddress());
			String msg = new String(dgPacket.getData());
						
			try {
				nbr = Integer.valueOf(msg.split(":")[0]);
				msg = msg.split(":")[1];
				String[]tabMsg = msg.split(" ");
				tabMsg[tabMsg.length-1] = tabMsg[tabMsg.length-1].split("\0|\n")[0];
				
				for (int i = 0; i < tabMsg.length; i++) {
					
					for (int j = 0; j < nbr; j++) {
						reponse = reponse + tabMsg[i] + " ";
					}
				}	
				
				
			}catch (NumberFormatException e) {
				reponse = "1Erreur : multiplicateur manquant";
			}

			byte[] bufMsg = reponse.getBytes();
			
			String longueur = bufMsg.length+"";
			dgPacket.setData(longueur.getBytes());
			dgSocket.send(dgPacket);
			
			dgPacket.setData(bufMsg, 0, bufMsg.length);
			System.out.println("Message envoyer : "+new String(bufMsg));
			dgSocket.send(dgPacket);
		}
	}
	

	public static void main(String[] args) throws IOException {
		final int DEFAULT_PORT = 9873;
		new ServeurUDP( args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]) ).go();
	}
}