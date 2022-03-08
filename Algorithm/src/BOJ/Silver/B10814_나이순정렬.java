package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10814_나이순정렬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		String[][] info = new String[N][3];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			info[i][0] = st.nextToken();
			info[i][1] = st.nextToken();
			info[i][2] = (i+1)+"";
		}
		
		Arrays.sort(info, (o1, o2)-> {
			if (Integer.parseInt(o1[0]) == Integer.parseInt(o2[0])) return Integer.parseInt(o1[2])-Integer.parseInt(o2[2]);
			else return Integer.parseInt(o1[0])-Integer.parseInt(o2[0]);
		});
		
		for (int i=0;i<N;i++) {
			sb.append(info[i][0]+" "+info[i][1]+"\n");
		}
		System.out.println(sb);
	}
}
