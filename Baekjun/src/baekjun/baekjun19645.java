package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun19645 {
    static int burgerCount;
    static int totalUtility;
    static int[] burgers;

    static void input() {
        Reader scanner = new Reader();

        burgerCount = scanner.nextInt();
        totalUtility = 0;
        burgers = new int[burgerCount];

        for (int idx = 0; idx < burgerCount; idx++) {
            burgers[idx] = scanner.nextInt();
            totalUtility += burgers[idx];
        }
    }

    static void solution() {
        boolean[][] utilityPair = findPossibleUtilityPair();
        System.out.println(findMaxThirdSeniorTotalUtility(utilityPair));
    }

    public static int findMaxThirdSeniorTotalUtility(boolean[][] utilityPair) {
        int maxTotalUtility = 0;

        for (int firstSeniorUtility = 0; firstSeniorUtility <= totalUtility; firstSeniorUtility++) {
            maxTotalUtility = findMaxThirdSeniorTotalUtility(firstSeniorUtility, maxTotalUtility, utilityPair);
        }

        return maxTotalUtility;
    }

    private static int findMaxThirdSeniorTotalUtility(int firstSeniorUtility, int maxTotalUtility,
                                                      boolean[][] utilityPair) {
        for (int secondSeniorUtility = 0; secondSeniorUtility <= firstSeniorUtility; secondSeniorUtility++) {
            int thirdSeniorUtility = totalUtility - (firstSeniorUtility + secondSeniorUtility);
            maxTotalUtility = getMaxTotalUtility(firstSeniorUtility, secondSeniorUtility, thirdSeniorUtility,
                    maxTotalUtility, utilityPair);
        }

        return maxTotalUtility;
    }

    private static int getMaxTotalUtility(int firstSeniorUtility, int secondSeniorUtility, int thirdSeniorUtility,
                                          int maxTotalUtility, boolean[][] utilityPair) {
        if (isPossibleThirdSeniorUtility(firstSeniorUtility, secondSeniorUtility, thirdSeniorUtility, utilityPair)) {
            maxTotalUtility = Math.max(maxTotalUtility, thirdSeniorUtility);
        }

        return maxTotalUtility;
    }

    private static boolean isPossibleThirdSeniorUtility(int firstSeniorUtility, int secondSeniorUtility,
                                                        int thirdSeniorUtility, boolean[][] utilityPair) {
        return thirdSeniorUtility <= secondSeniorUtility && utilityPair[firstSeniorUtility][secondSeniorUtility];
    }

    public static boolean[][] findPossibleUtilityPair() {
        boolean[][] utilityPair = new boolean[totalUtility + 1][totalUtility + 1];
        utilityPair[0][0] = true;

        for (int burger = 0; burger < burgerCount; burger++) {
            findPossibleUtilityPair(burgers[burger], utilityPair);
        }

        return utilityPair;
    }

    private static void findPossibleUtilityPair(int burgerUtility, boolean[][] utilityPair) {
        for (int firstSeniorUtility = totalUtility; firstSeniorUtility >= 0; firstSeniorUtility--) {
            findPossibleUtilityPair(burgerUtility, firstSeniorUtility, utilityPair);
        }
    }

    private static void findPossibleUtilityPair(int burgerUtility, int firstSeniorUtility, boolean[][] utilityPair) {
        for (int secondSeniorUtility = totalUtility; secondSeniorUtility >= 0; secondSeniorUtility--) {
            decidePossibility(burgerUtility, firstSeniorUtility, secondSeniorUtility, utilityPair);
        }
    }

    private static void decidePossibility(int burgerUtility, int firstSeniorUtility, int secondSeniorUtility,
                                          boolean[][] utilityPair) {
        if (firstSeniorUtility - burgerUtility >= 0) {
            utilityPair[firstSeniorUtility][secondSeniorUtility] |= utilityPair[firstSeniorUtility
                    - burgerUtility][secondSeniorUtility];
        }

        if (secondSeniorUtility - burgerUtility >= 0) {
            utilityPair[firstSeniorUtility][secondSeniorUtility] |= utilityPair[firstSeniorUtility][secondSeniorUtility
                    - burgerUtility];
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
