import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientFTP {

	private Socket socket;
	private BufferedWriter writer;
	private BufferedInputStream reader;
	private String host;
	private int port;
	private String user;

	public ClientFTP(String host, int port, String user) {
		this.host = host;
		this.port = port;
		this.user = user;
	}

	public ClientFTP(String host, int port) {
		this(host, port, "anonymous");
	}

	public void connect() throws IOException {
		try {
			socket = new Socket(host, port);
			reader = new BufferedInputStream(socket.getInputStream());
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		String reponse = read();
		System.out.println(reponse);
		send("USER " + user);
		reponse = read();
		System.out.println(reponse);
		String passwd = "";
		send("PASS " + passwd);
		reponse = read();
		System.out.println(reponse);
		send("LIST");
		reponse = read();
		System.out.println(reponse);
	}	
	// envoi d'une commande au serveur
	public void send(String commande){
		try {
			writer.write(commande+"\r\n");
			writer.flush();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public String read() {
		byte[] b = new byte[2048];

		try {
			reader.read(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new String(b);
	}


	public String pwd() {
	      this.send("PWD");
	      return this.read();
	}

	public String cwd(String dir) {
		this.send("CWD " + dir);
		return this.read();
	}

	public void cdup() {

	}

	public void quit() {

	}

	public void retr(String dir) {

	}

	public void stor(String dir) {

	}

	public void list(String dir) {

	}

	public void help(String cmd) {

	}

	public void noop() {

	}

	public void type(char type) {

	}

	public void type(char typeA, char typeB) {

	}
	
	public void type(char L, int sizeOctets) {

	}

	public void stru(char stru) {

	}

	public void mode(char mode) {

	}

	public void port(int port) {

	}
	
	public void pasv() {
		
	}

}

/*import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientFTP {

	private String pseudo;
	private String mdp;
	private String ip;
	private int port;
	
	
	private Socket PIsc = null; 
	private InputStream PIin = null;
	private OutputStream PIout = null;
	
	private Socket DTPsc = null; 
	private InputStream DTPin = null;
	private OutputStream DTPout = null;
	
	public ClientFTP(String pseudo,String mdp,String ip,int port) {
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.ip = ip;
		this.port = port;
		try {
			this.PIsc = new Socket(this.ip, this.port);
			this.PIin = PIsc.getInputStream();
			this.PIout = PIsc.getOutputStream();
			
			this.DTPsc = new Socket(this.ip, this.port);
			this.DTPin = DTPsc.getInputStream();
			this.DTPout = DTPsc.getOutputStream();
			
		}catch (Exception e) {e.printStackTrace();}
	}
	
	public ClientFTP(String ip) {this(ip,21);}
	public ClientFTP(String ip, int port) { this("anonymous","mdp",ip,port); }
	
	public String getPseudo() {return this.pseudo;}
	public String getMdp() { return this.mdp;}
	public String getIp() { return this.ip;}
	public int getPort() { return port;}
	
	public void connect(){
		//send(".");
		System.out.print("1>>"+recive());
		send("USER "+this.pseudo+"\r\n");
		System.out.print("2>>"+recive());
		send("PASS "+this.mdp+"\r\n");
		
		System.out.println("avant recive");
		System.out.print("3>>"+recive());
		send("LIST");
	}

	private void send(String string) {
		try {
			this.PIout.write(string.getBytes());
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	private String recive() {
		try {
			byte[] buf = new byte[2048];
			this.PIin.read(buf);
			return new String(buf);
		} catch (Exception e) {
			System.out.println("Erreur");
			e.printStackTrace();
		}
		return "";
	}

}*/
