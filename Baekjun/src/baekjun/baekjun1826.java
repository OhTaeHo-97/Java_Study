package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1826 {
	static int N;
    static PriorityQueue<Station> stations;
    static int endPosition, fuel;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        stations = new PriorityQueue<>();

        for(int idx = 0; idx < N; idx++) {
            int distance = scanner.nextInt(), fuel = scanner.nextInt();
            stations.offer(new Station(distance, fuel));
        }

        endPosition = scanner.nextInt();
        fuel = scanner.nextInt();
    }

    static void solution() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        // 현재 남아있는 연료량으로 갈 수 있는 주유소들 중에서 가장 많은 연료를 충전할 수 있는 주유소에서 연료를 충전한다
        // 처음에는 한 주유소에 도착했으면 해당 주유소까지의 주유소는 후보에서 제외시켜야한다고 생각했는데
        // 어차피 이전에 그 주유소를 들려서 온다고 생각하면 되기 때문에 굳이 후보에서 제외시킬 필요가 없다
        int answer = 0;
        while(fuel < endPosition) {
            while(!stations.isEmpty() && stations.peek().distance <= fuel) {
                queue.offer(stations.poll().fuel);
            }

            if(queue.isEmpty()) {
                System.out.println(-1);
                return;
            }

            answer++;
            fuel += queue.poll();
        }

        System.out.println(answer);
    }

    static class Station implements Comparable<Station> {
        int distance, fuel;

        public Station(int distance, int fuel) {
            this.distance = distance;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Station s) {
            if(distance != s.distance) return distance - s.distance;
            return s.fuel - fuel;
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
