import mina.client.MinaClient;
import mina.server.MinaServer;
import util.ReadUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    public static void main(String[] args) {

        String fileParent = "C:\\Users\\VNBear\\Documents\\WeChat Files\\zw791029369\\FileStorage\\File\\2019-03\\许沙源&谢辉敏（时间表）\\许沙源&谢辉敏（时间表）";
        //computer(fileParent);

        new MinaServer(9898);
        new MinaClient("192.168.56.1",9898);

    }

    public static void computer(String fileDir){
        List<String> filePathList = getFilePath(fileDir);
        for (String filePath : filePathList) {
            String str = readFileForString(filePath);
            String rgex = "\\[(.*?)\\]";
            List<String> stringList = getSubUtil(str, rgex);
            String timeResult = formatString(stringList);
            System.out.println(new File(filePath).getName() + " = [" + timeResult + "]");
        }
    }

    public static List<String> getFilePath(String directory) {
        File directoryFile = new File(directory);
        List<String> filepathList = new ArrayList<String>();
        if (directoryFile.isDirectory()) {
            String[] childPath = directoryFile.list();
            for (String s : childPath) {
                filepathList.addAll(getFilePath(new File(directory,s).getAbsolutePath()));
            }
        } else {
            filepathList.add(directoryFile.getAbsolutePath());
        }
        return filepathList;
    }

    public static String readFileForString(String filePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }
            //System.out.println("String = [" + stringBuilder.toString() + "]");
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("filePath不存在");
            return "";
        } catch (IOException e) {
            System.out.println("文件读取异常");
            return "";
        }
    }

    public static List<String> getSubUtil(String soap, String rgex) {
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            String s = m.group(i);
            if (s.startsWith("0:")) {
                //System.out.println("ss = "+s);
                list.add(m.group(i));
            }
            i++;
        }
        return list;
    }

    private static String formatString(List<String> stringList) {
        int min = 0;
        int second = 0;
        int ms = 0;

        for (String s : stringList) {
            //System.out.println("args = [" + s + "]");
            String[] split = s.split(":");
            //分钟累加
            min += Integer.valueOf(split[1]);
            //秒部位
            String sec = split[2];
            //秒累加
            second += Integer.valueOf(sec.substring(0, 2));
            //四舍五入
            if (Integer.valueOf(sec.substring(sec.length() - 2, sec.length())) >= 50) {
                second += 1;
            }
            //毫秒累加
            //ms += Integer.valueOf(sec.substring(sec.length()-2,sec.length()));
            //System.out.println("min = [" + min + "]");
            //System.out.println("second = [" + second + "]");
            //System.out.println("ms = [" + ms + "]");
        }
        //毫秒转秒
        int secondTemp = ms / 60;
        //秒转分
        int minTemp = second / 60;
        //剩余的秒数
        int second2 = second % 60;
        //分转小时
        int hourTemp = (min + minTemp) / 60;
        //剩余的分数
        int min2 = (min + minTemp) % 60;
        String result = "总时间 = " + hourTemp + " 小时 " + min2 + " 分 " + second2 + "  秒";
        return result;
    }
}
