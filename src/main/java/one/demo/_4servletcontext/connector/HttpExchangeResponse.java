package one.demo._4servletcontext.connector;

import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.io.OutputStream;

public interface HttpExchangeResponse {
    Headers getResponseHeaders();
    void sendResponseHeaders(int statusCode, long responseLength) throws IOException;
    OutputStream getResponseBody();
}
