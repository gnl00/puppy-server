package one.demo._3simpleservletserver.connector;

import java.net.URI;

public interface HttpExchangeRequest {
    String getRequestMethod();
    URI getRequestURI();
}
