package udpserver;

import java.io.IOException;

/**
 *
 * @author notechus
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        UDPServer server = new UDPServer();
        server.start();

    }
}
