import java.net.*;
import java.sql.*;
import com.google.gson.Gson;

public class UDPPrintServer extends UDPServer{
    public final static int DEFAULT_PORT = 3000;

    public UDPPrintServer(){
        super(DEFAULT_PORT);
    }

    @Override
    public void response(DatagramSocket socket, DatagramPacket request){
        String jsonData = new String(request.getData(), request.getOffset(), request.getLength());
        Gson gson = new Gson();
        GPSdata gpsdata = gson.fromJson(jsonData, GPSdata.class);
        String DeviceMac = gpsdata.DeviceMac;
        String DeviceInfo = gpsdata.DeviceInfo;
        double GPS_N = gpsdata.GPS_N;
        double GPS_E = gpsdata.GPS_E;
        int Warn_Sta = gpsdata.Warn_Sta;
        Connection sqlConn = MySQLConnect.getConnection();
        Statement stat = null;
        try {
            stat = sqlConn.createStatement();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        String sql = "INSERT INTO alertServer" +
                "(DeviceMac, DeviceInfo, GPS_N, GPS_E, Warn_Sta)" +
                "VALUES" +
                "(\"" + DeviceMac + "\", \"" + DeviceInfo + "\", \"" + GPS_N + "\", \"" + GPS_E +  "\", \"" + Warn_Sta + "\")";
        if (stat != null){
            try {
                stat.executeUpdate(sql);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

        //System.out.println(new String(request.getData(), request.getOffset(), request.getLength()) + request.getAddress().toString() + ":" + request.getPort());
    }

    public static void main(String[] args){
        UDPServer server = new UDPPrintServer();
        Thread t = new Thread(server);
        t.start();
    }
}

class GPSdata{
    String DeviceMac;
    String DeviceInfo;
    double GPS_N;
    double GPS_E;
    int Warn_Sta;
}
