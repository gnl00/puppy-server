package one.demo._1simplehandler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class SimpleHttpHandler implements HttpHandler {
    final Logger logger = LogManager.getLogger(getClass());
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String query = uri.getQuery();
        logger.info("{}: {}?{}", uri, path, query);
        // set response header
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html; charset=utf-8");
        responseHeaders.set("Cache-Control", "no-cache");
        exchange.sendResponseHeaders(200, 0);
        // output content
        try (OutputStream os = exchange.getResponseBody()) {
            os.write("<html><body><h1>Hello World</h1></body></html>".getBytes());
        }
    }
}
