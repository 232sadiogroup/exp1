import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    private static String[] regex = { // 正则表达式数组
            "(\\d+)",
            "昵称：(.*?)\\t",
            "所在地：(.*?)\\t",
            "性别：(.*?)\\t",
            "生日：(.*?)\\t",
            "公司：\\t(.*?)\\t",
            "教育信息\\t(.*?)\\t标签信息",
            "标签：\\t(.*?)(?:\\t\\t|\\t$)",
            "微博认证：(.*?)\\t注册时间",
            "(\\d+)关注",
            "(\\d+)粉丝",
            "(\\d+)微博\t",
            "当前等级：(.*?)经验值",
            "经验值：(\\d+)距离升级"
    };

    // 以行为单位循环正则提取
    private static String re(String str, String re) {
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            //System.out.println(matcher.group(1));
            return matcher.group(1);
        } else return null;
    }

    // 对提取的数据进行处理
    private static ArrayList<String> process(String str) {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            data.add(re(str, regex[i]));
        }

        // 换行符替换为空格
        for (int i = 6; i <= 7; i++) {
            String s = data.get(i);
            if (s != null)
                data.set(i, s.replace('\t', ' '));
        }

        // 微博认证处理
        if (data.get(8) == null)
            data.set(8, "否");
        else
            data.set(8, "是");
        return data;
    }

    public static void main(String[] args) throws IOException {
        String pathname = "src/data.txt";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line;
        line = br.readLine();
        //System.out.println(line);
        //process(line);
        excel exc = new excel();

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
