package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun15898 {
    private static final int SIZE = 5;
    private static final int SELECT_SIZE = 3;
    private static final int MATERIAL_SIZE = 4;
    private static Map<Character, Integer> points = new HashMap<>() {{
        put('R', 7);
        put('B', 5);
        put('G', 3);
        put('Y', 2);
        put('W', 0);
    }};

    private static int answer;
    private static int materialCount;
    private static Material[][][] materials;
    private static int[] selectedMaterials;
    private static int[] rotateDirections;
    private static boolean[] visited;
    private static Material[][] gama;

    private static void input() {
        Reader scanner = new Reader();

        materialCount = scanner.nextInt();
        materials = new Material[materialCount][MATERIAL_SIZE][MATERIAL_SIZE];
        selectedMaterials = new int[SELECT_SIZE];
        rotateDirections = new int[SELECT_SIZE];
        visited = new boolean[materialCount];
        gama = new Material[SIZE][SIZE];

        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                gama[row][col] = new Material();
            }
        }

        for(int count = 0; count < materialCount; count++) {
            for(int row = 0; row < MATERIAL_SIZE; row++) {
                for(int col = 0; col < MATERIAL_SIZE; col++) {
                    int quality = scanner.nextInt();
                    materials[count][row][col] = new Material(quality);
                }
            }
            for(int row = 0; row < MATERIAL_SIZE; row++) {
                for(int col = 0; col < MATERIAL_SIZE; col++) {
                    materials[count][row][col].color = scanner.next().charAt(0);
                }
            }
        }
    }

    private static void solution() {
        selectMaterial(0);
        System.out.println(answer);
    }

    private static void selectMaterial(int selectedCount) {
        if(selectedCount >= SELECT_SIZE) {
//            simulation(0, gama);
            simulation(0, new MaterialInfo[SELECT_SIZE]);
            return;
        }

        for(int idx = 0; idx < materialCount; idx++) {
            if(visited[idx]) continue;

            selectedMaterials[selectedCount] = idx;
            visited[idx] = true;
            selectMaterial(selectedCount + 1);
            selectedMaterials[selectedCount] = 0;
            visited[idx] = false;
        }
    }

//    private static void simulation(int materialIdx, Material[][] gama) {
//        if(materialIdx >= selectedMaterials.length) {
//            answer = Math.max(answer, calculateQuality(gama));
//            return;
//        }
//
//        for(int row = 0; row <= SIZE - MATERIAL_SIZE; row++) {
//            for(int col = 0; col <= SIZE - MATERIAL_SIZE; col++) {
//                Material[][] material = materials[selectedMaterials[materialIdx]];
//                for(int rotateCount = 0; rotateCount < 4; rotateCount++) {
//                    Material[][] copy = copyGama(gama);
//                    if(rotateCount == 0) {
//                        putMaterial(row, col, material, copy);
//                        simulation(materialIdx + 1, copy);
//                    } else {
//                        material = rotate90(material);
//                        putMaterial(row, col, material, copy);
//                        simulation(materialIdx + 1, copy);
//                    }
//                }
//            }
//        }
//    }

    private static void simulation(int materialIdx, MaterialInfo[] materialInfos) {
        if(materialIdx >= selectedMaterials.length) {
            answer = Math.max(answer, calculateQuality(materialInfos));
            return;
        }

        for(int row = 0; row <= SIZE - MATERIAL_SIZE; row++) {
            for(int col = 0; col <= SIZE - MATERIAL_SIZE; col++) {
                for(int rotateIdx = 0; rotateIdx < 4; rotateIdx++) {
                    materialInfos[materialIdx] = new MaterialInfo(row, col, selectedMaterials[materialIdx], rotateIdx);
                    simulation(materialIdx + 1, materialInfos);
                }
//                Material[][] material = materials[selectedMaterials[materialIdx]];
//                for(int rotateCount = 0; rotateCount < 4; rotateCount++) {
//                    Material[][] copy = copyGama(gama);
//                    if(rotateCount == 0) {
//                        putMaterial(row, col, material, copy);
//                        simulation(materialIdx + 1, copy);
//                    } else {
//                        material = rotate90(material);
//                        putMaterial(row, col, material, copy);
//                        simulation(materialIdx + 1, copy);
//                    }
//                }
            }
        }
    }

