// Ronak Kumar (2015080)

import java.io.*;
import java.net.*;
import java.util.*;
public class Server_2015080 {

	private ServerSocket server;
	public static List<Integer> connections = new ArrayList<Integer>();
	private final int DEFAULT_PORT = 3333;

	public static void main(String[] args) {
		new Server_2015080();
	}

	public Server_2015080(int number) {
		
	}
	
	public Server_2015080() {
		try {
			server = new ServerSocket(this.DEFAULT_PORT);
			for(;;) {
				Socket sock = server.accept();
				connections.add(sock.getPort());
				Connections_2015080 newConnection = new Connections_2015080(sock);
				newConnection.start();
				System.out.println("Client " + connections.size() + " Connected");	
			}
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public static int getIndex(int portNumber) {
		for(int i = 0; i < connections.size(); i++) {
			if(connections.get(i) == portNumber)
				return i;
		}
		return -1;
	}
}