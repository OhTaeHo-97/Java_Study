package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun20530 {
    static int nodeNum;
    static int queryNum;
    static int[][] queries;
    static int[] parents;
    static Map<Integer, List<Integer>> graph;
    static boolean[] isCycle;
    static boolean[] visited;
    static boolean[] finished;
    static int[] group;

    static void input() {
        Reader scanner = new Reader();

        nodeNum = scanner.nextInt();
        queryNum = scanner.nextInt();

        parents = new int[nodeNum + 1];
        graph = new HashMap<>();
        queries = new int[queryNum][2];
        isCycle = new boolean[nodeNum + 1];
        visited = new boolean[nodeNum + 1];
        finished = new boolean[nodeNum + 1];

        for(int node = 1; node <= nodeNum; node++) {
            parents[node] = node;
            graph.put(node, new ArrayList<>());
        }

        for(int edge = 0; edge < nodeNum; edge++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        for(int queryIdx = 0; queryIdx < queryNum; queryIdx++) {
            queries[queryIdx][0] = scanner.nextInt();
            queries[queryIdx][1] = scanner.nextInt();
        }
    }

    /*
        1. 주어진 연결 그래프에서 사이클을 찾는다
        2. 사이클에 포함되지 않는 노드들은 사이클에 포함되는 노드를 대표 노드로 하여 하나의 그룹들로 묶는다
        3. 같은 그룹에 속해있는지 아닌지에 따라 단순 경로의 수를 구한다
     */
    static void solution() {
        // 사이클을 찾는다
        findCycle(1, 0);

        // 사이클에 포함되지 않는 노드들은 사이클에 포함되는 노드를 대표 노드로 하여 하나의 그룹으로 묶는다
        group = new int[nodeNum + 1]; // 각 노드의 대표 노드를 저장할 배열
        visited = new boolean[nodeNum + 1]; // DFS 탐색 시에 방문체크를 진행할 배열
        // 모든 노드들 중 아직 방문하지 않았고 사이클에 포함되는 노드들에 대해서 DFS 탐색을 진행하여 하나의 그룹으로 노드들을 묶는ㄷ
        for(int node = 1; node <= nodeNum; node++) {
            if(!visited[node] && isCycle[node]) {
                findGroup(node, node);
            }
        }

        // 같은 그룹에 속하지 않은 노드들은 사이클에서 2개의 경로가 나올 수 있기 때문에 단순 경로의 수가 2가 된다
        // 그러나, 같은 그룹에 속한 노드들은 사이클에서 2개의 경로가 나올 수 없고, 1개의 경로만 나올 수 있기 때문에 단순 경로의 수가 1이 된다
        StringBuilder sb = new StringBuilder();
        for(int queryIdx = 0; queryIdx < queryNum; queryIdx++) {
            if(group[queries[queryIdx][0]] == group[queries[queryIdx][1]]) {
                sb.append(1).append('\n');
            } else {
                sb.append(2).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void findGroup(int node, int representativeNode) {
        visited[node] = true;
        // 현재 노드의 대표 노드를 설정한다
        group[node] = representativeNode;

        // 현재 노드에 연결된 다른 노드들은 같은 그룹에 속하는 것이기 때문에 연결된 다른 노드들도 같은 대표 노드로 설정한다
        for(int nextNode : graph.get(node)) {
            // 현재 대표 노드에 해당하는 사이클에 포함되는 노드는 이전에 대표 노드를 자기 자신으로 설정하였기 때문에 대표 노드를 설정할 필요가 없고
            // 다른 사이클에 포함되는 노드는 다른 그룹의 대표 노드이므로 사이클에 포함되는 노드는 제외시킨다
            // 또한 이미 방문했던 노드는 다시 방문할 필요가 없으니 마찬가지로 제외시킨다
            if(isCycle[nextNode] || visited[nextNode]) {
                continue;
            }
            findGroup(nextNode, representativeNode);
        }
    }

    static void findCycle(int node, int prev) {
        visited[node] = true; // 방문 체크
        // 현재 노드와 연결된 다른 노드들을 순회하며 사이클 여부를 판별한다
        for(int nextNode : graph.get(node)) {
            if(!visited[nextNode]) { // 아직 방문하지 않은 노드라면
                // 해당 노드의 부모 노드를 현재 노드로 설정하고 dfs를 통해 다음 노드를 확인한다
                // parents는 사이클을 체크할 때 사용된다
                parents[nextNode] = node;
                findCycle(nextNode, node);
            } else if(!finished[nextNode] && nextNode != prev) {
                // 아직 현재 노드의 탐색이 종료되지 않은 상황에서 다음 노드가 방문되어 있는 상황이고(즉, 이미 방문한 노드에 다시 방문하였고),
                // 이 노드가 바로 이전 노드가 아닐 경우,
                // 사이클을 체크한다
                //  - 아직 현재 노드의 탐색이 종료되지 않았는데 방문했던 노드를 다시 방문하는 경우, 사이클로 간주할 수 있다
                checkCycle(node, nextNode);
            }
        }

        // 현재 노드의 탐색이 종료되었음을 finished에 체크한다
        finished[node] = true;
    }

    static void checkCycle(int node, int root) {
        // 현재 노드가 사이클에 포함되는 것을 체크한다
        isCycle[node] = true;
        // 만약 두 번 방문한 노드로 다시 돌아왔다면, 사이클을 모두 체크한 것이니 return한다
        if(node == root) {
            return;
        }
        // 재귀를 통해 이전에 거쳐온 노드들을 탐색하며 사이클 체크를 진행한다
        checkCycle(parents[node], root);
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
    }
}
