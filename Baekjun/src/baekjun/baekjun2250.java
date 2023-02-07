package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun2250 {
	static int N, maxLevel, col;
	static ArrayList<Node> tree;
	static int[] maxCols, minCols;
	
	static void input() {
		Reader scanner = new Reader();
		
		N = scanner.nextInt();
		col = 1;
		maxLevel = 0;
		
		tree = new ArrayList<>();
		maxCols = new int[N + 1];
		minCols = new int[N + 1];
		for(int node = 1; node <= N; node++) {			
			tree.add(new Node(node));
			maxCols[node] = Integer.MIN_VALUE;
			minCols[node] = Integer.MAX_VALUE;
		}
		
		for(int node = 1; node <= N; node++) {
			int nodeNum = scanner.nextInt(), left = scanner.nextInt(), right = scanner.nextInt();
			
			tree.get(nodeNum - 1).left = left;
			tree.get(nodeNum - 1).right = right;
			
			if(left != -1)
				tree.get(left - 1).parent = nodeNum;
			if(right != -1)
				tree.get(right - 1).parent = nodeNum;
		}
	}
	
	static void solution() {
		int root = findRoot(tree);
		
		inOrder(root, 1);
		
		int maxWidth = Integer.MIN_VALUE, answerLevel = -1;
		
		for(int level = 1; level <= maxLevel; level++) {
			int width = maxCols[level] - minCols[level] + 1;
			
			if(maxWidth < width) {
				maxWidth = width;
				answerLevel = level;
			}
		}
		
		System.out.println(answerLevel + " " + maxWidth);
	}
	
	static void inOrder(int root, int level) {
		Node rootNode = tree.get(root - 1);
		
		if(maxLevel < level)
			maxLevel = level;
		
		if(rootNode.left != -1)
			inOrder(rootNode.left, level + 1);
		
		minCols[level] = Math.min(minCols[level], col);
		maxCols[level] = col;
		col++;
		
		if(rootNode.right != -1)
			inOrder(rootNode.right, level + 1);
	}
	
	static int findRoot(ArrayList<Node> tree) {
		int root = -1;
		
		for(int node = 1; node <= N; node++) {
			if(tree.get(node - 1).parent == -1) {
				root = node;
				break;
			}
		}
		
		return root;
	}
	
	static class Node {
		int nodeNum, left, right, parent;
		
		public Node(int nodeNum) {
			this.nodeNum = nodeNum;
			left = right = parent = -1;
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
                } catch (IOException e) {
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
