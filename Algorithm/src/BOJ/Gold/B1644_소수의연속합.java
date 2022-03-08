package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B1644_소수의연속합 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] num = new boolean[4000001];
		List<Integer> decimal = new ArrayList<>();
		
		num[1] = true;
		for (long i=2;i*2<num.length;i++) {
			for (long j=i*i;j<num.length;j+=i) {
				num[(int)j] = true;
			}
		}
		
		for (int i=1;i<num.length;i++) {
			if (!num[i]) decimal.add(i);
		}
		
		int start = 0, end = 1, cnt = 0, sum = decimal.get(0);
		
		while(true) {
			if (decimal.size()<=start || (decimal.size()<=end && sum < N) || decimal.get(start) > N) {
				break;
			}
			if (sum < N && decimal.size() > end) {
				sum += decimal.get(end++);
			} else if (sum > N){
				sum -= decimal.get(start++);
			} else if (sum == N) {
				cnt++;
				sum -= decimal.get(start++);
			}
			System.out.println("start : "+start+", end : "+end+", sum : "+sum);
		}
		
		System.out.println(cnt);
	}
}
