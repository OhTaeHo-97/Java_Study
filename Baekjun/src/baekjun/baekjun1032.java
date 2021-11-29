package baekjun;
import java.util.Scanner;
import java.util.StringTokenizer;

public class baekjun1032 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if(num < 1 || num > 50) {
            System.err.println("잘못된 숫자를 입력하셨습니다.");
            System.exit(0);
        }
        
        String answer = "";
        String[] names = new String[num];
        for(int i = 0; i < names.length; i++) 
            names[i] = sc.next();
        
        for(int i = 0; i < names[0].length(); i++) {
        	boolean flag = true;
        	for(int j = 1; j < names.length; j++) {
        		if(names[0].charAt(i) != names[j].charAt(i)) {
        			flag = false;
        			break;
        		}
        	}
        	if(flag)
        		answer += names[0].substring(i, i + 1);
        	else
        		answer += "?";
        }
        
        System.out.println(answer);
    }
}