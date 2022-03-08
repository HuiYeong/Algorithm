package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15961_회전초밥 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 접시 수
		int D = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int C = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] sushi = new int[N];
		for (int i=0;i<N;i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		// 입력완료
		int[] sushiYum = new int[D+1];
		int start = 0, end = K-1, size = 0, MAX = Integer.MIN_VALUE;
		
		for (int i=start;i<=end;i++) { 
			if (sushiYum[sushi[i]]==0) size++;
			sushiYum[sushi[i]]++; 
		}
		
		while(true) {
			if (sushiYum[C]==0) size++;
			MAX = Math.max(MAX, size);
			if (sushiYum[C]==0) size--;
			
			sushiYum[sushi[start]]--;
			if (sushiYum[sushi[start]]==0) size--;
			start++; end++;
			if (end==N) end = 0;
			if (start==N) start = 0;
			
			if (sushiYum[sushi[end]]==0) size++;
			sushiYum[sushi[end]]++;
			
			if (start==0 || MAX == K+1) break;
		}
		
		System.out.println(MAX);
	}
}	
