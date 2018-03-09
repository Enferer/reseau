package exo4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientBeg {

	private static boolean hasToStop = false;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Socket sock = null;
		BufferedReader br=null;
		PrintWriter pw=null;

		try {
			sock = new Socket(InetAddress.getByName("localhost"), 9876);
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}


		do {
			System.out.print("Entrez une phrase sous la forme \"4:bonjour\":");
			String line = sc.nextLine();
			if(checkStop(line)) break;
			pw.write(line+"\n");
			pw.flush();
			System.out.println("reponse :"+br.readLine());

		}while(!hasToStop);
		System.out.println("Aurevoir michel !");
		try {
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}

	private static boolean checkStop(String s) {
		return s.equalsIgnoreCase("FIN")||s.equalsIgnoreCase(".");
	}


}
