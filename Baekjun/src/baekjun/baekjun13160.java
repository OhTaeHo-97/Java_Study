package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun13160 {
    static int sectionsCount;
    static Section[] sections;

    static void input() {
        Reader scanner = new Reader();

        sectionsCount = scanner.nextInt();
        sections = new Section[sectionsCount];

        for (int sectionIdx = 0; sectionIdx < sectionsCount; sectionIdx++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            sections[sectionIdx] = new Section(start, end, sectionIdx + 1);
        }
    }

    static void solution() {
        StringBuilder answer = new StringBuilder();
        if (sectionsCount == 1) {
            answer.append(1).append('\n');
            answer.append(1);
            System.out.println(answer);
            return;
        }

        Arrays.sort(sections);
        int max = 1;
        Queue<Section> queue = new PriorityQueue<>();
        queue.offer(sections[0]);
        int minEnd = sections[0].end;

        for (int idx = 1; idx < sectionsCount; idx++) {
            if (sections[idx].start <= minEnd) {
                queue.offer(sections[idx]);
                if (sections[idx].end < minEnd) {
                    minEnd = sections[idx].end;
                }
            } else {
                while (!queue.isEmpty() && queue.peek().end < sections[idx].start) {
                    queue.poll();
                }
                queue.offer(sections[idx]);
            }

            max = Math.max(max, queue.size());
        }

        answer.append(max).append('\n');
        queue = new PriorityQueue<>();
        queue.offer(sections[0]);
        for (int idx = 1; idx < sectionsCount; idx++) {
            if (sections[idx].start <= minEnd) {
                queue.offer(sections[idx]);
                if (sections[idx].end < minEnd) {
                    minEnd = sections[idx].end;
                }
            } else {
                while (!queue.isEmpty() && queue.peek().end < sections[idx].start) {
                    queue.poll();
                }
                queue.offer(sections[idx]);
            }

            if (max == queue.size()) {
                while (!queue.isEmpty()) {
                    answer.append(queue.poll().index).append(' ');
                }
                break;
            }
        }

        System.out.println(answer);
    }

    static class Section implements Comparable<Section> {
        int start;
        int end;
        int index;

        public Section(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }

        @Override
        public int compareTo(Section o) {
            if (start != o.start) {
                return start - o.start;
            }
            return end - o.end;
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
