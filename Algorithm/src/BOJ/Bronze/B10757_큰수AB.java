package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/*
 * BigInteger은 문자열 형태로 이루어져 있어 숫자의 범위가 무한하기에 어떠한 숫자이든지 담을 수 있다.
 * 때문에 10의 10000승도 담을 수 있다.
 */
public class B10757_큰수AB {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BigInteger num1 = new BigInteger(st.nextToken());
		BigInteger num2 = new BigInteger(st.nextToken());
		System.out.println(num1.add(num2));
	}
}
