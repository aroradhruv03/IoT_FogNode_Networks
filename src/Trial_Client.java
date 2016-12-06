import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Dhruv
 * @version 1.0
 *          Date 10/23/16
 *          <p>
 *          This is a implementation class for
 */
public class Trial_Client {
    public static void main(String[] args) {
        String serverName = "localhost";

        ClientConnect c1 = new ClientConnect(serverName, 5005);
        ClientConnect c2 = new ClientConnect(serverName, 5005);
        c1.start();
        c2.start();

    }

}
class ClientConnect extends Thread{
    String serverName;
    int port;

    public ClientConnect(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
//            InputStream inFromServer = client.getInputStream();
//            DataInputStream in = new DataInputStream(inFromServer);

//            System.out.println("SocketServer says " + in.readUTF());

            /** Datagram */

            DatagramSocket sock = new DatagramSocket();
//                System.out.println("Enter message to send : ");
            String s = "Sending hello";
            byte[] b = s.getBytes();

            InetAddress host = InetAddress.getByName(serverName);

            DatagramPacket dp = new DatagramPacket(b , b.length , host , port);
            sock.send(dp);

            //now receive reply
            //buffer to receive incoming data
            byte[] buffer = new byte[65536];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            sock.receive(reply);

            byte[] data = reply.getData();
            s = new String(data, 0, reply.getLength());

            //echo the details of incoming data - client ip : client port - client message
            System.out.println(reply.getAddress().getHostAddress() + " : " + reply.getPort() + " - " + s);




            client.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
