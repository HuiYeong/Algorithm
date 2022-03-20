package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 수학 문제
 * 1에서 어느 방향으로도 이동 가능하므로 1을 감싸고 있는 벌집들의 사이즈 계산
 * 감싸고 있는 개수가 6, 12, 18, 24, -- 6씩 증가하므로 6의 배수를 더해준다
 * 만약 더해진 값보다 N값이 작으면 그 범위 안에 포함 -> 더해준 사이즈 출력
 */
public class B2292_벌집 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int size = 1, cnt = 1;
		while(true) {
			if (N<=size) {
				System.out.println(cnt);
				System.exit(0);
			}
			size+=6*cnt++;
		}
	}
}
