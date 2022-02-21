package baekjun;

import java.util.Scanner;
import java.util.ArrayList;

public class baekjun1236 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int column = sc.nextInt();
        String[] map = new String[row];
        for(int i = 0; i < map.length; i++)
            map[i] = sc.next();
        
        ArrayList<Integer> row_blank = new ArrayList<Integer>();
        ArrayList<Integer> col_blank = new ArrayList<Integer>();
        for(int i = 0; i < map.length; i++) {
            boolean flag = false;
            for(int j = 0; j < map[i].length(); j++) {
                if(map[i].charAt(j) == 'X') {
                    flag = true;
                    break;
                }
            }
            if(flag)
                continue;
            else
                row_blank.add(i);
        }
        
        for(int i = 0; i < map[0].length(); i++) {
            boolean flag = false;
            for(int j = 0; j < map.length; j++) {
                if(map[j].charAt(i) == 'X') {
                    flag = true;
                    break;
                }
            }
            if(flag)
                continue;
            else
                col_blank.add(i);
        }
        
        if(col_blank.size() >= row_blank.size())
            System.out.println(col_blank.size());
        else
            System.out.println(row_blank.size());
    }
}