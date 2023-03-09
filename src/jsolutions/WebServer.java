package jsolutions;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class WebServer {

	private static final int SERVER_PORT = 8080;

	public static void main(String[] args) {

		try (var server = new ServerSocket(SERVER_PORT)) {
			while (true) {
				try (var socket = server.accept()) {
					var reader = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
					while (!socket.isClosed()) {
						var line = reader.readLine();
						if (line == null) {
							break;
						}
						var query = line.substring(line.indexOf(' ') + 1, line.lastIndexOf(' ')).trim();
						Map<String, String> headers = new HashMap<>();
						while (line != null) {
							System.out.println(line);
							line = reader.readLine();
							if (line.trim().isEmpty()) {
								break;
							}
							var key = line.substring(0, line.indexOf(':')).trim();
							var value = line.substring(line.indexOf(':') + 1).trim();
							headers.put(key, value);
						}
						if (query.equals("/")) {
							var content = "<HTML><H1>FALLAAAA FRANCISCO !!!!</H1><IMG SRC='/img.jpg'/></HTML>"
									.getBytes();
							var out = socket.getOutputStream();
							out.write("HTTP 200 OK\r\n".getBytes());
							out.write(String.format("Content-Length: %s\r\n", content.length).getBytes());
							out.write("\r\n".getBytes());
							out.write(content);
						} else if (query.equals("/img.jpg")) {
							var imageBytes = Files.readAllBytes(Path.of("C:\\temp\\a.jpg"));
							var out = socket.getOutputStream();
							out.write("HTTP 200 OK\r\n".getBytes());
							out.write(String.format("Content-Length: %s\r\n", imageBytes).getBytes());
							out.write("\r\n".getBytes());
							out.write(imageBytes);
						} else {
							var out = socket.getOutputStream();
							out.write("HTTP 404 Not Found\r\n".getBytes());
							out.write("\r\n".getBytes());
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}