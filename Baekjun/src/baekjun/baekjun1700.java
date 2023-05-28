package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun1700 {
	static int N, K;
    static int[] electricalGoodsOrder;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        K = scanner.nextInt();
        electricalGoodsOrder = new int[K];

        for(int idx = 0; idx < K; idx++) electricalGoodsOrder[idx] = scanner.nextInt();
    }

    static void solution() {
        boolean[] isUsed = new boolean[K + 1];
        int usedPlugNum = 0, answer = 0;

        for(int electricalGoodIdx = 0; electricalGoodIdx < K; electricalGoodIdx++) {
            int electricalGood = electricalGoodsOrder[electricalGoodIdx];

            if(!isUsed[electricalGood]) {
                if(usedPlugNum < N) { // 콘센트 꽂을 공간이 있는 경우
                    isUsed[electricalGood] = true;
                    usedPlugNum++;
                } else { // 콘센트 꽂을 공간이 없는 경우
                    List<Integer> willUseElectricalGoodList = new ArrayList<>();
                    // 나중에 사용되는 현재 꽂혀있는 콘센트 구하기
                    for(int idx = electricalGoodIdx; idx < K; idx++) {
                        if(isUsed[electricalGoodsOrder[idx]] && !willUseElectricalGoodList.contains(electricalGoodsOrder[idx]))
                            willUseElectricalGoodList.add(electricalGoodsOrder[idx]);
                    }

                    // 나중에도 사용되는 콘센트가 구멍의 개수보다 작을 경우
                    if(willUseElectricalGoodList.size() < N) {
                        // 나중에 사용되지 않는 콘센트 빼기
                        for(int electricalGoodNum = 1; electricalGoodNum < isUsed.length; electricalGoodNum++) {
                            if(isUsed[electricalGoodNum] && !willUseElectricalGoodList.contains(electricalGoodNum)) {
                                isUsed[electricalGoodNum] = false;
                                break;
                            }
                        }
                    } else {
                        // 꽂혀있는 것 중 하나 제거
                        int removePlug = willUseElectricalGoodList.get(willUseElectricalGoodList.size() - 1);
                        isUsed[removePlug] = false;
                    }

                    // 사용되는 전기용품을 꽂고 하나의 플러그를 제거했기 때문에 answer 1 증가
                    isUsed[electricalGood] = true;
                    answer++;
                }
            }
        }

        System.out.println(answer);
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
