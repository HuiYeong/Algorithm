package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2564_경비원 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		int[][] store = new int[N+1][2]; // 마지막은 동근이 위치
		for (int i=0;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			store[i][0] = Integer.parseInt(st.nextToken());
			store[i][1] = Integer.parseInt(st.nextToken());
		}
		int ans = 0;
		for (int i=0;i<N;i++) {
			int dir1 = 0, dir2 = 0;
			if (store[i][0]==store[N][0]) ans += Math.abs(store[i][1]-store[N][1]); // 같은 줄에 있을 때
			else if ((store[i][0]<3 && store[N][0]<3) || (store[i][0]>2 && store[N][0]>2)) { // 맞은편일 때
				dir1 = store[i][0]<3?H:W;
				dir2 = dir1==H?W:H;
				ans+=Math.min(dir1+store[i][1]+store[N][1], dir1+(dir2-store[i][1])+(dir2-store[N][1]));
			}
			else if ((store[i][0]==1 && store[N][0]==3) || (store[i][0]==3 && store[N][0]==1)) {
				ans+=store[i][1]+store[N][1];
			}
			else if ((store[i][0]==1 && store[N][0]==4) || (store[i][0]==4 && store[N][0]==1)) {
				if (store[N][0]==1) ans+= (W-store[N][1])+store[i][1];
				else ans+= (W-store[i][1])+store[N][1];
			}
			else if ((store[i][0]==2 && store[N][0]==3) || (store[i][0]==3 && store[N][0]==2)) {
				if (store[N][0]==2) ans+= store[N][1]+(H-store[i][1]);
				else ans+= store[i][1]+(H-store[N][1]);
			}
			else if ((store[i][0]==2 && store[N][0]==4) || (store[i][0]==4 && store[N][0]==2)) {
				if (store[N][0]==2) ans+=(W-store[N][1])+(H-store[i][1]);
				else ans+=(W-store[i][1])+(H-store[i][1]);
			}
		}
		System.out.println(ans);
	}
}
