import java.io.InputStream;
import java.util.Scanner;

import pojo.Product;
import pojo.User;

import static javafx.application.Platform.exit;

public class LonginUi {
    static int key = 0;//表示还没有登录
    Product productBuy[] = new Product[10];//购物车大小
    int count = 0;//购物车已经装下的物品数量

    public static void main(String[] args) throws ClassNotFoundException {
        LonginUi ui = new LonginUi();
        int select = 0;
        String name;
        String pw;

        int exitNum = 0;
        Scanner io = new Scanner(System.in);
        while (exitNum == 0) {

            switch (select) {
                case 0:
                    System.out.println("*******菜单******\n" +
                            "*1:登录         *\n" +
                            "*2:查询商品     *\n" +
                            "*3:我的购物车   *\n" +
                            "*4:退出系统     *\n" +
                            "****************"
                    );
                    key=0;
                    System.out.print("请输入选择：");

                    select = io.nextInt();
                    break;
                case 1:
//                    System.out.println("清输入用户名：");
//                    name = io.next();


//                    pw = io.next();
                    select = ui.Login();

                    break;

                case 2:
                    select = ui.menu();
                    System.out.println("是否继续购买？是输入1，否输入0");
                    System.out.print("请输入选择：");
                    int se = io.nextInt();
                    if (se != 1) {
                        select = 5;
                    }
                    break;
                case 3:
                    if (key == 0) {
                        System.out.println("请先登录！");
                        select = 1;
                        break;
                    } else {
                        ui.myProduct();
                        select = 5;
                    }
                    break;
                case 4:
                    System.out.print("退出系统！");
                    exitNum = 1;
                    break;
                case 5:
                    System.out.println("*******菜单******\n" +
                            "*1:切换账号     *\n" +
                            "*2:查询商品     *\n" +
                            "*3:我的购物车   *\n" +
                            "*4:退出系统     *\n" +
                            "****************"
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
        InputStream in = null;
         in =Class.forName("Test").getResourceAsStream("/user.xlsx");//11，这里就是取Test的上级根为路径，变成了src路径
        User[] user = re.readExcel(in);

        int sum = 0;
        Scanner io = new Scanner(System.in);
        while (key == 0) {
            System.out.print("请输入ID：");
            String ID = io.next();
            System.out.print("请输入密码：");
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
//        Product productBuy[] = new Product[10];//购物车大小
//        int count = 0;//购物车已经装下的物品数量
        InputStream in =null;
         in =Class.forName("Test").getResourceAsStream("/product.xlsx");//11，这里就是取Test的上级根为路径，变成了src路径
        Product[] products = re.readExcel(in);
        for (Product pr : products) {
            System.out.println(pr.toString());
        }
        //至此，in读取到了结尾！
        in = null;
        in = Class.forName("Test").getResourceAsStream("/product.xlsx");//11，这里就是取Test的上级根为路径，变成了src路径
        if (key == 1) {
            System.out.print("输入商品ID可以添加商品到购物车：");
            Scanner ioProductId = new Scanner(System.in);
            String id = ioProductId.next();
            Product selectProduct = re.getProductById(id, in);
            if (selectProduct == null) {
                System.out.println("编号为" + id + "的商品不存在！");
            } else {
                if (count < 10) {
                    productBuy[count++] = selectProduct;
                    System.out.println("ID:" + selectProduct.getProductId() + "  名称" + selectProduct.getProductName() + "  加入购物车成功！");
                    return 2;
                } else {
                    System.out.println("购物车已满！");
                }
            }

        } else {
            System.out.println("请先登录");
        }


        return 5;
    }

    public int myProduct() {
        float sum = 0;
        System.out.println("\n我的购物车：");
        for (int i = 0; i < count; i++) {
            System.out.println(productBuy[i].toString());
            sum += productBuy[i].getProductPrice();
        }
        System.out.println("商品总价是：" + sum + "\n");
        return 5;
    }
}
