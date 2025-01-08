package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun24337 {
    private static int buildingCount;
    private static int gahuiCount;
    private static int danbiCount;

    private static void input() {
        Reader scanner = new Reader();

        buildingCount = scanner.nextInt();
        gahuiCount = scanner.nextInt();
        danbiCount = scanner.nextInt();
    }

    private static void solution() {
        int gahuiMaxBuildingIdx = gahuiCount - 1;
        int danbiMaxBuildingIdx = buildingCount - danbiCount;
        int[] buildingHeights = new int[buildingCount];

        if(gahuiMaxBuildingIdx < 0 || danbiMaxBuildingIdx < 0 || gahuiMaxBuildingIdx > danbiMaxBuildingIdx) {
            System.out.println(-1);
            return;
        }

        Stack<Integer> gahuiBuildingHeights = new Stack<>();
        Stack<Integer> danbiBuildingHeights = new Stack<>();

        int height = 1;
        for(int buildingIdx = 0; buildingIdx <= gahuiMaxBuildingIdx; buildingIdx++) {
            gahuiBuildingHeights.push(height++);
        }
        height = 1;
        for(int buildingIdx = buildingCount - 1; buildingIdx >= danbiMaxBuildingIdx; buildingIdx--) {
            danbiBuildingHeights.push(height++);
        }

        if(gahuiBuildingHeights.peek() > danbiBuildingHeights.peek()) {
            danbiBuildingHeights.pop();
        } else {
            gahuiBuildingHeights.pop();
        }

        if(gahuiBuildingHeights.isEmpty()) {
            buildingHeights[0] = danbiBuildingHeights.pop();
            int size = danbiBuildingHeights.size();
            for(int buildingIdx = buildingCount - size; buildingIdx < buildingCount; buildingIdx++) {
                buildingHeights[buildingIdx] = danbiBuildingHeights.pop();
            }
        } else if(danbiBuildingHeights.isEmpty()) {
            int size = gahuiBuildingHeights.size();
            for(int buildingIdx = buildingCount - 1; buildingIdx >= buildingCount - size; buildingIdx--) {
                buildingHeights[buildingIdx] = gahuiBuildingHeights.pop();
            }
        } else {
            int danbiSize = danbiBuildingHeights.size();
            for(int buildingIdx = buildingCount - danbiSize; buildingIdx < buildingCount; buildingIdx++) {
                buildingHeights[buildingIdx] = danbiBuildingHeights.pop();
            }
            int gahuiSize = gahuiBuildingHeights.size();
            for(int buildingIdx = buildingCount - danbiSize - 1; buildingIdx >= buildingCount - danbiSize - gahuiSize; buildingIdx--) {
                buildingHeights[buildingIdx] = gahuiBuildingHeights.pop();
            }
        }


        for(int buildingIdx = 0; buildingIdx < buildingCount; buildingIdx++) {
            buildingHeights[buildingIdx] = buildingHeights[buildingIdx] == 0 ? 1 : buildingHeights[buildingIdx];
            System.out.print(buildingHeights[buildingIdx] + " ");
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
