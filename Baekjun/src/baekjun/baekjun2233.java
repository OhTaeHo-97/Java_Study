package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2233 {
    static int nodeCount;
    static int treeHeight;
    static int firstRottenApple;
    static int secondRottenApple;
    static char[] status;
    static int[] depth;
    static int[] nodeIndexes;
    static int[][] parents;

    static void input() {
        Reader scanner = new Reader();

        nodeCount = scanner.nextInt();
        treeHeight = (int) Math.ceil(Math.log(nodeCount) / Math.log(2)) + 1;
        status = scanner.nextLine().toCharArray();
        firstRottenApple = scanner.nextInt();
        secondRottenApple = scanner.nextInt();
        depth = new int[nodeCount + 1];
        nodeIndexes = new int[nodeCount * 2 + 1];
        parents = new int[nodeCount + 1][treeHeight];
    }

    static void solution() {
        findEachStatus();
        fillParents();
        int lcaIdx = lca(nodeIndexes[firstRottenApple], nodeIndexes[secondRottenApple]);

        StringBuilder answer = new StringBuilder();
        for (int idx = 1; idx < 2 * nodeCount + 1; idx++) {
            if (nodeIndexes[idx] == lcaIdx) {
                answer.append(idx).append(' ');
            }
        }

        System.out.println(answer);
    }

    static void findEachStatus() {
        int nodeIdx = 1; // 노드 번호(지금까지 방문한 노드를 제외하고 그 다음 방문할 노드의 번호)
        int curNodeIdx = 0; // 현재 노드

        for (int statusIdx = 1; statusIdx <= status.length; statusIdx++) {
            if (status[statusIdx - 1] == '0') { // 새로운 노드를 방문할 때
                parents[nodeIdx][0] = curNodeIdx; // 현재 방문한 노드의 부모 노드 설정
                depth[nodeIdx] = depth[curNodeIdx] + 1; // 현재 방문한 노드의 깊이 설정
                nodeIndexes[statusIdx] = nodeIdx; // 이진수에서 statusIdx번째 수가 나타내는 노드를 현재 방문한 노드로 설정
                curNodeIdx = nodeIdx++; // 현재 노드를 현재 방문한 노드로 변경, nodeIdx 1 증가
            } else { // 모든 자식 노드를 방문하고 리턴할 때
                nodeIndexes[statusIdx] = curNodeIdx; // 이진수에서 statusIdx번째 수가 나타내는 노드를 현재 노드로 설정
                curNodeIdx = parents[curNodeIdx][0]; // 현재 노드를 현재 노드의 부모 노드로 변경(리턴했으므로 부모 노드로 이동)
            }
        }
    }

    static void fillParents() {
        // parents[nodeIdx][parentIdx] = nodeIdx번 노드의 2^{parentIdx}번째 부모
        //  - 이 parents 배열을 모두 설정
        for (int parentIdx = 1; parentIdx < treeHeight; parentIdx++) {
            for (int nodeIdx = 1; nodeIdx <= nodeCount; nodeIdx++) {
                parents[nodeIdx][parentIdx] = parents[parents[nodeIdx][parentIdx - 1]][parentIdx - 1];
            }
        }
    }

    static int lca(int node1, int node2) {
        if (depth[node1] > depth[node2]) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        // 두 노드의 깊이 일치시키기
        for (int idx = treeHeight - 1; idx >= 0; idx--) {
            if (Math.pow(2, idx) <= depth[node2] - depth[node1]) {
                node2 = parents[node2][idx];
            }
        }

        // 두 노드의 깊이를 일치시켰을 때 두 노드가 같다면 해당 위치를 자르면 되므로 해당 노드를 반환
        if (node1 == node2) {
            return node1;
        }

        // 두 노드의 깊이를 같이 올리면서 같은 노드까지 도달했을 때 해당 노드를 반환
        for (int idx = treeHeight - 1; idx >= 0; idx--) {
            if (parents[node1][idx] != parents[node2][idx]) {
                node1 = parents[node1][idx];
                node2 = parents[node2][idx];
            }
        }

        return parents[node1][0];
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
            while (st == null || !st.hasMoreElements()) {
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
