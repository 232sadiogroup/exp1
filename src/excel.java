import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class excel {

    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("微博");
    HSSFRow row = sheet.createRow(0);
    HSSFCellStyle style = wb.createCellStyle();
    HSSFCell cell;

    public excel(){
        style.setAlignment(HorizontalAlignment.CENTER);  //居中
        String[] headers = {"微博ID", "昵称", "所在地", "性别", "生日", "公司", "教育", "标签", "微博认证（是/否）", "关注数", "粉丝数", "微博数", "当前等级", "经验值"};
        for(int i = 0; i< headers.length; i++){
            cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
    }

    public void addRow(ArrayList<String> data){
        row = sheet.createRow(sheet.getLastRowNum()+1);
        for (int i = 0; i < data.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(data.get(i));
            cell.setCellStyle(style);
        }
    }

    public void output(){
        try {
            FileOutputStream fout = new FileOutputStream("src/excel.xls");
            wb.write(fout);
            fout.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel文件生成成功...");
    }

}
