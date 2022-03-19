package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 수학 문제
 * 정상에 한 번 도달하면 미끄러지지 않으므로
 * 막대 정상 - 낮에 올라가는 거리까지 계산 
 * 즉 낮에 올라간 거리 - 밤에 미끄러진 거리가 막대 정상 높이 - 낮에 올라가는 거리까지 며칠 걸리는 지 계산한다.
 * 정상 높이 - 낮에 올라가는 거리까지 왔으므로 이제 낮에 올라가기만 한다면 정상에 도달하므로 +1일
 */
public class B2869_달팽이는올라가고싶다 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		int temp = (int) Math.ceil((double) (V-A)/(double) (A-B));
		System.out.println(temp+1);
	}
}
