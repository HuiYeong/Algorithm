package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * < 이분 탐색 문제 >
 * 
 * 직전에 설치를 했던 집과 현재 집과의 거리를 기준으로 판단해야 한다.
 * 거리를 기준으로 이분 탐색을 하여 해당 거리에서 설치할 수 있는 공유기의 개수를 판별해준다.
 * 
 * 거리가 줄어들수록 설치할 수 있는 공유기의 개수는 늘어나고, 거리가 늘어날수록 설치할 수 있는 공유기의 개수는 줄어든다.  
 */
public class B2110_공유기설치 {
	static int N, C, house[], start, end, middle;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new int[N];
		
		for (int i=0;i<N;i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(house);
		
		start = 1;
		end = house[N-1]-house[0]+1;
		
		while(start < end) {
			middle = (start+end)/2;
			// 현재의 최소 거리에서 설치된 공유기의 개수가 설치해야 하는 공유기의 개수보다 작으면 거리를 늘려준다.
			if (check() < C) end = middle;
			// 현재의 최소 거리에서 설치된 공유기의 개수가 설치해야 하는 공유기의 개수보다 크다면 거리를 좁혀준다.
			else start = middle+1;
		}
		System.out.println(start-1);
	}
	
	static int check() {
		int cnt = 1; // 맨 처음은 무조건 설치되므로 1부터
		int lastLoc = house[0]; // 처음 설치하는 위치
		
		for(int i = 1; i < house.length; i++) {
			int loc = house[i];
			
			// 마지막 설치 지점에서 현재 설치 지점까지의 거리 차가 최소 거리보다 크다면
			if(loc - lastLoc >= middle) { 
				cnt++; // 설치
				lastLoc = loc;
			}
		}
		
		return cnt;
	}
}
