package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun20366 {
    /*
     * N개의 눈덩이, 각 눈덩이 i(1 <= i <= N)의 지름은 Hi
     * 하나의 눈사람은 두 개의 눈덩이로 구성됨, 눈덩이 하나를 아래에 두고 그 눈덩이보다 크지 않은 다른 눈덩이를 쌓아 올리는 방식
     *  - 눈사람의 키 = 두 눈덩이 지름의 합
     * 눈덩이 N개 중 서로 다른 4개를 골라 눈사람을 각각 1개씩 총 2개
     * 두 눈사람의 키의 차이가 작도록 하자
     *
     * 1 <= N <= 600
     * 1 <= Hi <= 10^9
     */

    // 600C2 => 600 * 599 / 2 = 179,700
    // log2 179,700 = 약 18
    // 179,700 * 18 = 3,235,400

    // 완탐
    //  - 가능한 모든 눈사람을 만들기
    //      - 이때, 높이와 더불어 사용된 눈 인덱스를 같이 저장
    //  - 눈사람을 높이순으로 정렬
    //  - 인접한 눈사람과의 차이가 가장 작은 차이가 될테니 같은 눈을 사용하지 않은 인접한 눈사람 간의 높이 차 중 가장 작은 것 이용
//    private static int snowballCount;
//    private static List<Integer> snowballDiameters;
//
//    private static void input() {
//        Reader scanner = new Reader();
//
//        snowballCount = scanner.nextInt();
//        snowballDiameters = new ArrayList<>();
//
//        for(int snowball = 0; snowball < snowballCount; snowball++) {
//            snowballDiameters.add(scanner.nextInt());
//        }
//    }
//
//    private static void solution() {
//        List<Snowman> allSnowmans = findAllSnowmans();
//        Collections.sort(allSnowmans, new Comparator<Snowman>() {
//            @Override
//            public int compare(Snowman o1, Snowman o2) {
//                return o1.height - o2.height;
//            }
//        });
//        int minDiff = Integer.MAX_VALUE;
//
//        for(int idx = 0; idx < allSnowmans.size() - 1; idx++) {
//            Snowman snowman1 = allSnowmans.get(idx);
//            Snowman snowman2 = allSnowmans.get(idx + 1);
//            if (snowman1.isDifferent(snowman2)) {
//                minDiff = Math.min(minDiff, Math.abs(snowman1.height - snowman2.height));
//            }
//        }
//        System.out.println(minDiff);
//    }
//
//    private static List<Snowman> findAllSnowmans() {
//        List<Snowman> snowmans = new ArrayList<>();
//        for(int snowball1 = 0; snowball1 < snowballCount - 1; snowball1++) {
//            for(int snowball2 = snowball1 + 1; snowball2 < snowballCount; snowball2++) {
//                int headSnow = snowball1 >= snowball2 ? snowball2 : snowball1;
//                int bodySnow = snowball1 >= snowball2 ? snowball1 : snowball2;
//                snowmans.add(new Snowman(headSnow, bodySnow, snowballDiameters.get(snowball1) + snowballDiameters.get(snowball2)));
//            }
//        }
//        return snowmans;
//    }
//
//    static class Snowman {
//        int headSnow;
//        int bodySnow;
//        int height;
//
//        public Snowman(int headSnow, int bodySnow, int height) {
//            this.headSnow = headSnow;
//            this.bodySnow = bodySnow;
//            this.height = height;
//        }
//
//        public boolean isDifferent(Snowman snowman) {
//            return this.headSnow != snowman.headSnow && this.bodySnow != snowman.headSnow && this.headSnow != snowman.bodySnow && this.bodySnow != snowman.bodySnow;
//        }
//    }

    // 투포인터
    private static int snowballCount;
    private static List<Integer> snowballDiameters;

    private static void input() {
        Reader scanner = new Reader();

        snowballCount = scanner.nextInt();
        snowballDiameters = new ArrayList<>();

        for(int snowball = 0; snowball < snowballCount; snowball++) {
            snowballDiameters.add(scanner.nextInt());
        }
    }

    private static void solution() {
        Collections.sort(snowballDiameters);

        int minDiff = Integer.MAX_VALUE;
        for(int idx = 0; idx < snowballCount; idx++) {
            for(int idx2 = idx + 1; idx2 < snowballCount; idx2++) {
                int snowman1 = snowballDiameters.get(idx) + snowballDiameters.get(idx2);
                int start = 0;
                int end = snowballCount - 1;

                while(start < end) {
                    if(start == idx || start == idx2) {
                        start++;
                        continue;
                    }
                    if(end == idx || end == idx2) {
                        end--;
                        continue;
                    }

                    int snowman2 = snowballDiameters.get(start) + snowballDiameters.get(end);
                    minDiff = Math.min(minDiff, Math.abs(snowman1 - snowman2));
                    if(snowman1 > snowman2) {
                        start++;
                    } else if(snowman1 < snowman2) {
                        end--;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        System.out.println(minDiff);
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
