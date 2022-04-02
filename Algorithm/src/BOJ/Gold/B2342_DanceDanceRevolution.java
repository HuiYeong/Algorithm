package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * < DP 문제 >
 * 
 * 이전의 행동이 지금의 행동에 영향을 주기 때문에 DP로 다 돌려본다.
 * 분할 정복 형태로 풀어주었다.
 */
public class B2342_DanceDanceRevolution {
	static int order, dp[][][];
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			order = Integer.parseInt(st.nextToken());
			if (order == 0) break;
			list.add(order);
		}
		dp = new int[list.size()+1][5][5]; // i번째 스텝일 때 최댓값, 왼발 스텝, 오른발 스텝
		
		System.out.println(dfs(0, 0, 0));
	}
	
	static int dfs(int cnt, int left, int right) {
		if (cnt == list.size()) return 0;
		
		// 값이 있다면 이미 최솟값을 구함
		if (dp[cnt][left][right] != 0) return dp[cnt][left][right];
		
		int nowValue = list.get(cnt); // 현재 스텝
		
		// 왼발로 움직이는 경우와 오른발로 움직이는 경우의 최솟값을 구해준다.
		dp[cnt][left][right] = Math.min(dfs(cnt+1, nowValue, right) + value(left, nowValue), 
				dfs(cnt+1, left, nowValue) + value(right, nowValue)); 
		
		return dp[cnt][left][right];
	}
	
	static int value(int pos, int d) {
		int num = Math.abs(pos-d);
		if(pos == 0) return 2; // 위치가 0이라면 어디든 이동해도 2점
		else if(num == 0) return 1; // 같은 위치라면 1점
		else if(num == 1|| num == 3) return 3; // 근접 위치라면 3점
		else return 4; // 반대 방향은 4점
	}
}
