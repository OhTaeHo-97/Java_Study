package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class baekjun1302 {
	public String getBestSeller(ArrayList<String> book_list) {
		Map<String, Integer> book_map = new HashMap<String, Integer>();
		for(int i = 0; i < book_list.size(); i++) {
			if(book_map.containsKey(book_list.get(i))) {
				book_map.put(book_list.get(i), book_map.get(book_list.get(i)) + 1);
			} else {
				book_map.put(book_list.get(i), 0);
			}
		}
		int max_num = 0;
		for(String i : book_map.keySet()) {
			if(max_num < book_map.get(i)) {
				max_num = book_map.get(i);
			}
		}
		ArrayList<String> bestSeller = new ArrayList<String>();
		for(String i : book_map.keySet()) {
			if(max_num == book_map.get(i)) {
				bestSeller.add(i);
			}
		}
		Collections.sort(bestSeller);
		return bestSeller.get(0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		ArrayList<String> book_list = new ArrayList<String>();
		for(int i = 0; i < num; i++) {
			book_list.add(br.readLine());
		}
		br.close();
		baekjun1302 b = new baekjun1302();
		bw.write(b.getBestSeller(book_list) + "\n");
		bw.flush();
		bw.close();
	}
}
