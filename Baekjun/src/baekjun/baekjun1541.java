package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1541 {
	public int getMinResult(String expression) {
		StringTokenizer st = new StringTokenizer(expression, "+-");
		ArrayList<Integer> num = new ArrayList<Integer>();
		while(st.hasMoreTokens()) {
			num.add(Integer.parseInt(st.nextToken()));
		}
		ArrayList<String> operator = new ArrayList<String>();
		st = new StringTokenizer(expression, "0123456789");
		while(st.hasMoreTokens()) {
			operator.add(st.nextToken());
		}
		for(int i = 0; i < operator.size(); i++) {
			if(operator.get(i).equals("+")) {
				int temp = num.get(i) + num.get(i + 1);
				num.remove(i);
				num.remove(i);
				num.add(i, temp);
				operator.remove(i);
				i--;
			}
		}
		int result = num.get(0);
		for(int i = 1; i < num.size(); i++) {
			result -= num.get(i);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String expression = br.readLine();
		br.close();
		baekjun1541 b = new baekjun1541();
		bw.write(b.getMinResult(expression) + "\n");
		bw.flush();
		bw.close();
	}
}
