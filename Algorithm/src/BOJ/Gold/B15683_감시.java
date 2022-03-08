package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15683_감시 {
	static int R, C, size, MIN, cnt;
	static List<cctv> list, list5;
	static int[] numbers;
	static boolean[] isSelect;
	static int[][] office, temp, deltas= {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 상 좌 하 우
	static class cctv{
		int r, c, num;

		public cctv(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		office = new int[R][C];
		list = new ArrayList<>();
		list5 = new ArrayList<>();
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				office[r][c] = Integer.parseInt(st.nextToken());
				if (office[r][c]==5) list5.add(new cctv(r, c, office[r][c])); // cctv5번 저장
				else if (office[r][c]!=0 && office[r][c]!=6) list.add(new cctv(r, c, office[r][c])); // cctv 저장
			}
		}
		// 입력완료
		
		for (int i=0;i<list5.size();i++) {
			cctv now = list5.get(i);
			for (int d=0;d<4;d++) set(now.r, now.c, d, office);
		}
		
		size = list.size();
		numbers = new int[size];
		isSelect = new boolean[4];
		MIN = Integer.MAX_VALUE;
		permutation(0);
		System.out.println(MIN);
	}
	
	static void permutation(int pos) {
		if (pos==size) {
			temp = new int[R][C];
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					temp[r][c] = office[r][c];
				}
			}
			surve();
			
			MIN = Math.min(MIN, cnt);
			if (MIN==0) {
				System.out.println(0);
				System.exit(0);
			}
			return;
		}
		
		for (int i=0;i<4;i++) {
			numbers[pos] = i;
			isSelect[i] = true;
			permutation(pos+1);
			isSelect[i] = false;
		}
	}
	
	static void surve() {
		cnt = 0;
		for (int i=0;i<size;i++) {
			cctv now = list.get(i);
			check(now.r, now.c, now.num, numbers[i]);
		}
		
		for (int i=0;i<R;i++) {
			for (int j=0;j<C;j++) {
				if (temp[i][j]==0) cnt++;
			}
		}
	}
	
	static void check(int r, int c, int num, int d) {
		set(r, c, d, temp);
		if (num==2) set(r, c, (d+2)%4, temp);
		else if (num==3) set(r, c, (d+1)%4, temp);
		else if (num==4) {
			set(r, c, (d+1)%4, temp);
			set(r, c, (d+2)%4, temp);
		}
	}
	
	static void set(int r, int c, int d, int[][] arr) {
		while(true) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc)) {
				if (arr[nr][nc]!=6) arr[nr][nc]=9;
				if (arr[nr][nc]==6) break;
			} else break;
			
			r = nr; c = nc;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
