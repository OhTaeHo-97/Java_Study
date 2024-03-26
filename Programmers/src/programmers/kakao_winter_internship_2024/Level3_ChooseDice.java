package src.programmers.kakao_winter_internship_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Level3_ChooseDice {
    private int diceCount;
    private boolean[] visited;
    private int[] answer;
    private List<int[]> diceCombinations;
    private List<Integer> scoreA;
    private List<Integer> scoreB;

    public int[] solution(int[][] dice) {
        diceCount = dice.length;
        visited = new boolean[diceCount];
        diceCombinations = new ArrayList<>();

        chooseADice(0, 0, new int[diceCount / 2]);

        answer = new int[diceCount / 2];
        findHighestWinningPossibilityDiceCombination(dice);
        return answer;
    }

    private void findHighestWinningPossibilityDiceCombination(int[][] dice) {
        int maxWinningCount = Integer.MIN_VALUE;
        for (int[] diceA : diceCombinations) {
            int[] diceB = findDiceB(diceA);

            scoreA = new ArrayList<>();
            scoreB = new ArrayList<>();

            sumDices(0, 0, diceA, dice, scoreA);
            sumDices(0, 0, diceB, dice, scoreB);

            Collections.sort(scoreA);
            Collections.sort(scoreB);

            maxWinningCount = findMaxWinningCountByEachDiceCombination(maxWinningCount, diceA);
        }

        for (int idx = 0; idx < answer.length; idx++) {
            answer[idx]++;
        }
    }

    private int findMaxWinningCountByEachDiceCombination(int maxWinningCount, int[] diceA) {
        int totalWinCount = 0;

        for (int sumA : scoreA) {
            int left = binarySearch(sumA);
            totalWinCount += left;
        }

        if (totalWinCount > maxWinningCount) {
            maxWinningCount = totalWinCount;
            answer = diceA;
        }

        return maxWinningCount;
    }

    private int binarySearch(int sumA) {
        int left = 0;
        int right = scoreB.size();

        while (left + 1 < right) {
            int mid = (left + right) / 2;

            if (sumA > scoreB.get(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private void sumDices(int diceIdx, int sum, int[] diceCombination, int[][] dice, List<Integer> score) {
        if (diceIdx == diceCombination.length) {
            score.add(sum);
            return;
        }

        for (int faceIdx = 0; faceIdx < 6; faceIdx++) {
            sumDices(diceIdx + 1, sum + dice[diceCombination[diceIdx]][faceIdx], diceCombination, dice, score);
        }
    }

    private int[] findDiceB(int[] diceA) {
        int[] diceB = new int[diceCount / 2];
        boolean[] diceAVisited = checkDiceA(diceA);

        int idx = 0;
        for (int dice = 0; dice < diceCount; dice++) {
            if (!diceAVisited[dice]) {
                diceB[idx++] = dice;
            }
        }

        return diceB;
    }

    private boolean[] checkDiceA(int[] diceA) {
        boolean[] visited = new boolean[diceCount];
        for (int dice : diceA) {
            visited[dice] = true;
        }

        return visited;
    }

    private void chooseADice(int index, int diceIdx, int[] diceA) {
        if (index == diceCount / 2) {
            diceCombinations.add(diceA.clone());
            return;
        }

        for (int idx = diceIdx; idx < diceCount; idx++) {
            if (!visited[idx]) {
                visited[idx] = true;
                diceA[index] = idx;
                chooseADice(index + 1, idx + 1, diceA);
                visited[idx] = false;
            }
        }
    }

    public static void main(String[] args) {
        Level3_ChooseDice l = new Level3_ChooseDice();
        System.out.println(Arrays.toString(l.solution(new int[][] {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}})));
    }
}
