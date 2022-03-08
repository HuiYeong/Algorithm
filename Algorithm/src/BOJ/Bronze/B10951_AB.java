package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10951_AB {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = null;
		int sum = 0;
		while((num = br.readLine())!=null) {
			sum = 0;
			StringTokenizer st = new StringTokenizer(num, " ");
			while(st.hasMoreTokens()) {
				sum+=Integer.parseInt(st.nextToken());
			}
			System.out.println(sum);
		}
	}
}
