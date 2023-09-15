import java.util.Scanner;

public class MaxDivisorsInRange {
    public static void main(String[] args) {
        int a, b,x=0;// 输入ab
        int n = 2, m = 1; // n：约数个数(1和他本身)，m:最大约数个数
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入a和b的值：");
        a = scanner.nextInt();
        b = scanner.nextInt();
        
        for (int i = a; i <= b; i++) {
            n = 2;
            for (int k = 2; k < i; k++) {
                if (i % k == 0) {
                    n++;
                }
                if (n > m) {
                    m = n;
                    x = i;
                }
            }
        }
        
        System.out.println(a + "与" + b + "之间约数个数最多的为：" + x + "，约数为" + m);
    }
}
