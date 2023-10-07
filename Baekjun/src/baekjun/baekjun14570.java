package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14570 {
    static int nodeNum;
    // 각 노드에서 왼쪽 자식, 오른쪽 자식을 나타내는 2차원 배열
    // tree[node][0] = node번째 노드의 왼쪽 자식, tree[node][1] = node번째 노드의 오른쪽 자식
    static int[][] tree;
    static long target;

    static void input() {
        Reader scanner = new Reader();

        nodeNum = scanner.nextInt();
        tree = new int[nodeNum + 1][2];

        for(int node = 1; node <= nodeNum; node++) {
            int leftChild = scanner.nextInt();
            int rightChild = scanner.nextInt();

            tree[node][0] = leftChild;
            tree[node][1] = rightChild;
        }

        target = scanner.nextLong();
    }

    static void solution() {
        dfs(1, target);
    }

    /*
        target번째 구슬이 어느 노드에서 멈출지 구하고자 할 때,

        루트 노드를 기준으로 바라보면 왼쪽 서브트리에 담긴 모든 구슬의 수가 오른쪽 서브트리에 담긴 모든 구슬의 수보다 작거나 같을 경우 왼쪽 자식 노드로, 그 외에는 오른쪽 자식 노드로 떨어지기 때문에
        target이 홀수일 때는 왼쪽 자식 노드로, 짝수일 때는 오른쪽 자식 노드로 떨어지게 된다
            - 1번째 구슬은 왼쪽, 오른쪽 서브트리 모두 구슬의 수가 0이기 때문에 같아서 왼쪽으로 떨어진다
            - 2번째 구슬은 왼쪽 서브트리에 1개, 오른쪽 서브트리에 0개 있기 때문에 오른쪽으로 떨어진다
            - 3번째 구슬은 왼쪽, 오른쪽 서브트리의 구슬의 수가 모두 1이기 때문에 왼쪽으로 떨어진다
            - 위와 같이 왼쪽으로 먼저 떨어지고 다음에 오른쪽으로 떨어지고를 반복한다

        그 아래 노드를 생각해보면
            - 1번째 구슬은 루트 노드에서는 왼쪽 자식 노드로 떨어지게 되고, 2번 노드에서는 왼쪽, 오른쪽 서브트리 모두 구슬의 수가 0이기 때문에 같아서 왼쪽 자식 노드로 떨어진다
            - 2번째 구슬은 루트 노드에서는 오른쪽 자식 노드로 떨어지게 되고, 3번 노드에서는 왼쪽, 오른쪽 서브트리 모두 구슬의 수가 0이기 때문에 같아서 왼쪽 자식 노드로 떨어진다
            - 3번째 구슬은 루트 노드에서는 왼쪽 자식 노드로 떨어지게 되고, 2번 노드에서는 왼쪽 서브트리에 1개, 오른쪽 서버트리에 0개 있기 때문에 오른쪽 자식 노드로 떨어진다
            - 4번째 구슬은 루트 노드에서는 오른쪽 자식 노드로 떨어지게 되고, 3번 노드에서는 왼쪽 서브트리에 1개, 오른쪽 서브트리에 0개 있기 때문에 오른쪽 자식 노드로 떨어진다
        -> 즉, 홀수여서 왼쪽 노드로 이동한 경우에는 target = target / 2 + 1로, 오른쪽 노드로 이동한 경우에는 target = target / 2로 target을 변경해주면 된다

        위와 같은 방식을 토대로 재귀를 통해 target번째 구슬이 어느 노드에서 멈출지 구한다
     */
    static void dfs(int curNode, long target) {
        int leftChild = tree[curNode][0];
        int rightChild = tree[curNode][1];

        // 만약 리프 노드라면 해당 노드에 떨어질 것이기 때문에 해당 노드를 출력하고 프로그램을 끝낸다
        if(leftChild == -1 && rightChild == -1) {
            System.out.println(curNode);
            System.exit(0);
        } else if(leftChild == -1 && rightChild != -1) { // 오른쪽 자식만 있다면
            // 오른쪽 자식으로 이동한다
            dfs(rightChild, target);
        } else if(leftChild != -1 && rightChild == -1) { // 왼쪽 자식만 있다면
            // 왼쪽 자식으로 이동한다
            dfs(leftChild, target);
        } else { // 왼쪽, 오른쪽 자식 모두 있다면
            // target 값에 따라 왼쪽으로 갈지, 오른쪽으로 갈지 정하고
            // 이동하면서 target 값을 변경해준다
            if(target % 2 == 0) {
                dfs(rightChild, target / 2);
            } else {
                dfs(leftChild, target / 2 + 1);
            }
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
