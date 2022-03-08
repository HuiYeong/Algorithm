package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B14891_톱니바퀴 {
	static int sum;
	static int[][] magnet;
	static List<int[]> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		magnet = new int[5][8];
		for (int i=1;i<=4;i++) {
			String input = br.readLine();
			for (int j=0;j<8;j++) {
				magnet[i][j] = input.charAt(j)-'0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			dir = dir==1?-1:1;
			
			rotate(num, dir);
		}
		
		for (int j=1;j<=4;j++) {
			if(magnet[j][0]==1) sum+=magnet[j][0]*Math.pow(2, j-1);
		}
		
		System.out.println(sum);
	}
	
	static void rotate(int num, int dir) {
		int originDir = dir;
		list = new ArrayList<>();
		list.add(new int[] {num, dir});
		for (int i=num;i<4;i++) {
			if (magnet[i][2] != magnet[i+1][6]) list.add(new int[] {i+1, dir = dir==1?-1:1});
			else break;
		}
		dir = originDir;
		for (int i=num;i>1;i--) {
			if (magnet[i][6] != magnet[i-1][2]) list.add(new int[] {i-1, dir = dir==1?-1:1});
			else break;
		}
		for (int i=0;i<list.size();i++) {
			rotation(list.get(i)[0], list.get(i)[1]);
		}
	}
	
	static void rotation(int num, int dir) { // dir = 1 : 반시계방향, dir = -1 : 시계방향
		int temp = magnet[num][0];
		int current = 0, next = 0, nextValue = 0;
		for (int i=0;i<8;i++) {
			next = current+dir;
			if (next==-1) next = 7;
			else if (next==8) next = 0;
			nextValue = magnet[num][next];
			magnet[num][current] = nextValue;
			if (i==7) magnet[num][current] = temp;
			current = next;
		}
	}
}
