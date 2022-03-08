package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B9205_맥주마시면서걸어가기 {
	static boolean[] visited;
	static List<int[]> list;
	static Queue<int[]> queue;
	static boolean flag;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N+1];
			queue = new LinkedList<>();
			list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int houseX = Integer.parseInt(st.nextToken());
			int houseY = Integer.parseInt(st.nextToken());
			queue.offer(new int[] {houseX, houseY});
			for (int i=0;i<N+1;i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new int[] {r, c});
			}
			flag = false;
			bfs();
			if (flag) sb.append("happy\n");
			if (!flag) sb.append("sad\n");
		}
		System.out.println(sb);
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[0]==list.get(list.size()-1)[0] && now[1] == list.get(list.size()-1)[1]) {
				flag = true;
				break;
			}
			for (int i=0;i<N+1;i++) {
				if (!visited[i]) {
					double dis = distance(now[0], now[1], list.get(i)[0], list.get(i)[1]);
					if (dis<=1000) {
						queue.offer(new int[] {list.get(i)[0], list.get(i)[1]});
						visited[i] = true;
					}
				}
			}
		}
	}
	
	static double distance(int startX, int endX, int startY, int endY) {
		return Math.abs(startY-startX)+Math.abs(endY-endX);
	}
}
