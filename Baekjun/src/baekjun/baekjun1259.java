package baekjun;

import java.util.Scanner;

public class baekjun1259 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = -1;
        while(true) {
            num = sc.nextInt();
            if(num == 0)
                break;
            String n = Integer.toString(num);
            String reverse_n = "";
            for(int i = n.length(); i > 0; i--) {
                reverse_n += n.substring(i - 1, i);
            }
            if(n.equals(reverse_n))
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }
}
