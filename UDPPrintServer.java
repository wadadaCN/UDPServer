import java.net.*;

public class UDPPrintServer extends UDPServer{
    public final static int DEFAULT_PORT = 3000;

    public UDPPrintServer(){
        super(DEFAULT_PORT);
    }

    @Override
    public void response(DatagramSocket socket, DatagramPacket request){
        System.out.println(new String(request.getData(), request.getOffset(), request.getLength()) + request.getAddress().toString() + ":" + request.getPort());
    }

    public static void main(String[] args){
        UDPServer server = new UDPPrintServer();
        Thread t = new Thread(server);
        t.start();
    }
}
