package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class B16992_로마숫자만들기 {
	static int[] src = {1, 5, 10, 50};
	static int[] numbers;
	static int N;
	static TreeSet<Integer> sumCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		sumCnt = new TreeSet<>();
		permutation(0);
		System.out.println(sumCnt.size());
	}
	
	// 중복 순열
	static void permutation(int cnt) {
		if (cnt==N) {
			int sum = 0;
			for (int i=0;i<numbers.length;i++) {
				sum+=numbers[i];
			}
			sumCnt.add(sum);
			return;
		}
		
		for (int i=0;i<4;i++) {
			numbers[cnt] = src[i];
			permutation(cnt+1);
		}
	}
}
