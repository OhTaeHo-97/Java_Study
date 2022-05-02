package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class baekjun2467 {
	public int[] getNearZero(int[] solution) {
		if(solution.length == 2) {
			Arrays.sort(solution);
			return new int[]{solution[0], solution[1]};
		}
		ArrayList<Integer> positive = new ArrayList<Integer>();
		ArrayList<Integer> negative = new ArrayList<Integer>();
		for(int i = 0; i < solution.length; i++) {
			if(solution[i] > 0) {
				positive.add(solution[i]);
			} else {
				negative.add(solution[i]);
			}
		}
		Collections.sort(positive);
		Collections.sort(negative, Collections.reverseOrder());
		int min = Integer.MAX_VALUE;
		if(positive.size() == 0) {
			return new int[]{positive.get(0), positive.get(1)};
		}
		if(negative.size() == 0) {
			return new int[]{negative.get(1), negative.get(0)};
		}
		int[] result = new int[2];
		if(positive.size() == 1) {
			min = Math.abs(negative.get(0) + negative.get(1));
			result[0] = negative.get(1);
			result[1] = negative.get(0);
			for(int i = 0; i < negative.size(); i++) {
				if(Math.abs(negative.get(i)) > positive.get(0) + min) {
					break;
				}
				int temp = positive.get(0) - min;
				if(temp > 0 && Math.abs(negative.get(i)) < temp) {
					continue;
				}
				if(min > Math.abs(negative.get(i) + positive.get(0))) {
					min = Math.abs(negative.get(i) + positive.get(0));
					result[0] = negative.get(i);
					result[1] = positive.get(0);
				}
			}
		} else if(negative.size() == 1) {
			min = positive.get(0) + positive.get(1);
			result[0] = positive.get(0);
			result[1] = positive.get(1);
			for(int i = 0; i < positive.size(); i++) {
				if(positive.get(i) > Math.abs(negative.get(0) - min)) {
					break;
				}
				int temp = negative.get(0) + min;
				if(temp < 0 && Math.abs(positive.get(i)) < Math.abs(temp)) {
					continue;
				}
				if(min > Math.abs(positive.get(i) + negative.get(0))) {
					min = Math.abs(positive.get(i) + negative.get(0));
					result[0] = negative.get(0);
					result[1] = positive.get(i);
				}
			}
		} else {
			if(positive.get(0) + positive.get(1) > Math.abs(negative.get(0) + negative.get(1))) {
				min = Math.abs(negative.get(0) + negative.get(1));
				result[0] = negative.get(1);
				result[1] = negative.get(0);
			} else {
				min = positive.get(0) + positive.get(1);
				result[0] = positive.get(0);
				result[1] = positive.get(1);
			}
			for(int i = 0; i < positive.size(); i++) {
				for(int j = 0; j < negative.size(); j++) {
					if(Math.abs(negative.get(j)) > positive.get(i) + min) {
						break;
					}
					int temp = positive.get(i) - min;
					if(temp > 0 && Math.abs(negative.get(j)) < temp) {
						continue;
					}
					if(min > Math.abs(positive.get(i) + negative.get(j))) {
						min = Math.abs(positive.get(i) + negative.get(j));
						result[0] = negative.get(j);
						result[1] = positive.get(i);
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] solution = new int[num];
		String[] input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < num; i++) {
			solution[i] = Integer.parseInt(input[i]);
		}
		baekjun2467 b = new baekjun2467();
		int[] result = b.getNearZero(solution);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
