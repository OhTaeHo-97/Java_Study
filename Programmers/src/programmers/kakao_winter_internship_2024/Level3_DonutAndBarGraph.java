package src.programmers.kakao_winter_internship_2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Level3_DonutAndBarGraph {
    private int maxVertexNumber;
    private int[] inDegrees;
    private int[] outDegrees;
    private Set<Integer> vertexNumbers;
    private Map<Integer, Set<Integer>> graphStatus;

    public int[] solution(int[][] edges) {
        findInDegreeAndOutDegreeOfEachVertex(edges);
        int newVertex = findNewVertex();
        if (newVertex == -1) {
            return new int[]{};
        }

        int[] answer = new int[4];
        answer[0] = newVertex;
        int totalGraphCount = outDegrees[newVertex];
        removeNewVertexAndItsEdges(newVertex);

        int barGraph = findBarGraphCount(newVertex);
        int eightShapeGraph = findEightShapeGraphCount(newVertex);

        answer[1] = totalGraphCount - barGraph - eightShapeGraph;
        answer[2] = barGraph;
        answer[3] = eightShapeGraph;

        return answer;
    }

    private int findEightShapeGraphCount(int newVertex) {
        int count = 0;
        for (int vertex = 1; vertex <= maxVertexNumber; vertex++) {
            if (!vertexNumbers.contains(vertex)) {
                continue;
            }
            if (vertex == newVertex) {
                continue;
            }
            if (inDegrees[vertex] == 2 && outDegrees[vertex] == 2) {
                count++;
            }
        }

        return count;
    }

    private int findBarGraphCount(int newVertex) {
        int noInDegreeVertex = 0;
        int noOutDegreeVertex = 0;
        for (int vertex = 1; vertex <= maxVertexNumber; vertex++) {
            if (!vertexNumbers.contains(vertex)) {
                continue;
            }
            if (vertex == newVertex) {
                continue;
            }

            if (inDegrees[vertex] == 0) {
                noInDegreeVertex++;
            }
            if (outDegrees[vertex] == 0) {
                noOutDegreeVertex++;
            }
        }

        return noInDegreeVertex;
    }

    private void removeNewVertexAndItsEdges(int newVertex) {
        for (int vertex : graphStatus.get(newVertex)) {
            inDegrees[vertex]--;
        }
    }

    private int findNewVertex() {
        for (int vertex = 1; vertex <= maxVertexNumber; vertex++) {
            if (!vertexNumbers.contains(vertex)) {
                continue;
            }
            if (outDegrees[vertex] >= 2 && inDegrees[vertex] == 0) {
                return vertex;
            }
        }

        return -1;
    }

    private void findInDegreeAndOutDegreeOfEachVertex(int[][] edges) {
        maxVertexNumber = Integer.MIN_VALUE;
        inDegrees = new int[edges.length + 2];
        outDegrees = new int[edges.length + 2];
        vertexNumbers = new HashSet<>();
        graphStatus = new HashMap<>();

        for (int[] edge : edges) {
            maxVertexNumber = Math.max(maxVertexNumber, Math.max(edge[0], edge[1]));
            if (!graphStatus.containsKey(edge[0])) {
                graphStatus.put(edge[0], new HashSet<>());
            }
            graphStatus.get(edge[0]).add(edge[1]);
            outDegrees[edge[0]]++;
            inDegrees[edge[1]]++;

            vertexNumbers.add(edge[0]);
            vertexNumbers.add(edge[1]);
        }
    }

    public static void main(String[] args) {
        Level3_DonutAndBarGraph l = new Level3_DonutAndBarGraph();
        System.out.println(Arrays.toString(l.solution(new int[][] {{2, 3}, {4, 3}, {1, 1}, {2, 1}})));
    }
}
