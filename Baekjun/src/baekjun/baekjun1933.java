package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1933 {
    static int N;
    static PriorityQueue<Building> buildings;

    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        buildings = new PriorityQueue<>();

        for(int count = 0; count < N; count++) {
            int start = scanner.nextInt(), height = scanner.nextInt(), end = scanner.nextInt();
            buildings.offer(new Building(start, end, height));
        }
    }

    static void solution() {
        Deque<Building> stack = new ArrayDeque<>();

        for(int count = 0; count < N; count++) {
            Building temp = buildings.peek();
            System.out.println(temp);
            System.out.println(stack);
            System.out.println();
            if(stack.isEmpty()) stack.push(buildings.poll());
            else {
                Building building = buildings.poll(), prevBuilding = stack.peek();
                if(building.height > prevBuilding.height) {
                    if(building.left == prevBuilding.left) {
                        stack.pop();
                        stack.push(building);
                    } else {
                        if(building.left >= prevBuilding.right) {
                            stack.push(building);
                        } else {
                            stack.pop();
                            stack.push(new Building(prevBuilding.left, building.left, prevBuilding.height));
                            if(building.right >= prevBuilding.right) {
                                stack.push(building);
                            } else {
                                stack.push(building);
                                stack.push(new Building(prevBuilding.right, building.right, building.height));
                            }
                        }
                    }
                } else if(building.height < prevBuilding.height) {
                    if(building.left == prevBuilding.left) {
                        if(building.right > prevBuilding.right)
                            stack.push(new Building(prevBuilding.right, building.right, building.height));
                    } else {
                        if(building.left >= prevBuilding.right)
                            stack.push(building);
                        else if(building.right > prevBuilding.right)
                            stack.push(new Building(prevBuilding.right, building.right, building.height));
                    }
                } else {
                    if(building.left > prevBuilding.right)
                        stack.push(building);
                    else if(building.right > prevBuilding.right)
                        stack.push(new Building(prevBuilding.left, building.right, building.height));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            Building building = stack.pollLast();
            sb.append(building.left).append(' ').append(building.height).append(' ');
        }

        System.out.println(sb);
    }

    static class Building implements Comparable<Building> {
        int left, right, height;

        public Building(int left, int right, int height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }

        @Override
        public int compareTo(Building o) {
            if(left != o.left) return left - o.left;
            else if(right != o.right) return right - o.right;
            return height - o.height;
        }

        @Override
        public String toString() {
            return "Building{" +
                    "left=" + left +
                    ", right=" + right +
                    ", height=" + height +
                    '}';
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
