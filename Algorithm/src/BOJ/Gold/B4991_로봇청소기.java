package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 푼 아이디어
 * 더러운 곳을 큐에 넣고 bfs를 돌려 로봇청소기까지의 거리와 다른 더러운 곳까지의 거리를 저장
 * 순열을 돌려 로봇청소기에서 모든 더러운 곳까지 이동할 수 있는 순서 지정 후 
 * 미리 구한 거리 값을 활용하여 최솟값 구하기
 * 
 */

public class B4991_로봇청소기 {
	static int R, C, MIN, start, size, sum, key, cnt, dirtyCnt, select[], dist[][], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Dirty now;
	static char arr[][];
	static boolean visit[][], perVisit[];
	static String input;
	static Queue<Dirty> queue = new LinkedList<>();
	static List<Dirty> list = new ArrayList<>();
	static class Dirty {
		int r, c, search;

		public Dirty(int r, int c, int search) {
			super();
			this.r = r;
			this.c = c;
			this.search = search; // 내가 찾아야 하는 거리의 로봇 청소기 & 다른 더러운 곳
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if (R==0 && C==0) break;
			arr = new char[R][C];
			dist = new int[11][11]; // 거리 저장할 배열
			list.clear();
			dirtyCnt = 1; key = 1;
			list.add(new Dirty(-1, 0, 0)); // list의 0번째는 로봇 청소기
			for (int r=0;r<R;r++) {
				input = br.readLine();
				 for (int c=0;c<C;c++) {
					 arr[r][c] = input.charAt(c);
					 if (arr[r][c] == '*') { 
						 arr[r][c] = Character.forDigit(dirtyCnt, 10); // 맵에 순서 번호로 저장
						 key |= 1<<dirtyCnt; // 내가 찾아야 할 거리들의 번호
						 list.add(new Dirty(r, c, key));
						 dirtyCnt++;
					 }
					 else if (arr[r][c] == 'o' && list.get(0).r == -1) {
						 list.get(0).r = r; list.get(0).c = c;
					 }
				 }
			}
			select = new int[dirtyCnt-1];
			perVisit = new boolean[dirtyCnt];
			distSearch();
			MIN = Integer.MAX_VALUE;
			permutation(0);
			sb.append((MIN=MIN==Integer.MAX_VALUE?-1:MIN==0?-1:MIN)+"\n");
		}
		System.out.println(sb);
	}
	
	static void permutation(int num) { // 순열
		if (num == dirtyCnt-1) {
			sum = 0;
			if (searchMin()==-1) return;
			MIN = Math.min(MIN, sum);
			return;
		}
		
		for (int i=1;i<dirtyCnt;i++) {
			if (perVisit[i]) continue;
			select[num] = i;
			perVisit[i] = true;
			permutation(num+1);
			perVisit[i] = false;
		}
	}
	
	static int searchMin() {
		start = 0;
		for (int i=0;i<dirtyCnt-1;i++) { 
			if (dist[start][select[i]] == 0) return -1; // 이동 거리가 없다면 그만
			sum += dist[start][select[i]]; // 이동거리 더하기
			start = select[i]; // 시작 위치를 현재의 목적지 위치로 변경
		}
		return sum;
	}
	
	static void distSearch() { // 거리 찾기
		for (int i=list.size()-1;i>0;i--) { // 마지막 더러운 곳은 이미 앞에서 찾았으므로 안찾아도 됨
			now = list.get(i);
			queue.offer(now);
			cnt = 1; key = 1<<i; // 내가 현재 가진 거리는 내 자신 뿐
			visit = new boolean[R][C];
			visit[now.r][now.c] = true; 
			bfs(i);
			queue.clear();
		}
	}
	
	static void bfs(int i) {
		while(!queue.isEmpty()) {
			size = queue.size();
			while(size-->0) {
				now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					if (!isIn(nr, nc) || arr[nr][nc] == 'x' || visit[nr][nc]) continue;
					
					if (arr[nr][nc] == 'o' && dist[0][i] == 0) { // 만약 로봇청소기라면
						key |= 1; // 내가 찾은 키에 저장
						dist[0][i] = cnt;
					}
					else if (arr[nr][nc] != '.' && dist[i][arr[nr][nc]-'0'] == 0) { // 더러운 곳이라면
						dist[i][arr[nr][nc]-'0'] = cnt;
						dist[arr[nr][nc]-'0'][i] = cnt;
						key |= 1<<arr[nr][nc]-'0'; // 내가 찾은 로봇 청소기 & 더러운 곳에 해당 번호 추가
					}
					if (key == now.search) return; // 다 찾았다면 그만
					visit[nr][nc] = true;
					queue.offer(new Dirty(nr, nc, now.search));
				}
			}
			cnt++;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
