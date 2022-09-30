package programmers;

public class Level3_LockAndKey {
	public static boolean solution(int[][] key, int[][] lock) {
		int kLen = key.length, lLen = lock.length;
		int len = kLen * 2 + lLen - 2;
		int[][] expandArr = new int[len][len];
		for(int row = kLen - 1; row < kLen + lLen - 1; row++) {
			for(int col = kLen - 1; col < kLen + lLen - 1; col++) {
				expandArr[row][col] = lock[row - (kLen - 1)][col - (kLen - 1)];
			}
		}
		
		for(int direction = 0; direction < 4; direction++) {
			if(isRight(expandArr, key, kLen, lLen)) {
				return true;
			}
			rotate(key);
		}
		return false;
	}
	
	public static void rotate(int[][] key) {
		int[][] rotateKey = new int[key.length][key.length];
		for(int row = 0; row < key.length; row++) {
			for(int col = 0; col < key.length; col++) {
				rotateKey[row][col] = key[(key.length - 1) - col][row];
			}
		}
		for(int row = 0; row < key.length; row++) {
			for(int col = 0; col < key.length; col++) {
				key[row][col] = rotateKey[row][col];
			}
		}
	}
	
	public static boolean isRight(int[][] expandArr, int[][] key, int kLen, int lLen) {
		int len = expandArr.length;
		for(int row = 0; row < len - kLen + 1; row++) {
			for(int col = 0; col < len - kLen + 1; col++) {
				for(int kRow = 0; kRow < kLen; kRow++) {
					for(int kCol = 0; kCol < kLen; kCol++) {
						expandArr[row + kRow][col + kCol] += key[kRow][kCol];
					}
				}
				boolean flag = true;
				for(int kRow = kLen - 1; kRow < kLen + lLen - 1; kRow++) {
					for(int kCol = kLen - 1; kCol < kLen + lLen - 1; kCol++) {
						if(expandArr[kRow][kCol] != 1) {
							flag = false;
							break;
						}
					}
					if(!flag) break;
				}
				if(flag) return true;
				for(int kRow = 0; kRow < kLen; kRow++) {
					for(int kCol = 0; kCol < kLen; kCol++) {
						expandArr[row + kRow][col + kCol] -= key[kRow][kCol];
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		System.out.println(solution(key, lock));
	}
}
