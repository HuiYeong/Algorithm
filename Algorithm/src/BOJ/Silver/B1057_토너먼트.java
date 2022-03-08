package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1057_토너먼트 {
	static int jimin, hansu, cnt;
	static Queue<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		jimin = Integer.parseInt(st.nextToken());
		hansu = Integer.parseInt(st.nextToken());
		list = new LinkedList<>();
		for (int i=1;i<=N;i++) {
			if (i==jimin || i==hansu) list.offer(1);
			else list.offer(0);
		}
		System.out.println(compete());
	}
	
	static int compete() {
		while(list.size()>1) {
			cnt++;
			int size = list.size();
			for (int i=0;i<size/2;i++) {
				int a = list.poll();
				int b = list.poll();
				if ((a==1 && b==1)) return cnt;
				
				if (a==1 || b==1) list.offer(1);
				else list.offer(0);
				
			}
			if (size%2==1) list.offer(list.poll());
			
		}
		return -1;
	}
}
