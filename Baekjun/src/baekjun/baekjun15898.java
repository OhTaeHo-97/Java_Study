package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun15898 {
    static int n, answer;
    static Material[][] map;
    static Material[][][] materials;
    static int[] candidates;
    static int[][] startIdx;
    static boolean[] visited;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        map = new Material[5][5];
        for(int row = 0; row < map.length; row++)
            Arrays.fill(map[row], new Material(0, 'W'));

        materials = new Material[n][4][4];
        for(int material = 0; material < n; material++) {
            // 효능 입력
            for(int row = 0; row < materials[material].length; row++) {
                for(int col = 0; col < materials[material][row].length; col++)
                    materials[material][row][col] = new Material(scanner.nextInt());
            }

            // 원소 입력
            for(int row = 0; row < materials[material].length; row++) {
                for(int col = 0; col < materials[material][row].length; col++)
                    materials[material][row][col].element = scanner.next().charAt(0);
            }
        }

        candidates = new int[3];
        startIdx = new int[3][2];
        visited = new boolean[n];
    }

    static void solution() {
        setMaterialAndOrder(0);
        System.out.println(answer);
    }

    static void setMaterialAndOrder(int index) {
        if(index == 3) {
            int quality = simulation();
            answer = Math.max(answer, quality);
            return;
        }

        for(int idx = 0; idx < n; idx++) {
            if(!visited[idx]) {
                visited[idx] = true;
                candidates[index] = idx;

                for(int dir = 0; dir < 4; dir++) {
                    for(int row = 0; row <= map.length - materials.length; row++) {
                        for(int col = 0; col <= map[0].length - materials[0].length; col++) {
                            startIdx[index] = new int[] {row, col};
                            setMaterialAndOrder(index + 1);
                        }
                    }

                    materials[idx] = rotate(materials[idx]);
                }

                visited[idx] = false;
            }
        }
    }

    static void print(Material[][] material) {
        for(int row = 0; row < material.length; row++)
            System.out.println(Arrays.toString(material[row]));
    }

    static int simulation() {
        Material[][] copy = copy(map);

        for(int idx = 0; idx < candidates.length; idx++) {
//            print(materials[candidates[idx]]);
//            System.out.println(Arrays.toString(startIdx[idx]));
//            System.out.println();
            change(startIdx[idx][0], startIdx[idx][1], materials[candidates[idx]], copy);
        }

//        print(copy);

        return calculateQuality(copy);
    }

    static int calculateQuality(Material[][] map) {
        int R = 0, B = 0, G = 0, Y = 0;

        for(int row = 0; row < map.length; row++) {
            for(int col = 0; col < map[row].length; col++) {
                char element = map[row][col].element;

                if(element == 'R') R += map[row][col].effect;
                else if(element == 'B') B += map[row][col].effect;
                else if(element == 'G') G += map[row][col].effect;
                else if(element == 'Y') Y += map[row][col].effect;
            }
        }

        return 7 * R + 5 * B + 3 * G + 2 * Y;
    }

    static void change(int startX, int startY, Material[][] material, Material[][] map) {
        for(int row = 0; row < material.length; row++) {
            for(int col = 0; col < material[row].length; col++) {
                int effect = map[startX + row][startY + col].effect + material[row][col].effect;
                if(effect < 0) effect = 0;
                else if(effect > 9) effect = 9;

                map[startX + row][startY + col].effect = effect;

                if(material[row][col].element != 'W')
                    map[startX + row][startY + col].element = material[row][col].element;
            }
        }
    }

    static Material[][] copy(Material[][] map) {
        Material[][] copy = new Material[map.length][map[0].length];
        for(int row = 0; row < copy.length; row++) {
            for(int col = 0; col < copy[row].length; col++)
                copy[row][col] = map[row][col].copy();
        }

        return copy;
    }

    static Material[][] rotate(Material[][] material) {
        Material[][] newMaterial = new Material[material.length][material[0].length];

        for(int row = 0; row < material.length; row++) {
            for(int col = 0; col < material[row].length; col++)
                newMaterial[col][(material.length - 1) - row] = material[row][col];
        }

        return newMaterial;
    }

    static class Material {
        int effect;
        char element;

        public Material(int effect) {
            this.effect = effect;
        }

        public Material(int effect, char element) {
            this.effect = effect;
            this.element = element;
        }

        public Material copy() {
            return new Material(this.effect, this.element);
        }

        @Override
        public String toString() {
            return "Material{" +
                    "effect=" + effect +
                    ", element=" + element +
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
