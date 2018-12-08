import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class makeHTML {
    final static String prefix = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "    <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />\n" +
            "    <style type=\"text/css\">\n" +
            "        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:\"微软雅黑\";}\n" +
            "    </style>\n" +
            "    <script type=\"text/javascript\" src=\"http://api.map.baidu.com/api?v=2.0&ak=yourak\"></script>\n" +
            "    <title>GPS转百度</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div id=\"allmap\"></div>\n" +
            "</body>\n" +
            "</html>\n" +
            "<script type=\"text/javascript\">";
    final static String suffix = "    var ggPoint = new BMap.Point(x,y);\n" +
            "\n" +
            "    //地图初始化\n" +
            "    var bm = new BMap.Map(\"allmap\");\n" +
            "    bm.centerAndZoom(ggPoint, 15);\n" +
            "    bm.addControl(new BMap.NavigationControl());\n" +
            "\n" +
            "    //坐标转换完之后的回调函数\n" +
            "    translateCallback = function (data){\n" +
            "      if(data.status === 0) {\n" +
            "        var marker = new BMap.Marker(data.points[0]);\n" +
            "        bm.addOverlay(marker);\n" +
            "        var label = new BMap.Label(\"员工所在位置\",{offset:new BMap.Size(20,-10)});\n" +
            "        marker.setLabel(label); //添加百度label\n" +
            "        bm.setCenter(data.points[0]);\n" +
            "      }\n" +
            "    }\n" +
            "\n" +
            "    setTimeout(function(){\n" +
            "        var convertor = new BMap.Convertor();\n" +
            "        var pointArr = [];\n" +
            "        pointArr.push(ggPoint);\n" +
            "        convertor.translate(pointArr, 1, 5, translateCallback)\n" +
            "    }, 1000);\n" +
            "</script>";
    final static String path = "C:\\code-project\\web\\GPS.html";

    private Double x;
    private Double y;

    makeHTML(Double x, Double y){
        this.x = x;
        this.y = y;
        makeFile();
    }

    private void makeFile(){
        File file = new File(path);
        try {
            String filename = file.getName();
            // tmpfile为缓存文件，代码运行完毕后此文件将重命名为源文件名字。
            //File tmpfile = new File(file.getParentFile().getAbsolutePath()
            //        + "\\" + filename + ".tmp");

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            boolean flag = false;
            String str = prefix + "\n    var x = " + x + ";\n" + "    var y = " + y + ";\n" + suffix;
            writer.write(str);

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
