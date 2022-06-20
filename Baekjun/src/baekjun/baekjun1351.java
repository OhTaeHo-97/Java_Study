package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class baekjun1351 {
	HashMap<Long, Long> map;
	public long getNthNum(long num, int p, int q) {
		if(num == 0)
			return 1;
		if(map.containsKey(num))
			return map.get(num);
		long left = num / p;
		long right = num / q;
		map.put(num, getNthNum(left, p, q) + getNthNum(right, p, q));
		return map.get(num);
	}
	
	public long getInfiniteSequence(long n, int p, int q) {
		map = new HashMap<>();
		return getNthNum(n, p, q);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		br.close();
		long n = Long.parseLong(input[0]);
		int p = Integer.parseInt(input[1]);
		int q = Integer.parseInt(input[2]);
		baekjun1351 b = new baekjun1351();
		bw.write(b.getInfiniteSequence(n, p, q) + "\n");
		bw.flush();
		bw.close();
	}
}
