package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun11687 {
	public int getFacto(int num) {
		int five_num = 0;
		int result = 0;
		for(int i = 5; i <= num * 5; i += 5) {
			int temp = i;
			while(temp % 5 == 0) {
				five_num++;
				temp /= 5;
			}
			if(five_num == num) {
				result = i;
				break;
			}
			if(five_num > num) {
				result = -1;
				break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun11687 b = new baekjun11687();
		bw.write(b.getFacto(num) + "\n");
		bw.flush();
		bw.close();
	}
}
