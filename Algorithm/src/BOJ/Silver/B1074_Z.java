package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1074_Z {
	private static int r, c, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		squard(0, 0, (int) Math.pow(2,  N));
	}
	
	private static void squard(int row, int col, int len) {
		if (len==1) {
			if (r==row && c==col) {
				System.out.println(ans);
			}
			ans++;
			return;
		}
		if (!(row<=r && row<row+len && col<=c && c<col+len)) {
			ans+=len*len;
			return;
		}
		int nextlen = len/2; 
		squard(row, col, nextlen);
		squard(row, col+nextlen, nextlen);
		squard(row+nextlen, col, nextlen);
		squard(row+nextlen, col+nextlen, nextlen);
	}
}
