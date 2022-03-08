package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B2110_공유기설치 {
	static long[] house;
	static List<Long> router;
	static int N, C, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new long[N];
		router = new ArrayList<>();
		
		for (int i=0;i<N;i++) {
			house[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(house);
		
		check(0, N-1, 0);
		
		Long MIN = Long.MAX_VALUE;
		Collections.sort(router);
		System.out.println(router);
		for (int i=0;i<router.size()-1;i++) {
			long num1 = router.get(i);
			long num2 = router.get(i+1);
			MIN = Math.min(MIN, Math.abs(num1-num2));
		}
		System.out.println(MIN);
	}
	
	static void check(long start, long end, int cnt) {
		if (C%2==0 && cnt==C/2) return;
		else if (C%2==1 && cnt == (C+1)/2) return;
		long middle = (start+end)/2;
		router.add(house[(int) middle]);
		check(start, middle-1, cnt+1);
		check(middle+1, end, cnt+1);
	}
}
