package cs250.hw3;

import java.net.*;
import java.io.*;
import java.util.Random;

public class Server {
	private static Socket clientSocketOne;
	private static Socket clientSocketTwo;
	private static ServerSocket serverSocket;
	private static DataOutputStream doutClient = null;

	public static void main(String[] args) {
		int portNumber = Integer.MAX_VALUE; // Garbage value so we don't start server on random port.
		if (args.length == 1) {
			portNumber = Integer.parseInt(args[0]);
		} else {
			System.err.println("Incorrect Number of Arguments. 1 Argument Required. java Server <portNumber>");
			System.exit(1);
		}
		try {
			serverSocket = new ServerSocket(portNumber);
			Random random = new Random();
			// wait on client
			clientSocketOne = serverSocket.accept();
			System.out.println("Connected to Client 1!");
			doutClient = new DataOutputStream(clientSocketOne.getOutputStream());
			doutClient.writeInt(portNumber);
			doutClient.flush();
			doutClient.writeInt(random.nextInt());
			doutClient.flush();
			System.out.println("Information Sent to Client 1.");

			clientSocketTwo = serverSocket.accept();
			System.out.println("Connected to Client 2!");
			doutClient = new DataOutputStream(clientSocketTwo.getOutputStream());
			doutClient.writeInt(portNumber);
			doutClient.flush();
			doutClient.writeInt(random.nextInt());
			doutClient.flush();
			System.out.println("Information Sent to Client 2.");
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + portNumber);
			System.exit(1);
		}
	}
}
