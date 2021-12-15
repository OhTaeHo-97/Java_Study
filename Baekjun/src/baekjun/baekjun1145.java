package baekjun;

import java.util.Scanner;
import java.util.Arrays;

public class baekjun1145 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[5];
        for(int i = 0; i < nums.length; i++){
            nums[i] = sc.nextInt();
            if(nums[i] < 1 || nums[i] > 100) {
                System.err.println("잘못된 숫자를 입력하셨습니다.");
                System.exit(1);
            }
        }
        Arrays.sort(nums);
        int result = nums[2];
        while(true) {
            int count = 0;
            for(int i = 0; i < nums.length; i++) {
                if(result % nums[i] == 0)
                    count++;
                if(count == 3)
                    break;
            }
            if(count == 3)
                break;
            result++;
        }
        
        System.out.println(result);
    }
}