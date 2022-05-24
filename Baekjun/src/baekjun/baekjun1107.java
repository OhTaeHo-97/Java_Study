package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun1107 {
	// 방법 1
//	public int getMinNum(int channel, ArrayList<Integer> broken_nums) {
//		if(channel == 100) {
//			return 0;
//		}
//		if(broken_nums.size() == 0) {
//			int min = Math.abs(channel - 100);
//			return Math.min(min, Integer.toString(channel).length());
//		}
//		int min = Math.abs(channel - 100);
//		if(broken_nums.size() == 10) {
//			return min;
//		}
//		int prev_channel = channel + 1;
//		int next_channel = channel - 1;
//		int near_channel = channel;
//		while(true) {
//			if(prev_channel > 0) {
//				prev_channel -= 1;
//				String num = Integer.toString(prev_channel);
//				boolean canChannel = true;
//				for(int i = 0; i < num.length(); i++) {
//					char n = num.charAt(i);
//					if(broken_nums.contains(n - '0')) {
//						canChannel = false;
//						break;
//					}
//				}
//				if(canChannel) {
//					near_channel = prev_channel;
//					break;
//				}
//			}
//			next_channel += 1;
//			String num = Integer.toString(next_channel);
//			boolean canChannel = true;
//			for(int i = 0; i < num.length(); i++) {
//				char n = num.charAt(i);
//				if(broken_nums.contains(n - '0')) {
//					canChannel = false;
//					break;
//				}
//			}
//			if(canChannel) {
//				near_channel = next_channel;
//				break;
//			}
//		}
//		int count = Integer.toString(near_channel).length();
//		count += Math.abs(near_channel - channel);
//		return Math.min(min, count);
//	}
	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		int channel = Integer.parseInt(br.readLine());
//		int num = Integer.parseInt(br.readLine());
//		ArrayList<Integer> broken_nums = new ArrayList<Integer>();
//		if(num != 0) {			
//			String[] input = br.readLine().split(" ");
//			br.close();
//			for(int i = 0; i < num; i++) {
//				broken_nums.add(Integer.parseInt(input[i]));
//			}
//		}
//		baekjun1107 b = new baekjun1107();
//		bw.write(b.getMinNum(channel, broken_nums) + "\n");
//		bw.flush();
//		bw.close();
//	}
	
	// 방법 2
	public int getMinNum(int channel, boolean[] broken) {
		int min = Math.abs(channel - 100);
		for(int i = 0; i <= 999999; i++) { // 원하는 채널의 최댓값이 500000이므로 최댓값은 9를 제외한 모든 버튼이 고장나 999999일 때
			String num = String.valueOf(i);
			int len = num.length();
			boolean isBreak = false;
			for(int j = 0; j < len; j++) {
				if(broken[num.charAt(j) - '0']) {
					isBreak = true;
					break;
				}
			}
			if(!isBreak) {
				int count = Math.abs(channel - i) + len;
				min = Math.min(min, count);
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int channel = Integer.parseInt(br.readLine());
		int num = Integer.parseInt(br.readLine());
		boolean[] broken = new boolean[10];
		if(num != 0) {			
			String[] input = br.readLine().split(" ");
			br.close();
			for(int i = 0; i < num; i++) {
				broken[Integer.parseInt(input[i])] = true;
			}
		}
		baekjun1107 b = new baekjun1107();
		bw.write(b.getMinNum(channel, broken) + "\n");
		bw.flush();
		bw.close();
	}
}
