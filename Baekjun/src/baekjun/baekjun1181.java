package baekjun;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class baekjun1181 {
    
    public String[] sort(int n, String[] words) {
    	if(n == 1) {
    		return words;
    	} else {
    		
    		Arrays.sort(words, new Comparator<String>() {
    			public int compare(String s1, String s2) {
    				if(s1.length() == s2.length()) {
    					return s1.compareTo(s2);    					
    				} else {
    					return s1.length() - s2.length();
    				}
    			}
    		});
//    		ArrayList<ArrayList<String>> sort_arr = new ArrayList<ArrayList<String>>();
//    		ArrayList<String> word_arr = new ArrayList<String>();
//    		ArrayList<String> sort_list = new ArrayList<String>();
//    		
//    		for(int i = 0; i < words.length - 1; i++) {
//    			if(i == words.length - 2) {
//    				if(words[i].length() == words[i + 1].length()) {
//    					word_arr.add(words[i]);
//    					word_arr.add(words[i + 1]);
//    					sort_arr.add(word_arr);
//    				} else {
//    					word_arr.add(words[i]);
//    					sort_arr.add(word_arr);
//    					word_arr = new ArrayList<String>();
//    					word_arr.add(words[i + 1]);
//    					sort_arr.add(word_arr);
//    				}
//    				break;
//    			}
//    			
//    			if(words[i].length() == words[i + 1].length()) {
//    				word_arr.add(words[i]);
//    			} else {
//    				word_arr.add(words[i]);
//    				sort_arr.add(word_arr);
//    				word_arr = new ArrayList<String>();
//    			}
//    		}
//    		
//    		
//    		for(int i = 0; i < sort_arr.size(); i++) {
//    			Collections.sort(sort_arr.get(i));
//    		}
//    		
//    		for(int i = 0; i < sort_arr.size(); i++) {
//    			sort_list.addAll(sort_arr.get(i));
//    		}
//    		
//    		for(int i = 0; i < sort_list.size(); i++) {
//    			result.add(sort_list.get(i));
//    		}
//    		
//    		System.out.println(result);
//    		
//    		int j = 0;
//    		
//    		for(int i = 0; i < sort_list.size() - 1; i++) {
//    			if(result.get(j).equals(result.get(i + 1))) {
//    				result.remove(i + 1);
//    			} else {
//    				j = i + 1;
//    			}
//    		}
    		return words;
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] words = new String[n];
		for(int i = 0; i < n; i++) {
			words[i] = br.readLine();
		}
		String[] result = new baekjun1181().sort(n, words);
		System.out.println(result[0]);
		for(int i = 1; i < result.length; i++) {
			if(!result[i - 1].equals(result[i])) {
				System.out.println(result[i]);
			}
		}
    }
}
