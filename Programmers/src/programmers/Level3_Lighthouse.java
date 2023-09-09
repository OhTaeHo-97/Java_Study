package src.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level3_Lighthouse {
    Map<Integer, List<Integer>> tree;
    int answer;
    public int solution(int n, int[][] lighthouse) {
        makeTree(n, lighthouse);
        isLightOn(1, -1);

        return answer;
    }

    public boolean isLightOn(int lighthouseNum, int prevHouseNum) {
        if(tree.get(lighthouseNum).size() == 1 && tree.get(lighthouseNum).get(0) == prevHouseNum)
            return false;

        boolean isLightOnLighthouse = false;
        for(int nextLightNum : tree.get(lighthouseNum)) {
            if(nextLightNum == prevHouseNum)
                continue;

            if(!isLightOn(nextLightNum, lighthouseNum))
                isLightOnLighthouse = true;
        }

        if(isLightOnLighthouse)
            answer++;

        return isLightOnLighthouse;
    }

    public void makeTree(int n, int[][] lighthouse) {
        tree = new HashMap<>();
        for(int lighthouseNum = 1; lighthouseNum <= n; lighthouseNum++)
            tree.put(lighthouseNum, new ArrayList<>());

        for(int[] road : lighthouse) {
            tree.get(road[0]).add(road[1]);
            tree.get(road[1]).add(road[0]);
        }
    }

    public static void main(String[] args) {
        Level3_Lighthouse l = new Level3_Lighthouse();
        System.out.println(l.solution(8, new int[][] {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}}));
    }
}
