package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun1141 {
	public int getPrefixSet(ArrayList<String> words) {
		for(int i = 0; i < words.size(); i++) {
			for(int j = 0; j < words.size(); j++) {
				if(i == j) {
					continue;
				}
				if(words.get(i).length() > words.get(j).length()) {
					if(words.get(i).indexOf(words.get(j)) == 0) {
						words.remove(j);
						if(i > j) {
							i--;
							j--;
						} else {
							j--;
						}
					}
				} else {
					if(words.get(j).indexOf(words.get(i)) == 0) {
						words.remove(i);
						i--;
						break;
					}
				}
			}
		}
		return words.size();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		ArrayList<String> words = new ArrayList<String>();
		for(int i = 0; i < num; i++) {
			words.add(br.readLine());
		}
		br.close();
		baekjun1141 b = new baekjun1141();
        bw.write(b.getPrefixSet(words) + "\n");
		bw.flush();
        bw.close();
	}
}
