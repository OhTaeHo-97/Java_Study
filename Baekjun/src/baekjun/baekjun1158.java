package baekjun;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class baekjun1158 {
	public ArrayList<Integer> Josephus(int n, int k) {
		ArrayList<Integer> circle = new ArrayList<Integer>();
		ArrayList<Integer> seq = new ArrayList<Integer>();
		for(int i = 1; i <= n; i++) {
			circle.add(i);
		}
		int index = k - 1;
		
		while(circle.size() > 1) {
			seq.add(circle.remove(index));
			index += (k - 1);
			while(index > (circle.size() - 1)) {
				index -= circle.size();
			}
		}
		seq.add(circle.get(0));
		return seq;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input_string = br.readLine();
		StringTokenizer st = new StringTokenizer(input_string);
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		baekjun1158 b = new baekjun1158();
		ArrayList<Integer> result = b.Josephus(n, k);
		System.out.print("<");
		for(int i : result) {
			System.out.print(i);
			if(i == result.get(result.size() - 1)) {
				break;
			}
			System.out.print(", ");
		}
		System.out.println(">");
	}
}
