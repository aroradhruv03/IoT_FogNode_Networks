import Model.FogNode;
import Utility.TextOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Dhruv
 * @version 1.0
 *          Date 11/23/16
 *          <p>
 *          This is a implementation class for
 */
class StartServer extends Thread {

    Model.FogNode fogNode;
    int portNo;

    public StartServer(FogNode model, int portNo) {
        super();
        this.fogNode = model;
        this.portNo = portNo;
    }

    public StartServer(FogNode model) {
        super();
        this.fogNode = model;
        this.portNo = fogNode.getTCP_port();
    }

    public StartServer(String name, int portNo) {
        super(name);
        this.portNo = portNo;
    }

    @Override
    public void run() {

//         for (int i = 0; i < 10; i++) {
//            System.out.println(i + " loaaaaaoping ...");
//        }
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i + " loading 2 ...");
//        }


//        createSocket(portNo);
        ServerSocket serverSocket = null;
        Socket socket = null;
        PrintStream printStream = null;
        BufferedReader bufferedReader = null;
        String message = null;
        DatagramSocket udp = null;


        try {
            System.out.println("Trying on port " + portNo);
            //1. creating a server socket - 1st parameter is port number and 2nd is the backlog
            serverSocket = new ServerSocket(portNo);

            //2. Wait for an incoming connection
            System.out.println("Server socket created on port " + portNo + ". Waiting for connection...");


            udp = new DatagramSocket(portNo);
            System.out.println("UDP port opened on "+portNo);

            UDP_Server udp_server = new UDP_Server(udp,portNo);
            Thread u = new Thread(udp_server);
            u.start();

            /**
             * Upon getting a new connection createa a new thread.
             */
            while ((socket = serverSocket.accept()) != null){
                //get the connection socket

                //print the hostname and port number of the connection
//            System.out.println("Connection received from " + socket.getInetAddress().getHostName() + " : " + socket.getPort()+" on port "+portNo);

                //3. get Input and Output streams
//            printStream = new PrintStream(socket.getOutputStream());
//            printStream.flush();
//            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            printStream.println("Welcome. Server version 1.0");
//            printStream.flush();

                ClientWorker clientWorker;

                clientWorker = new ClientWorker(socket, portNo);

                Thread t = new Thread(clientWorker);
                t.start();



            }
            System.out.println("End of tcp");

        }

        catch(IOException e)
        {
            System.err.println("IOException");
            e.printStackTrace();
        }
        finally {
            try
            {
                bufferedReader.close();
                printStream.close();
//                serverSocket.close();
            }

            catch(IOException ioException)
            {
                System.err.println("Unable to close. IOException");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 5. close the connections and stream


        // Sleep for a while
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
////             Interrupted exception will occur if
////             the Worker object's interrupt() method
////             is called. interrupt() is inherited
////             from the Thread class.
////                            break;
//        }
    }
}

class ClientWorker extends Thread{

    private Socket socket;
    int portNo;

    public ClientWorker(Socket client, int portNo) {
        this.socket = client;
        this.portNo = portNo;
//        print();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " loading ...");
        }
        System.out.println(Thread.activeCount());
        System.out.println("Connection received from " + socket.getInetAddress().getHostName() + " : " + socket.getPort()+" on port "+portNo);

    }

    void print(){
        System.out.println("Hello");
    }
}

class UDP_Server extends Thread{

    private DatagramSocket socket;
    int portNo;

    public UDP_Server(DatagramSocket client, int portNo) {
        this.socket = client;
        this.portNo = portNo;
//        print();
    }

    @Override
    public void run() {
        try
        {
            //1. creating a server socket, parameter is local port number

            //buffer to receive incoming data
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            //2. Wait for an incoming data
            System.out.println("Server socket created. Waiting for incoming data...");

            //communication loop
            while(true)
            {
                socket.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());

                //echo the details of incoming data - client ip : client port - client message
                System.out.println("UDP CONNECTION FROM "+incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + s);

                s = "OK : " + s;
                DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
                socket.send(dp);
            }
        }catch(IOException e)
        {
            System.err.println("IOException " + e);
        }

    }

    void print(){
        System.out.println("Hello");
    }
}


public class SocketServer{

//    public static int[] portNo = new int[]{5004, 5003};

    public static volatile int i;

    static {
        i = 0;
    }

    public static void main(String[] args) {

        /**
         * Configuration
         */

        // FogNode Max_Response_Time t MY_IP MY_UDP MY_TCP N1 TCP1 N2 TCP2
        // FogNode 12 12 localhost 5005 5005 localhost 5006 localhost 5007

        FogNode fogNode1 = new TextOperations().clean_FogNode("FogNode 12 12 localhost 5005 5005 localhost 5006"); //localhost 5007");

        System.out.println(fogNode1.toString());

//        FogNode fogNode1 = new FogNode("127.0.0.1", 5005, 5005, 0);

        FogNode fogNode2 = new TextOperations().clean_FogNode("FogNode 12 12 localhost 5006 5006 localhost 5005");
//        FogNode fogNode2 = new FogNode("localhost", 5006, 12345, 0);

        /**
         * Config Ends
         */

        /**
         * Start Threads
         */
        StartServer worker1 = new StartServer(fogNode1);
        worker1.start();

        StartServer worker2 = new StartServer(fogNode2);
        worker2.start();


        /**
         *
         System.out.println(Thread.activeCount());
         Thread [] threads = new Thread [Thread.activeCount ()];
         int n = Thread.enumerate (threads);
         for (int i = 0; i < n; i++)
         System.out.println (threads [i].toString ());
         */

        // You can call interrupt() if you want
        // to interrupt a thread. The thread itself
        // decides how to handle interrupts.
        // worker1.interrupt();
    }
}