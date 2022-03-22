package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * < 시뮬레이션 문제 >
 * 
 * 주사위는 현재 바닥면에서 상, 하, 좌, 우로 이동했을 때 값을 'dirDice' 배열에 저장한다.
 * 이동 후 이동 방향과 이동하기 전의 바닥면 값을 사용하여 현재 바닥면에서의 dirDice를 찾는다.
 * 
 * 나머지는 문제에 나와있는대로 구현
 */
public class B14499_주사위굴리기 {
	static int R, C, diceR, diceC, before, K, top, bottom, dice[], arr[][], topBottom[][] = {{0, 0}, {1, 6}, {2, 5}, {3, 4}, {4, 3}, {5, 2}, {6, 1}};
	static int dirDice[] = {0, 3, 4, 5, 2}, dir[][] = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		dice = new int[7];
		diceR = Integer.parseInt(st.nextToken());
		diceC = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝
		top = 1; bottom = 6; // 초기 윗면은 1, 바닥면은 6
		st = new StringTokenizer(br.readLine());
		for (int k=0;k<K;k++) {
			int order = Integer.parseInt(st.nextToken());
			int nr = diceR + dir[order][0];
			int nc = diceC + dir[order][1];
			if (!isIn(nr, nc)) continue;
			
			diceR = nr; diceC = nc; before = bottom; 
			bottom = dirDice[order]; top = topBottom[bottom][1]; // 이동 후 윗면, 바닥면 변경
			
			// 바닥면의 상하좌우 값 변경
			if (order>2) dirDice[order==3?4:3] = before; 
			else dirDice[order==2?1:2] = before;
			dirDice[order] = topBottom[before][1];
			
			if (arr[nr][nc] == 0) arr[nr][nc] = dice[bottom]; // 칸이 0이라면 칸에 주사위 바닥면 값 복사
			else { // 칸이 0이 아니라면 칸 값을 주사위 바닥면에 복사한 후 0으로 초기화
				dice[bottom] = arr[nr][nc];
				arr[nr][nc] = 0;
			}
			
			sb.append(dice[top]+"\n"); // 윗면 값 출력
		}
		System.out.println(sb);
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}
