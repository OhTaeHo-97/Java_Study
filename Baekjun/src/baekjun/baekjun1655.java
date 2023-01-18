package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1655 {
	// MaxHeap과 MinHeap 2개를 이용하여 구현
    // 원리는 주어진 수들을 반으로 나누어 큰 것은 MinHeap에, 작은 것은 MaxHeap에 있게 하는 것!
    //  -> 이렇게 한다면 MinHeap에는 중앙값과 같거나 큰 값들만, MaxHeap에는 중앙값과 같거나 작은 값들만 있게 됨
    //  -> 여기서 방법이 2개로 나뉘는데
    //      -> 만약 두 힙에 있는 원소의 개수가 같을 때 MinHeap에 넣게 되면, 중앙값이 MinHeap에 존재하게 됨
    //          -> 왜냐하면 총 원소 개수가 홀수일 때를 생각해보면 예를 들어 개수가 5일 때, 세 번째 있는 값이 중앙값
    //          -> 그런데 개수가 같을 때 MinHeap에 넣게 되면 항상 그 중앙이라는 것은 MinHeap에 존재하게 됨
    //          -> 만약 총 원소의 개수가 짝수라면 MinHeap에 중앙값이 존재하게 됨을 위에서 봤는데, 원소의 개수가 짝수라면 중앙값은 2개
    //          -> 즉, MaxHeap에서 뽑은 원소 1개와 MinHeap에서 뽑은 원소 1개 모두가 중앙값
    //          -> 그런데 우리는 MinHeap에 중앙값이 있음을 홀수에서 봤기 때문에 여기서도 중앙값은 MinHeap에 있어야 함
    //          -> 그렇기 때문에 이 때는 중앙값 중 큰 중앙값을 찾게 됨

    //      -> 만약 두 합에 있는 원소의 개수가 같을 때 MaxHeap에 넣게 되면, 중앙값이 MaxHeap에 존재하게 됨
    //          -> 이유는 총 원소 개수가 홀수일 때는 첫 번째 방법과 같은 이유
    //          -> 총 원소 개수가 짝수일 때는 위에서 설명한 바와 같이 MaxHeap에서 하나, MinHeap에서 하나가 중앙값
    //          -> 그런데 여기서는 MaxHeap에 중앙값이 존재하게 됨을 총 원소 개수가 홀수일 때를 통해 봤기 때문에 중앙값은 MaxHeap에 존재
    //          -> 즉, 중앙값 중 작은 값을 찾게 됨
    static StringBuilder sb = new StringBuilder();
    static void input() {
        Reader scanner = new Reader();
        int N = scanner.nextInt();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int idx = 0; idx < N; idx++) {
            if(maxHeap.size() == 0) maxHeap.offer(scanner.nextInt());
            else if(maxHeap.size() == minHeap.size()) maxHeap.offer(scanner.nextInt());
            else minHeap.offer(scanner.nextInt());

            if(maxHeap.size() != 0 && minHeap.size() != 0)
                solution(minHeap, maxHeap);
            else
                sb.append(maxHeap.peek()).append('\n');
        }
    }

    static void solution(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if(minHeap.peek() < maxHeap.peek()) {
            int maxCentral = maxHeap.poll(), minCentral = minHeap.poll();
            minHeap.offer(maxCentral);
            maxHeap.offer(minCentral);
        }

        sb.append(maxHeap.peek()).append('\n');
    }

    public static void main(String[] args) {
        input();
        System.out.println(sb);
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
