package programmers;

public class Level2_Joystick {
	public int solution(String name) {
        int answer = 0;
        int min = name.length() - 1;

        for(int idx = 0; idx < name.length(); idx++) {
            char curAlphabet = name.charAt(idx);
            int moveNum = Math.min(curAlphabet - 'A', 'Z' - curAlphabet + 1);
            answer += moveNum;

            int nextIdx = idx + 1;
            while(nextIdx < name.length() && name.charAt(nextIdx) == 'A')
                nextIdx++;

            // 현재 위치에서 오른쪽으로 이동 후 글자를 바꾸고 다시 돌아오는 경우
            // 현재 위치에서 왼쪽으로 이동 후 글자를 바꾸고 다시 돌아오는 경우
            min = Math.min(min, Math.min((idx * 2) + name.length() - nextIdx, (name.length() - nextIdx) * 2 + idx));
        }

        answer += min;
        return answer;
    }
}
