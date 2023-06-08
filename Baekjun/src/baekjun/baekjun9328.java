package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun9328 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int h, w;
    static char[][] map;
    static boolean[] hasKeys;
    static List<Loc>[] unopenedGates;

    static void input() {
        h = scanner.nextInt(); w = scanner.nextInt();

        init();

        for(int row = 1; row <= h; row++) {
            String info = scanner.nextLine();
            for(int col = 1; col <= w; col++)
                map[row][col] = info.charAt(col - 1);
        }

        String keyList = scanner.nextLine();
        if (keyList.charAt(0) != '0') {
            for(int idx = 0; idx < keyList.length(); idx++)
                hasKeys[keyList.charAt(idx) - 'a'] = true;
        }
    }

    static void init() {
        map = new char[h + 2][w + 2];
        for(int row = 0; row < map.length; row++)
            Arrays.fill(map[row], '.');

        hasKeys = new boolean[26];

        unopenedGates = new ArrayList[26];
        for(int idx = 0; idx < 26; idx++)
            unopenedGates[idx] = new ArrayList<>();
    }

    static void solution() {
        sb.append(bfs()).append('\n');
    }

    static int bfs() {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        int docNum = 0;

        Queue<Loc> queue = new LinkedList<>();
        boolean[][] visited = new boolean[h + 2][w + 2];

        queue.offer(new Loc(0, 0));
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();

            for(int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                if(isInMap(cx, cy)) {
                    if(map[cx][cy] != '*' && !visited[cx][cy]) {
                        char curStatus = map[cx][cy];

                        if (curStatus >= 'A' && curStatus <= 'Z') {
                            if(hasKeys[curStatus - 'A']) {
                                map[cx][cy] = '.';
                                visited[cx][cy] = true;
                                queue.offer(new Loc(cx, cy));
                            } else {
                                unopenedGates[curStatus - 'A'].add(new Loc(cx, cy));
                            }
                        } else if(curStatus >= 'a' && curStatus <= 'z') {
                            hasKeys[curStatus - 'a'] = true;
                            visited[cx][cy] = true;
                            queue.offer(new Loc(cx, cy));

                            for(int key = 0; key < 26; key++) {
                                if(unopenedGates[key].size() != 0 && hasKeys[key]) {
                                    for(int gate = 0; gate < unopenedGates[key].size(); gate++) {
                                        Loc temp = unopenedGates[key].get(gate);

                                        map[temp.x][temp.y] = '.';
                                        visited[temp.x][temp.y] = true;
                                        queue.offer(new Loc(temp.x, temp.y));
                                    }
                                }
                            }
                        } else if(curStatus == '$') {
                            docNum++;
                            visited[cx][cy] = true;
                            queue.offer(new Loc(cx, cy));
                        } else {
                            visited[cx][cy] = true;
                            queue.offer(new Loc(cx, cy));
                        }
                    }
                }
            }
        }

        return docNum;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < h + 2 && y >= 0 && y < w + 2;
    }

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();

        while(T-- > 0) {
            input();
            solution();
        }

        System.out.print(sb);
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
