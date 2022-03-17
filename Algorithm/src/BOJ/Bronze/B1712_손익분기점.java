package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * '고정비용 + (가변비용 * N개) < 노트북비용 * N개' -> 식을 만들 수 있다.
 * 만약 음수가 나온다면 손익분기점이 존재하지 않는 것으로 간주할 수 있다.
 */
public class B1712_손익분기점 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int value = C-B;
		if (value<0) A *= -1;
		System.out.println(A<0||value==0?-1:(A/value+1));
	}
}
