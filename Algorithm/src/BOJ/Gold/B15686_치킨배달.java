package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15686_치킨배달 {
	private static int N, M, disTotal, disTemp;
	private static ArrayList<int[]> chicken;
	private static ArrayList<int[]> home;
	private static int[][] numbers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		disTotal = Integer.MAX_VALUE;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=1;j<=N;j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) home.add(new int[] {i, j}); // 1 -> 집
				else if (temp == 2) chicken.add(new int[] {i, j}); // 2 -> 치킨
			}
		}
		numbers = new int[M][2];
		combination(0, 0);
		
		System.out.println(disTotal);
	}
	
	private static void combination(int cnt, int start) {
		if (cnt==M) {
			disTemp = 0;
			dis();
			return;
		}
		
		for (int i=start;i<chicken.size();i++) {
			numbers[cnt][0] = chicken.get(i)[0];
			numbers[cnt][1] = chicken.get(i)[1];
			combination(cnt+1, i+1);
		}
	}
	
	private static void dis() {
		for (int i=0;i<home.size();i++) {
			int dis = Integer.MAX_VALUE;
			for (int j=0;j<numbers.length;j++) {
				int x = Math.abs(home.get(i)[0]-numbers[j][0]); // 치킨과 집과의 x좌표 거리 값
				int y = Math.abs(home.get(i)[1]-numbers[j][1]); // 치킨과 집과의 y좌표 거리 값
				dis = Math.min(dis, x+y); // 각 치킨집과 집의 거리 최소값 저장
			}
			disTemp+=dis; // 전체 거리 구하기
		}
		
		disTotal = Math.min(disTemp, disTotal); // 전체 거리 최소값
	}
}
