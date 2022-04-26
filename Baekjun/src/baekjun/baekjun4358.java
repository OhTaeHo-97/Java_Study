package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class baekjun4358 {
	static ArrayList<String> trees;
	
	public LinkedHashMap<String, Double> getPercentage() {
		Collections.sort(trees);
		LinkedHashMap<String, Double> treesRemoveDupl = new LinkedHashMap<String, Double>();
		for(int i = 0; i < trees.size(); i++) {
			if(!treesRemoveDupl.containsKey(trees.get(i))) {
				treesRemoveDupl.put(trees.get(i), (double)1);
			} else {
				treesRemoveDupl.put(trees.get(i), treesRemoveDupl.get(trees.get(i)) + 1);
			}
		}
		double sum = 0;
		for(String key : treesRemoveDupl.keySet()) {
			sum += treesRemoveDupl.get(key);
		}
		for(String key : treesRemoveDupl.keySet()) {
			treesRemoveDupl.put(key, (treesRemoveDupl.get(key) / sum) * 100);
		}
		return treesRemoveDupl;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;
		trees = new ArrayList<String>();
		while((input = br.readLine()) != null) {
			trees.add(input);
		}
		br.close();
		baekjun4358 b = new baekjun4358();
		LinkedHashMap<String, Double> percentage = b.getPercentage();
		for(String key : percentage.keySet()) {
			bw.write(key + " " + String.format("%.4f", percentage.get(key)) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
