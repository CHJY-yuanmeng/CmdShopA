import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojo.Product;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ReadProductExcel {

    public static void main(String[] args) throws ClassNotFoundException {
        ReadProductExcel re = new ReadProductExcel();
        InputStream in = Class.forName("Test").getResourceAsStream("/product.xlsx");//11，这里就是取Test的上级根为路径，变成了src路径
        // InputStream in = Class.forName("Test").getResourceAsStream("/product.xlsx");这个是流！读了一次就读完了，得重新获取in才可再用
        Product[] products = re.readExcel(in);
        for(Product pr:products){
            System.out.println(pr.getProductId());
        }
    }




    /*
    readExcel是什么方法？成员方法
     */
    public Product[] readExcel(InputStream in) {
        Product products[] = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            products = new Product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setProductId(this.getValue(cell));
                    } else if (k == 1) {
                        product.setProductName(this.getValue(cell));
                    } else if (k == 2) {
                        product.setProductPrice(Float.valueOf(this.getValue(cell)));
                    } else if (k == 3) {
                        product.setProductDsec(this.getValue(cell));
                    }
                    products[j - 1] = product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }


    Product getProductById(String Id,InputStream io){
        Product product = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(io);
            XSSFSheet xs = xw.getSheetAt(0);

            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                product = new Product();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setProductId(this.getValue(cell));
                    } else if (k == 1) {
                        product.setProductName(this.getValue(cell));
                    } else if (k == 2) {
                        product.setProductPrice(Float.valueOf(this.getValue(cell)));
                    } else if (k == 3) {
                        product.setProductDsec(this.getValue(cell));
                    }

                }
                if(Id.equals(product.getProductId())){
                    return  product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


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









