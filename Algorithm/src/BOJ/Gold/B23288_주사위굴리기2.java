package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * < 시뮬레이션, dfs/bfs 문제 >
 * 
 * 주사위는 현재 바닥면에서 상, 하, 좌, 우로 이동했을 때 값을 'dirDice' 배열에 저장한다.
 * 이동 후 이동 방향과 이동하기 전의 바닥면 값을 사용하여 현재 바닥면에서의 dirDice를 찾는다.
 * 
 * bfs를 이용하여 현재 위치에서 상, 하, 좌, 우로 이동하며 인접한 곳에 있는 값 중 현재 위치의 값과 일치하는 개수를 찾는다.
 * 
 * 마지막으로 주사위 바닥면과 현재 위치의 값을 비교해 이동 방향을 결정해주면 끝
 */
public class B23288_주사위굴리기2 {
	static int R, C, K, d, r, c, nr, nc, cnt, sum, bottom, before, arr[][], dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int dirDice[] = {3, 5, 4, 2}, topBottom[][] = {{0, 0}, {1, 6}, {2, 5}, {3, 4}, {4, 3}, {5, 2}, {6, 1}};
	static boolean visit[][];
	static Queue<int[]> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료
		bottom = 6;
		while(K-->0) {
			nr = r + dir[d][0];
			nc = c + dir[d][1];
			if (!isIn(nr, nc)) { // 만약 범위를 벗어나면
				d = d==0?2:d==1?3:d==2?0:1; // 반대 방향으로 방향 전환
				K++;
				continue;
			}
			
			// 주사위 굴리기
			before = bottom;
			r = nr; c = nc; before = bottom;
			bottom = dirDice[d]; // 이동 후 바닥면 변경
			
			// 바닥면의 상하좌우 값 변경
			if (d%2==1) dirDice[d==1?3:1] = before;
			else dirDice[d==0?2:0] = before;
			dirDice[d] = topBottom[before][1];
			
			// 점수 획득
			bfs(r, c);
			sum += arr[r][c]*cnt;
			
			// 이동방향 결정
			if (bottom > arr[r][c]) d = d+1==4?0:d+1;
			else if (bottom < arr[r][c]) d = d-1<0?3:d-1;
		}
		System.out.println(sum);
	}
	
	static void bfs(int r, int c) {
		// 초기화
		cnt = 1;
		visit = new boolean[R][C];
		// 초기 위치 방문 처리 및 큐에 삽입
		visit[r][c] = true;
		queue.offer(new int[] {r, c});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int nd=0;nd<4;nd++) {
				nr = now[0] + dir[nd][0];
				nc = now[1] + dir[nd][1];
				// 범위를 벗어나거나 값이 일치하지 않거나 이미 방문한 곳이라면 continue;
				if (!isIn(nr, nc) || arr[nr][nc] != arr[r][c] || visit[nr][nc]) continue;
				cnt++;
				visit[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}
