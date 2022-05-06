package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class baekjun2866 {
	static int row, col;
	static String[][] table;
	
	public int getCount() {
		String[] str = new String[row];
		for(int i = 0; i < str.length; i++) {
			str[i] = "";
		}
		for(int i = 0; i < table.length; i++) {
			for(int j = 1; j < table[i].length; j++) {
				str[i] += table[i][j];
			}
		}
		int count = 0;
		for(int i = 0; i < table[0].length; i++) {
			HashSet<String> set = new HashSet<String>();
			boolean flag = true;
			for(int j = 0; j < str.length; j++) {
				if(!set.add(str[j])) {
					flag = false;
					break;
				}
			}
			if(!flag) {
				break;
			} else {
				count++;
				for(int j = 0; j < str.length; j++) {
					str[j] = str[j].substring(1, str[j].length());
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		col = Integer.parseInt(input[0]);
		row = Integer.parseInt(input[1]);
		table = new String[row][col];
		for(int i = 0; i < col; i++) {
			String temp = br.readLine();
			for(int j = 0; j < row; j++) {
				table[j][i] = temp.substring(j, j + 1);
			}
		}
		br.close();
		baekjun2866 b = new baekjun2866();
//		b.getCount();
		bw.write(b.getCount() + "\n");
		bw.flush();
		bw.close();
	}
}
