package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1010 {
	public int combination(int n, int m) {
		int m_temp = m;
		long m_mul = 1;
		long n_mul = 1;
		for(int i = 1; i <= n; i++) {
			m_mul *= m_temp;
			n_mul *= i;
			m_temp--;
		}
		return (int)(m_mul / n_mul);
	}
	public int calculateNum(int n, int m) {
		if(n == m) {
			return 1;
		}
		if(n > (m / 2)) {
			int dif = m - n;
			return combination(dif, m);
		} else {
			return combination(n, m);
		}
	}
	
	public ArrayList<Integer> putBridge(ArrayList<ArrayList<Integer>> testcase) {
		ArrayList<Integer> numBridge = new ArrayList<Integer>();
		for(int i = 0; i < testcase.size(); i++) {
			numBridge.add(calculateNum(testcase.get(i).get(0), testcase.get(i).get(1)));
		}
		return numBridge;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> testcase = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < num; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			String site = br.readLine();
			StringTokenizer st = new StringTokenizer(site);
			temp.add(Integer.parseInt(st.nextToken()));
			temp.add(Integer.parseInt(st.nextToken()));
			testcase.add(temp);
		}
		baekjun1010 b = new baekjun1010();
		ArrayList<Integer> numBridge = b.putBridge(testcase);
		for(int i = 0; i < numBridge.size(); i++) {
			System.out.println(numBridge.get(i));
		}
	}
}