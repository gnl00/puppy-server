package one.demo._4servletcontext.connector;

import java.net.URI;

public interface HttpExchangeRequest {
    String getRequestMethod();
    URI getRequestURI();
}
