package src.programmers.kakaocode2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Level3_Camping {
    public int solution(int n, int[][] data) {
        // 문제의 범위인 (0 ~ 2^31 - 1)에 해당하는 좌표를 이용하기에는 범위가 너무 크다!
        //  -> 그러므로 좌표 압축을 통해 좌표 범위를 줄여서 이용하겠다!
        //      - ex. 0, 1, 100, 10000, 100000 이러한 좌표들을 0, 1, 2, 3, 4 이렇게 압축하겠다!
        //      - 크기가 작은 좌표들부터 정렬하여 각 좌표를 0, 1, 2, 3.. 이렇게 변경할 것이다

        // 좌표 압축을 위해 중복 없는 x, y 좌표를 얻고 이를 크기에 맞게 정렬한다
        List<Integer> xList = new ArrayList<Integer>();
        List<Integer> yList = new ArrayList<Integer>();
        for(int[] point : data) {
            xList.add(point[0]);
            yList.add(point[1]);
        }

        xList = new ArrayList<Integer>(new HashSet<>(xList));
        yList = new ArrayList<Integer>(new HashSet<>(yList));

        Collections.sort(xList);
        Collections.sort(yList);

        // insideWedgeNum[x][y] = (0, 0) ~ (x, y) 직사각형 내부에 존재하는 쐐기 개수
        // 우선 data에 있는 모든 좌표들을 좌표 압축한 좌표들로 변경하고 insideWedgeNum의 초기값을 설정한다
        int[][] insideWedgeNum = new int[n][n];
        for(int[] point : data) {
            point[0] = xList.indexOf(point[0]);
            point[1] = yList.indexOf(point[1]);

            // 초기화
            insideWedgeNum[point[0]][point[1]] = 1;
        }

        // insideWedgeNum의 각 위치에 알맞은 값을 구한다
        //  - insideWedgeNum[x][y] += (insideWedgeNum[x - 1][y] + insideWedgeNum[x][y - 1] - insideWedgeNum[x - 1][y - 1])
        for(int xIdx = 0; xIdx < n; xIdx++) {
            for(int yIdx = 0; yIdx < n; yIdx++) {
                insideWedgeNum[xIdx][yIdx] += (
                        (xIdx - 1 >= 0 ? insideWedgeNum[xIdx-1][yIdx] : 0)
                                + (yIdx - 1 >= 0 ? insideWedgeNum[xIdx][yIdx - 1] : 0)
                                - (xIdx - 1 >= 0 && yIdx - 1 >= 0 ? insideWedgeNum[xIdx - 1][yIdx - 1] : 0)
                );
            }
        }

        int answer = 0; // 가능한 쐐기의 쌍의 개수
        // 모든 쐐기쌍들을 순회하면서 가능한 쐐기의 쌍을 찾는다
        for(int pointIdx1 = 0; pointIdx1 < n; pointIdx1++) {
            for(int pointIdx2 = pointIdx1 + 1; pointIdx2 < n; pointIdx2++) {
                // x좌표나 y좌표가 같다면 직사각형을 만들 수 없으니 이러한 경우는 제외하고 다른 경우를 살펴본다
                if(data[pointIdx1][0] == data[pointIdx2][0] || data[pointIdx1][1] == data[pointIdx2][1]) continue;

                // 직사각형의 시작 좌표와 끝 좌표를 구한다
                int startX = Math.min(data[pointIdx1][0], data[pointIdx2][0]);
                int startY = Math.min(data[pointIdx1][1], data[pointIdx2][1]);
                int endX = Math.max(data[pointIdx1][0], data[pointIdx2][0]);
                int endY = Math.max(data[pointIdx1][1], data[pointIdx2][1]);

                int wadgeNum = 0; // 현재 직사각형 내부에 존재하는 쐐기의 수
                // 직사각형의 한 변의 길이가 1이라면, 내부에 쐐기가 들어갈 공간이 없으니 이러한 경우는 제외하고 다른 경우에 대해 내부에 존재하는 쐐기의 수를 구한다
                if(startX + 1 <= endX - 1 && startY + 1 <= endY - 1) {
                    wadgeNum = insideWedgeNum[endX - 1][endY - 1] - insideWedgeNum[endX - 1][startY] - insideWedgeNum[startX][endY - 1] + insideWedgeNum[startX][startY];
                }

                // 내부에 존재하는 쐐기의 수가 0이라면 가능한 쐐기의 쌍이므로 정답 개수에 1을 추가한다
                if(wadgeNum == 0) answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Level3_Camping l = new Level3_Camping();
        System.out.println(l.solution(4, new int[][] {{0, 0}, {1, 1}, {0, 2}, {2, 0}}));
    }
}
