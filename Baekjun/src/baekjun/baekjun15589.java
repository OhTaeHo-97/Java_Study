package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun15589 {
    static int cowCnt;
    static List<Cow> cows;
    static List<TimeInfo> timeInfos;

    static void input() {
        Reader scanner = new Reader();

        cowCnt = scanner.nextInt();
        cows = new ArrayList<>();
        timeInfos = new ArrayList<>();

        for(int cowIdx = 0; cowIdx < cowCnt; cowIdx++) {
            int startTime = scanner.nextInt();
            int endTime = scanner.nextInt();

            cows.add(new Cow(startTime, endTime));

            timeInfos.add(new TimeInfo(startTime, 1));
            timeInfos.add(new TimeInfo(endTime, -1));
        }
    }

    static void solution() {
        Collections.sort(cows);
        Collections.sort(timeInfos);

        boolean hasOverlapped = false;
        int maxEnd = 0;
        // 완전히 overlap되는 구간이 있다면 그 구간을 포함하는 구간의 끝나는 시간을
        // 없다면 끝나는 시간의 최댓값을 구한다
        for(Cow cow : cows) {
            if(cow.endTime <= maxEnd) {
                hasOverlapped = true;
                break;
            }
            maxEnd = cow.endTime;
        }

        int overlap = 0; // overlap 정보
        int prevTime = timeInfos.get(0).time; // 이전 시간
        int totalPeriod = 0; // 모든 구간이 충당할 수 있는 영역의 길이
        int minGap = Integer.MAX_VALUE; // 어떤 두 구간도 overlap 되지 않는 가장 짧은 영역의 길이

        // 주어진 시작 및 끝 시간 정보를 이용하여 두 가지를 구한다
        //  1. 어떤 두 구간도 overlap 되지 않는 가장 짧은 영역의 길이
        //  2. 모든 구간이 충당할 수 있는 영역의 길이
        for(TimeInfo timeInfo : timeInfos) {
            // overlap 정보가 1일 때, 어떤 두 구간도 overlap 되지 않는 가장 짧은 영역의 길이를 갱신한다
            //  - overlap 정보가 1일 때는 바로 이전 시간을 나타내는 prevTime과, prevTime과 인접한 구간 사이의 차이를 구할 수 있다
            if(overlap == 1) {
                minGap = Math.min(minGap, timeInfo.time - prevTime); // 어떤 두 구간도 overlap 되지 않는 가장 짦은 영역의 길이
            }
            // overlap 정보가 1 이상이라면, 모든 구간이 충당할 수 있는 영역의 길이를 갱신한다
            //  - overlap 정보가 1 이상이라면 시작한 구간이 하나 이상 존재한다는 뜻이기 때문에 시간 차이를 계속 더해나가면 전체 구간의 길이를 구할 수 있다
            if(overlap >= 1) {
                totalPeriod += (timeInfo.time - prevTime); // 모든 구간이 커버하는 영역의 총 길이
            }

            prevTime = timeInfo.time; // 시간 갱신
            overlap += timeInfo.startEndInfo; // overlap 됐는지를 알기 위해 시작 혹은 끝 정보를 더해준다
        }

        if(hasOverlapped) { // 완전히 overlap 되는 구간이 있다면
            // 해당 구간을 없앤다면, 결국 모든 구간이 충당할 수 있는 영역의 길이와 같기 때문에
            // 모든 구간이 충당할 수 있는 영역의 길이를 출력한다
            System.out.println(totalPeriod);
        } else { // 완전히 overlap 되는 구간이 없다면
            // 모든 구간이 충당할 수 있는 영역의 길이에서 overlap 되지 않는 가장 짧은 영역의 길이를 뺀 값을 출력한다
            System.out.println(totalPeriod - minGap);
        }
    }

    static class TimeInfo implements Comparable<TimeInfo> {
        int time;
        int startEndInfo;

        public TimeInfo(int time, int startEndInfo) {
            this.time = time;
            this.startEndInfo = startEndInfo;
        }

        @Override
        public int compareTo(TimeInfo o) {
            if(time != o.time) {
                return time - o.time;
            }
            return startEndInfo - o.startEndInfo;
        }
    }

    static class Cow implements Comparable<Cow> {
        int startTime;
        int endTime;

        public Cow(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Cow o) {
            if(startTime != o.startTime) {
                return startTime - o.startTime;
            }
            return endTime - o.endTime;
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
