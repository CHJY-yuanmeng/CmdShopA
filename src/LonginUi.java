import java.io.InputStream;
import java.util.Scanner;

import pojo.Product;
import pojo.User;

import static javafx.application.Platform.exit;

public class LonginUi {
    public static void main(String[] args) throws ClassNotFoundException {
        LonginUi ui = new LonginUi();
        int select = 0;
        String name;
        String pw;
        int key = 0;//表示还没有登录
        int exitNum = 0;
        Scanner io = new Scanner(System.in);
        while (exitNum == 0) {

            switch (select) {
                case 0:
                    System.out.println("*******菜单*****\n" +
                            "1:登录\n" +
                            "2:查询商品\n" +
                            "3：我的购物车\n" +
                            "4：退出系统\n" +
                            "************\n"
                    );
                    System.out.print("请输入选择：");

                    select = io.nextInt();
                    break;
                case 1:
//                    System.out.println("清输入用户名：");
//                    name = io.next();
//                    System.out.println("清输入密码：");
//                    pw = io.next();
                    select = ui.Login();

                    break;

                case 2:
                    select = ui.menu();
                    break;
                case 3:
                    if (key == 0) {
                        System.out.println("请先登录！");
                        break;
                    }
                    break;
                case 4:
                    System.out.print("退出系统！");
                    exitNum = 1;
                    break;
                case 5:
                    System.out.println("*******菜单*****\n" +
                            "1:个人信息\n" +
                            "2:查询商品\n" +
                            "3：我的购物车\n" +
                            "4：退出系统\n" +
                            "***************\n"
                    );
                    System.out.print("请输入选择：");
                    select = io.nextInt();
                    break;
                default:
                    System.out.println("无此项选择！");
                    if (key == 0)
                        select = 0;
                    else
                        select = 5;
            }
        }
    }

    public int Login() throws ClassNotFoundException {
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
                return 5;
            } else {
                System.out.println("用户名或密码错误！");
            }
            sum++;
            if (sum == 3) {
                System.out.println("已登录错误三次！自动退出系统！");
                break;
            }
        }
        //如果登录成功就返回5，失败就返回0
        return 0;
    }

    public int menu() throws ClassNotFoundException {
        ReadProductExcel re = new ReadProductExcel();
        InputStream in = Class.forName("Test").getResourceAsStream("/product.xlsx");//11，这里就是取Test的上级根为路径，变成了src路径
        Product[] products = re.readExcel(in);
        for (Product pr : products) {
            System.out.println(pr.toString());
        }

        return 5;
    }


}
