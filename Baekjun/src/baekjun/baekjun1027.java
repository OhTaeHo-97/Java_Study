package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun1027 {
    private static int buildingCount;
    private static int[] answer;
    private static int[] heights;

    private static void input() {
        Reader scanner = new Reader();

        buildingCount = scanner.nextInt();
        heights = new int[buildingCount + 1];
        for(int buildingNum = 1; buildingNum <= buildingCount; buildingNum++) {
            heights[buildingNum] = scanner.nextInt();
        }
    }

    private static void solution() {
        int answer = 0;
        for(int buildingNum = 1; buildingNum <= buildingCount; buildingNum++) {
            answer = Math.max(answer, findSkyscraperCountByEachBuilding(buildingNum));
        }
        System.out.println(answer);
    }

    private static int findSkyscraperCountByEachBuilding(int buildingNum) {
        int startHeight = heights[buildingNum];
        return findLeftSkyscraperCount(buildingNum, startHeight) + findRightSkyscraperCount(buildingNum, startHeight);
    }

    private static int findLeftSkyscraperCount(int buildingNum, int startHeight) {
        int skyscraperCount = 0;
        double prevGradient = 0;

        for(int targetBuildingNum = buildingNum - 1; targetBuildingNum > 0; targetBuildingNum--) {
            double gradient = ((double) startHeight - heights[targetBuildingNum]) / (buildingNum - targetBuildingNum);
            if(targetBuildingNum == buildingNum - 1 || prevGradient > gradient) {
                prevGradient = gradient;
                skyscraperCount++;
            }
        }

        return skyscraperCount;
    }

    private static int findRightSkyscraperCount(int buildingNum, int startHeight) {
        int skyscraperCount = 0;
        double prevGradient = 0;

        for(int targetBuildingNum = buildingNum + 1; targetBuildingNum <= buildingCount; targetBuildingNum++) {
            double gradient = ((double) startHeight - heights[targetBuildingNum]) / (buildingNum - targetBuildingNum);
            if(targetBuildingNum == buildingNum + 1 || prevGradient < gradient) {
                prevGradient = gradient;
                skyscraperCount++;
            }
        }

        return skyscraperCount;
    }

//    private static void solution() {
//        answer = new int[buildingCount + 1];
//        for(int buildingNum = 1; buildingNum <= buildingCount; buildingNum++) {
//            findSkyscraperCountByEachBuilding(buildingNum);
//        }
//        int maxBuildingCount = Arrays.stream(answer).max().getAsInt();
//        System.out.println(maxBuildingCount);
//    }

//    private static void findSkyscraperCountByEachBuilding(int buildingNum) {
//        int startHeight = heights[buildingNum];
//        for(int targetNum = buildingNum + 1; targetNum <= buildingCount; targetNum++) {
//            int targetHeight = heights[targetNum];
//            double gradient = ((double) startHeight - targetHeight) / (buildingNum - targetNum);
//            double yIntercept = startHeight - (gradient * buildingNum);
//
//            boolean isPossible = canSeeTargetBuilding(buildingNum, targetNum, gradient, yIntercept);
//            if(isPossible) {
//                answer[buildingNum]++;
//                answer[targetNum]++;
//            }
//        }
//    }
//
//    private static boolean canSeeTargetBuilding(int buildingNum, int targetNum, double gradient, double yIntercept) {
//        boolean isPossible = true;
//        for(int middleBuildingNum = buildingNum + 1; middleBuildingNum < targetNum; middleBuildingNum++) {
//            double maxHeight = gradient * middleBuildingNum + yIntercept;
//            if(maxHeight <= heights[middleBuildingNum]) {
//                isPossible = false;
//                break;
//            }
//        }
//        return isPossible;
//    }

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
