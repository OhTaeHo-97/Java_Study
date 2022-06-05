package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun2981 {
	public int gcd(int a, int b) {
		if(b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	public ArrayList<Integer> getNum(int[] nums) {
		int[] dif = new int[nums.length - 1];
		for(int i = 0; i < nums.length - 1; i++) {
			dif[i] = Math.abs(nums[i] - nums[i + 1]);
		}
		int gcdNum = dif[0];
		for(int i = 1; i < dif.length; i++) {
			gcdNum = gcd(gcdNum, dif[i]);
		}
		ArrayList<Integer> divisor = new ArrayList<Integer>();
		for(int i = 2; i <= gcdNum; i++) {
			if(gcdNum % i == 0) {
				divisor.add(i);
			}
		}
		return divisor;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] nums = new int[num];
		for(int i = 0; i < num; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2981 b = new baekjun2981();
		ArrayList<Integer> divisor = b.getNum(nums);
		for(int i : divisor) {
			bw.write(i + " ");
		}
		bw.flush();
		bw.close();
	}
}
