package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun2841 {
	public int getMinMoveNum(Note[] melody) {
		ArrayList<Integer>[] frets = new ArrayList[6];
		for(int i = 0; i < frets.length; i++) {
			frets[i] = new ArrayList<Integer>();
		}
		int count = 0;
		for(int i = 0; i < melody.length; i++) {
			if(frets[melody[i].string_num - 1].size() == 0) {
				frets[melody[i].string_num - 1].add(melody[i].fret);
				count++;
			} else {
				if(frets[melody[i].string_num - 1].get(frets[melody[i].string_num - 1].size() - 1) != melody[i].fret) {
					if(frets[melody[i].string_num - 1].get(frets[melody[i].string_num - 1].size() - 1) < melody[i].fret) {
						frets[melody[i].string_num - 1].add(melody[i].fret);
						count++;
					} else {
						while(frets[melody[i].string_num - 1].size() != 0 && frets[melody[i].string_num - 1].get(frets[melody[i].string_num - 1].size() - 1) > melody[i].fret) {
							frets[melody[i].string_num - 1].remove(frets[melody[i].string_num - 1].size() - 1);
							count++;
						}
						if(frets[melody[i].string_num - 1].size() == 0) {
							frets[melody[i].string_num - 1].add(melody[i].fret);
							count++;
						} else {							
							if(frets[melody[i].string_num - 1].get(frets[melody[i].string_num - 1].size() - 1) < melody[i].fret) {
								frets[melody[i].string_num - 1].add(melody[i].fret);
								count++;
							}
						}
					}
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int note_num = Integer.parseInt(st.nextToken());
		int fret_num = Integer.parseInt(st.nextToken());
		Note[] melody = new Note[note_num];
		for(int i = 0; i < melody.length; i++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			melody[i] = new Note(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		br.close();
		baekjun2841 b = new baekjun2841();
		bw.write(b.getMinMoveNum(melody) + "\n");
		bw.flush();
		bw.close();
	}
}

class Note {
	int string_num;
	int fret;
	public Note(int string_num, int fret) {
		this.string_num = string_num;
		this.fret = fret;
	}
}