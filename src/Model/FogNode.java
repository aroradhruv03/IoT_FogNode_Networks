package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import com.google.common.collect.Multimap;

/**
 * @author Dhruv
 * @version 1.0
 *          Date 11/23/16
 *          <p>
 *          This is a implementation class for
 */
public class FogNode {

    private long max_response_time;
    private long queue_time;
    private String host_name;
    private String IP_addr;
    private int TCP_port;
    private int UDP_port;
    private Queue queue;
    private ArrayList<FogNode> list_neighbours;
    private String initializationMessage;
    private HashMap<String, Integer> list_of_neighbours = new HashMap<>();
    private int no_of_neighbours;

    public FogNode() {
    }

    public FogNode(long max_response_time, long queue_time, String host_name, int UDP_port, int TCP_port) {
        this.max_response_time = max_response_time;
        this.queue_time = queue_time;
        this.host_name = host_name;
        this.TCP_port = TCP_port;
        this.UDP_port = UDP_port;
    }

    public FogNode(String host_name, String IP_addr, int TCP_port, int UDP_port, Queue queue, ArrayList<FogNode> list_neighbours, long max_response_time, long queue_time) {
        this.host_name = host_name;
        this.IP_addr = IP_addr;
        this.TCP_port = TCP_port;
        this.UDP_port = UDP_port;
        this.queue = queue;
        this.list_neighbours = list_neighbours;
        this.max_response_time = max_response_time;
        this.queue_time = queue_time;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getIP_addr() {
        return IP_addr;
    }

    public void setIP_addr(String IP_addr) {
        this.IP_addr = IP_addr;
    }

    public int getTCP_port() {
        return TCP_port;
    }

    public void setTCP_port(int TCP_port) {
        this.TCP_port = TCP_port;
    }

    public int getUDP_port() {
        return UDP_port;
    }

    public void setUDP_port(int UDP_port) {
        this.UDP_port = UDP_port;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public ArrayList<FogNode> getList_neighbours() {
        return list_neighbours;
    }

    public void setList_neighbours(ArrayList<FogNode> list_neighbours) {
        this.list_neighbours = list_neighbours;
    }

    public long getMax_response_time() {
        return max_response_time;
    }

    public void setMax_response_time(long max_response_time) {
        this.max_response_time = max_response_time;
    }

    public long getQueue_time() {
        return queue_time;
    }

    public void setQueue_time(long queue_time) {
        this.queue_time = queue_time;
    }

    public HashMap<String, Integer> getList_of_neighbours() {
        return list_of_neighbours;
    }

    public void setList_of_neighbours(HashMap<String, Integer> list_of_neighbours) {
        this.list_of_neighbours = list_of_neighbours;
    }

    public void setList_of_neighbours(String neighbour_Host_Name, int neighbour_TCP_No){
        this.list_of_neighbours.put(neighbour_Host_Name, neighbour_TCP_No);
    }

    public int getNo_of_neighbours() {
        return no_of_neighbours;
    }

    public void setNo_of_neighbours(int no_of_neighbours) {
        this.no_of_neighbours = no_of_neighbours;
    }

    public String getInitializationMessage() {
        return initializationMessage;
    }

    public void setInitializationMessage(String initializationMessage) {
        this.initializationMessage = initializationMessage;
    }

    /**
     * A toString method for Debuggin purposes only
     * @return : Will return all the fields and their values
     */
    @Override
    public String toString() {
        return "FogNode{" +
                "max_response_time=" + max_response_time +
                ", queue_time=" + queue_time +
                ", host_name='" + host_name + '\'' +
                ", IP_addr='" + IP_addr + '\'' +
                ", TCP_port=" + TCP_port +
                ", UDP_port=" + UDP_port +
                ", initializationMessage='" + initializationMessage + '\'' +
                ", list_of_neighbours=" + list_of_neighbours +
                ", no_of_neighbours=" + no_of_neighbours +
                '}';
    }
}
