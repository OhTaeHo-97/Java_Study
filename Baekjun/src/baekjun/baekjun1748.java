package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1748 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		int result = 0;
		int cnt = 1;
		int length = 10;
		for(int i = 1; i <= num; i++) {
			if(i == length) {
				cnt++;
				length *= 10;
			}
			result += cnt;
		}
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
}
