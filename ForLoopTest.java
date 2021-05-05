//Multiplication Table
import java.util.Scanner;
public class ForLoopTest {

    public static void main(String[] args) {
Scanner myObj = new Scanner(System.in);
System.out.println("Enter number");
       int num = myObj.nextInt();
System.out.println("now printing multiplication table of"+num);
        for(int i = 1; i <= 10; ++i)
        {
            System.out.printf("%d * %d = %d \n", num, i, num * i);
        }
System.out.println("table complete");
    }
}