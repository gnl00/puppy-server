package one.demo._5filterchain.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Start {

    static final Logger logger = LogManager.getLogger(Start.class);

    public static void main(String[] args) {
        try (HttpConnector connector = new HttpConnector()) {
            for (;;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("connector start failed", e);
        } finally {
            logger.info("connector closed");
        }
    }
}
