package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun4716 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int N;
    static int[] remainingBalloons;
    static PriorityQueue<Team> teams;

    static void input() {
        N = scanner.nextInt();
        remainingBalloons = new int[2];
        remainingBalloons[0] = scanner.nextInt();
        remainingBalloons[1] = scanner.nextInt();

        if(N == 0 && remainingBalloons[0] == 0 && remainingBalloons[1] == 0) {
            System.out.print(sb);
            System.exit(0);
        }

        teams = new PriorityQueue<>();
        for(int team = 0; team < N; team++) {
            int balloonNum = scanner.nextInt(), distA = scanner.nextInt(), distB = scanner.nextInt();
            teams.offer(new Team(balloonNum, distA, distB));
        }
    }

    // 두 거리 사이의 차이에 대해 내림차순으로 정렬한 후에 거리가 가까운 곳에서 최대한 많이 풍선을 가지고 오고
    // 만약 가까운 곳에서 모두 가져올 수 없다면 남은 개수만큼 먼 곳에서 가져온다
    // 위와 같이 정렬한 이유는
    //  - 한 팀에 대해 A, B까지의 거리는 정해져있으므로 결국 두 거리 사이의 차이가 의미있는 값이 된다
    //  - 두 거리 사이의 차이가 크다는 것은 그만큼 먼 곳에서 가져오는 풍선을 줄여야한다는 뜻이 되므로
    //  - 두 거리 사이의 차이가 큰 팀은 최대한 가까운 곳에서 풍선을 많이 가져와야 하니 거리 차이가 큰 팀부터 가까운 곳에서 풍선을 가져간다
    static void solution() {
        int totalDist = 0;
        while(!teams.isEmpty()) {
            Team team = teams.poll();
            char near = ' ';
            int nearDist = 0, farDist = 0;
            if(team.distA > team.distB) {
                near = 'B';
                nearDist = team.distB;
                farDist = team.distA;
            }
            else if(team.distA < team.distB) {
                near = 'A';
                nearDist = team.distA;
                farDist = team.distB;
            }

            int balloonNum = team.balloonNum;
            if(near == ' ') {
                totalDist += balloonNum * team.distA;
            } else {
                if(remainingBalloons[near - 'A'] < balloonNum) {
                    totalDist += nearDist * remainingBalloons[near - 'A'];
                    balloonNum -= remainingBalloons[near - 'A'];
                    remainingBalloons[near - 'A'] = 0;
                    totalDist += farDist * balloonNum;
                    int idx = near - 'A' == 0 ? 1 : 0;
                    remainingBalloons[idx] -= balloonNum;
                } else {
                    totalDist += nearDist * balloonNum;
                    remainingBalloons[near - 'A'] -= balloonNum;
                }
            }
        }
        sb.append(totalDist).append('\n');
    }

    static class Team implements Comparable<Team> {
        int balloonNum, distA, distB;

        public Team(int balloonNum, int distA, int distB) {
            this.balloonNum = balloonNum;
            this.distA = distA;
            this.distB = distB;
        }

        @Override
        public int compareTo(Team o) {
            int distDiff1 = Math.abs(distA - distB), distDiff2 = Math.abs(o.distA - o.distB);
            return distDiff2 - distDiff1;
        }
    }

    public static void main(String[] args) {
        while(true) {
            input();
            solution();
        }
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
