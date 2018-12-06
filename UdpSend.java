import java.net.*;
public class UdpSender{
    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket();
        String str = "{" +
                "\t\"DeviceMac\":\"123456789012345\",\n" +
                "\t\"DeviceInfo\":\"Normal\",\n" +
                "\t\"GPS_N\":\"123.45\",\n" +
                "\t\"GPS_E\":\"123.45\",\n" +
                "\t\"Warn_Sta\":\"1\"\n" +
                "}";
        byte[] sendData = str.getBytes();
        //String ipAddress = InetAddress.getLocalHost().getHostAddress();
        String ipAddress = "127.0.0.1";
        int count = 0;
//        while (count < 200){
//            count++;
//            Thread.sleep(500);
//            ds.send(new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ipAddress), 3000));
//        }
        ds.send(new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ipAddress), 3000));
        ds.close();
    }
}
