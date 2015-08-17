package udpserver;

import networking.Packet;
import gui.AppWindow;
import java.io.*;
import java.net.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author notechus
 */
public class UDPServer {

    AppWindow wind = new AppWindow();
    static final Logger log = Logger.getLogger(UDPServer.class.getName());

    public UDPServer() throws IOException {
        //super();
        //Window.window();
        wind.setVisible(true);
    }

    public void start() throws IOException {

        DatagramSocket sock = null;

        //setting up logger
        FileHandler fh = new FileHandler("ClientLog.log", true);
        log.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        log.setUseParentHandlers(false);
        echo("Started application");

        try {
            // creating a server socket, parameter is local port number
            sock = new DatagramSocket(null);
            sock.bind(new InetSocketAddress(InetAddress.getByName("192.168.1.10"), 7777));

            //buffer to receive incoming data
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            // Wait for an incoming data
            echo("Server socket created. Waiting for incoming data...");

            //communication loop
            while (true) {
                sock.receive(incoming);
                byte[] data = incoming.getData();
                byte[] reply = new byte[65536];
                //initialising recieved object
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {
                    Packet p = (Packet) is.readObject();
                    echo("Packet recieved: " + p.toString() + " from: " + incoming.getAddress());
                    reply = ("Recieved packet " + p.toString()).getBytes();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                //setting up reply
                //byte[] reply = "Recieved packet ".getBytes();
                DatagramPacket replyPacket = new DatagramPacket(reply, reply.length, incoming.getAddress(), incoming.getPort());
                sock.send(replyPacket);
            }
        } catch (IOException e) {
            System.err.println("IOException " + e);
        } finally {
            //sock.close();
            echo("Stopped application");

        }
    }

    //simple function to echo data to terminal and to log file
    public void echo(String msg) {
        //System.out.println(msg);
        log.info(msg);
        AppWindow.updateLog(msg);
    }
}
