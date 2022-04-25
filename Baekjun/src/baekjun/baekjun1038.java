package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class baekjun1038 {
	static ArrayList<Long> list;
	public void findDecreaseNum(long num, int idx) {
		if(idx > 10) {
			return;
		}
		list.add(num);
		for(int i = 0; i < num % 10; i++) {
			findDecreaseNum((num * 10) + i, idx + 1);
		}
	}
	
	public int getDecreasingNum(int num) {
		list = new ArrayList<>();
		if(num <= 10) {
			return num;
		}
		if(num > 1022) {
			return -1;
		}
		for(int i = 0; i < 10; i++) {
			findDecreaseNum(i, 1);
		}
		Collections.sort(list);
		return Integer.MAX_VALUE;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun1038 b = new baekjun1038();
		if(b.getDecreasingNum(num) == Integer.MAX_VALUE) {
			bw.write(list.get(num) + "\n");
		} else {
			bw.write(b.getDecreasingNum(num) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
