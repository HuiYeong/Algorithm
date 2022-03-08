package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B18809_Gaaaaaaaaaarden {
	static int R, C, Red, Green, MAX, flower, select[], green[], visit[][], temp[][], map[][], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean check[];
	static List<int[]> list;
	static Queue<Flower> queue;
	static class Flower {
		int r, c, color;

		public Flower(int r, int c, int color) {
			super();
			this.r = r;
			this.c = c;
			this.color = color;
		}

		@Override
		public String toString() {
			return "Flower [r=" + r + ", c=" + c + ", color=" + color + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Green = Integer.parseInt(st.nextToken());
		Red = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		list = new ArrayList<>();
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) list.add(new int[] {r, c});
			}
		}
		select = new int[Green+Red];
		green = new int[Green];
		MAX = Integer.MIN_VALUE;
		combination(0, 0); // 배양액 뿌릴 땅 구하기
		System.out.println(MAX);
	}
	
	static void combination(int cnt, int start) { // 배양액을 뿌릴 수 있는 위치 중 배양액의 총 수 (초록색 배양액 + 빨강색 배양액)만큼 뿌릴 위치 구하기
		if (cnt == Green + Red) {
			GreenOrRed(0, 0); // 무슨 색 뿌릴지 정하쟈!!
			return;
		}
		
		for (int i=start;i<list.size();i++) {
			select[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	static void GreenOrRed(int cnt, int start) { // 배양액을 뿌릴 위치 중 어디에 무슨 색을 뿌릴지 구하기 (초록색 배양액 기준)
		if (cnt == Green) {
			flower = 0;
			check = new boolean[select.length]; // 배양액을 뿌린 위치 확인
			temp = new int[R][C]; // map을 계속 copy하지 않고 배양액과 꽃 위치 저장할 배열 선언
			visit = new int[R][C]; // 방문 배열 (레벨 확인)
			// 초록색 배양액 뿌리기
			for (int i=0;i<Green;i++) {
				check[green[i]] = true;
				int[] now = list.get(select[green[i]]);
				temp[now[0]][now[1]] = 5; // 5 가 그린 ~
				visit[now[0]][now[1]] = 1; // 방문처리
				queue.offer(new Flower(now[0], now[1], 5));
			}
			for (int i=0;i<check.length;i++) {
				if (!check[i]) { // 초록색 배양액이 뿌려지지 않은 위치가 빨강색 배양액이 뿌려질 위치
					int[] now = list.get(select[i]);
					temp[now[0]][now[1]] = 6; // 6 이 뤠드 ~
					visit[now[0]][now[1]] = 1; // 방문처리
					queue.offer(new Flower(now[0], now[1], 6));
				}
			}
			
			bfs();
			MAX = Math.max(MAX, flower); // 최댓값 구하기
			return;
		}
		
		for (int i=start;i<select.length;i++) {
			green[cnt] = i;
			GreenOrRed(cnt+1, i+1);
		}
	}
	
	static void bfs() {
		int cnt = 2; // 배양액 뿌릴 때 레벨 1로 뿌렸으니 2부터 시작
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int s=0;s<size;s++) {
				Flower now = queue.poll();
				if (temp[now.r][now.c] == 7) continue; // 이미 해당 위치가 꽃이라면 더 이상 인접한 땅으로 배양액을 퍼트리지 않음
				for (int d=0;d<4;d++) {
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					// 범위를 벗어나거나, 이동 위치가 호수 이거나, 이동 위치가 꽃이거나, 이미 방문했는데 같은 레벨이 아니라면 continue
					if (!isIn(nr, nc) || map[nr][nc] == 0 || temp[nr][nc] == 7 || (visit[nr][nc] != 0 && visit[nr][nc] < cnt)) continue;
					 
					if (visit[nr][nc] == cnt) { // 방문했는데 같은 레벨이라면
						if (now.color != temp[nr][nc]) { // 색이 달라!! 그럼 .. 꽃이다~!
							flower++;
							temp[nr][nc] = 7; // 꽃이 폈어요^^
						}
						continue; // 꽃이 피거나 이미 배양액이 있으니 퍼트리지 말자~
					}
					visit[nr][nc] = cnt; // 방문처리
					temp[nr][nc] = now.color; // 배양액이 퍼트려졌어요~~
					queue.offer(new Flower(nr, nc, now.color));
				}
			}
			cnt++;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
