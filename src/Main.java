import org.apache.poi.hssf.record.HCenterRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.examples.AligningCells;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    static String[] regex = {
            "(\\d+)",
            "昵称：(.*?)\\t",
            "所在地：(.*?)\\t",
            "性别：(.*?)\\t",
            "生日：(.*?)\\t",
            "公司：\\t(.*?)\\t",
            "教育信息\\t(.*?)\\t标签信息",
            //"标签：\\t(.*?)(?:\\t\\t达人信息|\\t\\t基本信息|\\t\\t勋章信息|\\t$)",
            "标签：\\t(.*?)(?:\\t\\t|\\t$)",
            "微博认证：(.*?)\\t注册时间",
            "(\\d+)关注",
            "(\\d+)粉丝",
            "(\\d+)微博\t",
            "当前等级：(.*?)经验值",
            "经验值：(\\d+)距离升级"
    };

    private static String re(String str,String re){
        Pattern pattern = Pattern.compile(re);
        Matcher matcher=pattern.matcher(str);
        if(matcher.find()) {
            //System.out.println(matcher.group(1));
            return matcher.group(1);
        }
        else return null;
    }

    private static ArrayList<String> process(String str){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<14;i++){
            data.add(re(str,regex[i]));
        }
//        for(int i = 0; i < data.size(); i++){
//            System.out.print(data.get(i) + " ");
//        }
        for(int i=6;i<=7;i++){
            String s = data.get(i);
            if(s!=null)
                data.set(i,s.replace('\t',' '));
        }
//        String s = data.get(7);
//        System.out.println(s);
//        String s1 = "";
//        if(s!=null)
//            s1 = s.replace('\t',' ');
//        data.set(7,s1);
        if(data.get(8)==null)
            data.set(8,"否");
        else
            data.set(8,"是");
        return data;
    }

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello World!");
        String pathname = "src/data.txt";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line;
        line = br.readLine();
        //System.out.println(line);
        //process(line);
        excel exc = new excel();
        //line="3347552932\t136关注\t47粉丝\t1249微博\t\t136关注\t47粉丝\t1249微博\t\t勋章信息\t等级信息\tLv.14\t当前等级：Lv.14经验值：5362距离升级需经验值：368\t查看详情a\t\t基本信息\t昵称：李爸托马斯回旋\t所在地：上海杨浦区\t性别：男\t生日：20年1月1日\t简介：\t微博认证：Anika男朋友\t注册时间：\t2013-04-21\t标签信息\t标签：\t微博奇葩\t搞笑幽默\t游戏动漫\t投资理财\t星座命理\t军事\t\n";

        //exc.addRow(process(line));
        while (line != null) {
            //System.out.println(line);
            exc.addRow(process(line));
            //System.out.print("\n");
            line = br.readLine(); // 一次读入一行数据
        }
        exc.output();
        br.close();
        reader.close();
    }
}
