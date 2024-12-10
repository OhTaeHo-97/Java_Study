package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2310 {
    private static Reader scanner = new Reader();
    private static StringBuilder answer = new StringBuilder();
    private static int roomCount;
    private static List<Room> rooms;

    private static void input() {
        roomCount = scanner.nextInt();
        if(roomCount == 0) {
            System.out.print(answer);
            System.exit(0);
        }

        rooms = new ArrayList<>();
        for(int room = 0; room < roomCount; room++) {
            String status = scanner.next();
            int cost = Integer.parseInt(scanner.next());
            List<Integer> nextRooms = new ArrayList<>();
            while(true) {
                String nextRoom = scanner.next();
                if(nextRoom.equals("0")) {
                    break;
                }
                nextRooms.add(Integer.parseInt(nextRoom));
            }
            rooms.add(new Room(status.charAt(0), cost, nextRooms));
        }
    }

    private static void solution() {
        Room startRoom = rooms.get(0);
        if(startRoom.status == 'T' && startRoom.cost > 0) {
            answer.append("No").append('\n');
            return;
        }
        int cost = startRoom.status == 'L' ? startRoom.cost : 0;
        if(bfs(cost)) {
            answer.append("Yes").append('\n');
        } else {
            answer.append("No").append('\n');
        }
    }

    private static boolean bfs(int cost) {
        Queue<Node> queue = new LinkedList<>();
        int[] costs = new int[roomCount + 1];
        Arrays.fill(costs, Integer.MIN_VALUE);

        queue.offer(new Node(cost, 1));
        costs[1] = cost;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            if(cur.cost < costs[cur.roomNumber]) {
                continue;
            }

            Room curRoom = rooms.get(cur.roomNumber - 1);
            for(int nextRoomNumber : curRoom.nextRooms) {
                Room nextRoom = rooms.get(nextRoomNumber - 1);
                int nextCost = cur.cost;
                if(nextRoom.status == 'L') {
                    if(nextCost < nextRoom.cost) {
                        nextCost = nextRoom.cost;
                    }
                } else if(nextRoom.status == 'T') {
                    if(nextCost < nextRoom.cost) {
                        continue;
                    }
                    nextCost -= nextRoom.cost;
                }

                if(costs[nextRoomNumber] < nextCost) {
                    costs[nextRoomNumber] = nextCost;
                    queue.offer(new Node(nextCost, nextRoomNumber));
                }
            }
        }

        return costs[roomCount] != Integer.MIN_VALUE;
    }

    static class Node {
        int cost;
        int roomNumber;

        public Node(int cost, int roomNumber) {
            this.cost = cost;
            this.roomNumber = roomNumber;
        }
    }

    static class Room {
        char status;
        int cost;
        List<Integer> nextRooms;

        public Room(char status, int cost, List<Integer> nextRooms) {
            this.status = status;
            this.cost = cost;
            this.nextRooms = nextRooms;
        }
    }

    public static void main(String[] args) {
        while (true) {
            input();
            solution();
        }
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
