package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class baekjun5639 {
	static Node head;
	static ArrayList<Integer> nodes;
	public void RearSearch(Node node) {
		if(node == null)
			return;
		RearSearch(node.left);
		RearSearch(node.right);
		System.out.println(node.value);
	}
	
	public void makeTree() {
		head = new Node(nodes.get(0));
		for(int i = 1; i < nodes.size(); i++) {
			head.insert(nodes.get(i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		nodes = new ArrayList<Integer>();
		while((line = br.readLine()) != null) {
			nodes.add(Integer.parseInt(line));
		}
		br.close();
		baekjun5639 b = new baekjun5639();
		b.makeTree();
		b.RearSearch(head);
	}
	
	public static class Node {
		Node left, right;
		int value;
		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
		void insert(int value) {
			if(value < this.value) {
				if(this.left == null) {
					this.left = new Node(value);
				} else {
					this.left.insert(value);
				}
			} else {
				if(this.right == null) {
					this.right = new Node(value);
				} else {
					this.right.insert(value);
				}
			}
		}
	}
}
