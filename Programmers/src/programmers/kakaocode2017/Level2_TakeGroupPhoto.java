package programmers.kakaocode2017;

import java.util.*;

public class Level2_TakeGroupPhoto {
    private static final int FRIEND_COUNT = 8;
    private static final Map<Character, Integer> FRIENDS = new HashMap<>() {{
        put('A', 0);
        put('C', 1);
        put('F', 2);
        put('J', 3);
        put('M', 4);
        put('N', 5);
        put('R', 6);
        put('T', 7);
    }};

    private int answer;
    private int[] friendSequence;
    private boolean[] visited;
    private String[] conditions;

    public int solution(int n, String[] data) {
        friendSequence = new int[FRIEND_COUNT];
        visited = new boolean[FRIEND_COUNT];
        conditions = data;
        answer = 0;
        selectSequence(0);
        return answer;
    }

    public void selectSequence(int count) {
        if(count == FRIEND_COUNT) {
            if(isPossibleSequence()) answer++;
            return;
        }

        for(int idx = 0; idx < FRIEND_COUNT; idx++) {
            if(visited[idx]) continue;

            visited[idx] = true;
            friendSequence[idx] = count;
            selectSequence(count + 1);
            visited[idx] = false;
            friendSequence[idx] = 0;
        }
    }

    public boolean isPossibleSequence() {
        for(String condition : conditions) {
            char basis = condition.charAt(0);
            char opponent = condition.charAt(2);
            char conditionRange = condition.charAt(3);
            int distance = condition.charAt(4) - '0';

            int startIdx = friendSequence[FRIENDS.get(basis)];
            int endIdx = friendSequence[FRIENDS.get(opponent)];
            int curDistance = Math.abs(startIdx - endIdx) - 1;

            if(conditionRange == '=') {
                if(curDistance != distance) return false;
            } else if(conditionRange == '>') {
                if(curDistance <= distance) return false;
            } else {
                if(curDistance >= distance) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Level2_TakeGroupPhoto l = new Level2_TakeGroupPhoto();
        System.out.println(l.solution(2, new String[] {"N~F=0", "R~T>2"}));
    }
}
