package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B20057_마법사상어와토네이도 {
	static int N, depth, r, c, d, cnt, ans, dirTrans, depTrans;
	static int[][] map, dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st = null;
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		depth = 1;
		r = c = N/2;
		while(true) {
			if (r==0 && c==0) break;
			tornado();
			r += dir[d][0]; c += dir[d][1];
			if (++dirTrans==depth) { // 방향을 depth만큼 바꿨으면 
				dirTrans = 0;
				d = (d+1)%4; // 방향 전환
				if (++depTrans==2) { // 방향을 2번 전환했으면
					depTrans = 0;
					depth++; // depth 늘려주기
				}
			}
		}
		System.out.println(ans);
	}
	
	static void tornado() {
		int nr = r+dir[d][0];
		int nc = c+dir[d][1];
		cnt = 0;
		
		if (d%2==1) sand(nr, nc, 0, 2); // 좌, 우 일 때
		else sand(nr, nc, 1, 3); // 상, 하 일 때
		
		// a 위치에 남은 모래 양을 넣어준다.      
		if (isIn(nr+dir[d][0], nc+dir[d][1])) map[nr+dir[d][0]][nc+dir[d][1]] += map[nr][nc]-cnt;
		// a 위치가 격자를 벗어난 곳이면 ans에 더해준다. 
		else ans += map[nr][nc]-cnt;
	}
	
	static void sand(int nr, int nc, int d1, int d2) {
		// 1%
		sandMove(r + dir[d1][0], c + dir[d1][1], 1);
		sandMove(r + dir[d2][0], c + dir[d2][1], 1);
		// 2%
		sandMove(nr + dir[d1][0] * 2, nc + dir[d1][1] * 2, 2);
		sandMove(nr + dir[d2][0] * 2, nc + dir[d2][1] * 2, 2);
		// 5%
		sandMove(nr + dir[d][0] * 2, nc + dir[d][1] * 2, 5);
		// 7%
		sandMove(nr + dir[d1][0], nc + dir[d1][1], 7);
		sandMove(nr + dir[d2][0], nc + dir[d2][1], 7);
		// 10%
		sandMove(nr + dir[d][0] + dir[d1][0], nc + dir[d][1] + dir[d1][1], 10);
		sandMove(nr + dir[d][0] + dir[d2][0], nc + dir[d][1] + dir[d2][1], 10);
	}
	
	static void sandMove(int mr, int mc, int ratio) {
		int value = (int)(map[r+dir[d][0]][c+dir[d][1]]*ratio*0.01);
		// 격자 안이라면 지정 위치에 값을 더해줌
		if (isIn(mr, mc)) map[mr][mc]+=value;
		// 격자를 벗어난다면 ans에 대해줌
		else ans+=value; 
		
		// 모래가 전부 흩날린 후 남은 모래의 양을 a에 더해야 하기 때문에 cnt를 사용
		cnt+=value;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}

