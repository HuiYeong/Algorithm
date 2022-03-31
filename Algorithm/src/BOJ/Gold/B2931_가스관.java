package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * < 구현 문제 >
 * 
 * M에서 시작하여 블록의 방향대로 이동하다 빈 칸을 만난다 == 지워진 블록 위치
 * 넣을 수 있는 블록을 모두 넣어 Z까지 도달 시 맞는 블록이므로 해당 블록을 찾아준다. 
 */
public class B2931_가스관 {
	static int R, C, nr, nc, numberD, dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char arr[][], searchBlock = 'Z', startBlock = 'M', blockArr[];
	static boolean visit[][];
	static Block search, initM, initZ;
	static Queue<Block> queue = new LinkedList<>();
	static class Block {
		int r, c, d;
		char order;
		public Block(int r, int c, int d, char order) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.order = order;
		}
		@Override
		public String toString() {
			return "Block [r=" + r + ", c=" + c + ", d=" + d + ", order=" + order + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		visit = new boolean[R][C];
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				arr[r][c] = input.charAt(c);
				if (arr[r][c] == 'M') initM = new Block(r, c, 0, 'i');
				else if (arr[r][c] == 'Z') initZ = new Block(r, c, 0, 'i');
			}
		}
		
		for (int i=0;i<2;i++) { // M 상하좌우에 아무 블록이 없다면 Z 에서부터 시작
			Block now = i==0?initM:initZ;
			for (int d=0;d<4;d++) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				if (!isIn(nr, nc) || arr[nr][nc] == '.' || arr[nr][nc] == 'Z') continue;
				if (i==1) searchBlock = 'Z'; startBlock = 'M';
				visit[nr][nc] = true;
				queue.offer(new Block(nr, nc, d, arr[nr][nc]));
			}
			if (!queue.isEmpty()) break;
		}
		
		bfs(true);
		System.out.println((search.r+1)+" "+(search.c+1)+" "+move());
	}
	
	static char move() {
		// 빈 칸 이전의 방향에서 이동할 수 있는 블록들
		if (search.d==0) blockArr = new char[] {'|', '+', '1', '4'};
		else if (search.d==1) blockArr = new char[] {'|', '+', '2', '3'};
		else if (search.d==2) blockArr = new char[] {'-', '+', '1', '2'};
		else blockArr = new char[] {'-', '+', '3', '4'};
		
		for (int i=0;i<4;i++) {
			visit = new boolean[R][C];
			visit[search.r][search.c] = true; // 방문처리
			arr[search.r][search.c] = blockArr[i]; // 맵에 현재 탐색하려는 블록 값 넣어주기
			queue.offer(new Block(search.r, search.c, search.d, blockArr[i]));
			if (bfs(false)) return blockArr[i]; 
			queue.clear();
		}
		
		return '.';
	}
	
	static boolean check(char next, int d) {
		// 현재 방향에서 이동할 수 있는 블록 찾기
		if ((d == 0 && ((next == '-' || next == '2' || next == '3'))) ||
			((d == 1 && (next == '-' || next == '1' || next == '4'))) ||
			((d == 2 && (next == '|' || next == '3' || next == '4'))) ||
			((d == 3 && (next == '|' || next == '1' || next == '2')))) return false;
		return true;
	}
	
	static boolean bfs(boolean isSearch) { // isSearch가 true : 지워진 칸 찾기, isSearch가 false : 현재 블록이 맞는지 확인
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-->0) {
				Block now = queue.poll();
				if (!isSearch && arr[now.r][now.c] == searchBlock) return true;
				if (now.order == '|' || now.order == '-') {
					if (!com(now.d, now, isSearch)) return false;;
				} else if (now.order == '+') {
					for (int d=0;d<4;d++) {
						if (!com(d, now, isSearch)) return false;;
					}
				} else {
					if (now.order == '1') numberD = now.d==2?1:3;
					else if (now.order == '2') numberD = now.d==2?0:3;
					else if (now.order == '3') numberD = now.d==3?0:2;
					else if (now.order == '4') numberD = now.d==3?1:2;
					
					if (!com(numberD, now, isSearch)) return false;;
				}
			}
		}
		return false;
	}
	
	static boolean com(int d, Block now, boolean isSearch) {
		nr = now.r + dir[d][0];
		nc = now.c + dir[d][1];
		
		if (!isIn(nr, nc)) return false;
		if (arr[nr][nc] == '.' || !check(arr[nr][nc], d)) { // 빈 칸이거나 갈 수 있는 위치가 아니라면 
			if (isSearch) search = new Block(nr, nc, d, now.order);
			return false; // 끝내주기
		}
		
		if (visit[nr][nc] || arr[nr][nc] == startBlock) return true;
		visit[nr][nc] = true;
		queue.offer(new Block(nr, nc, d, arr[nr][nc]));
		
		return true;
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}
