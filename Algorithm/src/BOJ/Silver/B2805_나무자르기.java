package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2805_나무자르기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		int[] tree = new int[N];
		for (int i=0;i<N;i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(tree);
		int Answer = 0;
		int low = 0;
		int high = tree.length-1;
		int mid = (low+high)/2;
		while(low<=high) {
			mid = (low+high)/2;
			int i = 1;
			while(true) { 
				if (Answer >= M && i <= high) {
					low = mid+1;
					break;
				}
				else if (i == high && Answer < M) {
					high = mid-1;
					break;
				}
				Answer += tree[mid+(i++)] - tree[mid];
			}
		}
		System.out.println(tree[mid]);
	}
}
