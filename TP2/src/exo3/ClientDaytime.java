package exo3;

import java.io.InputStream;
import java.net.Socket;

public class ClientDaytime {
	
	
	public void run() {
		Socket sc; 
		InputStream in;
		
		try {
			
			sc = new Socket ("localhost", 13);
			in = sc.getInputStream();
			byte[] buf = new byte[2048];
			
			
			while(in.read(buf)!= -1) {
				System.out.println(new String(buf));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		ClientDaytime cd = new ClientDaytime();
		cd.run();
		
	}



}
