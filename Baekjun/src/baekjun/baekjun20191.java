package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun20191 {
    static final int ALPHABET_COUNT = 26;

    static String targetStr;
    static String str;
    static List<Integer>[] alphabetIdxes;

    static void input() {
        Reader scanner = new Reader();

        targetStr = scanner.nextLine();
        str = scanner.nextLine();
        alphabetIdxes = new List[ALPHABET_COUNT];
    }

    static void solution() {
        findEachAlphabetIndex(str);

        int index = -1;
        int answer = 1;
        for (int idx = 0; idx < targetStr.length(); idx++) {
            char alphabet = targetStr.charAt(idx);
            List<Integer> alphabetIdx = alphabetIdxes[alphabet - 'a'];

            if (alphabetIdx == null) {
                answer = -1;
                break;
            }

            if (alphabetIdx.get(alphabetIdx.size() - 1) <= index) {
                answer++;
                index = alphabetIdx.get(0);
            } else {
                index = binarySearch(index, 0, alphabetIdx.size() - 1, alphabetIdx);
            }
        }

        System.out.println(answer);
    }

    static int binarySearch(int target, int left, int right, List<Integer> alphabetIdx) {
        while (left < right) {
            int mid = (left + right) / 2 + 1;

            if (alphabetIdx.get(mid) > target && alphabetIdx.get(mid - 1) <= target) {
                return alphabetIdx.get(mid);
            }
            if (alphabetIdx.get(mid) > target && alphabetIdx.get(mid - 1) >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return alphabetIdx.get(left);
    }

    static void findEachAlphabetIndex(String str) {
        for (int idx = 0; idx < str.length(); idx++) {
            char alphabet = str.charAt(idx);
            if (alphabetIdxes[alphabet - 'a'] == null) {
                alphabetIdxes[alphabet - 'a'] = new ArrayList<>();
            }
            alphabetIdxes[alphabet - 'a'].add(idx);
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
