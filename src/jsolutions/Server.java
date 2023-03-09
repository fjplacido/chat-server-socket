package jsolutions;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static final int SERVER_PORT = 3322;

	public static void main(String[] args) {

		try (ServerSocket server = new ServerSocket(SERVER_PORT)) {

			System.out.println("[SERVER]: Servidor iniciado na porta " + SERVER_PORT);

			int numMessages = 0;

			while (numMessages < 5) {

				Socket socket = server.accept();

				try (DataInputStream inputData = new DataInputStream(socket.getInputStream())) {
					int index = inputData.readInt();
					String message = inputData.readUTF();
					System.out.println("[RECEIVED FROM CLIENT]: (" + index + ") " + message);
				}
				numMessages++;
			}

			System.out.println("[SERVER]: Servidor encerrado!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
