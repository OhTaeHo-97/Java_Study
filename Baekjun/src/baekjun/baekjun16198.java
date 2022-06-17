package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun16198 {
	static int max = Integer.MIN_VALUE;
	static int n;
	public void dfs(ArrayList<Integer> weights, int total) {
		if(weights.size() <= 2) {
			max = Math.max(max, total);
			return;
		}
		for(int i = 1; i < weights.size() - 1; i++) {
			int weight = weights.get(i);
			int energy = weights.get(i - 1) * weights.get(i + 1);
			weights.remove(i);
			dfs(weights, total + energy);
			weights.add(i, weight);
		}
	}
	
//	public void getMaxEnergy(int count, int energy, int index) {
//		if(count == n - 2) {
//			max = Math.max(max, energy);
//			return;
//		}
//		for(int i = 1; i <)
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> weights = new ArrayList<>();
		String[] input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < n; i++) {
			weights.add(Integer.parseInt(input[i]));
		}
		baekjun16198 b = new baekjun16198();
		b.dfs(weights, 0);
		bw.write(max + "\n");
		bw.flush();
		bw.close();
	}
}
