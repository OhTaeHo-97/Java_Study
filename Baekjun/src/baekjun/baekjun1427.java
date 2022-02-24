package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class baekjun1427 {
	public String descending(String input) {
		if(input.length() == 1) {
			return input;
		}
		ArrayList<Integer> input_int = new ArrayList<Integer>();
		for(int i = 0; i < input.length(); i++) {
			input_int.add(Integer.parseInt(input.substring(i, i + 1)));
		}
		
		Collections.sort(input_int, Collections.reverseOrder());
		String result = "";
		for(int i = 0; i < input_int.size(); i++) {
			result += Integer.toString(input_int.get(i));
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		baekjun1427 b = new baekjun1427();
		System.out.println(b.descending(input));
	}
}
