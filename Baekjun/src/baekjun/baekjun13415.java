package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class baekjun13415 {
    static int seriesCnt;
    static int setCnt;
    static int maxSortIdx; // 정렬해야 하는 구간의 최댓값
    static int[] series;
    static int[] sortedSeries; // 최종 결과 수열
    static Deque<Integer> sortInfo; // 정렬 정보 (오름차순은 양수로서 구간의 값이, 내림차순은 음수로서 구간의 값이 나타내어진다)

    static void input() {
        Reader scanner = new Reader();

        seriesCnt = scanner.nextInt();
        series = new int[seriesCnt];
        sortedSeries = new int[seriesCnt];
        sortInfo = new ArrayDeque<>();

        for(int idx = 0; idx < seriesCnt; idx++) {
            series[idx] = scanner.nextInt();
            sortedSeries[idx] = series[idx]; // 최종 결과 수열 역시 기존 수열과 같은 값으로 채운다
        }

        setCnt = scanner.nextInt();
        for(int cnt = 0; cnt < setCnt; cnt++) {
            int ascendingIdx = scanner.nextInt();
            int descendingIdx = scanner.nextInt();
            maxSortIdx = Math.max(maxSortIdx, Math.max(ascendingIdx, descendingIdx)); // 정렬해야 하는 구간의 최댓값 갱신

            // 정렬 정보에서 입력받은 오름차순 구간보다 구간값이 작은 정보들은 뒤에서부터 삭제한다
            //  - 구간값이 작은 것들은 구간값이 큰 것에 의해 무시되어질 수 있다
            while((!sortInfo.isEmpty()) && (Math.abs(sortInfo.getLast()) <= ascendingIdx)) {
                sortInfo.pollLast();
            }
            // 입력받은 오름차순 구간을 마지막에 추가한다
            sortInfo.addLast(ascendingIdx);

            // 정렬 정보에서 입력받은 내림차순 구간보다 구간값이 작은 정보들은 뒤에서부터 삭제한다
            while((!sortInfo.isEmpty()) && (Math.abs(sortInfo.getLast()) <= descendingIdx)) {
                sortInfo.pollLast();
            }
            // 입력받은 내림차순 구간을 마지막에 추가한다
            sortInfo.addLast(-descendingIdx);
        }

        // 마지막 연산을 위해 0을 추가한다
        sortInfo.addLast(0);
    }

    static void solution() {
        sort();
        print();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < sortedSeries.length; idx++) {
            sb.append(sortedSeries[idx]).append(' ');
        }
        System.out.println(sb);
    }

    static void sort() {
        // 기존 수열을 오름차순으로 처음부터 정렬해야 하는 구간의 최댓값까지 정렬한다
        Arrays.sort(series, 0, maxSortIdx);

        // 최종 결과 배열에 값을 추가할 때 사용하는 인덱스
        //  - 정렬 구간 정보는 구간값에 따라 내림차순으로 정렬되어 있다고 볼 수 있다
        //  - 내림차순으로 정렬되어있는 인접한 두 구간 정보의 구간값의 차이만큼씩만 정렬을 진행하면 모든 구간의 정렬을 진행할 수 있다
        //  - 즉, 정렬된 수열에 값을 넣을 때는 가장 뒤에서부터 앞으로 가며 넣으면 된다!
        int sortedIdx = maxSortIdx - 1;
        // 오름차순 정렬을 통해 정렬할 때 사용하는 인덱스
        //  - 기존 수열을 오름차순으로 정렬했기 때문에 정렬된 수열에 뒤에서부터 값을 채우려면 뒤에서부터 앞으로 가져와야 한다
        int ascendingIdx = maxSortIdx - 1;
        // 내림차순 정렬을 통해 정렬할 때 사용하는 인덱스
        //  - 기존 수열을 오름차순으로 정렬했기 때문에 정렬된 수열에 뒤에서부터 값을 채우려면 앞에서부터 가져와야 한다
        int descendingIdx = 0;

        // 현재 정렬 정보와 다음 정렬 정보를 이용하여 사이 구간을 정렬해나가며 수열을 정렬한다
        int curSortInfo = sortInfo.pollFirst();
        while(!sortInfo.isEmpty()) {
            int nextSortInfo = sortInfo.pollFirst();
            // 현재 정렬 정보와 다음 정렬 정보 구간 사이의 차이를 구한다
            //  - 정렬 정보는 내림차순으로 정렬되어 있는 것과 마찬가지기 때문에 두 값의 차이는 양수가 될 수 밖에 없다
            int diff = Math.abs(curSortInfo) - Math.abs(nextSortInfo);

            if(curSortInfo > 0) { // 오름차순으로 정렬할 때
                // 현재 정렬 정보와 다음 정렬 정보 구간의 차이만큼 오름차순으로 정렬된 값을 채운다
                for(int idx = 0; idx < diff; idx++) {
                    sortedSeries[sortedIdx--] = series[ascendingIdx--];
                }
            } else { // 내림차순으로 정렬할 때
                // 현재 정렬 정보와 다음 정렬 정보 구간의 차이만큼 내림차순으로 정렬된 값을 채운다
                for(int idx = 0; idx < diff; idx++) {
                    sortedSeries[sortedIdx--] = series[descendingIdx++];
                }
            }

            // 인접한 두 구간 정보의 차이를 이용하기 때문에 현재 구간 정보를 다음 구간 정보로 변경해준다
            curSortInfo = nextSortInfo;
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
