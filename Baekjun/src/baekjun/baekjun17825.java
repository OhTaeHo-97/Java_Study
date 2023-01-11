package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun17825 {
    static final int SIZE = 10, HSIZE = 4;
    static int[] dices, horsePerDice;
    static Space[] horses;
    static Space start;
    static int answer;
    static void input() {
        Reader scanner = new Reader();
        dices = new int[SIZE + 1];
        horsePerDice = new int[SIZE + 1];
        horses = new Space[HSIZE + 1];
        for(int idx = 1; idx <= SIZE; idx++) dices[idx] = scanner.nextInt();
    }

    static void solution() {
        makeMap();
        decideOrder(1);
        System.out.println(answer);
    }

    static void decideOrder(int index) {
        if(index > SIZE) {
            answer = Math.max(answer, game());
            return;
        }
        for(int idx = 1; idx <= HSIZE; idx++) {
            horsePerDice[index] = idx;
            decideOrder(index + 1);
        }
    }

    static int game() {
        Arrays.fill(horses, start); // 모든 말 시작 지점에 두기

        int score = 0;
        for(int idx = 1; idx <= SIZE; idx++) {
            Space cur = horses[horsePerDice[idx]];
            cur.isEmpty = true; // 말을 이동시킬 것이기 때문에 현재 위치를 비도록 함

            for(int move = 1; move <= dices[idx]; move++) {
                if(move == 1 && cur.shortcut != null) {
                    // 현재 말이 지름길이 있는 곳에 위치한 경우 지름길로 이동
                    cur = cur.shortcut;
                } else {
                    cur = cur.next;
                }
            }

            horses[horsePerDice[idx]] = cur;

            if(!cur.isEmpty && !cur.isFinish) {
                // 끝나는 지점이 아닌 곳에서 말이 놓여져 있는 경우
                // 이 순서로 말을 배치할 경우는 불가함
                score = 0;
                break;
            } else {
                cur.isEmpty = false;
                score += cur.score;
            }
        }

        for(int horse = 1; horse <= HSIZE; horse++) {
            horses[horse].isEmpty = true;
        }

        return score;
    }

    static void makeMap() {
        // 시작 지점
        start = new Space(0);

        // 지름길을 제외한 길
        Space temp = start;
        for(int score = 2; score <= 40; score += 2) temp = temp.add(score);

        // 도착 지점
        Space end = temp.add(0);
        end.isFinish = true;
        end.next = end; // 도착 지점을 넘어설 때를 대비해 NullPointerException이 나지 않도록

        // 지름길의 중앙 부분
        Space center = new Space(25);

        // 중앙 부분부터 도착 지점까지
        temp = center.add(30);
        temp = temp.add(35);
        temp.next = Space.getSpace(start, 40);

        // 10점부터 중앙 부분까지 지름길
        temp = Space.getSpace(start, 10);
        temp = temp.shortcut = new Space(13);
        temp = temp.add(16);
        temp = temp.add(19);
        temp.next = center;

        // 20점부터 중앙 부분까지 지름길
        temp = Space.getSpace(start, 20);
        temp = temp.shortcut = new Space(22);
        temp = temp.add(24);
        temp.next = center;

        // 30점부터 중앙 부분까지 지름길
        temp = Space.getSpace(start, 30);
        temp = temp.shortcut = new Space(28);
        temp = temp.add(27);
        temp = temp.add(26);
        temp.next = center;
    }
    
    static class Space {
        int score;
        boolean isEmpty, isFinish;
        Space next;
        Space shortcut;
        public Space(int score) {
            this.score = score;
            this.isEmpty = true;
        }

        public Space add(int score) {
            Space next = new Space(score);
            this.next = next;
            return next;
        }

        public static Space getSpace(Space start, int score) {
            Space temp = start;
            while(true) {
                if(temp == null) return null;
                if(temp.score == score) return temp;
                temp = temp.next;
            }
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