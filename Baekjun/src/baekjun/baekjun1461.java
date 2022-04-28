package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class baekjun1461 {
	static int[] bookPlace;
	
	public int getMinWalkNum(int max_book) {
		if(bookPlace.length == 1) {
			return Math.abs(bookPlace[0]);
		}
		Arrays.sort(bookPlace);
		if(bookPlace.length <= max_book) {
			if(bookPlace[0] * bookPlace[bookPlace.length - 1] < 0) {
				if(Math.abs(bookPlace[0]) > bookPlace[bookPlace.length - 1]) {
					return Math.abs(bookPlace[0]) + bookPlace[bookPlace.length - 1] * 2;
				} else {
					return Math.abs(bookPlace[0]) * 2 + bookPlace[bookPlace.length - 1];
				}
			} else {
				return Math.abs(bookPlace[bookPlace.length - 1]);
			}
		}
		PriorityQueue<Integer> negative = new PriorityQueue<Integer>();
		PriorityQueue<Integer> positive = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i = 0; i < bookPlace.length; i++) {
			if(bookPlace[i] < 0) {
				negative.offer(bookPlace[i]);
			} else {
				positive.offer(bookPlace[i]);
			}
		}
		int max_distance = 0;
		if(negative.isEmpty()) {
			max_distance = positive.peek();
		} else if(positive.isEmpty()) {
			max_distance = Math.abs(negative.peek());
		} else {
			max_distance = Math.max(positive.peek(), Math.abs(negative.peek()));
		}
		int distance = 0;
		while(positive.size() > 0) {
			int temp = positive.peek();
			for(int i = 0; i < max_book; i++) {
				positive.poll();
				if(positive.isEmpty()) {
					break;
				}
			}
			distance += (temp * 2);
		}
		while(negative.size() > 0) {
			int temp = negative.peek();
			for(int i = 0; i < max_book; i++) {
				negative.poll();
				if(negative.isEmpty()) {
					break;
				}
			}
			distance += (Math.abs(temp) * 2);
		}
		distance -= max_distance;
		return distance;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int book_num = Integer.parseInt(input[0]);
		int max_book = Integer.parseInt(input[1]);
		bookPlace = new int[book_num];
		input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < book_num; i++) {
			bookPlace[i] = Integer.parseInt(input[i]);
		}
		baekjun1461 b = new baekjun1461();
		bw.write(b.getMinWalkNum(max_book) + "\n");
		bw.flush();
		bw.close();
	}
}
