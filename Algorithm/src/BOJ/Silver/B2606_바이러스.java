package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2606_바이러스 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][N+1];
		for (int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int M1 = Integer.parseInt(st.nextToken());
			int M2 = Integer.parseInt(st.nextToken());
			arr[M1][M2] = 1;
			arr[M2][M1] = 1;
		}
		
		// bfs
		Queue<Integer> bfs = new LinkedList<>();
		boolean[] isSelect = new boolean[N+1];
		int virus = 1;
		int Answer = 0;
		outer:while(true) {
			if (!bfs.isEmpty() && isSelect[virus]) {
				while(true) {
					if (bfs.isEmpty()) {
						break outer;
					}
					virus = bfs.poll();
					if (!isSelect[virus]) break;
				}
			}
			
			isSelect[virus] = true;
			for (int i=1;i<N+1;i++) {
				if (arr[virus][i]==1) bfs.offer(i);
			}
			
			if(!bfs.isEmpty()) virus = bfs.poll();
			
		}
		for (int i=1;i<N+1;i++) {
			if (isSelect[i]) Answer++;
		}
		System.out.println(Answer-1);
	}
}
