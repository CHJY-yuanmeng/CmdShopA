import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class ReadExcel {
    /*
    readExcel是什么方法？成员方法
     */
    public User[] readExcel(File file) {
        User users[] = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet xs = xw.getSheetAt(0);
            users = new User[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                User user = new User();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        user.setId(this.getValue(cell));
                    } else if (k == 1) {
                        user.setUserPw(this.getValue(cell));
                    } else if (k == 2) {
                        user.setUserNmae(this.getValue(cell));
                    } else if (k == 3) {
                        user.setUserAddress(this.getValue(cell));
                    }
                    users[j - 1] = user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC://密码和电话bug
                DecimalFormat df=new DecimalFormat("#");
                value=df.format(cell.getNumericCellValue());
//                value = cell.getNumericCellValue() + "";
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}









