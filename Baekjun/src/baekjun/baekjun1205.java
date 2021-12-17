package baekjun;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun1205 {
	public int rank(int p, int score, Integer[] scores) {
		Arrays.parallelSort(scores, Collections.reverseOrder());
        // Case 1. 기존에 점수가 없을 경우
        if(scores.length == 0) {
            return 1;
        // Case 2. 기존의 점수가 최대 개수만큼 다 차있을 경우
        } else if(scores.length == p) {
            // Case 2 - 1. 기존의 점수들에서 가장 작은 점수와 같다면
            if(score == scores[scores.length - 1]) {
                return -1;
            // Case 2 - 2. 그렇지 않다면
            } else {
                int index = -1;
                for(int i = 0; i < scores.length; i++) {
                    if(scores[i] <= score) {
                        index = i;
                        break;
                    }
                }
                
                if(index == -1) {
                    return -1;
                } else {
                    return index + 1;
                }
            }
        // Case 3. 이외의 경우
        } else {
            int index = -1;
            for(int i = 0; i < scores.length; i++) {
                if(scores[i] <= score) {
                    index = i;
                    break;
                }
            }
            
            if(index == -1) {
                return scores.length + 1;
            } else {
                return index + 1;
            }
        }
    }
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        
        if(n != 0) {
            input = br.readLine();
            st = new StringTokenizer(input);
        }
        Integer[] scores = new Integer[n];
        for(int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
        
        baekjun1205 m = new baekjun1205();
        System.out.println(m.rank(p, score, scores));
    }
}
