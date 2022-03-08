package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B13335_트럭 {
	private static int sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		Queue<Integer> truck = new LinkedList<>();
		Queue<Integer> bridge = new LinkedList<>();
		st = new StringTokenizer(br.readLine()," ");
		for (int i=0;i<N;i++) {
			truck.offer(Integer.parseInt(st.nextToken()));
		}
		
		int cnt = 0;
		while(bridge.size() != W) bridge.offer(0);
		while(true) {
			if (truck.isEmpty() && sum == 0) break;
			if (!bridge.isEmpty()) {
				sum-=bridge.peek();
				bridge.poll();
			}
			if (!truck.isEmpty() && bridge.size()<W && sum+truck.peek()<=L) {
				sum+=truck.peek();
				bridge.offer(truck.poll());
			}
			else bridge.offer(0);
			cnt++;
		}
		System.out.println(cnt);
	}
}
