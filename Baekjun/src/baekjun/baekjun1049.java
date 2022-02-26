package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun1049 {
	public int getMinPrice(int n, ArrayList<Integer> pack, ArrayList<Integer> unit) {
		Collections.sort(pack);
		Collections.sort(unit);
		int minPrice = 0;
		if(pack.get(0) == 0 || unit.get(0) == 0) {
			return 0;
		}
		if(n == 1) {
			if(pack.get(0) > unit.get(0)) {
				minPrice = unit.get(0);
			} else {
				minPrice = pack.get(0);
			}
			return minPrice;
		}
		if(pack.get(0) > unit.get(0)) {
			int defer = pack.get(0) / unit.get(0);
			if(defer >= 6) {
				minPrice = n * unit.get(0);
			} else {
				int quote = n / 6;
				int remainder = n % 6;
				if(remainder > defer) {
					minPrice = (quote + 1) * pack.get(0);
				} else {
					minPrice = (quote * pack.get(0)) + (remainder * unit.get(0));
				}
			}
		} else {
			if(n % 6 == 0) {
				minPrice = pack.get(0) * (n / 6);
			} else {
				minPrice = pack.get(0) * (n / 6 + 1);
			}
		}
		return minPrice;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer> pack = new ArrayList<Integer>();
		ArrayList<Integer> unit = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			String price = br.readLine();
			st = new StringTokenizer(price);
			pack.add(Integer.parseInt(st.nextToken()));
			unit.add(Integer.parseInt(st.nextToken()));
		}
		baekjun1049 b = new baekjun1049();
		System.out.println(b.getMinPrice(n, pack, unit));
	}
}
