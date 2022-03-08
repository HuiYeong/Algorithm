package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1920_수찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] n = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i=0;i<N;i++) {
			n[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(n);
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine()," ");
		for (int i=0;i<M;i++) {
			int search = Integer.parseInt(st.nextToken());
			int low = 0;
			int high = n.length-1;
			boolean success = false;
			while(low<=high) {
				int mid = (low+high)/2;
				if (n[mid] > search) high = mid-1;
				else if (n[mid] < search) low = mid+1;
				else {
					success = true;
					sb.append(1+"\n");
					break;
				}
			}
			if (!success) sb.append(0+"\n");
		}
		System.out.println(sb);
	}
}
