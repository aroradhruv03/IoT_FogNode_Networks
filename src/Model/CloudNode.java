package Model;

import java.util.Queue;

/**
 * @author Dhruv
 * @version 1.0
 *          Date 11/23/16
 *          <p>
 *          This is a implementation class for
 */
public class CloudNode {

    private String IP_addr;
    private long TCP_port;;
    private Queue queue;

    public CloudNode(String IP_addr, long TCP_port, Queue queue) {
        this.IP_addr = IP_addr;
        this.TCP_port = TCP_port;
        this.queue = queue;
    }

    public String getIP_addr() {
        return IP_addr;
    }

    public void setIP_addr(String IP_addr) {
        this.IP_addr = IP_addr;
    }

    public long getTCP_port() {
        return TCP_port;
    }

    public void setTCP_port(long TCP_port) {
        this.TCP_port = TCP_port;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
}
