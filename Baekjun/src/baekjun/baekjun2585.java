package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2585 {
	static int n, k;
    static int[][] airfields;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        k = scanner.nextInt();
        airfields = new int[n + 1][2];
        airfields[0][0] = airfields[0][1] = 0;

        for(int airfield = 1; airfield <= n; airfield++) {
            int x = scanner.nextInt(), y = scanner.nextInt();

            airfields[airfield][0] = x;
            airfields[airfield][1] = y;
        }
    }

    static void solution() {
        binarySearch();
    }

    static void binarySearch() {
        int min = 0, max = 14143;
        int result = 0;

        while(min <= max) {
            int mid = (min + max) / 2;

            if(bfs(0, mid * 10)) {
                result = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(result);
    }

    static boolean bfs(int startIdx, int fuelAmount) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int landing = 0;

        queue.offer(startIdx);

        while(!queue.isEmpty()) {
            if(landing > k) return false;

            int size = queue.size();
            for(int count = 0; count < size; count++) {
                int cur = queue.poll();

                if(!visited[cur]) {
                    visited[cur] = true;

                    for(int idx = 1; idx <= n; idx++) {
                        double distance = Math.sqrt(Math.pow(airfields[startIdx][0] - airfields[idx][0], 2) + Math.pow(airfields[startIdx][1] - airfields[idx][1], 2));

                        if(distance <= fuelAmount) {
                            double distanceToDistance = Math.sqrt(Math.pow(10000 - airfields[idx][0], 2) + Math.pow(10000 - airfields[idx][1], 2));

                            if(distanceToDistance <= fuelAmount) return true;
                            queue.offer(idx);
                        }
                    }
                }
            }

            landing++;
        }

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
