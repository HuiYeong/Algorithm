package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16953_A_B {
	static long A, B;
	static int cnt;
	static boolean flag;
	static Queue<Long> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		queue = new LinkedList<>();
		queue.offer(A);
//		boolean flag = bfs();
		bfs(A, B, 1);
		if (flag) System.out.println(cnt);
		else System.out.println(-1);
	}
	
	static void bfs(long a, long b, int cnt) {
		if (a>b) return;
		else if (a==b) {
			System.out.println(cnt);
			System.exit(0);
		}
		
		bfs(a*2, b, cnt+1);
		bfs(a*10+1, b, cnt+1);
	}
	
//	static boolean bfs() {
//		while(!queue.isEmpty()) {
//			int size = queue.size();
//			for (int i=0;i<size;i++) {
//				long now = queue.poll();
//				if(now==B) return true;
//				long first = now*2;
//				String second = now+"1";
//				if (first<=B) queue.offer(first);
//				if (Long.parseLong(second)<=B) queue.offer(Long.parseLong(second));
//			}
//			cnt++;
//		}
//		
//		return false;
//	}
}
