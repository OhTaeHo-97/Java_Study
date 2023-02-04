package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1525 {
	static final int SIZE = 3;
    static final String RESULT="123456780";
    static String info;
    static Map<String, Integer> map = new HashMap<>();
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int answer;

    static void input() {
        Reader scanner = new Reader();
        info = "";
        map = new HashMap<>();

        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++)
                info += scanner.next();
        }

        map.put(info, 0);
    }

    static void solution() {
        answer = Integer.MAX_VALUE;

        bfs(info);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void bfs(String info) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(info);

        while(!queue.isEmpty()) {
            String cur = queue.poll();
            int moveNum = map.get(cur);
            int emptyIdx = cur.indexOf('0');

            if(cur.equals(RESULT)) {
                answer = moveNum;
                return;
            }

            for(int dir = 0; dir < 4; dir++) {
                int cx = emptyIdx / SIZE + dx[dir], cy = emptyIdx % SIZE + dy[dir];

                if(isInMap(cx, cy)) {
                    int idx = cx * SIZE + cy;
                    char num = cur.charAt(idx);

                    String next = cur.replace(num, '9');
                    next = next.replace('0', num);
                    next = next.replace('9', '0');

                    if(!map.containsKey(next)) {
                        queue.offer(next);
                        map.put(next, moveNum + 1);
                    }
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < SIZE && y >= 0 && y < SIZE) return true;
        return false;
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
