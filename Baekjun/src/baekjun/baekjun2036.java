package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class baekjun2036 {
	public long getMaxPoint(ArrayList<Integer> positive, ArrayList<Integer> negative, ArrayList<Integer> zero) {
		Collections.sort(positive, Collections.reverseOrder());
		Collections.sort(negative);
		long point = 0;
		if(positive.size() != 0 && positive.size() % 2 != 0) {
			point += positive.get(positive.size() - 1);
			positive.remove(positive.size() - 1);
		}
		if(negative.size() != 0 && negative.size() % 2 != 0) {
			if(zero.size() == 0) {
				point += negative.get(negative.size() - 1);
			}
			negative.remove(negative.size() - 1);
		}
		for(int i = 0; i < positive.size(); i += 2) {
			if(positive.get(i) == 1 || positive.get(i + 1) == 1) {
				point += positive.get(i) + positive.get(i + 1);
			} else {
				point += (positive.get(i) * (long)positive.get(i + 1));
			}
		}
		for(int i = 0; i < negative.size(); i += 2) {
			point += (negative.get(i) * (long)negative.get(i + 1));
		}
		return point;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		ArrayList<Integer> positive = new ArrayList<Integer>();
		ArrayList<Integer> negative = new ArrayList<Integer>();
		ArrayList<Integer> zero = new ArrayList<Integer>();
		for(int i = 0; i < num; i++) {
			int temp = Integer.parseInt(br.readLine());
			if(temp > 0) {
				positive.add(temp);
			} else if(temp < 0) {
				negative.add(temp);
			} else {
				zero.add(temp);
			}
		}
		br.close();
		baekjun2036 b = new baekjun2036();
		bw.write(b.getMaxPoint(positive, negative, zero) + "\n");
		bw.flush();
		bw.close();
	}
}
