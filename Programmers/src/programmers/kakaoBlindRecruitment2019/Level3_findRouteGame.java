package programmers.kakaoBlindRecruitment2019;

import java.util.*;

public class Level3_findRouteGame {
	// 카카오 프렌즈 두 팀으로 나눔 -> 각 팀이 같은 곳을 다른 순서로 방문하도록 해서 먼저 순회를 마친 팀이 승리
	// 방문할 곳의 2차원 좌표 값을 구하고 각 장소를 이진트리의 노드가 되도록 구성한 후,
	// 순회 방법을 힌트로 주어 각 팀이 스스로 경로를 찾도록 할 계획
	// 트리 노드들 구성 방법
	// 1. 트리를 구성하는 모든 노드의 x, y 좌표 값은 정수
	// 2. 모든 노드는 서로 다른 x값 가짐
	// 3. 같은 level에 있는 노드는 같은 y좌표를 가짐
	// 4. 자식 노드의 y값은 항상 부모 노드보다 작음
	// 5. 임의의 노드 V의 왼쪽 서브 트리에 있는 모든 노드의 x값은 V의 x값보다 작다
	// 6. 임의의 노드 V의 오른쪽 서브 트리에 있는 모든 노드의 x값은 V의 x값보다 크다
	// 전위 순회, 후위 순회한 결과를 배열에 담아 return
	
	// 1 <= nodeinfo 길이 <= 10,000
	// nodeinfo[i]는 i + 1번 노드의 좌표 -> [x축 좌표, y축 좌표]
	// 0 <= 모든 노드의 좌표값 <= 100,000
	// 트리의 깊이 <= 1,000
	
	static int idx;
	static int[][] result;
	public static int[][] solution(int[][] nodeinfo) {
		Node[] nodes = new Node[nodeinfo.length];
		for(int index = 0; index < nodeinfo.length; index++)
			nodes[index] = new Node(nodeinfo[index][0], nodeinfo[index][1], index + 1, null, null);
		Arrays.sort(nodes, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if(n1.y != n2.y) return n2.y - n1.y;
				else return n1.x - n2.x;
			}
		});
		
		Node root = nodes[0];
		for(int index = 1; index < nodes.length; index++)
			insertNode(root, nodes[index]);
		result = new int[2][nodes.length];
		idx = 0;
		preorder(root);
		idx = 0;
		postorder(root);
		return result;
	}
	
	static void insertNode(Node parent, Node child) {
		if(parent.x > child.x) {
			if(parent.left == null) parent.left = child;
			else insertNode(parent.left, child);
		} else {
			if(parent.right == null) parent.right = child;
			else insertNode(parent.right, child);
		}
	}
	
	static void preorder(Node root) {
		if(root != null) {
			result[0][idx++] = root.idx;
			preorder(root.left);
			preorder(root.right);
		}
	}
	
	static void postorder(Node root) {
		if(root != null) {
			postorder(root.left);
			postorder(root.right);
			result[1][idx++] = root.idx;
		}
	}
	
	static class Node {
		int x, y, idx;
		Node left, right;
		public Node(int x, int y, int idx, Node left, Node right) {
			this.x = x;
			this.y = y;
			this.idx = idx;
			this.left = left;
			this.right = right;
		}
	}
	
	public static void main(String[] args) {
		int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		int[][] answer = solution(nodeinfo);
		for(int i = 0; i < answer.length; i++) {
			for(int j = 0; j < answer[i].length; j++) System.out.print(answer[i][j] + " ");
			System.out.println();
		}
	}
}
