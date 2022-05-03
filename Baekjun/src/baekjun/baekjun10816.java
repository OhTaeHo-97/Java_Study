package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun10816 {
	public int getHighBound(int[] card, int num) {
		int start = 0;
		int end = card.length;
		while(start < end) {
			int mid = (start + end) / 2;
			if(num < card[mid]) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
	
	public int getLowBound(int[] card, int num) {
		int start = 0;
		int end = card.length;
		while(start < end) {
			int mid = (start + end) / 2;
			if(num <= card[mid]) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
	
	public int[] getNumOfNums(int[] card, int[] nums) {
		int[] result = new int[nums.length];
		Arrays.sort(card);
		for(int i = 0; i < nums.length; i++) {
			result[i] = getHighBound(card, nums[i]) - getLowBound(card, nums[i]);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int card_num = Integer.parseInt(br.readLine());
		int[] card = new int[card_num];
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < card.length; i++) {
			card[i] = Integer.parseInt(input[i]);
		}
		int num = Integer.parseInt(br.readLine());
		int[] nums = new int[num];
		input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(input[i]);
		}
		baekjun10816 b = new baekjun10816();
		int[] result = b.getNumOfNums(card, nums);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
