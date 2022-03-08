package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B14719_빗물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int MAX = Integer.MIN_VALUE;
		for (int w=0;w<W;w++) {
			list.add(Integer.parseInt(st.nextToken()));
			if (MAX < list.get(w)) MAX = list.get(w);
		}
		int cnt, totalCnt = 0;
		boolean isWall;
		
		for (int h=0;h<H;h++) {
			cnt = 0; isWall = false; // 초기화
			if (h>MAX) break; // 현재 높이가 가장 높은 벽보다 높아지면 멈춰주기
			for (int w=0;w<W;w++) {
				int now = list.get(w); 
				if (now>h && !isWall) isWall = true; // 현재 높이보다 값이 크면 -> 벽이므로 해당 높이에 물 고일 수 있음
				else if (now<=h && isWall) cnt++; // 현재 높이보다 값이 작고 앞에 이미 벽이 있었다면 cnt++
				else if (now>h && isWall) { // 앞에 벽이 있었고 또 다른 벽이 나타났다면
					totalCnt+=cnt; // 총 빗물 개수 구해주기
					cnt = 0; // 초기화
				}
			}
		}
		
		System.out.println(totalCnt);
	}
}

