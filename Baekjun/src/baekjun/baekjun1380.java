package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1380 {
	public String getLostStudent(String[] student, ArrayList<Integer> list_num) {
		String result = "";
		int[] num_count = new int[student.length];
		for(int i = 0; i < list_num.size(); i++) {
			for(int j = 1; j <= student.length; j++) {
				if(list_num.get(i) == j) {
					num_count[j - 1]++;
					break;
				}
			}
		}
		for(int i = 0; i < num_count.length; i++) {
			if(num_count[i] != 2) {
				result = student[i];
				break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		ArrayList<String> result = new ArrayList<String>();
		baekjun1380 b = new baekjun1380();
		while(num != 0) {
			String[] student= new String[num];
			for(int i = 0; i < num; i++) {
				student[i] = br.readLine();
			}
			ArrayList<String> list_code = new ArrayList<String>();
			ArrayList<Integer> list_num = new ArrayList<Integer>();
			for(int i = 0; i < num * 2 - 1; i++) {
				String temp = br.readLine();
				StringTokenizer st = new StringTokenizer(temp);
				list_num.add(Integer.parseInt(st.nextToken()));
			}
			result.add(b.getLostStudent(student, list_num));
			num = Integer.parseInt(br.readLine());
		}
		for(int i = 0; i < result.size(); i++) {
			System.out.println((i + 1) + " " + result.get(i));
		}
	}
}