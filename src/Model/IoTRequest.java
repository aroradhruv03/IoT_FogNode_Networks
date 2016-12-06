package Model;

/**
 * @author Dhruv
 * @version 1.0
 *          Date 11/23/16
 *          <p>
 *          This is a implementation class for
 */
public class IoTRequest {

    private long pkt_seq_no;
    private String requestType;
    private String IP_addr;
    private long UDP_port;
    private long forwardLimit;
    private long responseTime;
    private String message;

    public IoTRequest(long pkt_seq_no, String requestType, String IP_addr, long UDP_port, long forwardLimit, long responseTime) {
        this.pkt_seq_no = pkt_seq_no;
        this.requestType = requestType;
        this.IP_addr = IP_addr;
        this.UDP_port = UDP_port;
        this.forwardLimit = forwardLimit;
        this.responseTime = responseTime;
    }

    public long getPkt_seq_no() {
        return pkt_seq_no;
    }

    public void setPkt_seq_no(long pkt_seq_no) {
        this.pkt_seq_no = pkt_seq_no;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getIP_addr() {
        return IP_addr;
    }

    public void setIP_addr(String IP_addr) {
        this.IP_addr = IP_addr;
    }

    public long getUDP_port() {
        return UDP_port;
    }

    public void setUDP_port(long UDP_port) {
        this.UDP_port = UDP_port;
    }

    public long getForwardLimit() {
        return forwardLimit;
    }

    public void setForwardLimit(long forwardLimit) {
        this.forwardLimit = forwardLimit;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