//    private static int calculateQuality(Material[][] gama) {
//        int totalQuality = 0;
//
//        for(int row = 0; row < SIZE; row++) {
//            for(int col = 0; col < SIZE; col++) {
//                totalQuality += (gama[row][col].quality * points.get(gama[row][col].color));
//            }
//        }
//
//        return totalQuality;
//    }

    private static int calculateQuality(MaterialInfo[] materialInfos) {
        int totalQuality = 0;

        Material[][] gama = new Material[SIZE][SIZE];
        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                gama[row][col] = new Material();
            }
        }

        for(MaterialInfo materialInfo : materialInfos) {
            if(materialInfo.rotateIdx != 0) {
                Material[][] material = materials[materialInfo.materialIdx];
                for(int count = 0; count < materialInfo.rotateIdx; count++) {
                    material = rotate90(material);
                }

                putMaterial(materialInfo.x, materialInfo.y, material, gama);
            }
        }

        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                totalQuality += (gama[row][col].quality * points.get(gama[row][col].color));
            }
        }

        return totalQuality;
    }

    private static void putMaterial(int x, int y, Material[][] material, Material[][] gama) {
        for(int row = x; row < x + MATERIAL_SIZE; row++) {
            for(int col = y; col < y + MATERIAL_SIZE; col++) {
                int quality = gama[row][col].quality + material[row - x][col - y].quality;
                if(quality < 0) quality = 0;
                if(quality > 9) quality = 9;
                char color = material[row - x][col - y].color == 'W' ? gama[row][col].color : material[row - x][col - y].color;

                gama[row][col].quality = quality;
                gama[row][col].color = color;
            }
        }
    }

    private static Material[][] copyGama(Material[][] gama) {
        Material[][] copyGama = new Material[SIZE][SIZE];
        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                Material materialElement = gama[row][col];
                copyGama[row][col] = new Material(materialElement.quality, materialElement.color);
            }
        }
        return copyGama;
    }

    private static Material[][] rotate90(Material[][] material) {
        Material[][] copy = new Material[MATERIAL_SIZE][MATERIAL_SIZE];
        for(int count = 0; count < MATERIAL_SIZE / 2; count++) {
            for(int row = count; row < MATERIAL_SIZE - count; row++) {
                copy[row][(MATERIAL_SIZE - 1) - count] = new Material(material[0][row].quality, material[0][row].color);
                copy[row][count] = new Material(material[(MATERIAL_SIZE - 1) - count][row].quality, material[(MATERIAL_SIZE - 1) - count][row].color);
            }
            for(int col = count; col < MATERIAL_SIZE - count; col++) {
                copy[(MATERIAL_SIZE - 1) - count][col] = new Material(material[(MATERIAL_SIZE - 1) - col][(MATERIAL_SIZE - 1) - count].quality, material[(MATERIAL_SIZE - 1) - col][(MATERIAL_SIZE - 1) - count].color);
                copy[count][col] = new Material(material[(MATERIAL_SIZE - 1) - col][0].quality, material[(MATERIAL_SIZE - 1) - col][0].color);
            }
        }

        return copy;
    }

    static class MaterialInfo {
        int x;
        int y;
        int materialIdx;
        int rotateIdx;

        public MaterialInfo(int x, int y, int materialIdx, int rotateIdx) {
            this.x = x;
            this.y = y;
            this.materialIdx = materialIdx;
            this.rotateIdx = rotateIdx;
        }
    }

    static class Material {
        int quality = 0;
        char color = 'W';

        public Material() {
            quality = 0;
            color = 'W';
        }

        public Material(int quality) {
            this.quality = quality;
        }

        public Material(int quality, char color) {
            this.quality = quality;
            this.color = color;
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
                } catch(IOException e) {
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
