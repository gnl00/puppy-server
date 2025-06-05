package one.demo._3simpleservletserver.connector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import one.demo._3simpleservletserver.engine.HttpServletRequestImpl;
import one.demo._3simpleservletserver.engine.HttpServletResponseImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;

/**
 * HttpConnector 实际上就是一个 Server
 */
public class HttpConnector implements HttpHandler, AutoCloseable {
    static final Logger logger = LogManager.getLogger(HttpConnector.class);

    final HttpServer httpServer;
    final String host;
    final int port;

    public HttpConnector(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.httpServer = HttpServer.create(new InetSocketAddress(host, port), 0, "/", this);
        this.httpServer.start();
        logger.info("puppy-server started at {}:{}", host, port);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpExchangeAdapter adapter = new HttpExchangeAdapter(exchange);
        HttpServletRequestImpl request = new HttpServletRequestImpl(adapter);
        HttpServletResponseImpl response = new HttpServletResponseImpl(adapter);
        process(request, response);
    }

    /**
     * process方法内部就可以按照Servlet标准来处理HTTP请求了，因为方法参数是标准的Servlet接口
     */
    void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String html = "<h1>Hello, " + (name == null ? "world" : name) + ".</h1>";
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.write(html);
        pw.close();
    }

    @Override
    public void close() {
        this.httpServer.stop(3);
    }

}
