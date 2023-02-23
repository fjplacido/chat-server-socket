package jsolutions;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		ServerSocket server;

		try {

			server = new ServerSocket(3322);

			System.out.println("Servidor iniciado na porta 3322");

			Socket cliente = server.accept();

			System.out.println("Cliente se conectour com o IP " + cliente.getInetAddress().getHostAddress());

			System.out.println(cliente.getInputStream().toString());

			server.close();

			System.out.println("Servidor encerrado!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
