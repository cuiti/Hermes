package hermes.http;

import java.io.IOException;
import java.net.*;
import com.sun.net.httpserver.HttpServer;
import java.util.concurrent.Executors;

public class MonitorServer {

	public MonitorServer() {
		
		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(8000),0);	
			server.createContext("/hermes", new RequestHandler());
			server.setExecutor(Executors.newCachedThreadPool());
			server.start();
		} catch (IOException e) {
			System.out.println("Error al crear el HTTPserver");
			e.printStackTrace();
		}
	}

}
