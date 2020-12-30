import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import pojo.User;
public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        String fileAbsolutePath = "D:\\Bdisk\\StardreamStudio\\CmdShopA\\src\\user.xlsx";//此路径不利于跨平台使用，改为下面的11
        ReadUserExcel re = new ReadUserExcel();
        InputStream in = Class.forName("Test").getResourceAsStream("/user.xlsx");//11，这里就是取Test的上级根为路径，变成了src路径
        User[] user = re.readExcel(in);
        int key = 0;
        int sum = 0;
        Scanner io = new Scanner(System.in);
        while (key == 0) {
            System.out.println("请输入ID");
            String ID = io.next();
            System.out.println("请输入密码：");
            String pw = io.next();
            for (int i = 0; i < user.length; i++) {
                if (ID.equals(user[i].getId()) && pw.equals(user[i].getUserPw())) {
                    key = 1;
                    break;
                }
            }
            if (key == 1) {
                System.out.println("登录成功！");
            } else {
                System.out.println("用户名或密码错误！");
            }
              sum++;
            if (sum == 3) {
                System.out.println("已登录错误三次！自动退出系统！");
                break;
            }
        }
    }
}
