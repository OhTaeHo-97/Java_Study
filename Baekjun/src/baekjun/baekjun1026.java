package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun1026 {
	public int makeMinMultiply(ArrayList<Integer> list_a, ArrayList<Integer> list_b) {
		Collections.sort(list_a);
		Collections.sort(list_b, Collections.reverseOrder());
		int result = 0;
		for(int i = 0; i < list_a.size(); i++) {
			result += list_a.get(i) * list_b.get(i);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		ArrayList<Integer> list_a = new ArrayList<Integer>();
		ArrayList<Integer> list_b = new ArrayList<Integer>();
		String a_nums = br.readLine();
		StringTokenizer st = new StringTokenizer(a_nums);
		while(st.hasMoreTokens()) {
			list_a.add(Integer.parseInt(st.nextToken()));
		}
		String b_nums = br.readLine();
		st = new StringTokenizer(b_nums);
		for(int i = 0; i < num; i++) {
			list_b.add(Integer.parseInt(st.nextToken()));
		}
		baekjun1026 b = new baekjun1026();
		System.out.println(b.makeMinMultiply(list_a, list_b));
	}
}
