package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B9328_열쇠 {
	static int R, C, key, now[], newM[], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}, ans, size;
	static boolean visit[][];
	static String temp;
	static char arr[][], nowKey;
	static Queue<int[]> queue = new LinkedList<>(), door = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int tc=0;tc<TC;tc++) {
			st = new StringTokenizer(br.readLine());
			// 빌딩 안팎을 드나들 수 있으므로 주변에 패딩 둘러주기
			R = Integer.parseInt(st.nextToken()) + 2;
			C = Integer.parseInt(st.nextToken()) + 2;
			arr = new char[R][C];
			visit = new boolean[R][C];
			key = 0; ans = 0;
			door.clear();
			for (int r=1;r<R-1;r++) {
				temp = br.readLine();
				for (int c=1;c<C-1;c++) {
					arr[r][c] = temp.charAt(c-1);
				}
			}
			temp = br.readLine();// key
			temp = temp.equals("0")?"":temp; // key가 하나도 없으면 넘어가~
			for (int i=0;i<temp.length();i++) {
				key |= (1 << temp.charAt(i)-'a'); // key 저장
			}
			queue.offer(new int[] {0, 0});
			visit[0][0] = true;
			sb.append(bfs()+"\n");
		}
		System.out.println(sb);
	}
	
	static void findKey() {
		size = door.size();
		while(size-->0) {
			int[] now = door.poll();
			if ((key & (1 << arr[now[0]][now[1]]-'A'))==0) door.add(now); // 지금 가지고 있는 열쇠로 문 못열어.. 그럼 다시 저장
			else queue.offer(new int[] {now[0], now[1]}); // 만약 지금 가진 열쇠로 문을 열 수 있다면 해당 위치로 이동 후 탐색
		}
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			size = queue.size();
			while(size-->0) {
				now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now[0] + dir[d][0];
					int nc = now[1] + dir[d][1];
					if (!isIn(nr, nc) || arr[nr][nc] == '*' || visit[nr][nc]) continue;
					
					/*
					 위치로 방문체크를 할 수 있는 이유는 열쇠를 찾으면 새로 열 수 있는 문을 열고 탐색을 하기 때문에
					 이미 방문한 위치는 새로운 열쇠를 찾지 않아도 방문할 수 있는 곳. 즉, 또 방문 안해도 되는 곳~
					 */ 
					visit[nr][nc] = true; 
					
					if (arr[nr][nc] == '$') {
						ans++;
						arr[nr][nc] = '.';
					}
					
					// 열쇠 찾으면
					if (arr[nr][nc] >= 'a' && arr[nr][nc] <= 'z') { 
						key |= (1 << arr[nr][nc]-'a'); // 열쇠 저장
						findKey(); // 열쇠로 들어갈 수 있는 문 찾으러 ~~
					}
					newM = new int[] {nr, nc};
					
					// 내가 이동한 곳에 문이 있는데 열쇠가 없으면 문 큐에 저장 (열쇠 찾으면 해당 위치로 이동할 수 있게끔 하려고)
					if (arr[nr][nc] >= 'A' && arr[nr][nc] <= 'Z' && ((key & (1 << arr[nr][nc]-'A'))==0)) {
						door.add(newM);
						continue;
					}
					
					queue.offer(newM);
				}
			}
		}
		return ans;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
