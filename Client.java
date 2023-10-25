package cs250.hw3;

import java.io.*;
import java.net.*;

public class Client {
	
	private static Socket socket;
	private static DataInputStream din = null;

	public static void main(String[] args) {
		// java Client <address> <port>
		String address = null;
		int portNumber = Integer.MAX_VALUE;
		
		if (args.length == 2) {
			address = args[0];
			portNumber = Integer.parseInt(args[1]);
		} else {
			System.err.println("Incorrect number of arguments. 2 arguments required. java Client <address> <port-number>");
			System.exit(1);
		}
		
		try {
		  	socket = new Socket(address, portNumber);
			System.out.println("Connected to server!");			
			
			din = new DataInputStream(socket.getInputStream());
			int message = din.readInt();
			System.out.println("Message from server: " + message);
			System.out.println("Terminating.");
		} catch (IOException e) {
			System.err.println("Fatal connection error");
			e.printStackTrace();
		}

	}

}
