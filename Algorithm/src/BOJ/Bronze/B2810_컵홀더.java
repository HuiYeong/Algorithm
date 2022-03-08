package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2810_컵홀더 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String seat = br.readLine();
		int cnt = 1;
		boolean flag = false;
		for (int i=0;i<seat.length();i++) {
			char temp = seat.charAt(i);
			if (temp == 'S') {
				cnt++;
				flag = false;
			}
			if (flag) {
				cnt++;
				flag = false;
			}
			else if (temp == 'L') {
				flag = true;
			}
		}
		cnt = cnt>N?N:cnt;
		System.out.println(cnt);
	}
}
