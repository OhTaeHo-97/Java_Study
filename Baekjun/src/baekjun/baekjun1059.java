package baekjun;

import java.util.Arrays;
import java.util.Scanner;

public class baekjun1059 {
    public int goodRange(Integer[] elem, int n) {
		if(Arrays.asList(elem).contains(n)) {
			return 0;
		} else {
            if(n < elem[0]) {
                int total = 0;
                for(int i = 1; i < n; i++) {
                    for(int j = n; j < elem[0]; j++){
                        total++;
                    }
                }
                for(int i = n + 1; i < elem[0]; i++)
                    total++;
                
                return total;
            }
            if(n > elem[elem.length - 1]) {
                int total = 0;
                for(int i = elem[elem.length - 1] + 1; i < n; i++) {
                    for(int j = n; j <= 1000; j++){
                        total++;
                    }
                }
                for(int i = n + 1; i <= 1000; i++)
                    total++;
                
                return total;
            }
            
			int index = -1;
			for(int i = 0; i < elem.length; i++) {
				if(n < elem[i]) {
					index = i;
					break;
				}
			}
			
			int total = 0;
			
			for(int i = elem[index - 1] + 1; i <= n; i++) {
				if(i == n) {
					for(int j = n + 1; j < elem[index]; j++) {
						total++;
					}
				}
				else {
					for(int j = n; j < elem[index]; j++) {
						total++;
					}
				}
			}
			
			return total;
		}
	}
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Integer[] elem = new Integer[size];
        for(int i = 0; i < elem.length; i++) {
            elem[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        Arrays.sort(elem);
        
        baekjun1059 m = new baekjun1059();
        System.out.println(m.goodRange(elem, n));
    }
}