package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class baekjun1269 {
	public int getNumSet(int num_a, int num_b, String list_a, String list_b) {
		ArrayList<Integer> component_a = new ArrayList<Integer>();
		ArrayList<Integer> component_b = new ArrayList<Integer>();
		ArrayList<Integer> copy_a = new ArrayList<Integer>();
		ArrayList<Integer> copy_b = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(list_a);
		for(int i = 0; i < num_a; i++) {
			component_a.add(Integer.parseInt(st.nextToken()));
			copy_a.add(component_a.get(i));
		}
		st = new StringTokenizer(list_b);
		for(int i = 0; i < num_b; i++) {
			component_b.add(Integer.parseInt(st.nextToken()));
			copy_b.add(component_b.get(i));
		}
		copy_a.removeAll(component_b);
		copy_b.removeAll(component_a);
		copy_a.addAll(copy_b);
		HashSet<Integer> set = new HashSet<Integer>(copy_a);
		return set.size();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String nums = br.readLine();
		StringTokenizer st = new StringTokenizer(nums);
		int num_a = Integer.parseInt(st.nextToken());
		int num_b = Integer.parseInt(st.nextToken());
		String list_a = br.readLine();
		String list_b = br.readLine();
		baekjun1269 b = new baekjun1269();
		System.out.println(b.getNumSet(num_a, num_b, list_a, list_b));
	}
}
