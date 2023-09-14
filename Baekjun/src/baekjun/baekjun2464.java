package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjun2464 {
    static long A;
    static long lowerNum;
    static long higherNum;
    static long oneBit;
    static long mask;
    static int oneNum;

    static void input() {
        Reader scanner = new Reader();

        A = scanner.nextLong();
        // A의 비트를 일의 자리부터 순회하며 가장 가까운 작은 수와 가장 가까운 큰 수를 찾을 것인데
        // 순회할 때 사용할 인덱스와 같은 비트
        // 작은 수를 구할 떄는 10, 큰 수를 구할 때는 01 비트를 찾아야 하는데 이와 같은지 비교할 떄 사용하기도 할 것임
        oneBit = 1L;
        // 순회할 때 A 비트에서 현재 비교할 두 비트를 찾는 마스크 비트
        mask = 3L;
        // A 비트를 순회하면서 1의 개수를 세기 위한 변수
        oneNum = 0;
    }

    static void solution() {
        // A의 마지막 비트까지 순회
        while(oneBit <= A) {
            // 가장 가까운 작은 수와 가장 가까운 큰 수를 구했다면 순회를 멈춘다
            if(lowerNum != 0L && higherNum != 0L)
                break;

            // 현재 비교할 비트가 10이라면 가장 가까운 작은 수를 구한다
            if((A & mask) == (oneBit << 1)) {
                getNearestLowerNum();
            }
            // 현재 비교할 비트가 01이라면 가장 가까운 큰 수를 구한다
            if((A & mask) == oneBit) {
                getNearestHigherNum();
            }
            // 현재 비트가 1이라면 1의 개수를 나타내는 oneNum의 값을 1 증가시킨다
            if((A & oneBit) != 0L) {
                oneNum++;
            }

            // 다음 비트를 체크하기 위해 oneBit와 mask를 왼쪽으로 한 칸씩 민다
            oneBit <<= 1;
            mask <<= 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lowerNum).append(' ').append(higherNum);
        System.out.println(sb);
    }

    // 가장 가까운 작은 수를 구하는 메서드
    static void getNearestLowerNum() {
        // 가장 가까운 작은 수를 구하기 위해서는 10인 비트를 01인 비트로 우선 변경해야 한다
        lowerNum = A ^ mask;
        // 그리고 01 비트로 바꾼 위치 뒤로는 모두 0으로 만든다
        lowerNum = lowerNum / oneBit * oneBit;

        // 0으로 만든 곳에서 0을 왼쪽부터 1로 oneNum만큼 변경할 것이다
        // 그래서 그 비트의 시작점을 bitIdx에 설정한다
        long bitIdx = (oneBit >> 1);
        // 채워줄 1의 개수를 받는다
        int count = oneNum;

        // 모든 비트를 돌거나 oneNum만큼 1을 채울 때까지 bitIdx 위치에 1을 채운다
        while(bitIdx != 0L && count-- > 0) {
            // bitIdx 위치에 1을 채운다는 뜻은 결국 bitIdx만큼 더해준다는 뜻이므로 lowerNum에 bitIdx를 더한다
            lowerNum += bitIdx;
            // 1로 변경할 비트 위치를 옮긴다
            bitIdx >>= 1;
        }
    }

    // 가장 가까운 큰 수를 구하는 메서드
    static void getNearestHigherNum() {
        // 가장 가까운 큰 수를 구하기 위해서는 01인 비트를 10인 비트로 우선 변경해야 한다
        higherNum = A ^ mask;
        // 그리고 10 비트로 바꾼 위치 뒤로는 모두 0으로 만든다
        higherNum = higherNum / oneBit * oneBit;

        // 가장 오른쪽 비트부터 10 비트 전까지 0을 1로 oneNum만큼 변경할 것이다
        // 그래서 그 비트의 시작점을 bitIdx에 설정한다
        long bitIdx = 1;
        // 채워줄 1의 개수를 받는다
        int count = oneNum;

        // 모든 비트를 돌거나 oneNum만큼 1을 채울 때까지 bitIdx 위치에 1을 채운다
        while(bitIdx < oneBit && count-- > 0) {
            // bitIdx 위치에 1을 채운다는 뜻은 결국 bitIdx만큼 더해준다는 뜻이므로 higherNum에 bitIdx를 더한다
            higherNum += bitIdx;
            // 1로 변경할 비트 위치를 옮긴다
            bitIdx <<= 1;
        }
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        long nextLong() {
            try {
                return Long.parseLong(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                return 0L;
            }
        }
    }
}
