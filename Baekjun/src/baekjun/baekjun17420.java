package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun17420 {
    static int giftCardNum;
    static PriorityQueue<GiftCard> giftCards;

    static void input() {
        Reader scanner = new Reader();

        giftCardNum = scanner.nextInt();

        List<GiftCard> giftCardList = new ArrayList<>();

        for(int idx = 0; idx < giftCardNum; idx++) {
            giftCardList.add(new GiftCard(scanner.nextInt(), 0));
        }

        for(int idx = 0; idx < giftCardNum; idx++) {
            giftCardList.get(idx).plan = scanner.nextInt();
        }

        giftCards = new PriorityQueue<>(giftCardList);
    }

    static void solution() {
        long answer = 0L;
        int prevMax = giftCards.peek().plan;
        int curMax = 0;

        while(!giftCards.isEmpty()) {
            GiftCard giftCard = giftCards.poll();

            if(prevMax > giftCard.deadline) {
                prevMax = Math.max(prevMax, giftCard.plan);

                int extensionNum = ((prevMax - giftCard.deadline) / 30) + ((prevMax - giftCard.deadline) % 30 == 0 ? 0 : 1);

                answer += extensionNum;
                giftCard.deadline += extensionNum * 30;
            }

            curMax = Math.max(curMax, giftCard.deadline);

            if(giftCards.size() > 0 && giftCard.plan != giftCards.peek().plan) {
                prevMax = curMax;
            }
        }

        System.out.println(answer);
    }

    static class GiftCard implements Comparable<GiftCard> {
        int deadline;
        int plan;

        public GiftCard(int deadline, int plan) {
            this.deadline = deadline;
            this.plan = plan;
        }

        @Override
        public int compareTo(GiftCard o) {
            if(plan != o.plan) {
                return plan - o.plan;
            }
            return deadline - o.deadline;
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
