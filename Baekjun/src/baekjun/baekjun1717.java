package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1717 {
	static int[] parents;
	public int find(int num) {
		if(parents[num] == num) {
			return num;
		}
		return parents[num] = find(parents[num]);
	}
	
	public void union(int n1, int n2) {
		int n1_position = find(n1);
		int n2_position = find(n2);
		if(n1_position != n2_position) {
			if(n1_position < n2_position) {
				parents[n2_position] = n1_position;
			} else {
				parents[n1_position] = n2_position;
			}
		}
	}
	
	public boolean isSameParent(int n1, int n2) {
		int n1_position = find(n1);
		int n2_position = find(n2);
		if(n1_position == n2_position) {
			return true;
		}
		return false;
	}
	
	public String[] getOperResult(String[] opers, int one_num) {
		for(int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		String[] result = new String[one_num];
		int idx = 0;
		for(int i = 0; i < opers.length; i++) {
			String[] oper = opers[i].split(" ");
			int oper_type = Integer.parseInt(oper[0]);
			int n1 = Integer.parseInt(oper[1]);
			int n2 = Integer.parseInt(oper[2]);
			if(oper_type == 0) {
				union(n1, n2);
			} else if(oper_type == 1) {
				if(isSameParent(n1, n2)) {
					result[idx] = "YES";
				} else {
					result[idx] = "NO";
				}
				idx++;
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int oper_num = Integer.parseInt(input[1]);
		parents = new int[n + 1];
		String[] opers = new String[oper_num];
		int one_num = 0;
		for(int i = 0; i < oper_num; i++) {
			opers[i] = br.readLine();
			if(opers[i].charAt(0) == '1')
				one_num++;
		}
		br.close();
		baekjun1717 b = new baekjun1717();
		String[] result = b.getOperResult(opers, one_num);
		for(String s : result) {
			bw.write(s + "\n");
		}
		bw.flush();
		bw.close();
	}
}
