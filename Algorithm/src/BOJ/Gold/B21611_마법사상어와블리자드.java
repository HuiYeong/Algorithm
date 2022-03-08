package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B21611_마법사상어와블리자드 {
	static int N, M, S, D, sharkR, sharkC;
	static int[] destoryCnt;
	static int[][] map, moveDir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}}, mapDir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static List<Integer> list, temp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		destoryCnt = new int[4];
		sharkR = (N+1)/2-1; sharkC = (N+1)/2-1;
		
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new ArrayList<>();
		temp = new ArrayList<>();
		list.add(0);
		
		init();
		for (int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			destoryBead();
			bombBead();
			if (m+1<M) transBead();
		}
		System.out.println((1*destoryCnt[1])+(2*destoryCnt[2])+(3*destoryCnt[3]));
	}
	
	static void destoryBead() {
		for (int s=1;s<=S;s++) {
			int nr = sharkR + moveDir[D][0] * s;
			int nc = sharkC + moveDir[D][1] * s;
			
			if (!isIn(nr, nc) || (map[nr][nc] < list.size() && list.get(map[nr][nc]) == 0)) break;
			
			temp.add(map[nr][nc]);
		}
		for (int t=0;t<temp.size();t++) {
			list.remove(temp.get(t)-t);
			list.add(0);
		}
		
		temp.clear();
	}
	
	static void bombBead() {
		while(true) {
			int pre = 1, preNum = list.get(pre), cnt = 1;
			boolean flag = false;
			for (int i=2;i<list.size();i++) {
				int now = list.get(i);
				if (preNum == now) cnt++;
				else if ((cnt >= 4 && preNum != now) || (now == 0 && cnt >= 4)) {
					flag = true;
					int idx = i-cnt; destoryCnt[preNum] += cnt;
					for (int c=0;c<cnt;c++) {
						list.remove(idx);
						list.add(0);
					}
					preNum = list.get(pre); i = pre; cnt = 1;
					if (now == 0) break;
				} else if (preNum != now || now == 0){
					if (cnt>1) pre = i-cnt;
					else pre = i-1;
					preNum = now;
					cnt = 1;
					if (now == 0) break;
				}
			}
			if (!flag) break;
		}
	}
	
	static void transBead() {
		temp.add(0);
		int preNum = list.get(1), cnt = 1;
		for (int i=2;i<list.size();i++) {
			int now = list.get(i);
			if (preNum == now) cnt++;
			else if (preNum != now){
				temp.add(cnt);
				if (temp.size()==N*N) break;
				temp.add(preNum);
				if (temp.size()==N*N) break;
				preNum = now; cnt = 1;
			}
		}
		
		for (int j=temp.size();j<N*N;j++) {
			temp.add(0);
		}
		
		list = new ArrayList<>(temp);
		temp.clear();
	}
	
	static void init() {
		int r = sharkR, c = sharkC;
		int dir = 0, cnt = 1, dirCnt = 0, lenCnt = 1;
		stop:while(true) {
			if (cnt == N*N) break;
			int[] nowDir = mapDir[dir];
			
			for (int i=0;i<lenCnt;i++) {
				int nr = r + nowDir[0];
				int nc = c + nowDir[1];
				if (!isIn(nr, nc)) continue;
				
				list.add(map[nr][nc]);
				map[nr][nc] = cnt++;
				if (cnt == N*N) break stop;
				
				r = nr; c = nc;
			}
			
			dir = (dir+1)%4;
			
			if (++dirCnt == 2) {
				dirCnt = 0;
				lenCnt++;
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
