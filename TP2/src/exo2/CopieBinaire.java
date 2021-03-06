package exo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CopieBinaire {

	private String src;
	private String dst;
	
	
	public CopieBinaire(String src, String dst) {
		this.src = src;
		this.dst = dst;
	}
	
	
	public void copy() throws FileNotFoundException {
		
		File src = new File(this.src);
		File dst = new File(this.dst);
		if(!src.exists() || !dst.exists()) {
			System.out.println("Erreur fichier inconnu");
			return;
		}	
		
		FileInputStream fi;
		FileOutputStream fo;
		try {
			
			fi = new FileInputStream(src);
			fo = new FileOutputStream(dst);
			byte[] buf = new byte[2048];
			int nbr;
			
			while((nbr=fi.read(buf))!= -1) {
				fo.write(buf,0,nbr);
				
			}
			
			fi.close();
			fo.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	
	
	public static void main(String[] args) {
		
		//CopieBinaire cp = new CopieBinaire(args[0], args[1]);
		CopieBinaire cp = new CopieBinaire("test", "dtest");
		try{
			
			cp.copy();
			
		}catch (FileNotFoundException e) {e.printStackTrace();}
		
	}
	
}
