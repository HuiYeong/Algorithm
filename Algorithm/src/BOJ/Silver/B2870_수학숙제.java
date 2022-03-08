package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class B2870_수학숙제 {
	static String[] work;
	static ArrayList<BigInteger> numbers;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		numbers = new ArrayList<>();
		for (int i=0;i<N;i++) {
			work = br.readLine().split("\\D"); // '\\D' -> 숫자를 제외한 모든 문자를 캐치하는 코드
			for (int j=0;j<work.length;j++) {
				if (!work[j].equals("")) numbers.add(new BigInteger(work[j]));
			}
		}
		numbers.sort(null); // null이면 디폴트로 비내림차순 (오름차순)으로 정렬
		for (BigInteger x : numbers) sb.append(x+"\n");
		System.out.println(sb);
	}
}