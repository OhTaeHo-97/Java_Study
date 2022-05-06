package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun11687_binarySearch {
	public int getFacto(int num) {
		int start = 1;
		int end = 500000000;
		int result = -1;
		while(start < end) {
			int count = 0;
			int mid = (start + end) / 2;
			for(int i = 5; i <= mid; i *= 5) {
				count += mid / i;
			}
			if(count >= num) {
				if(count == num) {
					result = mid;
				}
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun11687_binarySearch b = new baekjun11687_binarySearch();
		bw.write(b.getFacto(num) + "\n");
		bw.flush();
		bw.close();
	}
}
