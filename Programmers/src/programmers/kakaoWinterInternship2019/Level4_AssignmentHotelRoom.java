package src.programmers.kakaoWinterInternship2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Level4_AssignmentHotelRoom {
    Map<Long, Long> nextRooms;
    public long[] solution(long k, long[] room_number) {
        nextRooms = new HashMap<>();
        long[] answer = new long[room_number.length];

        for(int idx = 0; idx < room_number.length; idx++)
            answer[idx] = findNextRoom(room_number[idx]);

        return answer;
    }

    public long findNextRoom(long roomNum) {
        if(!nextRooms.containsKey(roomNum)) {
            nextRooms.put(roomNum, roomNum + 1);
            return roomNum;
        }

        long room = nextRooms.get(roomNum);
        long nextRoom = findNextRoom(room);
        nextRooms.put(roomNum, nextRoom);
        return nextRoom;
    }

    public static void main(String[] args) {
        Level4_AssignmentHotelRoom l = new Level4_AssignmentHotelRoom();
        System.out.println(Arrays.toString(l.solution(10, new long[] {1,3,4,1,3,1})));
    }
}
