package cs250.hw3;

import java.net.*;
import java.util.Random;
import java.io.*;

public class Client {
	private static Socket socket;
	private static DataInputStream din = null;
	private static DataOutputStream dout = null;
	private static long senderSum = 0;
	private static int numOfSentMessages = 0;

	public static void main(String[] args) {
		String address = null;
		int portNumber = Integer.MAX_VALUE;
		if (args.length == 2) {
			address = args[0];
			portNumber = Integer.parseInt(args[1]);
		} else {
			System.err.println("Incorrect Number of Arguments. 2 Arguments Required. java Client <address> <portNumber>");
			System.exit(1);
		}
		try {
			socket = new Socket(address, portNumber);
			System.out.println("Connected to Server!");
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			int numberOfMessages = din.readInt();
			int seed = din.readInt();
			Random random = new Random(seed);
			while (numberOfMessages > numOfSentMessages) {
				int nextNumber = random.nextInt();
				senderSum += nextNumber;
				dout.writeInt(nextNumber);
				dout.flush();
				numOfSentMessages++;
			}
			System.out.println("Terminating.");
		} catch (IOException e) {
			System.err.println("Fatal Connection Error!");
			e.printStackTrace();
		}
	}
}
