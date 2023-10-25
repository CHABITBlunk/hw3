package cs250.hw3;

import java.io.*;
import java.net.*;

public class Server {
	
	private static Socket clientSocket;
	private static ServerSocket serverSocket; 
	private static DataOutputStream doutClient;

	public static void main(String[] args) {
		// java Server <port-number>
		int portNumber = Integer.MAX_VALUE;
		if (args.length == 1) {
			portNumber = Integer.parseInt(args[0]);
		} else {
			System.err.println("Incorrect number of arguments. 1 argument required. java Server <port-number>");
			System.exit(1);
		}
		
		// listen on given port
		try {
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Waiting on client");
			// wait for client
			clientSocket = serverSocket.accept();
			System.out.println("Connected to client");
			
			int theAnswer = 42;
			doutClient = new DataOutputStream(clientSocket.getOutputStream());
			doutClient.writeInt(theAnswer);
			doutClient.flush();
			
			
		} catch (IOException e) {
			System.err.printf("Could not listen on port %d\n", portNumber);
			System.exit(1);
		}
	}

}
