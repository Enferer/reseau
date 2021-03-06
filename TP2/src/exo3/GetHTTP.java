package exo3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class GetHTTP {

	public void run() {
		
		Socket sc; 
		InputStream in;
		OutputStream out;
		String requete = "GET / HTTP/1.0\r\n\r\n”";
		
		PrintWriter pw;
		BufferedReader br;
		
		try {
			
			sc = new Socket ("localhost", 80);
			in = sc.getInputStream();
			out = sc.getOutputStream();
			
			pw = new PrintWriter(out,true);
			br = new BufferedReader(new InputStreamReader(in));
			
			pw.println(requete);	
			
			String msg = " ";
			
			boolean entete = true;
			
			while((msg=br.readLine())!= null) {
				if(msg.equals("")) entete = false;
				if(!entete) System.out.println(msg);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		GetHTTP cd = new GetHTTP();
		cd.run();
		
	}
	
	
}
