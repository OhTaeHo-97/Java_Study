package programmers;

public class Level3_FlipTwoDimensionalCoin {
	// 열, 행을 어떤 순서로 뒤집는지는 결과에 지장이 없었음
    // 그렇기 때문에 열 먼저 뒤집어보고 거기에 따라 행을 뒤집어보며 해보는 것
    static int R, C;
    static int solution(int[][] beginning, int[][] target) {
        R = beginning.length;
        C = beginning[0].length;
        int answer = Integer.MAX_VALUE;

        // 각 줄을 뒤집을지 안뒤집을지 2가지 선택지 -> 2^N
        // 그래서 열을 뒤집는 경우의 수 -> 2^C
        for(int mask = 0; mask < 1 << C; mask++) {
            int[][] copy = new int[R][C];
            copyBeginning(copy, beginning); // 초기화
            int flipNum = 0;

            // 뒤집을 열들을 뒤집음
            for(int col = 0; col < C; col++) {
                if((mask & 1 << col) == 0) continue; // 뒤집지 않는 열을 말함
                flipCol(copy, col);
                flipNum++;
            }

            boolean isSame = true; // 각 행을 뒤집거나 뒤집지 않을 때, 해당 열이 target과 같은지 확인
            Loop1:
            for(int row = 0; row < R; row++) {
                if(copy[row][0] == target[row][0]) { // 만약 행의 첫 번째 숫자가 같다면
                    // 해당 행은 뒤집으면 안됨
                    for(int col = 1; col < C; col++) {
                        if(copy[row][col] != target[row][col]) { // 행에 다른 숫자가 있다면
                            // 해당 행은 target과 같아질 수 없음
                            // 그래서 해당 경우의 수는 target과 같아질 수 없으므로 끝냄
                            isSame = false;
                            break Loop1;
                        }
                    }
                } else { // 만약 행의 첫 번째 숫자가 다르다면
                    // 해당 행은 뒤집어야 함
                    for(int col = 1; col < C; col++) {
                        if(copy[row][col] == target[row][col]) { // 행에 같은 숫자가 있다면
                            // 행을 뒤집어도 target과 같아질 수 없음
                            // 그래서 해당 경우의 수는 target과 같아질 수 없으므로 끝냄
                            isSame = false;
                            break Loop1;
                        }
                    }
                    flipNum++;
                }
            }

            if(isSame) answer = Math.min(answer, flipNum); // isSame이 true라면
            // 모든 행에 대해 뒤집거나 뒤집지 않거나 해서 target과 같아질 수 있다는 뜻
            // 그래서 답을 갱신시킴
        }
        answer = answer == Integer.MAX_VALUE ? -1 : answer;
        return answer;
    }

    static void flipCol(int[][] copy, int col) {
        for(int row = 0; row < R; row++)
            copy[row][col] = copy[row][col] == 0 ? 1 : 0;
    }

    static void copyBeginning(int[][] copy, int[][] beginning) {
        for(int row = 0; row < R; row++) {
            for(int col = 0; col < C; col++) copy[row][col] = beginning[row][col];
        }
    }

    public static void main(String[] args) {
//        int[][] beginning = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
//        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
//        int[][] beginning = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//        int[][] target = {{1, 0, 1}, {0, 0, 0}, {0, 0, 0}};
        int[][] beginning = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] target = {{1, 0, 1}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(beginning, target));
    }
}
