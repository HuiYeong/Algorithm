package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B17143_낚시왕 {
	static int R, C, fishKingC, getFish;
	static int[][] map, deltas = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static List<Shark> sharks;
	static class Shark {
		int r, c, s, d, z; // r, c : 상어 위치, s : 속력, d : 이동 방향, z : 크기 
		boolean alive;

		public Shark(int r, int c, int s, int d, int z, boolean alive) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.alive = alive;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 데이터 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		int M = Integer.parseInt(st.nextToken()); // 상어 개수
		
		// 초기화
		map = new int[R][C];
		sharks = new ArrayList<>();
		fishKingC = 0;
		
		// 상어 입력 받기
		for (int m=1;m<=M;m++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken())-1; 
			int sc = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks.add(new Shark(sr, sc, s, d, z, true));
			map[sr][sc] = m;
		}
		
		while(true) {
			// 낚시왕 이동
			fishKingMove();
		}
	}
	
	static void fishKingMove() {
		for (int r=0;r<R;r++) {
			if (map[r][fishKingC]!=0 && sharks.get(map[r][fishKingC]-1).alive) {
				getFish += sharks.get(map[r][fishKingC]-1).z; // 제일 가까운 물고기 잡았다!
				sharks.get(map[r][fishKingC]-1).alive = false;
				map[r][fishKingC] = 0;
				break; // 한 마리 잡았으면 그만 잡아~
			}
		}
		// 상어 이동
		sharksMove();
		if (fishKingC++==C-1) {
			System.out.println(getFish);
			System.exit(0);
		}; 
	}
	
	static void sharksMove() {
		for (int s=0;s<sharks.size();s++) {
			Shark now = sharks.get(s); // 현재 이동할 상어
			int sr = now.r;
			int sc = now.c;
			if (now.alive) {
				for (int speed=0;speed<now.s;speed++) { // 속력만큼 이동
					// 이동 후 원래 자리 변경 위해 변수로 저장
					if (now.d<=2) { // 상하 이동 
						sr += deltas[now.d][0];
						if (sr<0 || sr>=R) {
							if (now.d%2==0) now.d--; 
							else now.d++;
							sr += deltas[now.d][0]*2;
						}
					} else { // 좌우 이동
						sc += deltas[now.d][1];
						if (sc<0 || sc>=C) {
							if (now.d%2==0) now.d--; 
							else now.d++;
							sc += deltas[now.d][1]*2;
						}
					}
				}
				if (map[now.r][now.c]==s+1) map[now.r][now.c] = 0; // 원래 있던 자리 비우기
				if (map[sr][sc]!=0 && s>map[sr][sc]-1 && sharks.get(map[sr][sc]-1).alive) { // 만약 내가 이동할 자리에 이미 물고기가 있다면
					// 물고기 크기 비교 
					if (sharks.get(map[sr][sc]-1).z > now.z) { // 기존에 있는게 더 크다면 
						now.alive = false; // 새로운거 죽여!
					} else { // 새로운게 더 크다면
						sharks.get(map[sr][sc]-1).alive = false; // 기존에 있던거 죽여!
						map[sr][sc] = s+1; // 새롭게 변경쓰
					}
				} else map[sr][sc] = s+1;
				
				now.r = sr; now.c = sc;
			}
		}
	}
}
