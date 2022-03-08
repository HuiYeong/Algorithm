package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3980_선발명단 {
	static int MAX, arr[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		arr = new int[11][11];
		
		for (int tc=0;tc<TC;tc++) {
			for (int i=0;i<11;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<11;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
 				}
			}
			// 입력 완료
			MAX = Integer.MIN_VALUE;
			dfs(0, 0, new boolean[11]);
			sb.append(MAX+"\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int cnt, int sum, boolean[] select) {
		if (cnt == 11) {
			MAX = Math.max(MAX, sum); // 최댓값 구하기
			return;
		}
		
		for (int i=0;i<11;i++) {
			if (select[i] || arr[i][cnt] == 0) continue; // 이미 선출된 선수이거나 포지션에 적합하지 않을 땐 넘기기
			
			sum += arr[i][cnt]; // 능력치 더하기
			select[i] = true; // 선출 됐다!!!!
			dfs(cnt+1, sum, select); // 다음 포지션 선수 구하자~
			select[i] = false; // 다음 포지션 선수가 선출 못됐어?! 그럼 이번 포지션 선수를 바꿔보게 다시 빼!!!
			sum -= arr[i][cnt]; // 위랑 동일
		}
	}
}
