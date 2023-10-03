package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun12767 {
    static int prototypeNum;
    static int layerNum;
    static Set<String> prototypes;

    static void input() {
        Reader scanner = new Reader();

        prototypeNum = scanner.nextInt();
        layerNum = scanner.nextInt();
        prototypes = new HashSet<>();

        for(int idx = 0; idx < prototypeNum; idx++) {
            Tree tree = new Tree();
            for(int nodeNum = 0; nodeNum < layerNum; nodeNum++) {
                tree.insert(scanner.nextInt());
            }
            addNewTreeShape(tree);
        }
    }

    static void addNewTreeShape(Tree tree) {
        Collections.sort(tree.nodeNums);
        StringBuilder sb = new StringBuilder();

        for(int nodeNum : tree.nodeNums) {
            sb.append(nodeNum);
        }

        prototypes.add(sb.toString());
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    static class Tree {
        Node root;
        List<Integer> nodeNums;
        int nodeNum;

        public Tree() {
            this.nodeNums = new ArrayList<>();
        }

        public void insert(int value) {
            Node newNode = new Node(value);
            nodeNum = 1;

            if(root == null) {
                root = newNode;
                nodeNums.add(nodeNum);
                return;
            }

            Node cur = root;
            Node prev = null;
            while(cur != null) {
                prev = cur;

                if(cur.value > value) {
                    cur = cur.left;
                    nodeNum *= 2;
                    if(cur == null) {
                        prev.left = newNode;
                        nodeNums.add(nodeNum);
                        return;
                    }
                } else {
                    cur = cur.right;
                    nodeNum = nodeNum * 2 + 1;
                    if(cur == null) {
                        prev.right = newNode;
                        nodeNums.add(nodeNum);
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        input();
        System.out.println(prototypes.size());
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