package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun14891 {
    static final int SAW_TOOTHED_WHEEL_COUNT = 4;
    static final int SAWTOOTH_COUNT = 8;

    static int rotateCount;
    static int[][] rotateInfos;
    static int[][] sawToothedWheels;

    static void input() {
        Reader scanner = new Reader();

        sawToothedWheels = new int[SAW_TOOTHED_WHEEL_COUNT][SAWTOOTH_COUNT];
        for (int idx = 0; idx < SAW_TOOTHED_WHEEL_COUNT; idx++) {
            String sawtoothInfo = scanner.nextLine();
            for (int sawtoothIdx = 0; sawtoothIdx < SAWTOOTH_COUNT; sawtoothIdx++) {
                sawToothedWheels[idx][sawtoothIdx] = sawtoothInfo.charAt(sawtoothIdx) - '0';
            }
        }

        rotateCount = scanner.nextInt();
        rotateInfos = new int[rotateCount][2];
        for (int idx = 0; idx < rotateCount; idx++) {
            rotateInfos[idx][0] = scanner.nextInt();
            rotateInfos[idx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        for (int[] rotateInfo : rotateInfos) {
            eachRotate(rotateInfo);
        }
        int answer = calculatePoint();
        System.out.println(answer);
    }

    static int calculatePoint() {
        int answer = 0;
        for (int sawToothedWheelIdx = 0; sawToothedWheelIdx < SAW_TOOTHED_WHEEL_COUNT; sawToothedWheelIdx++) {
            answer += sawToothedWheels[sawToothedWheelIdx][0] * (1 << sawToothedWheelIdx);
        }
        return answer;
    }

    static void eachRotate(int[] rotateInfo) {
        int[] rotateDirections = calculateRotateDirection(rotateInfo[0] - 1, rotateInfo[1]);
        for (int sawToothedWheelIdx = 0; sawToothedWheelIdx < SAW_TOOTHED_WHEEL_COUNT; sawToothedWheelIdx++) {
            rotateSawToothedWheel(sawToothedWheelIdx, rotateDirections[sawToothedWheelIdx]);
        }
    }

    static void rotateSawToothedWheel(int sawToothedWheel, int rotateDirection) {
        if (rotateDirection == 0) {
            return;
        }
        if (rotateDirection == 1) {
            rotateClockWise(sawToothedWheels[sawToothedWheel]);
            return;
        }
        rotateCountClockWise(sawToothedWheels[sawToothedWheel]);
    }

    static int[] calculateRotateDirection(int sawToothedWheelNumber, int rotateDirection) {
        int[] rotateDirections = new int[SAW_TOOTHED_WHEEL_COUNT];
        rotateDirections[sawToothedWheelNumber] = rotateDirection;

        for (int idx = sawToothedWheelNumber - 1; idx >= 0; idx--) {
            if (sawToothedWheels[idx][2] == sawToothedWheels[idx + 1][6]) {
                Arrays.fill(rotateDirections, 0, idx, 0);
                rotateDirections[idx] = 0;
                break;
            } else {
                rotateDirections[idx] = -rotateDirections[idx + 1];
            }
        }

        for (int idx = sawToothedWheelNumber + 1; idx < SAW_TOOTHED_WHEEL_COUNT; idx++) {
            if (sawToothedWheels[idx - 1][2] == sawToothedWheels[idx][6]) {
                Arrays.fill(rotateDirections, idx, SAW_TOOTHED_WHEEL_COUNT, 0);
                break;
            } else {
                rotateDirections[idx] = -rotateDirections[idx - 1];
            }
        }

        return rotateDirections;
    }

    static void rotateCountClockWise(int[] sawtooth) {
        int temp = sawtooth[0];
        for (int idx = 0; idx < SAWTOOTH_COUNT - 1; idx++) {
            sawtooth[idx] = sawtooth[idx + 1];
        }
        sawtooth[SAWTOOTH_COUNT - 1] = temp;
    }

    static void rotateClockWise(int[] sawtooth) {
        int temp = sawtooth[SAWTOOTH_COUNT - 1];
        for (int idx = SAWTOOTH_COUNT - 1; idx > 0; idx--) {
            sawtooth[idx] = sawtooth[idx - 1];
        }
        sawtooth[0] = temp;
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
