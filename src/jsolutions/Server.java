package jsolutions;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

	private static final int SERVER_PORT = 3322;

	public static void main(String[] args) {

		Socket socket = null;

		try (ServerSocket server = new ServerSocket(SERVER_PORT);) {

			System.out.println("[SERVER]: Servidor iniciado na porta " + SERVER_PORT);

			int numMessages = 0;

			while (numMessages < 5) {

				socket = server.accept();

				DataInputStream inputData = new DataInputStream(socket.getInputStream());
				byte[] byteArrray = inputData.readAllBytes();
				System.out.println("[RECEIVED FROM CLIENT]: " + new String(byteArrray, StandardCharsets.UTF_8));

				numMessages++;

			}

			System.out.println("[SERVER]: Servidor encerrado!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
