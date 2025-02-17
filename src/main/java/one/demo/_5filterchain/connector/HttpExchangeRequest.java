package one.demo._5filterchain.connector;

import java.net.URI;

public interface HttpExchangeRequest {
    String getRequestMethod();
    URI getRequestURI();
}
