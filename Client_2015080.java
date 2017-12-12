// Ronak Kumar (2015080)

import java.io.*;
import java.net.*;
import java.util.*;
public class Client_2015080 {

	private Socket sock;
	private final int DEFAULT_PORT = 3333;
	private Integer sleepTiming = 1;
	private Integer isAvailable = 0;
	private DataInputStream dataInput;
	private DataOutputStream dataOutput;
	
	public static void main(String[] args) {
		new Client_2015080();
	}

	public Client_2015080() {
		try {
			sock = new Socket("localhost", this.DEFAULT_PORT);
			dataInput = new DataInputStream(sock.getInputStream());
			dataOutput = new DataOutputStream(sock.getOutputStream());
			sendStringToServer();
		}
		catch(UnknownHostException exception) {
			exception.printStackTrace();
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void sendStringToServer() {
		Scanner s = new Scanner(System.in);
		while(true) {
			String messageToSend = s.nextLine();
			if(messageToSend.equals("quit") || messageToSend.equals("exit")) {
				try {
					dataOutput.writeUTF(messageToSend);
				}
				catch(IOException exception) {
					exception.printStackTrace();
				}
				break;
			}
			try {
				dataOutput.writeUTF(messageToSend);
				while(dataInput.available() == this.isAvailable) {
					try {
						Thread.sleep(this.sleepTiming);
					}
					catch(InterruptedException exception) {
						exception.printStackTrace();
					}
				}
				String messageReceived = dataInput.readUTF();
				System.out.println("Received from Server" + " " + messageReceived);
			}
			catch(IOException exception) {
				exception.printStackTrace();
				break;
			}
		}
		s.close();
	}

}