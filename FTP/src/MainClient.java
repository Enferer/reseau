
public class MainClient {
		
	public static void main(String[] args) {
		ClientFTP c = new ClientFTP("localhost",2121);
		try {
			c.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
