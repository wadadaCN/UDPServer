## 流程图

## 记录
2018.12.6 UDP发json数据为byte数组，标点符号如"{"、“,”等不能省；
          UDP监听服务器收到DatagramPacket后通过get方法将DatagramPacket对象转换为字符串，然后通过gson解析；
          gson解析可以通过`gson.fromJson(jsonData, GPSdata.class)`来获取GPSdata对象，GPSdata是根据json格式来设计的对象，方便获取json中的小项；
          通过构造sql语句往数据库写入数据，形式为`String sql = "INSERT INTO alertServer" +
                "(DeviceMac, DeviceInfo, GPS_N, GPS_E, Warn_Sta)" +
                "VALUES" +
                "(\"" + DeviceMac + "\", \"" + DeviceInfo + "\", \"" + GPS_N + "\", \"" + GPS_E +  "\", \"" + Warn_Sta + "\")"`，变量前后的`\"`不能省。
