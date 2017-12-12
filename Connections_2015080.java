// Ronak Kumar (2015080)

import java.io.*;
import java.net.*;
public class Connections_2015080 extends Thread {
	
	private Socket sock;
	private Integer sleepTiming = 1;
	private Integer isAvailable = 0;
	private DataInputStream dataInput;
	private DataOutputStream dataOutput;

	public Connections_2015080(Socket sock) {
		this.sock = sock;
	}
	
	public void run() {
		try {
			dataInput = new DataInputStream(sock.getInputStream());
			dataOutput = new DataOutputStream(sock.getOutputStream());
			for(;;) {
				while(dataInput.available() == this.isAvailable) {
					try {
						Thread.sleep(this.sleepTiming);
					}
					catch(InterruptedException exception) {
						exception.printStackTrace();
					}
				}
				String text = dataInput.readUTF();
				Integer temp = Server_2015080.getIndex(this.sock.getPort());
				if(text.equals("quit") || text.equals("exit")) {
					System.out.println("Client " + (temp+1) + " Disconnected");
				}
				else {
					System.out.println("Received From Client " + (temp+1) + " " + text);
					String rev = reverseStringWords(text);
					sendToClient(rev);
				}	
			}
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public String reverseStringWords(String sentence) {
		String temp = sentence;
		String[] str = temp.split(" ");
		String finalString = "";
		int length = str.length;
		int i = length - 1;
		while(i >= 0) {
			finalString += str[i] + " ";
			i--;
		}
		return finalString;
	}
	
	public void sendToClient(String text) {
		try {
			dataOutput.writeUTF(text);
			dataOutput.flush();
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}