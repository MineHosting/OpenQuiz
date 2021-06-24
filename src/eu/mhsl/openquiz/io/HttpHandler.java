package eu.mhsl.openquiz.io;

import com.sun.net.httpserver.HttpExchange;
import eu.mhsl.openquiz.out.Logger;

import java.io.IOException;
import java.io.OutputStream;

public class HttpHandler implements com.sun.net.httpserver.HttpHandler {

    final String response;
    public HttpHandler(String response) {
        this.response = response;
    }

    /**
     * Is called when an User sends an HTTP-Request to the Internal Server
     * @param he the Http-Info Obj
     */
    @Override
    public void handle(HttpExchange he) throws IOException {
        Logger.info("Handles " + he.getRequestMethod() + " request from " + he.getRemoteAddress().getHostString() + " (" + he.getProtocol() + ")");
        he.sendResponseHeaders(200, this.response.length());
        OutputStream os = he.getResponseBody();
        os.write(this.response.getBytes());
        os.close();
    }
}
