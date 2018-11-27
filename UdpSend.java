import java.net.*;
public class UdpSend{
    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket();
        String str = "000000000000000000000";
        byte[] sendData = str.getBytes();
        //String ipAddress = InetAddress.getLocalHost().getHostAddress();
        String ipAddress = "127.0.0.1";
        int count = 0;
        while (count < 200){
            count++;
           Thread.sleep(500);
            ds.send(new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ipAddress), 3000));
        }
        ds.send(new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ipAddress), 3000));
        ds.close();
    }
}
