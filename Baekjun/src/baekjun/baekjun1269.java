package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class baekjun1269 {
	public int getNumSet(int num_a, int num_b, String list_a, String list_b) {
		ArrayList<Integer> component_a = new ArrayList<Integer>();
		ArrayList<Integer> component_b = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(list_a);
		for(int i = 0; i < num_a; i++) {
			component_a.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(list_b);
		for(int i = 0; i < num_b; i++) {
			component_b.add(Integer.parseInt(st.nextToken()));
		}
		TreeSet set = new TreeSet();
		for(int i = 0; i < component_a.size(); i++) {
			if(!set.add(component_a.get(i))) {
				set.remove(component_a.get(i));
			}
		}
		for(int i = 0; i < component_b.size(); i++) {
			if(!set.add(component_b.get(i))) {
				set.remove(component_b.get(i));
			}
		}
		
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
		br.close();
		baekjun1269 b = new baekjun1269();
		bw.write(b.getNumSet(num_a, num_b, list_a, list_b) + "\n");
		bw.flush();
		bw.close();
	}
}
