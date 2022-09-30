package programmers;

import java.util.Arrays;

public class Level1_FindHighestAndLowestRank {
	public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
        int zeroNum = 0;
        if(zeroNum == 6) {
        	answer[0] = 1;
        	answer[1] = 6;
        } else {        	
        	for(int index = 0; index < 6; index++) {
        		if(lottos[index] == 0) zeroNum++;
        	}
        	int min = 0;
        	for(int index = 0; index < 6; index++) {
        		if(binarySearch(win_nums[index], lottos) != -1) min++;
        	}
        	int max = min + zeroNum;
        	answer[0] = rank[max];
        	answer[1] = rank[min];
        }
        return answer;
    }
	
	public static int binarySearch(int num, int[] lottos) {
		int L = 0, R = 5;
		while(L <= R) {
			int mid = (L + R) / 2;
			if(lottos[mid] == num) return mid;
			if(lottos[mid] < num) L = mid + 1;
			else R = mid - 1;
		}
		return -1;
	}
	
	public static void main(String[] args) {
//		int[] lottos = {44, 1, 0, 0, 31, 25};
//		int[] win_nums = {31, 10, 45, 1, 6, 19};
//		int[] lottos = {0, 0, 0, 0, 0, 0};
//		int[] win_nums = {38, 19, 20, 40, 15, 25};
		int[] lottos = {45, 4, 35, 20, 3, 9};
		int[] win_nums = {20, 9, 3, 45, 4, 35};
		int[] answer = solution(lottos, win_nums);
		for(int a : answer) System.out.println(a);
	}
}
