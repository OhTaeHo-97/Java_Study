package baekjun;

import java.util.Scanner;

public class baekjun1010 {
	static int calculate(int n, int m, int index) {
        int sum = 0;
        for(int i = m - n + 1 - index - 1; i > 0; i--) {
            sum += i;
        }
        int temp = sum;
        for(int i = m - n + 1 - index - 1; i > 0; i--) {
            temp -= i;
            sum += temp;
        }
        return sum;
    }
	
//	static long combination(int m, int n) {
//		long result = 1;
//		long divisor = 1;
//		for(int i = m; i > m - n; i--) {
//			result *= i;
//		}
//		System.out.println("result = " + result);
//		
//		for(int i = 1; i <= n; i++) {
//			divisor *= i;
//		}
//		System.out.println("divisor = " + divisor);
//		return result / divisor;
//	}
	
	static long fact(int m) {
        if(m == 0)
            return 1;
        return m * fact(m - 1);
    }
    static long combination(int m, int n) {
        return (fact(m) / (fact(n) * fact(m - n)));
    }
	
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int num = sc.nextInt();
//        for(int i = 0; i < num; i++) {
//            int n = sc.nextInt();
//            int m = sc.nextInt();
//            if(n < 0 || n >= 30 || m < 0 || m >= 30 || n > m) {
//                System.err.println("잘못된 숫자를 입력하셨습니다.");
//                System.exit(1);    
//            }
//            if(n == 1) {
//                System.out.println(m);
//            } else if(m == n) {
//                System.out.println("1");
//            } else {
//                int sum = 0;
//                for(int j = 1; j <= (m - n + 1); j++) {
//                    sum += calculate(n, m, j);
//                }
//                System.out.println(sum);
//            }
//        }
//    	System.out.println(combination(29, 13));
//    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        long[] result = new long[num];
        for(int i = 0; i < num; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            result[i] = combination(m, n);
        }
        
        for(int i = 0; i < num; i++) {
            System.out.println(result[i]);
        }
    }
}
