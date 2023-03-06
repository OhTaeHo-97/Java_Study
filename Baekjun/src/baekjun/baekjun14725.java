package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun14725 {
	static int N;
	static Node root;
	
	static void input() {
		Reader scanner = new Reader();
		
		N = scanner.nextInt();
		root = new Node();
		
		for(int idx = 0; idx < N; idx++) {
			int infoNum = scanner.nextInt();
			Node curNode = root;
			
			for(int infos = 0; infos < infoNum; infos++) {
				String info = scanner.next();
				
				if(!curNode.children.containsKey(info))
					curNode.children.put(info, new Node());
				curNode = curNode.children.get(info);
			}
		}
	}
	
	static void solution() {
		printAntTunnel(root, "");
	}
	
	static void printAntTunnel(Node root, String level) {
		Object[] keyArr = root.children.keySet().toArray();
		Arrays.sort(keyArr);
		
		for(Object key : keyArr) {
			System.out.println(level + key);
			printAntTunnel(root.children.get(key), level + "--");
		}
	}
	
	static class Node {
		HashMap<String, Node> children;
		
		public Node() {
			children = new HashMap<>();
		}
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Reader {
		BufferedReader br;
		StringTokenizer st;
		
		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		
		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
