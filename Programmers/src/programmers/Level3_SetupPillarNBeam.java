package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;

public class Level3_SetupPillarNBeam {
//	static int size;
//    static int[][] pillar;
//    static int[][] beam;
//    public static ArrayList<int[]> solution(int n, int[][] build_frame) {
//        size = n;
//        pillar = new int[n + 1][n + 1];
//        beam = new int[n + 1][n + 1];
//        for(int index = 0; index < build_frame.length; index++) {
//            int x = n - build_frame[index][1];
//            int y = build_frame[index][0];
//            int sort = build_frame[index][2];
//            int type = build_frame[index][3];
//            if(type == 0) { // 삭제
//                if(sort == 0) { // 기둥
//                    removePillar(x, y);
//                } else if(sort == 1) { // 보
//                    removeBeam(x, y);
//                }
//            } else if(type == 1) { // 설치
//                if(sort == 0) { // 기둥
//                    setUpPillar(x, y);
//                } else if(sort == 1) { // 보
//                    setUpBeam(x, y);
//                }
//            }
//        }
//        ArrayList<int[]> answer = new ArrayList<int[]>();
//        for(int col = 0; col <= n; col++) {
//            for(int row = n; row > 0; row--) {
//                if(pillar[row][col] > 0) {
//                    int[] location = new int[3];
//                    location[0] = col;
//                    location[1] = size - row;
//                    location[2] = 0;
//                    answer.add(location);
//                    pillar[row][col]--;
//                    pillar[row - 1][col]--;
//                }
//            }
//        }
//        for(int row = size - 1; row >= 0; row--) {
//            for(int col = 0; col < size; col++) {
//                if(beam[row][col] > 0) {
//                    int[] location = new int[3];
//                    location[0] = col;
//                    location[1] = size - row;
//                    location[2] = 1;
//                    answer.add(location);
//                    beam[row][col]--;
//                    beam[row][col + 1]--;
//                }
//            }
//        }
//        Collections.sort(answer, new Comparator<int[]>() {
//           public int compare(int[] l1, int[] l2) {
//               if(l1[0] != l2[0]) return l1[0] - l2[0];
//               else {
//                   if(l1[1] != l2[1]) return l1[1] - l2[1];
//                   else return l1[2] - l2[2];
//               }
//           } 
//        });
//        return answer;
//    }
//    
//    public static void removePillar(int x, int y) {
//        if(pillar[x - 1][y] - 1 > 0) return;
//        if(beam[x - 1][y] > 0) {
//            if(y - 1 < 0) {
//                if(pillar[x - 1][y + 1] == 0) return;
//            } else if(y + 1 > size) {
//                if(pillar[x - 1][y - 1] == 0) return;
//            } else {
//                if(beam[x - 1][y] == 2) {
//                    if(beam[x - 1][y - 1] == 2 && beam[x - 1][y + 1] == 2) {
//                        boolean flag1 = false;
//                        for(int col = y - 1; col >= 0; col--) {
//                            if(pillar[x - 1][col] > 0) {
//                                flag1 = true;
//                                break;
//                            }
//                            if(beam[x - 1][col] == 1) break;
//                        }
//                        boolean flag2 = false;
//                        for(int col = y + 1; col <= size; col++) {
//                            if(pillar[x - 1][col] > 0) {
//                                flag2 = true;
//                                break;
//                            }
//                            if(beam[x - 1][col] == 1) break;
//                        }
//                        if(!flag1 || !flag2) return; 
//                    } else if(beam[x - 1][y - 1] == 2 && beam[x - 1][y + 1] == 1) {
//                        if(pillar[x - 1][y + 1] == 0) return;
//                        boolean flag = false;
//                        for(int col = y - 1; col >= 0; col--) {
//                            if(pillar[x - 1][col] > 0) {
//                                flag = true;
//                                break;
//                            }
//                            if(beam[x - 1][col] == 1) break;
//                        }
//                        if(!flag) return;
//                    } else if(beam[x - 1][y - 1] == 1 && beam[x - 1][y + 1] == 2) {
//                        if(pillar[x - 1][y - 1] == 0) return;
//                        boolean flag = false;
//                        for(int col = y + 1; col <= size; col++) {
//                            if(pillar[x - 1][col] > 0) {
//                                flag = true;
//                                break;
//                            }
//                            if(beam[x - 1][col] == 1) break;
//                        }
//                        if(!flag) return;
//                    } else if(beam[x - 1][y - 1] == 1 && beam[x - 1][y + 1] == 1) {
//                        if(pillar[x - 1][y - 1] == 0 || pillar[x - 1][y + 1] == 0) return;
//                    }
//                } else {
//                    if(beam[x - 1][y - 1] == 1) {
//                        if(pillar[x - 1][y - 1] == 0) return;
//                    } else if(beam[x - 1][y + 1] == 1) {
//                        if(pillar[x - 1][y + 1] == 0) return;
//                    } else if(beam[x - 1][y - 1] == 2) {
//                        boolean flag = false;
//                        for(int col = y - 1; col >= 0; col--) {
//                            if(pillar[x - 1][col] > 0) {
//                                flag = true;
//                                break;
//                            }
//                            if(beam[x - 1][col] == 1) break;
//                        }
//                        if(!flag) return;
//                    } else if(beam[x - 1][y + 1] == 2) {
//                        boolean flag = false;
//                        for(int col = y + 1; col <= size; col++) {
//                            if(pillar[x - 1][col] > 0) {
//                                flag = true;
//                                break;
//                            }
//                            if(beam[x - 1][col] == 1) break;
//                        }
//                        if(!flag) return;
//                    }
//                }
//            }
//        }
//        pillar[x][y]--;
//        pillar[x - 1][y]--;
//    }
//    
//    public static void removeBeam(int x, int y) {
//        if(y - 1 < 0) {
//            if(beam[x][y + 1] - 1 > 0) {
//                if(pillar[x][y + 1] == 0 && pillar[x][y + 2] == 0) return;
//            }
//        } else if(y == size - 1) {
//            if(beam[x][y] - 1 > 0) {
//                if(pillar[x][y - 1] == 0 && pillar[x][y] == 0) return;
//            }
//        } else {
//            if(beam[x][y] == 2 && beam[x][y + 1] == 2) {
//                if(pillar[x][y] == 0 && pillar[x][y - 1] == 0) return;
//                if(pillar[x][y + 1] == 0 && pillar[x][y + 2] == 0) return;
//            } else if(beam[x][y] == 1 && beam[x][y + 1] == 2) {
//                if(pillar[x][y + 1] == 0 && pillar[x][y + 2] == 0) return;
//            } else if(beam[x][y] == 2 && beam[x][y + 1] == 1) {
//                if(pillar[x][y] == 0 && pillar[x][y - 1] == 0) return;
//            }
//        }
//        beam[x][y]--;
//        beam[x][y + 1]--;
//    }
//    
//    public static void setUpPillar(int x, int y) {
//        if(x != size) {
//            if(beam[x][y] == 0) {
//                if(pillar[x][y] == 0) return;
//            }
//        }
//        pillar[x][y]++;
//        pillar[x - 1][y]++;
//    }
//    
//    public static void setUpBeam(int x, int y) {
//        if(pillar[x][y] == 0 && pillar[x][y + 1] == 0) {
//            if(beam[x][y] == 0 || beam[x][y + 1] == 0) return;
//        }
//        beam[x][y]++;
//        beam[x][y + 1]++;
//    }
	
