import java.sql.SQLOutput;
import java.util.Scanner;

public class LonginUi {
    public static void main(String[] args) {
        int select = 0;
        Scanner io = new Scanner(System.in);
        while (true) {
            switch (select) {
                case 0:
                    System.out.println("************");
                    break;
                case 1:
                    break;

                case 2:

                    break;
                case 3:

                    break;
                    default:
                        System.out.println("无此项选择！");
                        select=0;
            }
        }
    }
}
