package one.demo._2simplehttpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.time.LocalDateTime;

public class SimpleHttpServer implements HttpHandler, AutoCloseable {
    static final Logger logger = LogManager.getLogger(SimpleHttpServer.class);

    final HttpServer httpServer;
    final String host;
    final int port;

    public SimpleHttpServer(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.httpServer = HttpServer.create(new InetSocketAddress(host, port), 0, "/", this);
        this.httpServer.start();
        logger.info("puppy-server started at {}:{}", host, port);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String query = uri.getQuery();
        logger.info("uri={}: path={} query={}", uri, path, query);
        // set response header
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html; charset=utf-8");
        responseHeaders.set("Cache-Control", "no-cache");
        exchange.sendResponseHeaders(200, 0);
        // output content
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(("<html><body><h1>Hello World</h1><p>" + LocalDateTime.now().withNano(0) + "</p></body></html>").getBytes());
        }
    }

    @Override
    public void close() {
        this.httpServer.stop(3);
    }

    public static void main(String[] args) {
        try (SimpleHttpServer connector = new SimpleHttpServer("localhost", 8080)) {
            for (;;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            logger.info("puppy-server closed");
        }
    }
}