	static boolean[][] pillar, beam;
	static int N;
	static Deque<int[]> list = new ArrayDeque<int[]>();
	
	public static int[][] solution(int n, int[][] build_frame) {
		N = n + 1;
		pillar = new boolean[N][N];
		beam = new boolean[N][N];
		
		for(int[] bf : build_frame) {
			int x = bf[0];
			int y = bf[1];
			int sort = bf[2];
			int type = bf[3];
			if(sort == 0 && type == 1) {
				if(pillarCheck(x, y)) {
					pillar[x][y] = true;
					list.add(new int[] {x, y, sort});
				}
			}
			if(sort == 1 && type == 1) {
				if(beamCheck(x, y)) {
					beam[x][y] = true;
					list.add(new int[] {x, y, sort});
				}
			}
			if(sort == 0 && type == 0) {
				pillar[x][y] = false;
				remove(x, y, sort);
				if(!check()) {
					pillar[x][y] = true;
					list.add(new int[] {x, y, sort});
				}
			}
			if(sort == 1 && type == 0) {
				beam[x][y] = false;
				remove(x, y, sort);
				if(!check()) {
					beam[x][y] = true;
					list.add(new int[] {x, y, sort});
				}
			}
		}
		return toArr();
	}
	
	static int[][] toArr() {
		Iterator<int[]> iterator = list.iterator();
		int[][] answer = new int[list.size()][3];
		int index = 0;
		while(iterator.hasNext()) {
			answer[index++] = iterator.next();
		}
		Arrays.sort(answer, new Comparator<int[]>() {
			public int compare(int[] l1, int[] l2) {
				if(l1[0] != l2[0]) return l1[0] - l2[0];
				else {
					if(l1[1] != l2[1]) return l1[1] - l2[1];
					else return l1[2] - l2[2];
				}
			}
		});
		return answer;
	}
	
	static boolean beamCheck(int x, int y) {
		if(y == 0) return false;
		else if(pillar[x][y - 1] || pillar[x + 1][y - 1]) return true;
		else if(x - 1 >= 0 && x + 1 < N && beam[x - 1][y] && beam[x + 1][y]) return true;
		return false;
	}
	
	static boolean pillarCheck(int x, int y) {
		if(y == 0) return true;
		else if((x - 1 >= 0 && beam[x - 1][y]) || beam[x][y]) return true;
		else if(y - 1 >= 0 && pillar[x][y - 1]) return true;
		return false;
	}
	
	static void remove(int x, int y, int sort) {
		int size = list.size();
		for(int index = 0; index < size; index++) {
			int[] temp = list.pollFirst();
			if(!(temp[0] == x && temp[1] == y && temp[2] == sort)) list.addLast(temp);
		}
	}
	
	static boolean check() {
		Iterator<int[]> iterator = list.iterator();
		while(iterator.hasNext()) {
			int[] temp = iterator.next();
			if(temp[2] == 1) {
				if(!beamCheck(temp[0], temp[1])) return false;
			} else {
				if(!pillarCheck(temp[0], temp[1])) return false;
			}
		}
		return true;
	}
    
	public static void main(String[] args) {
//		int n = 5;
//		int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
		int n = 5;
		int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1, 1, 1, 0},{2, 2, 0, 1}};
//		int n = 105;
//		int[][] build_frame = {{2,0,0,1},{100,0,0,1},{100,1,1,1},{99,1,1,1},{99,1,0,1},{99,0,0,1}};
		int[][] answer = solution(n, build_frame);
		for(int[] a : answer) {
			System.out.println("[" + a[0] + " " + a[1] + " " + a[2] + "]");
		}
	}

}
